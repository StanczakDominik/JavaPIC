package pic;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private XVPlotPanel phasePlot;
    private SimulationEngine engine;
    private FieldJFreeChartPlot fieldPlot;
    public MainFrame()
    {
        engine = new SimulationEngine();
        //TODO: get a separate thread for GUI
        //TODO: use SwingWorker and so on
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(2, 1));

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

    public XVPlotPanel getPhasePlot() {
        return phasePlot;
    }

    public SimulationEngine getEngine() {
        return engine;
    }

    public FieldJFreeChartPlot getFieldPlot() {
        return fieldPlot;
    }

    public static void main(String[] args)
    {
        MainFrame mainFrame = new MainFrame();
        for(int t=0; t<Parameters.iterations; t++)
        {
            if(Parameters.printIterations) System.out.println("Iteration " + t);
            mainFrame.getEngine().step();

            mainFrame.getPhasePlot().update(mainFrame.getEngine());
            mainFrame.getFieldPlot().update(mainFrame.getEngine());
        }
    }

}
