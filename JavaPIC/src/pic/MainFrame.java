package pic;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private XVPlotPanel phasePlot;
    private SimulationEngine engine;
    private FieldJFreeChartPlot fieldPlot;
    private EnergyPlot energyPlot;
    protected int iteration = 0;
    public MainFrame()
    {
        System.out.println(Parameters.numberOfParticles);
        engine = new SimulationEngine();
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(3, 1));

        phasePlot = new XVPlotPanel(engine);
        frame.add(phasePlot);
        phasePlot.setSize(new Dimension(1500, 300));
        phasePlot.update(engine);
        phasePlot.setVisible(true);

        fieldPlot = new FieldJFreeChartPlot(engine);
        frame.add(fieldPlot);
        fieldPlot.setSize(new Dimension(1500, 300));
        fieldPlot.update(engine);
        fieldPlot.setVisible(true);

        energyPlot = new EnergyPlot(engine);
        frame.add(energyPlot);
        energyPlot.setSize(new Dimension(1500, 300));
        energyPlot.setVisible(true);

        frame.setTitle("Two stream instability");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public SimulationEngine getEngine() {
        return engine;
    }

    public void setEngine(SimulationEngine engine) {
        this.engine = engine;
    }

    public void updatePlots(SimulationEngine data)
    {
        phasePlot.update(data);
        fieldPlot.update(data);
        energyPlot.update(data, iteration);
    }


    public static void main(String[] args)
    {
        MainFrame mainFrame = new MainFrame();
        CalculationLoop loop = new CalculationLoop(mainFrame);
        loop.start();
    }

}
