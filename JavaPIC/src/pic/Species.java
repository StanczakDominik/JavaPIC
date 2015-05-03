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
	
	public void move(double dt)
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
			double howManyPushesNeeded=Math.floor(this.position[i]/Parameters.size);
			this.position[i]-=howManyPushesNeeded*Parameters.size;
		}
	}
	
	public void accelerate(double dt)
	{
		for (int i=0; i<this.numberOfParticles; i++)
		{
			//TODO
			//if this.position[i]<
		}
	}
	
	public void temperature()
	{
		//calculates temperature in K
		//TODO
	}
	public void step()
	{
		//performs one simulation step with one 
		this.accelerate(Parameters.timeStep);
		this.move(Parameters.timeStep);
		//TODO: gather trajectory data
		//TODO: gather velocity data
		//TODO: gather temperature data
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
