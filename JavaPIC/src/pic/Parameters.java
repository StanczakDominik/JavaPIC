package pic;

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
	public static double timeStep = 1e-6;
	
	//species parameters
	public static int numberOfParticles=64;
	
	//grid parameters
	public static double gridSize = 1e-3;
	public static int gridPointNumber = 32;
	public static double gridStep = gridSize/gridPointNumber;
	public static double fieldErrorTolerance=1e-6;
}