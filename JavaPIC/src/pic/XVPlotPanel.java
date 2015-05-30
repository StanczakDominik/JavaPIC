package pic;

import javax.swing.*;
import java.awt.*;


class XVPlotPanel extends JPanel {

	private int N;
	private int[] positions1;
	private int[] positions2;
	private int[] velocities1;
	private int[] velocities2;
	private double maxX;
	private double minV;
	private double maxV;
	public XVPlotPanel(SimulationEngine engine)
	{
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		maxX = Parameters.gridSize;
		minV = -Parameters.plotMaxVMultiplier * engine.parameters.initialVelocity;
		maxV = Parameters.plotMaxVMultiplier * engine.parameters.initialVelocity;
		N = engine.parameters.numberOfParticles;
	}

	public void update(SimulationEngine engine)
	{

		positions1 = new int[N];
		positions2 = new int[N];
		velocities1 = new int[N];
		velocities2 = new int[N];

		for (int i = 0; i < N; i++) {
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
		for (int i = 0; i < N; i++) {
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

