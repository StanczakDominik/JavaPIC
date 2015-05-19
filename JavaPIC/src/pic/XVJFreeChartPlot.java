package pic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;


public class XVJFreeChartPlot extends JPanel  {

    private XYSeries dataBeam1, dataBeam2;
    private double x, y;
    private File file;
    private Scanner sc;
    private XYDataset xyDataset;
    private XYSeriesCollection xySeriesCollection;
    private JFreeChart scatterGraph;
    private ValueAxis xAxis, yAxis;
    private ChartPanel panel;
    private SimulationEngine engine;

    public void update(SimulationEngine engine)
    {
        this.engine = engine;
        dataBeam1.clear();
        dataBeam2.clear();
        for (int i=0; i<Parameters.numberOfParticles; i++)
        {
            dataBeam1.add(engine.listOfSpecies[0].position[i], engine.listOfSpecies[0].velocity[i]);
            dataBeam2.add(engine.listOfSpecies[1].position[i], engine.listOfSpecies[1].velocity[i]);
        }
        repaint();
    }

    public XVJFreeChartPlot(SimulationEngine engine)
    {
        setSize(1000, 500);
        dataBeam1 = new XYSeries("Beam 1");
        dataBeam2 = new XYSeries("Beam 2");
        update(engine);
        xySeriesCollection = new XYSeriesCollection(dataBeam1);
        xySeriesCollection.addSeries(dataBeam2);
        scatterGraph = ChartFactory.createScatterPlot("Particles", "X position ", "V velocity", xySeriesCollection, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel chartPanel = new ChartPanel(scatterGraph);
        chartPanel.setPreferredSize(new Dimension(getWidth(), (int)(0.95*getHeight())));
        //FastScatterPlot scatterPlot = (FastScatterPlot) scatterGraph.getPlot();

        xAxis=((XYPlot) scatterGraph.getPlot()).getDomainAxis();
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(Parameters.gridSize);
        yAxis=((XYPlot) scatterGraph.getPlot()).getRangeAxis();
        yAxis.setLowerBound(-Parameters.plotMaxVMultiplier*Parameters.initialVelocity);
        yAxis.setUpperBound(Parameters.plotMaxVMultiplier*Parameters.initialVelocity);

        add(chartPanel);
        chartPanel.setVisible(true);
        setVisible(true);
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

