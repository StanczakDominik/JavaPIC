package pic;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
public class SimulationEngine {

	Grid grid = new Grid();
	Species beam1, beam2;
	Species[] listOfSpecies = new Species[2];
	double[] velocities1;
	double[] velocities2;
	double[] positions1;
	double[] positions2;

	public SimulationEngine()
	{
		velocities1=Parameters.uniformVelocity(Parameters.numberOfParticles, Parameters.initialVelocity);
		velocities2=Parameters.uniformVelocity(Parameters.numberOfParticles, -Parameters.initialVelocity);
		positions1=Parameters.randomPosition(Parameters.numberOfParticles, Parameters.perturbationAmplitude);
		positions2=Parameters.randomPosition(Parameters.numberOfParticles, -Parameters.perturbationAmplitude);
		beam1 = new Species(Parameters.numberOfParticles, Parameters.charge, positions1, velocities1);
		beam2 = new Species(Parameters.numberOfParticles, Parameters.charge, positions2, velocities2);
		
		listOfSpecies[0]=beam1;
		listOfSpecies[1]=beam2;
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
