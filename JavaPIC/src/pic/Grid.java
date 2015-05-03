package pic;

public class Grid {

	String name;
	

	double[] gridPoints;
	public double gridSize = Parameters.gridSize;
	public int gridPointNumber = Parameters.gridPointNumber;
	public double gridStep = gridSize/(gridPointNumber-1);
	
	double[] eField;
	double[] density;
	double[] frequencies;
	double[] potential;
	
	public int getIndexOnGrid(double position)
	{
		return (int)(Math.floor(position/gridStep));
	}
	
	public void update(Species[] listofspecies)
	{
		int index;
		density = new double[gridPointNumber];
		double[] speciesDensity = new double[gridPointNumber];
		
		//calculate density
		for (Species species:listofspecies)
		{
			for (int i=0; i<species.numberOfParticles; i++)
			{
				index = getIndexOnGrid(species.position[i]);			
				if (index<gridPointNumber-1)
				{
					speciesDensity[index]+=1-(species.position[i]-gridPoints[index])/gridStep;
					speciesDensity[index+1]+=(species.position[i]-gridPoints[index])/gridStep;
				}
				else
				{
					speciesDensity[index]+=1-(species.position[i]-gridPoints[index])/gridStep;
					speciesDensity[0]+=(species.position[i]-gridPoints[index])/gridStep;
				}
			}
			for(int i=0; i<gridPointNumber; i++)
			{
				density[i]+=speciesDensity[i]*species.charge;
			}
		}
//        self.freq=self.L*fftfreq(NG, self.dX)
//        self.freq[0]=0.01
//        self.pot = np.real(ifft(fft(self.density)[0:NG]/self.freq[0:NG]**2/4./pi**2/EPSILON0))
//        self.efield = -np.gradient(self.pot)
//        self.efield+=externalfield(self.X)
		
	}
	
	public Grid() {
		gridPoints = new double[gridPointNumber];
		eField = new double[gridPointNumber];
		density = new double[gridPointNumber];
		frequencies = new double[gridPointNumber];
		potential = new double[gridPointNumber];
	}

}
