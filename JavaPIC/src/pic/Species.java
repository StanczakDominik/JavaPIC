package pic;

import java.awt.Color;
import java.util.Vector;

public class Species {
	//Class containing information for a set of particles
	//for example, we could have a set of protons and a set of electrons
	//or two sets of electrons with different velocities
	//for the case of two-stream instability
	
	//everything related to movement is done here
	//(except for computing the fields)
	
	String name;
	Color color;
	int numberOfParticles;
	double mass;
	double charge;
	double[] position;
	double[] velocity;
	
	//historical data for plotting
	Vector<double[]> plotPositions;
	Vector<double[]> plotVelocities;
	Vector<Double> plotTemperatures = new Vector<Double>();
	
	public void move(double dt, Grid grid)
	{

		double max=0;
		double min = 1e9;
		double sum=0;
		for (int i=0; i<numberOfParticles; i++)
		{
			position[i] += velocity[i]*dt; //move particle
			//enforce boundary conditions
			
			//suppose size=3, position = 7
			//howManyPushes = floor(7/3) = 2
			//particle gets pushed back by 2*3=6, to 1
			
			//suppose position = -2
			//howManyPushes = floor (-2/3) = -1
			//particle gets pushed forward by 1*3=3, to +1
			int howManyPushesNeeded=(int)-Math.floor(position[i]/grid.gridSize);
			//System.out.println("Position " + position[i] + " Pushes " + howManyPushesNeeded);
			//if(howManyPushesNeeded!=0)
			//{
			//	System.out.println(howManyPushesNeeded);
			//}
			position[i]+=howManyPushesNeeded*grid.gridSize;

			sum+=position[i];
			//positions[i]-=(positions[i]%gridSize)*gridSize;Math.floor(positions[i]/gridSize)*gridSize;
			if((position[i])>max)
			{
				max = (position[i]);
			}
			if((position[i])<min)
			{
				min = (position[i]);
			}
		}
		System.out.println("Position: average " + sum/numberOfParticles + " max " + max + " min " + min);

	}
	
	public void accelerate(double dt, Grid grid)
	{
		int index;
		double max=0;
		double min = 1e9;
		double sum=0;
		double field;
		//System.out.println("Velocity");
		for (int i=0; i<numberOfParticles; i++)
		{
			field=0d;
			index = grid.getIndexOnGrid(position[i]);
			//System.out.println(i + " " + index);
			if (index<grid.gridPointNumber-1)
			{	
					//interpolate from left
					field += (grid.gridPoints[index+1]-position[i])*grid.eField[index];
					//interpolate from right

					//interpolate from right
					field += (position[i]-grid.gridPoints[index])*grid.eField[index+1];
			}
			else
			{
				//interpolate from left
				field += (grid.gridSize-position[i])*grid.eField[grid.gridPointNumber-1];
				//interpolate from right
				field += (grid.gridPoints[grid.gridPointNumber-1]-position[i])*grid.eField[0];
			}
			field/=grid.gridStep;
			velocity[i]+=field*dt*Parameters.chargeToMassRatio; //time charge for field;
			sum+=velocity[i];
			if(Math.abs(velocity[i])>max)
			{
				max = Math.abs(velocity[i]);
			}
			if(Math.abs(velocity[i])<min)
			{
				min = Math.abs(velocity[i]);
			}
											//over mass for accel; over gridstep from interpolation
			//System.out.print(velocity[i] + " ");
		}

		System.out.println("Velocity: average " + sum/numberOfParticles + " max " + max + " min " + min);
		//System.out.println("");
	}
	
	public double temperature()
	{
		double averageVel=0;
		for (int i=0; i<numberOfParticles; i++)
		{
			averageVel+=velocity[i]*velocity[i];
		}
		averageVel=Math.sqrt(averageVel/numberOfParticles);
		return mass*averageVel;

	}
	public void step(Grid grid)
	{
		//performs one simulation step for a species 
		accelerate(Parameters.timeStep, grid);
		move(Parameters.timeStep, grid);
		//TODO: gather trajectory data
		//plotPositions.addElement(position);
		//TODO: gather velocity data
		//plotPositions.addElement(velocity);
		
		//TODO: gather temperature data
		//System.out.println(temperature());
		plotTemperatures.addElement((Double)temperature());
	}
	//TODO: remove useless parameters
	public Species(String nameI, Color colorI, int numberOfParticlesI, double massI, double chargeI, double[] positionI, double[] velocityI)
	{
		name=nameI;
		color=colorI;
		numberOfParticles=numberOfParticlesI;
		mass=massI;
		charge=chargeI;
		position=positionI;
		velocity=velocityI;
		plotTemperatures.addElement((Double)temperature());
		//plotTemperatures.addElement(temperature());
		//plotPositions
	}

}
