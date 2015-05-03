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
		
		
		//Gauss Seidel Method as used at particleincell.com
		//I have used this once, a while ago, but used that
		//version as a refresher
		potential = new double[gridPointNumber];
		int forwardIndex, backwardIndex;
		double potentialTemporaryVariable, sum;
		for (int iter=0; iter<20000; iter++)
		{
			for (int i =0; i<gridPointNumber-1; i++)
			{
				forwardIndex=i+1;
				if(forwardIndex==gridPointNumber-1) forwardIndex=0;
				backwardIndex=i-1;
				if(backwardIndex==-1) backwardIndex=gridPointNumber-2;
				potentialTemporaryVariable = 0.5*(potential[i]/Parameters.epsilonZero)*gridStep*gridStep;
				potentialTemporaryVariable += potential[forwardIndex] + potential[backwardIndex];
				potential[i] += 1.4*(potentialTemporaryVariable-potential[i]);
			}
			
			if (iter%25==0)
			{
				sum=0;
				for (int i=1; i<gridPointNumber-1; i++)
				{
					forwardIndex=i+1;
					if(forwardIndex==gridPointNumber-1) forwardIndex=0;
					double res = potential[i]/Parameters.epsilonZero+(potential[i-1]-2*potential[i]+potential[forwardIndex])/(gridStep*gridStep);
					sum+=res*res;
				}
				sum=Math.sqrt(sum/gridPointNumber);
				if(sum<Parameters.fieldErrorTolerance) break;
				
			}
		}
		//eField calculation
		for (int i =0; i<gridPointNumber-1; i++)
		{
			forwardIndex=i+1;
			if(forwardIndex==gridPointNumber) forwardIndex=1;
			backwardIndex=i-1;
			if(backwardIndex==-1) backwardIndex=gridPointNumber-2;
			
			eField[i]=(potential[backwardIndex]-potential[forwardIndex])/(2*gridStep);
			
		}
	}
	
	public Grid() {
		gridPoints = new double[gridPointNumber];
		eField = new double[gridPointNumber];
		density = new double[gridPointNumber];
		frequencies = new double[gridPointNumber];
		potential = new double[gridPointNumber];
	}

}
