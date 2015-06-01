package pic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

/**
 * Autor: Mateusz Kaczorek, Dominik Stañczak
 * GUI o którym jeszcze chyba nie bêdê wiele pisa³. :D
 */
class MainFrame {

    int iteration = 0;
    SimulationEngine engine;
    CalculationLoop loop;
    Parameters parameters;
    private XVPlotPanel phasePlot;
    private FieldJFreeChartPlot fieldPlot;
    private EnergyPlot energyPlot;
    public MiddleRightPanel MiddleRightPanel;
    public UpperRightPanel UpperRightPanel;

    private MainFrame()
    {

        //ma wbudowane defaultowe parametry
        parameters = new Parameters();
        //a tak bym to widzia³ przy restartowaniu z ró¿nymi parametrami:
        //parameters = new Parameters(double timeStep, int cellParticleDensity, double initialVelocity, int gridPointNumber,
        //double fieldErrorTolerance, double perturbationAmplitude, double charge1, double charge2, double mass1, double mass2) {
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


        UpperRightPanel = new UpperRightPanel(this);
        //settingsPanel.setPreferredSize(new Dimension(100, 1000));
        RightPanel.add(UpperRightPanel, BorderLayout.EAST);

        MiddleRightPanel = new MiddleRightPanel(this);
        RightPanel.add(MiddleRightPanel, BorderLayout.EAST);

        frame.setTitle("Two stream instability");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loop = new CalculationLoop(this);
        loop.start();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        //ten fragment kodu odpowiada za ca³¹ symulacjê
        //czy calculationloop jest do przerobienia?
        ///

        //Zrobiony na szybko schemat zatrzymywania

        //noinspection InfiniteLoopStatement
        while (true) {

            //reaguje na enter w konsoli
            try {
                //noinspection ResultOfMethodCallIgnored
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Robi screenshoty
            mainFrame.takeSnapshots();


//          //Odkomentuj ten blok ¿eby przetestowaæ play\pause
//            //ta linijka zatrzymuje animacjê - takie pause
//            loop.stop();
//
//            try {
//                //noinspection ResultOfMethodCallIgnored
//                System.in.read();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            //mo¿na j¹ na bie¿¹co z powrotem puœciæ
//            loop.start();
//            //restartu jeszcze nie mam
        }

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

    private void takeSnapshots() {
        fieldPlot.saveChartAsPng();
        energyPlot.saveChartAsPng();
        phasePlot.saveChartAsPng();
    }

}
