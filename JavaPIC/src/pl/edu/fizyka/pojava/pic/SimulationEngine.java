package pl.edu.fizyka.pojava.pic;

/**
 * Autor: Dominik
 * Klasa - silnik obliczeniowy. Zawiera jednowymiarową siatkę Grid oraz dwa strumienie elektronów Species
 * Odpowiada za ich inicjalizację z ustalonymi parametrami oraz odwołując się do metod klas Species i Grid
 * wykonuje na nich obliczenia.
 * <p>
 * metody:
 * konstruktor
 * step - wykonuje po jednej iteracji tak obu Species, jak Gridu
 */

public class SimulationEngine {

	public Parameters parameters;
	public Grid grid;
	Species[] listOfSpecies;

	public SimulationEngine(Parameters parameters)
	{
		this.parameters = parameters;
		grid = new Grid(parameters);
		listOfSpecies = new Species[2];
		double[] velocities1 = Parameters.uniformVelocity(parameters.numberOfParticles, parameters.initialVelocity);
		double[] velocities2 = Parameters.uniformVelocity(parameters.numberOfParticles, -parameters.initialVelocity);
		double[] positions1 = Parameters.randomPosition(parameters.numberOfParticles, parameters.perturbationAmplitude);
		double[] positions2 = Parameters.randomPosition(parameters.numberOfParticles, -parameters.perturbationAmplitude);


		Species beam1 = new Species(parameters.numberOfParticles, parameters.charge1, parameters.mass1, positions1,
				velocities1, parameters);
		listOfSpecies[0] = beam1;
		Species beam2 = new Species(parameters.numberOfParticles, parameters.charge2, parameters.mass2, positions2,
				velocities2, parameters);
		listOfSpecies[1] = beam2;
		grid.update(listOfSpecies);
	}


	public void step()
	{
		for(int i=0; i<2; i++)
		{
			listOfSpecies[i].step(grid);
		}
		grid.update(listOfSpecies);
	}
}
