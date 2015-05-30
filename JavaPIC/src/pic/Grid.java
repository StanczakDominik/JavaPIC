package pic;

class Grid {
	public double gridSize;
	public int gridPointNumber;
	public double gridStep;
	Parameters parameters;
	double[] gridPoints;
	double[] eField;
	double[] density;
	double[] potential;
	double totalFieldEnergy;

	public Grid(Parameters parameters) {
		this.parameters = parameters;
		gridSize = Parameters.gridSize;
		gridPointNumber = parameters.gridPointNumber;
		gridStep = parameters.gridStep;
		gridPoints = Parameters.uniformPositions(gridPointNumber, gridSize);
		eField = new double[gridPointNumber];
		density = new double[gridPointNumber];
		potential = new double[gridPointNumber];


	}

	public Grid(Grid source)
	{
		gridPoints = source.gridPoints;
		eField = source.eField;
		density = source.density;
		potential = source.potential;
		parameters = source.parameters;
	}

	public int getIndexOnGrid(double position) {
		return (int) (Math.floor(position / gridStep));
	}

	public void update(Species[] listofspecies)
	{
		int index;
		density = new double[gridPointNumber];
		totalFieldEnergy = 0;
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
		density[gridPointNumber - 1] += density[0];
		density[0] = density[gridPointNumber - 1];

		//Gauss Seidel Method
		potential = new double[gridPointNumber];
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
		for (int i = 0; i < gridPointNumber; i++) {
			forwardIndex = i + 1;
			if (forwardIndex == gridPointNumber) forwardIndex = 1;
			backwardIndex = i - 1;
			if (backwardIndex == -1) backwardIndex = gridPointNumber - 2;
			eField[i] = (potential[backwardIndex] - potential[forwardIndex]) / (2d * gridStep);
			totalFieldEnergy += 0.5 * potential[i] * density[i] * gridStep;
			if (Parameters.printFields) System.out.println(i + "\t" + potential[i] + "\t" + eField[i]);
		}
	}

}
