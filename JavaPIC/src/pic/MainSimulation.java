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
		protonVelocities=new double[Parameters.numberOfParticles];
		electronVelocities=new double[Parameters.numberOfParticles];
		
		Species protons = new Species("Protons", Color.RED, Parameters.numberOfParticles, Parameters.protonRestMass, 1000000*Parameters.electronCharge, protonPositions, protonVelocities);
		Species electrons = new Species("Electrons", Color.GREEN, Parameters.numberOfParticles, Parameters.electronRestMass, -1000000*Parameters.electronCharge, electronPositions, electronVelocities);
		listOfSpecies[0]=protons;
		listOfSpecies[1]=electrons;
		grid.update(listOfSpecies);
		for(int t=0; t<4; t++)
		{			
			for(int i=0; i<2; i++)
			{
				listOfSpecies[i].step(grid);
				grid.update(listOfSpecies);
			}
		}
		System.out.println("Finished");
	}

}
//Species(String nameI, Color colorI, int numberOfParticlesI, double massI, double chargeI, double[] positionI, double[] velocityI)
