package pic;

public class Parameters {
	//"global variables" for the whole simulation
	
	//physical constants
	public static double boltzmannConstant=1.3807e-23d;		// J/K
	public static double electronCharge = 1.6022e-19d;		// Coulombs
	public static double epsilonZero= 1d;			// F/m
	public static double electronRestMass=9.109e-31d; 		//kg
	public static double protonRestMass=1.6726e-27d; 			//kg
	
	
	public static double joulesToKelvin(double energy)
	{
		//takes an energy in joules - average kinetic
		//returns temperature in kelvin
		
		return energy/boltzmannConstant;
	}
	//debug booleans
	
	public static boolean printFields = false;
	public static boolean printIterations = false;
	
	
	//simulation parameters
	public static double timeStep = 1e-1d;
	public static int iterations = 100;
	public static int cellParticleDensity = 100;
	
	public static double plasmaFrequency=1d;
	
	
	//grid parameters
	public static double gridSize = 2d*Math.PI;//1e-3;
	public static int gridPointNumber = 64;
	public static double gridStep = gridSize/((double)(gridPointNumber));
	
	public static double fieldErrorTolerance=4.525e-5d;
	public static int fieldCalculationIterations=25000;
	public static int fieldCalculationStep=100;

	//species parameters
	public static double chargeToMassRatio = -1d;
	
	public static int numberOfParticles=gridPointNumber*cellParticleDensity;
	public static double initialVelocity = 1d;
	
	public static double perturbationAmplitude=1e-1d;

	public static double charge = plasmaFrequency*plasmaFrequency*(1d/chargeToMassRatio)*epsilonZero*gridSize/((double)numberOfParticles);
	//public static double charge = 1;
	public static double backgroundCharge = -charge;
		
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
	
	static double[] cosinePosition(int numberOfParticles, double amplitude)
	{
		double x0, theta;
		double[] positions = new double[numberOfParticles];
		for (int i=0; i<numberOfParticles; i++)
		{	x0 = ((double)(i+0.5))*gridStep;
			theta = 2d*Math.PI*x0/gridSize;
			positions[i] = (x0+amplitude*Math.cos(theta))%gridSize;
			
			if((positions[i]<0) || (positions[i]>gridSize))
			{
				positions[i]-=Math.floor(positions[i]/gridSize)*gridSize;
			}
			
		}
		return positions;
	}
	
	
	
	public static void checkParameters()
	{
		double wp = Math.sqrt(numberOfParticles/gridSize*electronCharge*electronCharge/
				epsilonZero/electronRestMass);
		System.out.println("Plasma frequency: " + wp + " rad/s");
	}
}