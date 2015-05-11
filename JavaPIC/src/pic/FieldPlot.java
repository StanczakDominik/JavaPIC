package pic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dominik on 2015-05-11.
 */
public class FieldPlot extends JPanel {

    SimulationEngine engine;

    public FieldPlot(SimulationEngine engine)
    {
        super();
        this.engine=engine;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        g=getGraphics();
    }
    int[] density, potential, field;
    public int radius = 5;

    double minX=0;
    double maxX=Parameters.gridSize;
    double minV=-Parameters.fieldPlotSize;
    double maxV=Parameters.fieldPlotSize;
    Graphics g;

    public void update(SimulationEngine engine)
    {
        density=new int[Parameters.gridPointNumber];
        potential=new int[Parameters.gridPointNumber];
        field=new int[Parameters.gridPointNumber];

        this.engine=engine;

        for (int i = 0; i < Parameters.gridPointNumber; i++) {
            //System.out.println(listOfSpecies[0].position[i]);
            density[i] = (int) ((engine.grid.density[i] / (maxV-minV)+0.5) * getHeight());
            potential[i] = (int) ((engine.grid.potential[i] / (maxV-minV)+0.5) * getHeight());
            field[i] = (int) ((engine.grid.eField[i] / (maxV - minV)+0.5) * getHeight());
        }
        repaint();
    }

    protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
        g.setColor(Color.white);
        g.clearRect(0, 0, getWidth(), getHeight());
        for (int i =0; i<Parameters.gridPointNumber; i++) {
            //for (int i = 0; i < Parameters.numberOfParticles; i+=(int)(Parameters.numberOfParticles/10000d)) {
//		for (int i = 0; i < 10; i++) {
            //System.out.println(positions1[i] + " " + velocities1[1]);
            g.setColor(Color.RED);
            g.fillOval((int)(i*Parameters.gridStep/Parameters.gridSize*((double)(getWidth())) - radius), density[i] - radius, 2 * radius, 2 * radius);
            g.setColor(Color.BLUE);
            g.fillOval((int)(i*Parameters.gridStep/Parameters.gridSize*((double)(getWidth())) - radius), potential[i] - radius, 2 * radius, 2 * radius);
            g.setColor(Color.GREEN);
            g.fillOval((int)(i*Parameters.gridStep/Parameters.gridSize*((double)(getWidth())) - radius), field[i] - radius, 2 * radius, 2 * radius);

        }
    }
}

