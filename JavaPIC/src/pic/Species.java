package pic;

import java.awt.Color;

public class Species {
	//Class containing information for a set of particles
	//for example, we could have a set of protons and a set of electrons
	//or two sets of electrons with different velocities
	//for the case of two-stream instability
	
	//everything related to movement is done here
	//(except for computing the fields)
	
	String name;
	Color color;
	long numberOfParticles;
	double mass;
	double charge;
	double[] position;
	double[] velocity;
	
	double[] plotPositions;
	double[] plotVelocities;
	double[] plotTemperatures;
	
	public void move(double dt, Grid grid)
	{
		for (int i=0; i<this.numberOfParticles; i++)
		{
			this.position[i] += this.velocity[i]*dt; //move particle
			//enforce boundary conditions
			
			//suppose size=3, position = 7
			//howManyPushes = floor(7/3) = 2
			//particle gets pushed back by 2*3=6, to 1
			
			//suppose position = -2
			//howManyPushes = floor (-2/3) = -1
			//particle gets pushed forward by 1*3=3, to +1
			double howManyPushesNeeded=Math.floor(this.position[i]/grid.gridSize);
			this.position[i]-=howManyPushesNeeded*grid.gridSize;
		}
	}
	
	public void accelerate(double dt, Grid grid)
	{
		int index;
		double field;
		for (int i=0; i<this.numberOfParticles; i++)
		{
			index = grid.getIndexOnGrid(this.position[i]);
			if (index<grid.gridPointNumber-1)
			{	
				//interpolate from left
				field = (grid.gridPoints[index+1]-this.position[i])*grid.eField[i];
				//interpolate from right
				field += (this.position[i]-grid.gridPoints[index])*grid.eField[i+1];
			}
			else
			{
				//interpolate from left
				field = (-this.position[i])*grid.eField[grid.gridPointNumber-1];
				//interpolate from right
				field += (grid.gridPoints[grid.gridPointNumber-1]-this.position[i])*grid.eField[0];
			}
			this.velocity[i]+=field*dt/this.mass*this.charge/grid.gridStep; //time charge for field;
											//over mass for accel; over gridstep from interpolation
		}
	}
	
	public double temperature()
	{
		double averageVel=0;
		for (int i=0; i<this.numberOfParticles; i++)
		{
			averageVel+=this.velocity[i]*this.velocity[i];
		}
		averageVel=Math.sqrt(averageVel/this.numberOfParticles);
		return this.mass*averageVel;

	}
	public void step(Grid grid)
	{
		//performs one simulation step for a species 
		this.accelerate(Parameters.timeStep, grid);
		this.move(Parameters.timeStep, grid);
		//TODO: gather trajectory data
		//TODO: gather velocity data
		//TODO: gather temperature data
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
