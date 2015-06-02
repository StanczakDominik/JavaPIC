package pic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/** Author: Mateusz Kaczorek
 *
 */
class LanguageChangeListener implements ActionListener {
    private final MainFrame mainFrame;

    public LanguageChangeListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e) {
       
        if (mainFrame.middleRightPanel.LanguageChange.getText().equals("PL")) {

            mainFrame.middleRightPanel.LanguageChange.setText("EN");
            mainFrame.middleRightPanel.ScreenCapture.setText("Zdjecie");

            mainFrame.energyPlot.lineGraph.setTitle("Wykres Energii");
            mainFrame.fieldPlot.lineGraph.setTitle("Wykres P�l");
            mainFrame.upperRightPanel.runButton.setText("Inicjuj");

            mainFrame.energyPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Czas");
            mainFrame.energyPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Energia");
            mainFrame.energyPlot.kineticEnergy1.setKey("Energia kinetyczna 1");
            mainFrame.energyPlot.kineticEnergy2.setKey("Energia kinetyczna 2");
            mainFrame.energyPlot.fieldEnergy.setKey("Energia pola elektrycznego");
            mainFrame.energyPlot.totalEnergy.setKey("Ca�kowita energia");

            mainFrame.fieldPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Po�o�enie na siatce");
            mainFrame.fieldPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Amplituda");
            mainFrame.fieldPlot.dataSetField.setKey("Pole elektryczne");
            mainFrame.fieldPlot.dataSetDensity.setKey("G�sto�� �adunku");

            mainFrame.upperRightPanel.mass1label.setText("Masa 1");
            mainFrame.upperRightPanel.mass2label.setText("Masa 2");
            mainFrame.upperRightPanel.charge1label.setText("Lad 1");
            mainFrame.upperRightPanel.charge2label.setText("Lad 2");
            mainFrame.upperRightPanel.timeSteplabel.setText("Krok T");
            mainFrame.upperRightPanel.cellParticleDensitylabel.setText("N/dx");
            mainFrame.upperRightPanel.initialVelocitylabel.setText("V pocz.");
            mainFrame.upperRightPanel.gridPointNumberlabel.setText("N Siatki");
            mainFrame.upperRightPanel.fieldErrorTolerancelabel.setText("DeltaE");
            mainFrame.upperRightPanel.perturbationAmplitudelabel.setText("Scatter");

        } else {
            mainFrame.middleRightPanel.LanguageChange.setText("PL");
            mainFrame.middleRightPanel.ScreenCapture.setText("PrintScreen");

            mainFrame.energyPlot.lineGraph.setTitle("Energies Chart");
            mainFrame.fieldPlot.lineGraph.setTitle("Fields Chart");
            mainFrame.upperRightPanel.runButton.setText("Initiate");

            mainFrame.energyPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Time");
            mainFrame.energyPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Energy");
            mainFrame.energyPlot.kineticEnergy1.setKey("Species 1 kinetic energy");
            mainFrame.energyPlot.kineticEnergy2.setKey("Species 2 kinetic energy");
            mainFrame.energyPlot.fieldEnergy.setKey("Field energy");
            mainFrame.energyPlot.totalEnergy.setKey("Total energy");

            mainFrame.fieldPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("X Axis(grid)");
            mainFrame.fieldPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Field Magnitude");
            mainFrame.fieldPlot.dataSetField.setKey("Electric field");
            mainFrame.fieldPlot.dataSetDensity.setKey("Charge density");

            mainFrame.upperRightPanel.mass1label.setText("Mass 1");
            mainFrame.upperRightPanel.mass2label.setText("Mass 2");
            mainFrame.upperRightPanel.charge1label.setText("Charge 1");
            mainFrame.upperRightPanel.charge2label.setText("Charge 2");
            mainFrame.upperRightPanel.timeSteplabel.setText("T Step");
            mainFrame.upperRightPanel.cellParticleDensitylabel.setText("Density");
            mainFrame.upperRightPanel.initialVelocitylabel.setText("Init. Vel.");
            mainFrame.upperRightPanel.gridPointNumberlabel.setText("Grid N");
            mainFrame.upperRightPanel.fieldErrorTolerancelabel.setText("DeltaE");
            mainFrame.upperRightPanel.perturbationAmplitudelabel.setText("Scatter");
        }
    }
}

