package pic;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Mateusz Kaczorek, Dominik Stañczak
 * GUI o którym jeszcze chyba nie bêdê wiele pisa³. :D
 */
class MainFrame {

    UpperRightPanel upperRightPanel;
    MiddleRightPanel middleRightPanel;
    LowerRightPanel lowerRightPanel;
    int iteration = 0;
    CalculationLoop loop;
    Parameters parameters;
    FieldJFreeChartPlot fieldPlot;
    EnergyPlot energyPlot;
    private SimulationEngine engine;
    private XVPlotPanel phasePlot;

    private MainFrame()
    {

        parameters = new Parameters();
        engine = new SimulationEngine(parameters);
        JFrame frame = new JFrame();
        frame.setSize(1100, 1000);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel ChartPanel = new JPanel(new GridLayout(2, 0));
        frame.add(ChartPanel, BorderLayout.CENTER);

        JPanel RightPanel = new JPanel(new GridLayout(3,0));
        frame.add(RightPanel, BorderLayout.EAST);

        phasePlot = new XVPlotPanel(engine);
        ChartPanel.add(phasePlot);
        phasePlot.setSize(new Dimension(1500, 300));
        phasePlot.update(engine);
        phasePlot.setVisible(true);

        JPanel EnergyAndFieldPanel = new JPanel(new GridLayout(2, 0));
        ChartPanel.add(EnergyAndFieldPanel);

        fieldPlot = new FieldJFreeChartPlot(engine);
        EnergyAndFieldPanel.add(fieldPlot);
        fieldPlot.setSize(new Dimension(1500, 300));
        fieldPlot.update(engine);
        fieldPlot.setVisible(true);

        energyPlot = new EnergyPlot();
        EnergyAndFieldPanel.add(energyPlot);
        energyPlot.setSize(new Dimension(1500, 300));
        energyPlot.setVisible(true);


        upperRightPanel = new UpperRightPanel(this);
        RightPanel.add(upperRightPanel, BorderLayout.EAST);

        middleRightPanel = new MiddleRightPanel(this);
        RightPanel.add(middleRightPanel, BorderLayout.EAST);

        lowerRightPanel = new LowerRightPanel();
        RightPanel.add(lowerRightPanel, BorderLayout.EAST);

        frame.setTitle("Two stream instability");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loop = new CalculationLoop(this);
        loop.start();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

    public void restart() {
        loop.stop();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iteration = 0;
        engine = new SimulationEngine(parameters);
        energyPlot.clear();
        phasePlot.changeN(parameters);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loop = new CalculationLoop(this);
        loop.start();
    }

    public SimulationEngine getEngine() {
        return engine;
    }

    public void updatePlots(SimulationEngine data)
    {
        phasePlot.update(data);
        fieldPlot.update(data);
        energyPlot.update(data, iteration);
    }

    public void takeSnapshots() {
        fieldPlot.saveChartAsPng();
        energyPlot.saveChartAsPng();
        phasePlot.saveChartAsPng();
    }

}
