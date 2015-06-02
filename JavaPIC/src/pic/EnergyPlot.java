package pic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Autor: Dominik
 * Klasa oparta na JFreeCharcie odpowiadaj¹ca za wykres energii symulacji
 * Nale¿y zauwa¿yæ, ¿e model PIC przez u¿yte przybli¿enia nawet nie powinien zachowywaæ energii tak, jak robi to np.
 * algorytm leapfrog (który zachowuje j¹ w 100% przez swoj¹ symetriê czasow¹).
 * Generalnie wszystko dzia³a podobnie jak w FieldPlocie.
 * Metoda saveChartAsPng zapisuje wykres fazowy do pliku EnergyPlot#.png, gdzie # jest numerem zdjêcia
 */
class EnergyPlot extends JPanel {
    JFreeChart lineGraph;
    private XYSeries kineticEnergy1, kineticEnergy2, fieldEnergy, totalEnergy;
    private int snapshotsTaken = 0;

    public EnergyPlot() {
        setSize(1000, 240);
        fieldEnergy = new XYSeries("Field energy");
        kineticEnergy1 = new XYSeries("Species 1 kinetic energy");
        kineticEnergy2 = new XYSeries("Species 2 kinetic energy");
        totalEnergy = new XYSeries("Total energy");
//        update(engine);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(fieldEnergy);
        xySeriesCollection.addSeries(kineticEnergy1);
        xySeriesCollection.addSeries(kineticEnergy2);
        xySeriesCollection.addSeries(totalEnergy);
        lineGraph = ChartFactory.createXYLineChart("Wykres Energii", "Time", "Energy", xySeriesCollection, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel chartPanel = new ChartPanel(lineGraph);
        chartPanel.setPreferredSize(new Dimension(getWidth(), (int) (0.95 * getHeight())));

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

    public void update(SimulationEngine engine, int iteration)
    {
        fieldEnergy.add(iteration * engine.parameters.timeStep, engine.grid.totalFieldEnergy);
        kineticEnergy1.add(iteration * engine.parameters.timeStep, engine.listOfSpecies[0].totalKineticEnergy);
        kineticEnergy2.add(iteration * engine.parameters.timeStep, engine.listOfSpecies[1].totalKineticEnergy);
        totalEnergy.add(iteration * engine.parameters.timeStep, engine.grid.totalFieldEnergy + engine.listOfSpecies[0].totalKineticEnergy + engine.listOfSpecies[1].totalKineticEnergy);
        repaint();
    }

    public void clear() {
        fieldEnergy.clear();
        kineticEnergy2.clear();
        kineticEnergy1.clear();
        totalEnergy.clear();
    }

    public void saveChartAsPng() {
        try {
            FileOutputStream output = new FileOutputStream("EnergyPlot" + snapshotsTaken + ".png");
            ChartUtilities.writeChartAsPNG(output, lineGraph, 1000, 600);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        snapshotsTaken++;
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