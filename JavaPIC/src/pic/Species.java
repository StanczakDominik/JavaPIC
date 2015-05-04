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
			double howManyPushesNeeded=Math.floor(position[i]/grid.gridSize);
			position[i]-=howManyPushesNeeded*grid.gridSize;
		}
	}
	
	public void accelerate(double dt, Grid grid)
	{
		int index;
		double field=0;
		for (int i=0; i<numberOfParticles; i++)
		{
			index = grid.getIndexOnGrid(position[i]);
			System.out.println(i + " " + index);
			if (index<grid.gridPointNumber-1)
			{	
				try
				{
					//interpolate from left
					field += (grid.gridPoints[index+1]-position[i])*grid.eField[i];
					//interpolate from right
					field += (position[i]-grid.gridPoints[index])*grid.eField[i+1];
				}
				catch(IndexOutOfBoundsException e)
				{
					System.err.println("Index out of bounds at acceleration. Index:" + index + ", index+1:" + (index+1)
							+ ", position: " + position[i] + ",message:" + e.getMessage());
				}
			}
			else
			{
				//interpolate from left
				field += (-position[i])*grid.eField[grid.gridPointNumber-1];
				//interpolate from right
				field += (grid.gridPoints[grid.gridPointNumber-1]-position[i])*grid.eField[0];
			}
			velocity[i]+=field*dt/mass*charge/grid.gridStep; //time charge for field;
											//over mass for accel; over gridstep from interpolation
		}
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
		//TODO: gather velocity data
		
		//TODO: gather temperature data
		plotTemperatures.addElement((Double)temperature());
	}

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
