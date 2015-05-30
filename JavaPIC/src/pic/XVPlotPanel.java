package pic;

import javax.swing.*;
import java.awt.*;


class XVPlotPanel extends JPanel {

	private int[] positions1;
	private int[] positions2;
	private int[] velocities1;
	private int[] velocities2;
	private double maxX = Parameters.gridSize;
	private double minV = -Parameters.plotMaxVMultiplier * Parameters.initialVelocity;
	private double maxV = Parameters.plotMaxVMultiplier * Parameters.initialVelocity;
	public XVPlotPanel(SimulationEngine engine)
	{
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void update(SimulationEngine engine)
	{

		positions1 = new int[Parameters.numberOfParticles];
		positions2 = new int[Parameters.numberOfParticles];
		velocities1 = new int[Parameters.numberOfParticles];
		velocities2 = new int[Parameters.numberOfParticles];

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
			int radius = 2;
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

