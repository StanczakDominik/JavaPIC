package pic;

import java.awt.*;
import javax.swing.*;


public class XVPlotPanel extends JPanel  {

	SimulationEngine engine;
	
	public XVPlotPanel(SimulationEngine engine)
	{
		super();
		this.engine=engine;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		g=getGraphics();
	}
	int[] positions1, positions2, velocities1, velocities2;
	public int radius = 2;
	
	double minX=0;
	double maxX=Parameters.gridSize;
	double minV=-Parameters.plotMaxVMultiplier*Parameters.initialVelocity;
	double maxV=Parameters.plotMaxVMultiplier*Parameters.initialVelocity;
	Graphics g;
	public void update(SimulationEngine engine)
	{

		positions1 = new int[Parameters.numberOfParticles];
		positions2 = new int[Parameters.numberOfParticles];
		velocities1 = new int[Parameters.numberOfParticles];
		velocities2 = new int[Parameters.numberOfParticles];
		this.engine=engine;

		for (int i = 0; i < Parameters.numberOfParticles; i++) {
			positions1[i] = (int) (engine.listOfSpecies[0].position[i] / maxX * getWidth());
			positions2[i] = (int) (engine.listOfSpecies[1].position[i] / maxX * getWidth());
			velocities1[i] = (int) ((engine.listOfSpecies[0].velocity[i] / (maxV - minV)+0.5) * getHeight());
			velocities2[i] = (int) ((engine.listOfSpecies[1].velocity[i] / (maxV - minV)+0.5) * getHeight());
		}
		repaint();
	}

	protected void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.clearRect(0, 0, getWidth(), getHeight());
		for (int i =0; i<Parameters.numberOfParticles; i++) {
			g.setColor(Color.BLUE);
			g.fillOval(positions1[i] - radius, velocities1[i] - radius, 2 * radius, 2 * radius);
			g.setColor(Color.RED);
			g.fillOval(positions2[i] - radius, velocities2[i] - radius, 2 * radius, 2 * radius);

		}
	}

//	@Override
//	public void run() {
//		while(true)
//		{
//			repaint();
//			try{Thread.sleep(30);} catch (InterruptedException e) {return;}
//		}
//	}
}

