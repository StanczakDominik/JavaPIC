package pl.edu.fizyka.pojava.pic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Author: Mateusz Kaczorek
 *
 * Środkowy panel z przyciskami odpowiadającymi za robienie zdjęć, zmianę języka, restartowanie całej symulacji
 *              bez zmiany parametrów.
 */
class MiddleRightPanel extends JPanel {

    JButton languageChange, screenCapture, plotsToTxtExport;
    int NumberOfFiles=0;

    MiddleRightPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(1, 1));

        JPanel SimulationControl = new JPanel(new GridLayout(5, 1, 0, 20));
        this.add(SimulationControl);

        JButton StartAndStop = new JButton("||");
        SimulationControl.add(StartAndStop);
        StartAndStop.addActionListener(e -> {

            if (StartAndStop.getText().equals("||")) {

                StartAndStop.setText(">");
                mainFrame.loop.stop();
            } else {
                StartAndStop.setText("||");
                mainFrame.loop.start();
            }
        });

        JButton Restart = new JButton("Restart");
        SimulationControl.add(Restart);
        Restart.addActionListener(e -> mainFrame.restart());

        languageChange = new JButton("PL");
        SimulationControl.add(languageChange);

        screenCapture = new JButton("Print Screen");
        SimulationControl.add(screenCapture);

        plotsToTxtExport = new JButton("Export Data");
        SimulationControl.add(plotsToTxtExport);

        languageChange.addActionListener(new LanguageChangeListener(mainFrame));

        screenCapture.addActionListener(e -> mainFrame.takeSnapshots());

        ActionListener plotsToTxtExportButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PhaseToTxt phaseFile = new PhaseToTxt();
                    phaseFile.openFile(NumberOfFiles);
                    phaseFile.addData(mainFrame.engine.velocities1, mainFrame.engine.velocities2, mainFrame.engine.positions1, mainFrame.engine.positions2, mainFrame.parameters.numberOfParticles);
                    phaseFile.closeFile();
                }catch(Exception IO){
                    IO.printStackTrace();
                }

                try{
                    EnergyToTxt energyFile = new EnergyToTxt();
                    energyFile.openFile(NumberOfFiles);
                    energyFile.addData(mainFrame.energyPlot.fieldEnergyToExport,mainFrame.energyPlot.kineticEnergyToExport1,mainFrame.energyPlot.kineticEnergyToExport2,mainFrame.energyPlot.totalEnergyToExport,mainFrame.energyPlot.timeStepToExport);
                    energyFile.closeFile();
                }catch(Exception IO2){
                    IO2.printStackTrace();
                }
                try{
                    FieldToTxt fieldFile = new FieldToTxt();
                    fieldFile.openFile(NumberOfFiles);
                    fieldFile.addData(mainFrame.engine.grid.gridPoints,mainFrame.engine.grid.eField,mainFrame.engine.grid.density,mainFrame.engine.grid.gridPointNumber);
                    fieldFile.closeFile();

                }catch(Exception IO3){
                    IO3.printStackTrace();
                }
                NumberOfFiles++;
            }
        };
        plotsToTxtExport.addActionListener(plotsToTxtExportButtonListener);




    }

}
