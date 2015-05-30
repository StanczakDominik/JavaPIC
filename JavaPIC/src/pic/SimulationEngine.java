package pic;

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
		double[] positions1 = Parameters.randomPosition(parameters.numberOfParticles, Parameters.perturbationAmplitude);
		double[] positions2 = Parameters.randomPosition(parameters.numberOfParticles, -Parameters.perturbationAmplitude);


		Species beam1 = new Species(parameters.numberOfParticles, parameters.charge, positions1, velocities1, parameters);
		listOfSpecies[0] = beam1;
		Species beam2 = new Species(parameters.numberOfParticles, parameters.charge, positions2, velocities2, parameters);
		listOfSpecies[1] = beam2;
		grid.update(listOfSpecies);
	}

	public SimulationEngine(SimulationEngine source)
	{
		listOfSpecies[0]=source.listOfSpecies[0];
		listOfSpecies[1]=source.listOfSpecies[1];
		grid=source.grid;
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
