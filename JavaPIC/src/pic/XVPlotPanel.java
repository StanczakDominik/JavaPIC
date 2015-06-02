package pic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Autor: Dominik
 * Klasa odpowiadaj¹ca za wykres fazowy - na osi poziomej znajduj¹ siê po³o¿enia, na osi pionowej - prêdkoœci
 * <p>
 * Metoda update aktualizuje tablice po³o¿eñ i prêdkoœci, a nastêpnie odwo³uje siê do paintComponent
 * Gdzie nastêpuje rysowanie kropek odpowiadaj¹cych cz¹stkom
 * Metoda saveChartAsPng zapisuje wykres fazowy do pliku XVPlot#.png, gdzie # jest numerem zdjêcia
 * Rozmiar wykresu zale¿y od rozmiaru okienka
 */

class XVPlotPanel extends JPanel {

	private int N;
	private int[] positions1;
	private int[] positions2;
	private int[] velocities1;
	private int[] velocities2;
	private double maxX;
	private double minV;
	private double maxV;
	private int snapshotsTaken = 0;
	private double initialVelocity;

	public XVPlotPanel(SimulationEngine engine)
	{
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		initialVelocity = engine.parameters.initialVelocity;
		maxX = Parameters.gridSize;
		minV = -Parameters.plotMaxVMultiplier * engine.parameters.initialVelocity;
		maxV = Parameters.plotMaxVMultiplier * engine.parameters.initialVelocity;
		N = engine.parameters.numberOfParticles;
	}

	public void changeN(Parameters parameters) {
		N = parameters.numberOfParticles;
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

	public void saveChartAsPng() {
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		paint(graphics2D);
		String fileName = "XVPlot" + snapshotsTaken + ".png";
		try {
			ImageIO.write(image, "png", new File(fileName));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		snapshotsTaken++;
	}

	protected void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g.drawLine(getWidth() / 100, 0, getWidth() / 100, getHeight());
		g.drawString("x", (int) (getWidth() * 0.99), getHeight() / 2 - 3);
		g.drawString("V0", getWidth() / 100 + 2, -2 + (int) ((-initialVelocity / (maxV - minV) + 0.5) * getHeight()));
		g.drawString((int) Parameters.plotMaxVMultiplier + "V0", getWidth() / 100 + 2, 10);

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

