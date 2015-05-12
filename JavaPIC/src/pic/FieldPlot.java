package pic;

import javax.swing.*;
import java.awt.*;

public class FieldPlot extends JPanel implements Runnable {

    SimulationEngine engine;

    public FieldPlot(SimulationEngine engine) {
        super();
        this.engine = engine;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        g = getGraphics();

        t = new Thread(this);
        t.start();
    }

    int[] density, potential, field;
    public int radius = 5;

    double minV = -Parameters.fieldPlotSize;
    double maxV = Parameters.fieldPlotSize;
    Graphics g;
    public Thread t;

    public void update(SimulationEngine engine) {
        density = new int[Parameters.gridPointNumber];
        potential = new int[Parameters.gridPointNumber];
        field = new int[Parameters.gridPointNumber];

        this.engine = engine;

        for (int i = 0; i < Parameters.gridPointNumber; i++) {
            density[i] = (int) ((engine.grid.density[i] / (maxV - minV) + 0.5) * getHeight());
            potential[i] = (int) ((engine.grid.potential[i] / (maxV - minV) + 0.5) * getHeight());
            field[i] = (int) ((engine.grid.eField[i] / (maxV - minV) + 0.5) * getHeight());
        }
    }

    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.clearRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < Parameters.gridPointNumber; i++) {
            g.setColor(Color.RED);
            g.fillOval((int) (i * Parameters.gridStep / Parameters.gridSize * ((double) (getWidth())) - radius), density[i] - radius, 2 * radius, 2 * radius);
            g.setColor(Color.BLUE);
            g.fillOval((int) (i * Parameters.gridStep / Parameters.gridSize * ((double) (getWidth())) - radius), potential[i] - radius, 2 * radius, 2 * radius);
            g.setColor(Color.GREEN);
            g.fillOval((int) (i * Parameters.gridStep / Parameters.gridSize * ((double) (getWidth())) - radius), field[i] - radius, 2 * radius, 2 * radius);
        }
    }


    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
