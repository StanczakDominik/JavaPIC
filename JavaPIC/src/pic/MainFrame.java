package pic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Autor: Mateusz Kaczorek, Dominik Stañczak
 * GUI o którym jeszcze chyba nie bêdê wiele pisa³. :D
 */
class MainFrame {

    int iteration = 0;
    private XVPlotPanel phasePlot;
    private SimulationEngine engine;
    private FieldJFreeChartPlot fieldPlot;
    private EnergyPlot energyPlot;

    private MainFrame()
    {

        //ma wbudowane defaultowe parametry
        Parameters parameters = new Parameters();
        //a tak bym to widzia³ przy restartowaniu z ró¿nymi parametrami:
        //parameters = new Parameters(double timeStep, int cellParticleDensity, double initialVelocity, int gridPointNumber,
        //double fieldErrorTolerance, double perturbationAmplitude, double charge1, double charge2, double mass1, double mass2) {
        engine = new SimulationEngine(parameters);
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

        energyPlot = new EnergyPlot();
        frame.add(energyPlot);
        energyPlot.setSize(new Dimension(1500, 300));
        energyPlot.setVisible(true);

        frame.setTitle("Two stream instability");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        //ten fragment kodu odpowiada za ca³¹ symulacjê
        //czy calculationloop jest do przerobienia?
        CalculationLoop loop = new CalculationLoop(mainFrame);
        loop.start();
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
