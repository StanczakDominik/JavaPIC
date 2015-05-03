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
		return (int)(Math.floor(position/this.gridStep));
	}
	public Grid() {
		// TODO Auto-generated constructor stub
	}

}
