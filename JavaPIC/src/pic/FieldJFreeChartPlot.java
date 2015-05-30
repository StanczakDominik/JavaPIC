package pic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Autor: Dominik
 * Wykres pola elektrycznego, gêstoœci ³adunku oraz potencja³u (potencja³ ma tendencjê do bycia wysokim i znikania) w
 * zale¿noœci od po³o¿enia na siatce
 * Oparty na JFreeCharcie
 * Metoda update czyœci zbiory danych i wype³nia je obecnymi
 */

class FieldJFreeChartPlot extends JPanel {
    private XYSeries dataSetDensity, dataSetPotential, dataSetField;

    public FieldJFreeChartPlot(SimulationEngine engine) {
        setSize(1000, 300);
        dataSetDensity = new XYSeries("Density");
        dataSetPotential = new XYSeries("Potential");
        dataSetField = new XYSeries("Field");
        update(engine);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(dataSetDensity);
        xySeriesCollection.addSeries(dataSetPotential);
        xySeriesCollection.addSeries(dataSetField);
        JFreeChart lineGraph = ChartFactory.createXYLineChart("Fields", "X axis (grid)", "Field magnitude", xySeriesCollection, PlotOrientation.VERTICAL, true, true, true);
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
        dataSetPotential.clear();
        dataSetDensity.clear();
        dataSetField.clear();
        for (int i = 0; i < engine.grid.gridPointNumber; i++)
        {
            dataSetDensity.add(engine.grid.gridPoints[i], engine.grid.density[i]);
            dataSetPotential.add(engine.grid.gridPoints[i], engine.grid.potential[i]);
            dataSetField.add(engine.grid.gridPoints[i], engine.grid.eField[i]);
        }
        repaint();
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