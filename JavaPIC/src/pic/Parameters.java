package pic;

import java.io.PrintWriter;

public class Parameters {
	//"global variables" for the whole simulation
	
	//physical constants
	public static double boltzmannConstant=1.3807e-23;		// J/K
	public static double electronCharge = 1.6022e-19;		// Coulombs
	public static double epsilonZero= 8.854e-12;			// F/m
	public static double electronRestMass=9.109e-31; 		//kg
	public static double protonRestMass=1.6726e-27; 			//kg
	
	
	public static double joulesToKelvin(double energy)
	{
		//takes an energy in joules - average kinetic
		//returns temperature in kelvin
		
		return energy/boltzmannConstant;
	}
	
	//simulation parameters
	public static double timeStep = 1e-1d;
	public static int iterations = 1;
	
	//species parameters
	public static int numberOfParticles=128;
	
	//grid parameters
	public static double gridSize = 1e3;//1e-3;
	public static int gridPointNumber = 32;
	public static double gridStep = gridSize/gridPointNumber;
	public static double fieldErrorTolerance=1e-14;
	
	static double[] uniformPositions(int numberOfParticles, double min, double max, double shift)
	{
		double[] locations = new double[numberOfParticles];
		double dx = (max-min)/numberOfParticles;
		for (int i=0; i<numberOfParticles; i++)
		{
			locations[i]=(i+shift)*dx;
			//System.out.println((i+0.5)*dx);	//debug
		}
		return locations;
	}
	static double[] uniformVelocity(int numberOfParticles, double value)
	{
		double[] velocities = new double[numberOfParticles];
		for (int i=0; i<numberOfParticles; i++)
		{
			velocities[i] = value;
		}
		return velocities;
	}
	
	
	
	public static void checkParameters()
	{
		double wp = Math.sqrt(numberOfParticles/gridSize*electronCharge*electronCharge/
				epsilonZero/electronRestMass);
		System.out.println("Plasma frequency: " + wp + " rad/s");
	}
}