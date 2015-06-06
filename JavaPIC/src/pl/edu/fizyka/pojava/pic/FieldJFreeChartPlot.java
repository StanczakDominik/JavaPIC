package pl.edu.fizyka.pojava.pic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: Dominik
 * Wykres pola elektrycznego oraz gęstości ładunku
 * w zależności od położenia na siatce
 * Oparty na JFreeCharcie
 * Metoda update czyści zbiory danych i wypełnia je obecnymi
 * Metoda saveChartAsPng zapisuje wykres fazowy do pliku FieldJFreeChart#.png, gdzie # jest numerem zdjęcia
 */

class FieldJFreeChartPlot extends JPanel {
    JFreeChart lineGraph;
    XYSeries dataSetDensity, dataSetField;
    private int snapshotsTaken = 0;

    public FieldJFreeChartPlot(SimulationEngine engine) {
        setSize(1000, 240);
        dataSetDensity = new XYSeries("Charge Density");
        dataSetField = new XYSeries("Electric Field");
        update(engine);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(dataSetDensity);
        xySeriesCollection.addSeries(dataSetField);
        lineGraph = ChartFactory.createXYLineChart("Fields Chart", "X axis (grid)", "Field magnitude", xySeriesCollection, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel chartPanel = new ChartPanel(lineGraph);
        chartPanel.setPreferredSize(new Dimension(getWidth(), (int) (0.95 * getHeight())));

        XYPlot xyPlot = (XYPlot) lineGraph.getPlot();
        ValueAxis xAxis = xyPlot.getDomainAxis();
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(Parameters.gridSize);
        ValueAxis yAxis = xyPlot.getRangeAxis();
        yAxis.setLowerBound(-Parameters.fieldPlotMaximumValue);
        yAxis.setUpperBound(Parameters.fieldPlotMaximumValue);

        add(chartPanel);
        chartPanel.setVisible(true);
        setVisible(true);
    }

    public void update(SimulationEngine engine)
    {
        dataSetDensity.clear();
        dataSetField.clear();
        for (int i = 0; i < engine.grid.gridPointNumber; i++)
        {
            dataSetDensity.add(engine.grid.gridPoints[i], engine.grid.density[i]);
            dataSetField.add(engine.grid.gridPoints[i], engine.grid.eField[i]);
        }
        repaint();
    }

    public void saveChartAsPng() {
        try {
            FileOutputStream output = new FileOutputStream("FieldJFreeChartPlot" + snapshotsTaken + ".png");
            ChartUtilities.writeChartAsPNG(output, lineGraph, 1000, 600);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        snapshotsTaken++;
    }

}