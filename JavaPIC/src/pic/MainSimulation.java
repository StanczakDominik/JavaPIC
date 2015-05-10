package pic;

import java.awt.Color;

public class MainSimulation {

	public MainSimulation() {
	}
	
	public static void main(String[] args) {
		System.out.println("Starting");
		double[] protonPositions, protonVelocities, electronPositions, electronVelocities;
		Grid grid = new Grid();
		Species[] listOfSpecies = new Species[2];
		protonPositions=Parameters.uniformPositions(Parameters.numberOfParticles, 0, Parameters.gridSize, 0.25);
		electronPositions=Parameters.uniformPositions(Parameters.numberOfParticles, 0, Parameters.gridSize, 0.75);
		protonVelocities=Parameters.uniformVelocity(Parameters.numberOfParticles, 1d);
		electronVelocities=Parameters.uniformVelocity(Parameters.numberOfParticles, -1d);		
		
		Species protons = new Species("Protons", Color.RED, Parameters.numberOfParticles, Parameters.protonRestMass, Parameters.electronCharge, protonPositions, protonVelocities);
		Species electrons = new Species("Electrons", Color.GREEN, Parameters.numberOfParticles, Parameters.electronRestMass, -Parameters.electronCharge, electronPositions, electronVelocities);
		listOfSpecies[0]=protons;
		listOfSpecies[1]=electrons;
		for (int i=0; i<Parameters.numberOfParticles; i++)
		{
			//System.out.println(protons.position[i]);
		}
		
		Parameters.checkParameters();

		grid.update(listOfSpecies);
		for(int t=0; t<Parameters.iterations; t++)
		{			
			for(int i=0; i<2; i++)
			{
				listOfSpecies[i].step(grid);
			}
			grid.update(listOfSpecies);
		}
		System.out.println("Finished");
	}

}
//Species(String nameI, Color colorI, int numberOfParticlesI, double massI, double chargeI, double[] positionI, double[] velocityI)
