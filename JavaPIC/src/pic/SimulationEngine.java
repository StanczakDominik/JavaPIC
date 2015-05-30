package pic;

public class SimulationEngine {

	Grid grid = new Grid();
	Species[] listOfSpecies = new Species[2];
	private double[] velocities1 = Parameters.uniformVelocity(Parameters.numberOfParticles, Parameters.initialVelocity);
	private double[] velocities2 = Parameters.uniformVelocity(Parameters.numberOfParticles, -Parameters.initialVelocity);
	private double[] positions1 = Parameters.randomPosition(Parameters.numberOfParticles, Parameters.perturbationAmplitude);
	private double[] positions2 = Parameters.randomPosition(Parameters.numberOfParticles, -Parameters.perturbationAmplitude);

	public SimulationEngine()
	{
		Species beam1 = new Species(Parameters.numberOfParticles, Parameters.charge, positions1, velocities1);
		listOfSpecies[0] = beam1;
		Species beam2 = new Species(Parameters.numberOfParticles, Parameters.charge, positions2, velocities2);
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
