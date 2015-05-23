package pic;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private XVPlotPanel phasePlot;
//    private XVJFreeChartPlot phasePlot;
    private SimulationEngine engine;
    private FieldJFreeChartPlot fieldPlot;
    public MainFrame()
    {
        System.out.println(Parameters.numberOfParticles);
        engine = new SimulationEngine();
        //TODO: get a separate thread for GUI
        //TODO: use SwingWorker and so on
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(2, 1));

//        phasePlot = new XVJFreeChartPlot(engine);
        phasePlot = new XVPlotPanel(engine);
        frame.add(phasePlot);
        phasePlot.setSize(new Dimension(1000, 500));
        phasePlot.update(engine);
        phasePlot.setVisible(true);

        fieldPlot = new FieldJFreeChartPlot(engine);
        frame.add(fieldPlot);
        fieldPlot.setSize(new Dimension(1000, 500));
        fieldPlot.update(engine);
        fieldPlot.setVisible(true);

        frame.setTitle("Plots. Density - red, potential - blue, field - green");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
    }

//    public static void main(String[] args)
//    {
//        MainFrame mainFrame = new MainFrame();
//        CalculationWorkerAlternate worker = new CalculationWorkerAlternate(mainFrame);
//        worker.execute();
//    }

    public static void main(String[] args)
    {
        MainFrame mainFrame = new MainFrame();
        CalculationLoop loop = new CalculationLoop(mainFrame);
        loop.start();
    }

}
