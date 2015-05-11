package pic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.*;


public class XVPlotPanel extends JPanel {

	SimulationEngine engine;
	
	public XVPlotPanel(SimulationEngine engine)
	{
		super();
		this.engine=engine;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	int[] positions1, positions2, velocities1, velocities2;
	int radius = 2;
	
	double minX=0;
	double maxX=Parameters.gridSize;
	double minV=-20d*Parameters.initialVelocity;
	double maxV=20d*Parameters.initialVelocity;
	Graphics g;
	public XVPlotPanel() {
		g=getGraphics();
	}
	
	public void update(SimulationEngine engine)
	{

		positions1 = new int[Parameters.numberOfParticles];
		positions2 = new int[Parameters.numberOfParticles];
		velocities1 = new int[Parameters.numberOfParticles];
		velocities2 = new int[Parameters.numberOfParticles];
		this.engine=engine;

		for (int i = 0; i < Parameters.numberOfParticles; i++) {
			//System.out.println(listOfSpecies[0].position[i]);
			positions1[i] = (int) (engine.listOfSpecies[0].position[i] / maxX * getWidth());
			positions2[i] = (int) (engine.listOfSpecies[1].position[i] / maxX * getWidth());
			velocities1[i] = (int) ((engine.listOfSpecies[0].velocity[i] / (maxV - minV)+0.5) * getHeight());
			velocities2[i] = (int) ((engine.listOfSpecies[1].velocity[i] / (maxV - minV)+0.5) * getHeight());
		}
		repaint();
	}

	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		g.setColor(Color.white);
		g.clearRect(0, 0, getWidth(), getHeight());
		for (int i =0; i<Parameters.numberOfParticles; i++) {
		//for (int i = 0; i < Parameters.numberOfParticles; i+=(int)(Parameters.numberOfParticles/10000d)) {
//		for (int i = 0; i < 10; i++) {
			//System.out.println(positions1[i] + " " + velocities1[1]);
			g.setColor(Color.RED);
			g.fillOval(positions1[i] - radius, velocities1[i] - radius, 2 * radius, 2 * radius);
			g.setColor(Color.BLUE);
			g.fillOval(positions2[i] - radius, velocities2[i] - radius, 2 * radius, 2 * radius);

		}
	}
}

