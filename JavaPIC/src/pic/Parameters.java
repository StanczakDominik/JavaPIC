package pic;

/**
 * Author: Dominik
 * Klasa zawieraj¹ca parametry programu - symulacji i wykresów.
 * Wiêkszoœæ zmiennych ustawionych na sztywno jest generowana jako zmienne statyczne
 * Zmienne kontrolowane przez u¿ytkownika s¹ tworzone w konstruktorze
 * <p>
 * Parametry wykorzystywane all over the symulacja s¹ podawane jako obiekt klasy Parameters
 * <p>
 * Zawiera kilka pomocniczych metod zwi¹zanych z inicjalizacj¹ symulacji
 * jednorodny rozk³ad po³o¿eñ
 * ustawienie ustalonej pocz¹tkowej prêdkoœci dla ka¿dej z cz¹stek
 * jednorodny z losowym zaburzeniem rozk³ad po³o¿eñ
 */
class Parameters {
	//"global variables" for the whole simulation
	//debug booleans
	
	public static boolean printFields = false;
	public static boolean printMovement = false;
	public static boolean printGridConvergence = false;
	//na sztywno
	public static double gridSize = 2d * Math.PI;
	public static double plotMaxVMultiplier = 5d;
	public static double epsilonZero = 1d;            // F/m
	public static int fieldCalculationIterations=50000;
	public static int fieldCalculationStep=100;
	public static double fieldPlotMaximumValue = 2d;
	//do podania
	public double timeStep;
	public int cellParticleDensity;
	public double initialVelocity;
	public int gridPointNumber;
	public double fieldErrorTolerance;
	public double charge1;
	public double charge2;
	public double mass1;
	public double mass2;
	public double perturbationAmplitude;
	//obliczane
	public double gridStep;
	public int numberOfParticles;
	public double backgroundCharge;


	public Parameters() //default
	{
		this.timeStep = 5e-1d;
		this.cellParticleDensity = 60;
		this.initialVelocity = 0.1d;//0.1d;
		this.gridPointNumber = 64;
		this.fieldErrorTolerance = 1e-10;
		this.perturbationAmplitude = 1e-2d;

		this.gridStep = gridSize / ((double) gridPointNumber);
		numberOfParticles = gridPointNumber * cellParticleDensity;

		mass1 = mass2 = 1;
		charge1 = charge2 = -gridSize / numberOfParticles * 10; //parametr kontroluje szybkosc interakcji. jest ustawiony
		//silowo na 10, bo inaczej po prostu byloby za wolno
		backgroundCharge = -(charge1 + charge2);
	}

	public Parameters(double timeStep, int cellParticleDensityI, double initialVelocity, int gridPointNumberI,
					  double fieldErrorTolerance, double perturbationAmplitude, double charge1I, double charge2I, double mass1, double mass2) {
		this.timeStep = timeStep;
		this.cellParticleDensity = cellParticleDensityI;
		this.initialVelocity = initialVelocity;
		this.gridPointNumber = gridPointNumberI;
		this.fieldErrorTolerance = fieldErrorTolerance;
		this.perturbationAmplitude = perturbationAmplitude;

		this.gridStep = gridSize / (double) gridPointNumber;
		numberOfParticles = gridPointNumberI * cellParticleDensityI;

		this.charge1 = charge1I * gridSize / numberOfParticles;
		this.charge2 = charge2I * gridSize / numberOfParticles;
		this.mass1 = mass1;
		this.mass2 = mass2;

		backgroundCharge = -(charge1 + charge2);
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