package pic;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FieldJFreeChartPlot extends JPanel implements Runnable {
    private XYSeries dataSetDensity, dataSetPotential, dataSetField;
    private double x, y;
    private File file;
    private Scanner sc;
    private XYDataset xyDataset;
    private XYSeriesCollection xySeriesCollection;
    JFreeChart lineGraph;

    private ChartPanel panel;

    private SimulationEngine engine;

    public void update(SimulationEngine engine)
    {
        this.engine = engine;
        dataSetPotential.clear();
        dataSetDensity.clear();
        dataSetField.clear();
        for (int i=0; i<Parameters.gridPointNumber; i++)
        {
            dataSetDensity.add(engine.grid.gridPoints[i], engine.grid.density[i]);
            dataSetPotential.add(engine.grid.gridPoints[i], engine.grid.potential[i]);
            dataSetField.add(engine.grid.gridPoints[i], engine.grid.eField[i]);
        }

        //TODO: add legend
    }

    public FieldJFreeChartPlot(SimulationEngine engine)
    {
        setSize(1000, 500);
        dataSetDensity = new XYSeries("Density");
        dataSetPotential = new XYSeries("Potential");
        dataSetField = new XYSeries("Field");
        update(engine);
        xySeriesCollection = new XYSeriesCollection(dataSetDensity);
        xySeriesCollection.addSeries(dataSetPotential);
        xySeriesCollection.addSeries(dataSetField);
        //TODO: set xlim constant, ylim growing as needed
        lineGraph = ChartFactory.createXYLineChart("Fields", "X axis (grid)", "Field magnitude", xySeriesCollection, PlotOrientation.VERTICAL, true, true, false); //this should make a legend but doesn't work right now
        ChartPanel chartPanel = new ChartPanel(lineGraph);
        chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(chartPanel);
        chartPanel.setVisible(true);
        setVisible(true);
    }

    @Override
    public void run() {
        repaint();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            return;
        }
    }
}