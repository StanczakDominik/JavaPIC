package pic;

public class Grid {

	String name;
	

	double[] gridPoints;
	public double gridSize = Parameters.gridSize;
	public int gridPointNumber = Parameters.gridPointNumber;
	public double gridStep = (double)gridSize/(gridPointNumber-1);
	
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
				System.out.println("speciesDensity " + speciesDensity[index]);
			}
			for(int i=0; i<gridPointNumber; i++)
			{
				density[i]+=speciesDensity[i]*species.charge;
					System.out.println("density at " + i + ": " + density[i]);
				//System.out.println(density[i]);
			}
		}
		
		//Gauss Seidel Method
		potential = new double[gridPointNumber];
		int forwardIndex, backwardIndex;
		double potentialTemporaryVariable, sum;
		for (int iter=0; iter<400; iter++)
		{
			for (int i =0; i<gridPointNumber; i++)
			{
				forwardIndex=i+1;
				if(forwardIndex==gridPointNumber) forwardIndex=0;
				backwardIndex=i-1;
				if(backwardIndex==-1) backwardIndex=gridPointNumber-1;
					//System.out.println("Forward index " + forwardIndex + ", backward index " + backwardIndex);
				potentialTemporaryVariable = 0.5d*(density[i]/Parameters.epsilonZero)*gridStep*gridStep;
					//System.out.println("PotentialTemporary at " + i + ": " + potentialTemporaryVariable);
				potentialTemporaryVariable += 0.5d*(potential[forwardIndex] + potential[backwardIndex]);
					//System.out.println("PotentialTemporary after add at " + i + ": " + potentialTemporaryVariable);
				potential[i] += 1.4d*(potentialTemporaryVariable-potential[i]);
			
			}

//			try {
//				System.in.read();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			if ((iter%5==0) && (iter>0))
			{
				System.out.println("Iteration " + iter);
				//for (int i=0; i<gridPointNumber; i++)
				//{
				//	System.out.println("Potential at " + i + ": " + potential[i]);
				//}
				
				sum=0d;
				for (int i=1; i<gridPointNumber; i++)
				{
					forwardIndex=i+1;
					if(forwardIndex==gridPointNumber) forwardIndex=0;
						System.out.println("Density/e0 " + density[i]/Parameters.epsilonZero + "second term: " + (potential[i-1]-2*potential[i]+potential[forwardIndex])/(gridStep*gridStep));
					double res = density[i]/Parameters.epsilonZero+(potential[i-1]-2*potential[i]+potential[forwardIndex])/(gridStep*gridStep);
					sum+=res*res;
					
				}
				System.out.println("sum" + sum);
				sum=Math.sqrt((double)(sum/(double)gridPointNumber));
				System.out.println("Total field error estimated as " + sum);
				if(sum<Parameters.fieldErrorTolerance) break;
				
				//try {
				//	System.in.read();
				//} catch (IOException e) {
				//	// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				
			}
			if(iter==Parameters.fieldCalculationIterations-1)
			{
				System.out.println("MAY NOT HAVE CONVERGED");
			}
		}
		//System.out.println("Potential");
		for (int i =0; i<gridPointNumber-1; i++)
		{
			//System.out.print(potential[i] + " ");
		}
		//System.out.println("");
		//eField calculation
		//System.out.println("Field");
		for (int i =0; i<gridPointNumber-1; i++)
		{
			forwardIndex=i+1;
			if(forwardIndex==gridPointNumber) forwardIndex=1;
			backwardIndex=i-1;
			if(backwardIndex==-1) backwardIndex=gridPointNumber-2;
			
			eField[i]=(potential[backwardIndex]-potential[forwardIndex])/(2*gridStep);
			//System.out.print(eField[i] + " ");
			//DEBUG
			//eField[i]=1;
		}
		//System.out.println("");
	}
	
	public Grid() {
		gridPoints = Parameters.uniformPositions(gridPointNumber, 0, gridSize, 0);
		eField = new double[gridPointNumber];
		density = new double[gridPointNumber];
		frequencies = new double[gridPointNumber];
		potential = new double[gridPointNumber];
	}

}
