package pic;

/**
 * Author: Dominik
 * Klasa Grid odpowiada za jednowymiarowÄ… siatkÄ™, na ktĂłrej rozwiÄ…zywane sÄ… jednowymiarowe rĂłwnania Maxwella przy uĹĽyciu
 * danych czastek (poĹ‚oĹĽeĹ„ -> gÄ™stoĹ›ci Ĺ‚adunkĂłw)
 * <p>
 * Metoda update wykonywaĹ‚a ongiĹ› miÄ™cho obliczeniowe. Teraz wykonuje jeno poszczegĂłlne metody.
 *
 * Metoda calculateDensity - bierze poĹ‚oĹĽenia czÄ…stek i liczy z nich gÄ™stoĹ›Ä‡ Ĺ‚adunkĂłw.
 * GÄ™stoĹ›Ä‡ Ĺ‚adunkĂłw jest obliczana na podstawie liniowej interpolacji (model CIC - Cloud In Cell. CzÄ…stki majÄ… w tym
 * modelu w przybliĹĽeniu trĂłjkÄ…tny ksztaĹ‚t).
 *
 * Metoda calculatePotential - na podstawie bieĹĽÄ…cej gÄ™stoĹ›ci Ĺ‚adunkĂłw liczy potencjaĹ‚
 * PotencjaĹ‚ obliczany jest przy pomocy metody Gaussa-Seidela.
 *
 * Metoda calculateFieldAndEnergy - na podstawie bieĹĽÄ…cego potencjaĹ‚u liczy pole elektryczne oraz jego energiÄ™.
 * Liczy jednoczeĹ›nie pole i energiÄ™, dlatego jest zrobiona voidem, nie double[]m.
 * Pole w komĂłrce i obliczane jest jako minus gradient potencjaĹ‚u, wykorzystujÄ…c potencjaĹ‚ w komĂłrkach i+1, i-1
 * (dziÄ™ki temu bĹ‚Ä…d obliczenia pola ~gridstep^2 zamiast ^1)
 * <p>
 * Metoda getIndexOnGrid zwraca pozycjÄ™ (indeks) danej czÄ…stki na siatce)
 */
class Grid {
	public double gridSize;
	public int gridPointNumber;
	public double gridStep;
	double[] gridPoints;
	double[] eField;
	double[] density;
	double totalFieldEnergy;
	private double[] potential;
	private Parameters parameters;

	public Grid(Parameters parameters) {
		totalFieldEnergy = 0;
		this.parameters = parameters;
		gridSize = Parameters.gridSize;
		gridPointNumber = parameters.gridPointNumber;
		gridStep = parameters.gridStep;
		gridPoints = Parameters.uniformPositions(gridPointNumber, gridSize);
		eField = new double[gridPointNumber];
		density = new double[gridPointNumber];
		potential = new double[gridPointNumber];


	}

	public int getIndexOnGrid(double position) {
		return (int) (Math.floor(position / gridStep));
	}

	double[] calculateDensity(Species[] listofspecies) {
		double[] density = new double[gridPointNumber];
		int index;
		double[] speciesDensity;
		//calculate density
		for (Species species : listofspecies) {
			speciesDensity = new double[gridPointNumber];
			for (int i = 0; i < species.numberOfParticles; i++) {
				index = getIndexOnGrid(species.position[i]);
				if (index < gridPointNumber - 1) {
					speciesDensity[index] += 1 - (species.position[i] - gridPoints[index]) / gridStep;
					speciesDensity[index + 1] += (species.position[i] - gridPoints[index]) / gridStep;
				} else {
					speciesDensity[index] += 1 - (species.position[i] - gridPoints[index]) / gridStep;
					speciesDensity[0] += (species.position[i] - gridPoints[index]) / gridStep;
				}
			}
			for (int i = 0; i < gridPointNumber; i++) {
				density[i] += speciesDensity[i] * species.charge;
			}
		}
		for (int i = 0; i < gridPointNumber; i++) {
			density[i] += parameters.cellParticleDensity * parameters.backgroundCharge;
		}
		density[gridPointNumber - 1] = (density[gridPointNumber - 1] + density[0]) / 2;
		density[0] = density[gridPointNumber - 1];

		return density;
	}

	double[] calculatePotential() {
		double[] potential = new double[gridPointNumber];
		int forwardIndex, backwardIndex;
		double oldPotential, delta, maxChange;
		for (int iter = 0; iter < Parameters.fieldCalculationIterations; iter++) {
			maxChange = 0;
			for (int i = 0; i < gridPointNumber; i++) {
				forwardIndex = i + 1;
				if (forwardIndex == gridPointNumber) forwardIndex = 0;
				backwardIndex = i - 1;
				if (backwardIndex == -1) backwardIndex = gridPointNumber - 1;
				oldPotential = potential[i];
				potential[i] = (density[i] * gridStep * gridStep / Parameters.epsilonZero + potential[forwardIndex] + potential[backwardIndex]) / 2;
				delta = potential[i] - oldPotential;
				if (Math.abs(delta) > maxChange) {
					maxChange = Math.abs(delta);
				}
			}

			if (((iter > 0) && (iter % Parameters.fieldCalculationStep == 0) && (maxChange < parameters.fieldErrorTolerance)) || (iter == Parameters.fieldCalculationIterations)) {
				if (Parameters.printGridConvergence)
					System.out.println("iteration " + iter + " " + maxChange + " converged");
				break;
			}
			if (iter == Parameters.fieldCalculationIterations) {
				if (Parameters.printGridConvergence) System.out.println("May not have converged!");
			}
		}

		return potential;
	}

	void calculateFieldAndEnergy() {
		int forwardIndex, backwardIndex;
		for (int i = 0; i < gridPointNumber; i++) {
			forwardIndex = i + 1;
			if (forwardIndex == gridPointNumber) forwardIndex = 1;
			backwardIndex = i - 1;
			if (backwardIndex == -1) backwardIndex = gridPointNumber - 2;

			//calculate field by a central finite difference scheme
			eField[i] = (potential[backwardIndex] - potential[forwardIndex]) / (2d * gridStep);

			totalFieldEnergy += 0.5 * Parameters.epsilonZero * eField[i] * eField[i];//*gridStep;
			//debug
			if (Parameters.printFields) System.out.println(i + "\t" + potential[i] + "\t" + eField[i]);
		}
	}

	public void update(Species[] listofspecies)
	{
		totalFieldEnergy = 0;
		//calculate density
		density = calculateDensity(listofspecies);
		potential = calculatePotential();
		calculateFieldAndEnergy();
	}

}
