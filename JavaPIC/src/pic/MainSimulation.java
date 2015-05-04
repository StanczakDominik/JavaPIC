package pic;

import java.awt.Color;

public class MainSimulation {

	Grid grid;
	
	double[] uniformPositions(int numberOfParticles, double min, double max)
	{
		double[] locations = new double[numberOfParticles];
		return locations;
	}
	
	public MainSimulation() {
		grid = new Grid();
	}

	public static void main(String[] args) {
		double[] protonPositions, protonVelocities, electronPositions, electronVelocities;
		protonPositions=new double[Parameters.numberOfParticles];
		electronPositions=new double[Parameters.numberOfParticles];
		protonVelocities=new double[Parameters.numberOfParticles];
		electronVelocities=new double[Parameters.numberOfParticles];
		
		
		
		Species[] listOfSpecies = new Species[2];
		Species protons = new Species("Protons", Color.RED, Parameters.numberOfParticles, Parameters.protonRestMass, Parameters.electronCharge, protonPositions, protonVelocities);
		Species electrons = new Species("Electrons", Color.GREEN, Parameters.numberOfParticles, Parameters.electronRestMass, -Parameters.electronCharge, electronPositions, electronVelocities);
		
	}

}
//Species(String nameI, Color colorI, int numberOfParticlesI, double massI, double chargeI, double[] positionI, double[] velocityI)
