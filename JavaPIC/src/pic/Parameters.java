package pic;

public class Parameters {
	//"global variables" for the whole simulation
	
	//physical constants
	public static double epsilonZero= 1d;			// F/m
	//debug booleans
	
	public static boolean printFields = false;
	public static boolean printIterations = false;
	
	
	//simulation parameters
	public static double timeStep = 1e-1d;
	public static int iterations = 3000;
	public static int cellParticleDensity = 60;
	public static double plotMaxVMultiplier=10d;
	public static double fieldPlotSize=1d;
	//TODO: add scaling to fieldPlot?
	public static double fieldFieldScale;
	public static double fieldDensityScale;
	//grid parameters
	public static double gridSize = 2d*Math.PI;
	public static int gridPointNumber = 64;
	public static double gridStep = gridSize/((double)(gridPointNumber));
	
	public static double fieldErrorTolerance=1e-9;
	public static int fieldCalculationIterations=25000;
	public static int fieldCalculationStep=100;

	//species parameters
	public static double chargeToMassRatio = -1d;
	
	public static int numberOfParticles=gridPointNumber*cellParticleDensity;
	public static double initialVelocity = 0.5d;
	
	public static double perturbationAmplitude=1e-4d;

	public static double charge = gridSize/numberOfParticles;
	public static double backgroundCharge = -2*charge;

	static double[] uniformPositions(int numberOfParticles, double min, double max, double shift)
	{
		double[] locations = new double[numberOfParticles];
		double dx = (max-min)/numberOfParticles;
		for (int i=0; i<numberOfParticles; i++)
		{
			locations[i]=(i+shift)*dx;
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

	static double[] randomPosition(int numberOfParticles, double amplitude)
	{
		double[] positions = new double[numberOfParticles];
		for (int i=0; i<numberOfParticles; i++)
		{
			positions[i] = ((i * gridSize) /((double) numberOfParticles)) + (amplitude * Math.random());
			if((positions[i]<0) || (positions[i]>gridSize))
			{
				positions[i]-=Math.floor(positions[i]/gridSize)*gridSize;
			}
		}
		return positions;
	}


}