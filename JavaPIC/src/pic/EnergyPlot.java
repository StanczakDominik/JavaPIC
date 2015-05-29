package pic;

import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
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

public class EnergyPlot extends JPanel {
    private XYSeries kineticEnergy1, kineticEnergy2, fieldEnergy, totalEnergy;
    private double x, y;
    private File file;
    private Scanner sc;
    private XYDataset xyDataset;
    private XYSeriesCollection xySeriesCollection;
    private JFreeChart lineGraph;
    private ValueAxis xAxis, yAxis;
    private ChartPanel panel;

    private SimulationEngine engine;

    public void update(SimulationEngine engine, int iteration)
    {
        this.engine = engine;
        fieldEnergy.add(iteration*Parameters.timeStep, engine.grid.totalFieldEnergy);
        kineticEnergy1.add(iteration*Parameters.timeStep, engine.listOfSpecies[0].totalKineticEnergy);
        kineticEnergy2.add(iteration*Parameters.timeStep, engine.listOfSpecies[1].totalKineticEnergy);
        totalEnergy.add(iteration*Parameters.timeStep, engine.grid.totalFieldEnergy + engine.listOfSpecies[0].totalKineticEnergy+engine.listOfSpecies[1].totalKineticEnergy);
        repaint();
    }

    public EnergyPlot(SimulationEngine engine)
    {
        setSize(1000, 300);
        fieldEnergy = new XYSeries("Field energy");
        kineticEnergy1 = new XYSeries("Species 1 kinetic energy");
        kineticEnergy2 = new XYSeries("Species 2 kinetic energy");
        totalEnergy = new XYSeries("Total energy");
//        update(engine);
        xySeriesCollection = new XYSeriesCollection(fieldEnergy);
        xySeriesCollection.addSeries(kineticEnergy1);
        xySeriesCollection.addSeries(kineticEnergy2);
        xySeriesCollection.addSeries(totalEnergy);
        lineGraph = ChartFactory.createXYLineChart("Energies", "Time", "Energy", xySeriesCollection, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel chartPanel = new ChartPanel(lineGraph);
        chartPanel.setPreferredSize(new Dimension(getWidth(), (int)(0.95*getHeight())));

//        XYPlot xyPlot = (XYPlot) lineGraph.getPlot();
//        xAxis=xyPlot.getDomainAxis();
//        xAxis.setLowerBound(0);
//        xAxis.setUpperBound(Parameters.gridSize);
//        yAxis=xyPlot.getRangeAxis();
//        yAxis.setLowerBound(-Parameters.fieldPlotMaximumValue);
//        yAxis.setUpperBound(Parameters.fieldPlotMaximumValue);


        add(chartPanel);
        chartPanel.setVisible(true);
        setVisible(true);
    }

//    @Override
//    public void run() {
//        repaint();
//        try {
//            Thread.sleep(30);
//        } catch (InterruptedException e) {
//            return;
//        }
//    }
}