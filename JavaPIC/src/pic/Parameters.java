package pic;

public class Parameters {
	//"global variables" for the whole simulation
	//
	public static double gridSize = 1e-3;
	public static double timeStep = 1e-6;
	public static int gridPointNumber = 32;
	public static double gridStep = gridSize/gridPointNumber;
	
	public static double fieldErrorTolerance=1e-6;
	
	public static double boltzmannConstant=1.3807e-23;		// J/K
	public static double electronCharge = 1.6022e-19;		// Coulombs
	public static double epsilonZero= 8.854e-12;			// F/m
	public static double joulesToKelvin(double energy)
	{
		//takes an energy in joules - average kinetic
		//returns temperature in kelvin
		
		return energy/boltzmannConstant;
	}
}