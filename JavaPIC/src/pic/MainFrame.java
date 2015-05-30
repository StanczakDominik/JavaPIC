package pic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Autor: jak dotπd Dominik ALE WEè SI  TU WPISZ OK? :)
 * GUI o ktÛrym jeszcze chyba nie bÍdÍ wiele pisa≥. :D
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
        //a tak bym to widzia≥ przy restartowaniu z rÛønymi parametrami:
        //parameters = new Parameters(double timeStep, int cellParticleDensity, double initialVelocity, int gridPointNumber,double fieldErrorTolerance);

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
        //ten fragment kodu odpowiada za ca≥π symulacjÍ
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


//          //Odkomentuj ten blok øeby przetestowaÊ play\pause
//            //ta linijka zatrzymuje animacjÍ - takie pause
//            loop.stop();
//
//            try {
//                //noinspection ResultOfMethodCallIgnored
//                System.in.read();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            //moøna jπ na bieøπco z powrotem puúciÊ
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

    public void takeSnapshots() {
        fieldPlot.saveChartAsPng();
        energyPlot.saveChartAsPng();
    }

}
