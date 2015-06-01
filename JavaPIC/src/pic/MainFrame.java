package pic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Autor: Mateusz Kaczorek, Dominik Sta�czak
 * GUI o kt�rym jeszcze chyba nie b�d� wiele pisa�. :D
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
        //a tak bym to widzia� przy restartowaniu z r�nymi parametrami:
        //parameters = new Parameters(double timeStep, int cellParticleDensity, double initialVelocity, int gridPointNumber,
        //double fieldErrorTolerance, double perturbationAmplitude, double charge1, double charge2, double mass1, double mass2) {
        engine = new SimulationEngine(parameters);
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel ChartPanel = new JPanel(new GridLayout(2, 0));
        frame.add(ChartPanel, BorderLayout.CENTER);


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

        JPanel ButtonPanel = new JPanel(new GridLayout(3, 1));
        frame.add(ButtonPanel, BorderLayout.WEST);

        JPanel SimulationControl = new JPanel(new GridLayout(3, 1));
        ButtonPanel.add(SimulationControl);

        JButton Start = new JButton(">");
        SimulationControl.add(Start);

        JButton Stop = new JButton("||");
        SimulationControl.add(Stop);

        JButton Restart = new JButton("R");
        SimulationControl.add(Restart);

        JButton LanguageChange = new JButton("EN");
        ButtonPanel.add(LanguageChange);

        frame.setTitle("Two stream instability");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        //ten fragment kodu odpowiada za ca�� symulacj�
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


//          //Odkomentuj ten blok �eby przetestowa� play\pause
//            //ta linijka zatrzymuje animacj� - takie pause
//            loop.stop();
//
//            try {
//                //noinspection ResultOfMethodCallIgnored
//                System.in.read();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            //mo�na j� na bie��co z powrotem pu�ci�
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
