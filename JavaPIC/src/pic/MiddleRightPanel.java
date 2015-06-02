package pic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Autor: Mateusz Kaczorek
 */
public class MiddleRightPanel extends JPanel {


    MiddleRightPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(1, 1));

        JPanel SimulationControl = new JPanel(new GridLayout(5, 1, 0, 20));
        this.add(SimulationControl);

        JButton StartAndStop = new JButton("||");
        SimulationControl.add(StartAndStop);
        StartAndStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(StartAndStop.getText()=="||"){

                    StartAndStop.setText(">");
                    mainFrame.loop.stop();
                }
                else{
                    StartAndStop.setText("||");
                    mainFrame.loop.start();
                }
            }
        });

        JButton Restart = new JButton("Restart");
        SimulationControl.add(Restart);
        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.restart();
            }
        });


        JButton LanguageChange = new JButton("PL");
        SimulationControl.add(LanguageChange);

        JButton ScreenCapture = new JButton("Print Screen");
        SimulationControl.add(ScreenCapture);

        LanguageChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (LanguageChange.getText().equals("PL")) {
                    LanguageChange.setText("EN");
                    ScreenCapture.setText("Zdjecie");

                    mainFrame.energyPlot.lineGraph.setTitle("Wykres Energii");
                    mainFrame.fieldPlot.lineGraph.setTitle("Wykres Pol");
                    mainFrame.UpperRightPanel.runButton.setText("Inicjuj");

                    mainFrame.energyPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Czas");
                    mainFrame.energyPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Energia");
                    mainFrame.fieldPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Po³o¿enie na siatce");
                    mainFrame.fieldPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Amplituda");

                    mainFrame.UpperRightPanel.mass1label.setText("Masa 1");
                    mainFrame.UpperRightPanel.mass2label.setText("Masa 2");
                    mainFrame.UpperRightPanel.charge1label.setText("Lad 1");
                    mainFrame.UpperRightPanel.charge2label.setText("Lad 2");
                    mainFrame.UpperRightPanel.timeSteplabel.setText("Krok T");
                    mainFrame.UpperRightPanel.cellParticleDensitylabel.setText("N/dx");
                    mainFrame.UpperRightPanel.initialVelocitylabel.setText("V pocz.");
                    mainFrame.UpperRightPanel.gridPointNumberlabel.setText("N Siatki");
                    mainFrame.UpperRightPanel.fieldErrorTolerancelabel.setText("DeltaE");
                    mainFrame.UpperRightPanel.perturbationAmplitudelabel.setText("Scatter");

                }
                else{
                    LanguageChange.setText("PL");
                    ScreenCapture.setText("PrintScreen");

                    mainFrame.energyPlot.lineGraph.setTitle("Energies Chart");
                    mainFrame.fieldPlot.lineGraph.setTitle("Fields Chart");
                    mainFrame.UpperRightPanel.runButton.setText("Initiate");

                    mainFrame.energyPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Time");
                    mainFrame.energyPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Energy");
                    mainFrame.fieldPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("X Axis(grid)");
                    mainFrame.fieldPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Field Magnitude");

                    mainFrame.UpperRightPanel.mass1label.setText("Mass 1");
                    mainFrame.UpperRightPanel.mass2label.setText("Mass 2");
                    mainFrame.UpperRightPanel.charge1label.setText("Charge 1");
                    mainFrame.UpperRightPanel.charge2label.setText("Charge 2");
                    mainFrame.UpperRightPanel.timeSteplabel.setText("T Step");
                    mainFrame.UpperRightPanel.cellParticleDensitylabel.setText("Density");
                    mainFrame.UpperRightPanel.initialVelocitylabel.setText("Init. Vel.");
                    mainFrame.UpperRightPanel.gridPointNumberlabel.setText("Grid N");
                    mainFrame.UpperRightPanel.fieldErrorTolerancelabel.setText("DeltaE");
                    mainFrame.UpperRightPanel.perturbationAmplitudelabel.setText("Scatter");
                }
            }
        });

        ScreenCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.takeSnapshots();
            }
        });

    }

}
