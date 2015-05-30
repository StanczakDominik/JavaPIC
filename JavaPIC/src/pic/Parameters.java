package pic;

class Parameters {
	//"global variables" for the whole simulation
	//debug booleans
	
	public static boolean printFields = false;
	public static boolean printIterations = false;
	public static boolean printMovement = false;
	public static boolean printGridConvergence = false;
	//na sztywno
	public static double gridSize = 2d * Math.PI;
	public static double plotMaxVMultiplier = 25d;
	public static double epsilonZero = 1d;            // F/m
	public static int fieldCalculationIterations=50000;
	public static int fieldCalculationStep=100;
	public static double fieldPlotMaximumValue=1d;
	public static double chargeToMassRatio = -1d;
	public static double perturbationAmplitude=1e-4d;
	//do podania
	public double timeStep;
	public int cellParticleDensity;
	public double initialVelocity;
	public int gridPointNumber;
	public double fieldErrorTolerance;
	//obliczane
	public double gridStep;
	public int numberOfParticles;
	public double charge;
	public double backgroundCharge;


	public Parameters(double timeStep, int cellParticleDensity, double initialVelocity, int gridPointNumber,
					  double fieldErrorTolerance) {
		this.timeStep = timeStep;
		this.cellParticleDensity = cellParticleDensity;
		this.initialVelocity = initialVelocity;
		this.gridPointNumber = gridPointNumber;
		this.fieldErrorTolerance = fieldErrorTolerance;

		this.gridStep = gridSize / ((double) gridPointNumber);
		numberOfParticles = gridPointNumber * cellParticleDensity;
		charge = gridSize / numberOfParticles;
		backgroundCharge = -2 * charge;
	}

	public Parameters() //default
	{
		this.timeStep = 3e-1d;
		this.cellParticleDensity = 60;
		this.initialVelocity = 0.1d;
		this.gridPointNumber = 64;
		this.fieldErrorTolerance = 1e-10;

		this.gridStep = gridSize / ((double) gridPointNumber);
		numberOfParticles = gridPointNumber * cellParticleDensity;
		charge = gridSize / numberOfParticles;
		backgroundCharge = -2 * charge;
	}


	//Funkcje
	static double[] uniformPositions(int numberOfParticles, double max)
	{
		double[] locations = new double[numberOfParticles];
		double dx = max / numberOfParticles;
		for (int i=0; i<numberOfParticles; i++)
		{
			locations[i] = (i) * dx;
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