package pic;

import java.awt.Color;

public class MainSimulation {

	public MainSimulation() {
	}
	
	public static void main(String[] args) {
		System.out.println("Starting");
		Grid grid = new Grid();
		Species[] listOfSpecies = new Species[2];
		
		double[] velocities1=Parameters.uniformVelocity(Parameters.numberOfParticles, Parameters.initialVelocity);
		double[] velocities2=Parameters.uniformVelocity(Parameters.numberOfParticles, -Parameters.initialVelocity);		
		double[] positions1=Parameters.cosinePosition(Parameters.numberOfParticles, Parameters.perturbationAmplitude);	
		double[] positions2=Parameters.cosinePosition(Parameters.numberOfParticles, -Parameters.perturbationAmplitude);
		
		Species beam1 = new Species("Beam 1", Color.RED, Parameters.numberOfParticles/2, Parameters.protonRestMass, Parameters.charge, positions1, velocities1);
		Species beam2 = new Species("Beam 2", Color.GREEN, Parameters.numberOfParticles/2, Parameters.electronRestMass, Parameters.charge, positions2, velocities2);
		listOfSpecies[0]=beam1;
		listOfSpecies[1]=beam2;
		
		//Parameters.checkParameters();
		System.out.println("gridstep " + Parameters.gridStep);
		System.out.println("charge " + Parameters.charge);
		System.out.println("bg " + Parameters.backgroundCharge);
		System.out.println("number" + 2*Parameters.numberOfParticles);
		grid.update(listOfSpecies);
		for(int t=0; t<Parameters.iterations; t++)
		{	System.out.println("Iteration " + t);
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
