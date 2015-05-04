package pic;

import java.awt.Color;

public class MainSimulation {
	static double[] uniformPositions(int numberOfParticles, double min, double max)
	{
		double[] locations = new double[numberOfParticles];
		double dx = (max-min)/numberOfParticles;
		for (int i=0; i<numberOfParticles; i++)
		{
			locations[i]=(i+0.5)*dx;
			//System.out.println((i+0.5)*dx);	//debug
		}
		return locations;
	}
	
	public MainSimulation() {
	}

	public static void main(String[] args) {
		System.out.println("Starting");
		double[] protonPositions, protonVelocities, electronPositions, electronVelocities;

		Grid grid;
		Species[] listOfSpecies;
		listOfSpecies = new Species[2];
		grid = new Grid();
		protonPositions=uniformPositions(Parameters.numberOfParticles, 0, Parameters.gridSize);
		electronPositions=uniformPositions(Parameters.numberOfParticles, 0, Parameters.gridSize);
		protonVelocities=new double[Parameters.numberOfParticles];
		electronVelocities=new double[Parameters.numberOfParticles];
		listOfSpecies = new Species[2];
		Species protons = new Species("Protons", Color.RED, Parameters.numberOfParticles, Parameters.protonRestMass, Parameters.electronCharge, protonPositions, protonVelocities);
		Species electrons = new Species("Electrons", Color.GREEN, Parameters.numberOfParticles, Parameters.electronRestMass, -Parameters.electronCharge, electronPositions, electronVelocities);
		listOfSpecies[0]=protons;
		listOfSpecies[1]=electrons;
//		for (int t=0; t<10; t++)
//		{
//			for(Species i: listOfSpecies)
//				{
//					System.out.println(i.name);
//					i.step(grid);
//				}
//		}
		for(int i=0; i<2; i++)
		{
			listOfSpecies[i].step(grid);
			System.out.println(listOfSpecies[i].name);
		}
	}

}
//Species(String nameI, Color colorI, int numberOfParticlesI, double massI, double chargeI, double[] positionI, double[] velocityI)
