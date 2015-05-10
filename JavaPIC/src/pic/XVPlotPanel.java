package pic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;


public class XVPlotPanel extends JPanel {

	SimulationEngine engine;
	
	public XVPlotPanel(SimulationEngine engine)
	{
		super();
		this.engine=engine;
	}
	int[] positions1, positions2, velocities1, velocities2;
	int radius = 10;
	
	double minX=0;
	double maxX=Parameters.gridSize;
	double minV=-5d*Parameters.initialVelocity;
	double maxV=-5d*Parameters.initialVelocity;
	Graphics g;
	public XVPlotPanel() {
		g=getGraphics();
		setBackground(Color.YELLOW);
	}
	
	public void update(SimulationEngine engine)
	{
		this.engine=engine;
	}
	
	public void printParticles(Graphics2D g, Species[] listOfSpecies)
	{
		positions1 = new int[Parameters.numberOfParticles];
		positions2 = new int[Parameters.numberOfParticles];
		velocities1 = new int[Parameters.numberOfParticles];
		velocities2 = new int[Parameters.numberOfParticles];
		for (int i=0; i<Parameters.numberOfParticles; i++)
		{
			positions1[i] = (int)(listOfSpecies[0].position[i]/maxX*getWidth());
			positions2[i] = (int)(listOfSpecies[1].position[i]/maxX*getWidth());
			velocities1[i] = (int)(listOfSpecies[0].velocity[i]/(maxV-minV)*getHeight());
			velocities2[i] = (int)(listOfSpecies[1].velocity[i]/(maxV-minV)*getHeight());

			System.out.println(i);
			g.setColor(Color.RED);
			g.fillOval(positions1[i]-radius, velocities1[i]-radius, 2*radius, 2*radius);
			g.setColor(Color.BLUE);
			g.fillOval(positions2[i]-radius, velocities2[i]-radius, 2*radius, 2*radius);
		}
		repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		g2.clearRect(0, 0, getWidth(), getWidth());
		printParticles(g2, null);
		repaint();
	}
}
