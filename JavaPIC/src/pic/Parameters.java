package pic;

public class Parameters {
	//"global variables" for the whole simulation
	//
	public static double gridSize = 1e-3;
	public static double timeStep = 1e-6;
	public static int gridPointNumber = 32;
	public static double gridStep = gridSize/gridPointNumber;
	
	public static double boltzmannConstantJoulesOverKelvin=1.3807e-23;
	public static double electronChargeC = 1.6022e-19;
	public static double joulesToKelvin(double energy)
	{
		//takes an energy in joules - average kinetic
		//returns temperature in kelvin
		
		return energy/boltzmannConstantJoulesOverKelvin;
	}
}