package pic;

public class Grid {
	double[] gridPoints;
	public double gridSize = Parameters.gridSize;
	public int gridPointNumber = Parameters.gridPointNumber;
	public double gridStep = Parameters.gridStep;
	double[] eField;
	double[] density;
	double[] potential;
	
	public int getIndexOnGrid(double position)
	{
		return (int)(Math.floor(position/gridStep));
	}
		
	public void update(Species[] listofspecies)
	{
		int index;
		density = new double[gridPointNumber];
		double[] speciesDensity;
		
		//calculate density
		for (Species species:listofspecies)
		{	
			speciesDensity = new double[gridPointNumber];
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
		for (int i=0; i<gridPointNumber; i++)
		{
			density[i]+=Parameters.cellParticleDensity*Parameters.backgroundCharge;
		}
		density[gridPointNumber-1]+=density[0];
		density[0]=density[gridPointNumber-1];
		
		//Gauss Seidel Method
		potential = new double[gridPointNumber];
		int forwardIndex, backwardIndex;
		double potentialTemporaryVariable, oldPotential, delta, sum, maxChange=2d*Parameters.fieldErrorTolerance;
		for (int iter=0; iter<Parameters.fieldCalculationIterations; iter++)
		{
			maxChange=0;
			for (int i =0; i<gridPointNumber; i++)
			{
				forwardIndex=i+1;
				if(forwardIndex==gridPointNumber) forwardIndex=0;
				backwardIndex=i-1;
				if(backwardIndex==-1) backwardIndex=gridPointNumber-1;
				oldPotential=potential[i];
				potential[i]= (density[i]*gridStep*gridStep/Parameters.epsilonZero+potential[forwardIndex]+potential[backwardIndex])/2;
				delta = potential[i]-oldPotential;
				if (Math.abs(delta)>maxChange)
				{
					maxChange=Math.abs(delta);
				}
			}

			if (((iter>0) && (iter%Parameters.fieldCalculationStep==0) && (maxChange<Parameters.fieldErrorTolerance)) || (iter==Parameters.fieldCalculationIterations))
			{
				if(Parameters.printGridConvergence) System.out.println("iteration " + iter + " " + maxChange + " converged");
				break;
			}
			if(iter==Parameters.fieldCalculationIterations)
			{
				if(Parameters.printGridConvergence) System.out.println("May not have converged!");
			}
		}
		for (int i =0; i<gridPointNumber; i++)
		{
			forwardIndex=i+1;
			if(forwardIndex==gridPointNumber) forwardIndex=1;
			backwardIndex=i-1;
			if(backwardIndex==-1) backwardIndex=gridPointNumber-2;
			
			eField[i]=(potential[backwardIndex]-potential[forwardIndex])/(2d*gridStep);
			if(Parameters.printFields) System.out.println(i + "\t" + potential[i] + "\t" + eField[i]);
		}
	}
	
	public Grid() {
		gridPoints = Parameters.uniformPositions(gridPointNumber, 0, gridSize, 0);
		eField = new double[gridPointNumber];
		density = new double[gridPointNumber];
		potential = new double[gridPointNumber];
	}

	public Grid(Grid source)
	{
		gridPoints = source.gridPoints;
		eField = source.eField;
		density = source.density;
		potential = source.potential;
	}

}
