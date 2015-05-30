package pic;

class Species {
	//Class containing information for a set of particles
	//for example, we could have a set of protons and a set of electrons
	//or two sets of electrons with different velocities
	//for the case of two-stream instability
	
	//everything related to movement is done here
	//(except for computing the fields)
	
	int numberOfParticles;
	double charge;
	double[] position;
	double[] velocity;
	Parameters parameters;
	double totalKineticEnergy;

	public Species(int numberOfParticlesI, double chargeI, double[] positionI, double[] velocityI, Parameters parameters)
	{
		charge = chargeI;
		numberOfParticles = numberOfParticlesI;
		position = positionI;
		velocity = velocityI;
		this.parameters = parameters;
	}

	public Species(Species source) {
		charge = source.charge;
		numberOfParticles = source.numberOfParticles;
		position = source.position;
		velocity = source.velocity;
		this.parameters = source.parameters;
	}

	private void move(double dt, Grid grid) {

		double max=0;
		double min = 1e9;
		double sum=0;
		for (int i=0; i<numberOfParticles; i++)
		{
			position[i] += velocity[i] * dt; //move particle
			//enforce boundary conditions

			//suppose size=3, position = 7
			//howManyPushes = floor(7/3) = 2
			//particle gets pushed back by 2*3=6, to 1

			//suppose position = -2
			//howManyPushes = floor (-2/3) = -1
			//particle gets pushed forward by 1*3=3, to +1
			int howManyPushesNeeded = (int) -Math.floor(position[i] / grid.gridSize);
			position[i] += howManyPushesNeeded * grid.gridSize;

			sum += position[i];
			if ((position[i]) > max) {
				max = (position[i]);
			}
			if ((position[i]) < min)
			{
				min = (position[i]);
			}
		}
		if (Parameters.printMovement)
			System.out.println("Position: average " + sum / numberOfParticles + " max " + max + " min " + min);

	}

	private void accelerate(double dt, Grid grid)
	{
		int index;
		double max = 0;
		double min = 1e9;
		double sum = 0;
		double field;
		totalKineticEnergy = 0;
		for (int i = 0; i < numberOfParticles; i++) {
			field = 0d;
			index = grid.getIndexOnGrid(position[i]);
			if (index < grid.gridPointNumber - 1) {
				//interpolate from left
				field += (grid.gridPoints[index + 1] - position[i]) * grid.eField[index];
				//interpolate from right

				//interpolate from right
				field += (position[i] - grid.gridPoints[index]) * grid.eField[index + 1];
			} else {
				//interpolate from left
				field += (grid.gridSize - position[i]) * grid.eField[grid.gridPointNumber - 1];
				//interpolate from right
				field += (grid.gridPoints[grid.gridPointNumber - 1] - position[i]) * grid.eField[0];
			}
			field /= grid.gridStep;
			double oldVelocity = velocity[i];
			velocity[i] += field * dt * Parameters.chargeToMassRatio;
			//TODO: Does this need a mass term?
			totalKineticEnergy += oldVelocity * 0.5 * velocity[i] / Parameters.chargeToMassRatio * parameters.charge;
			sum += velocity[i];
			if (Parameters.printMovement) {
				if (Math.abs(velocity[i]) > max) {
					max = Math.abs(velocity[i]);
				}
				if (Math.abs(velocity[i]) < min) {
					min = Math.abs(velocity[i]);
				}
			}
		}

		if (Parameters.printMovement)
			System.out.println("Velocity: average " + sum / numberOfParticles + " max " + max + " min " + min);
	}

	public void step(Grid grid)
	{
		//performs one simulation step for a species
		accelerate(parameters.timeStep, grid);
		move(parameters.timeStep, grid);
	}

}
