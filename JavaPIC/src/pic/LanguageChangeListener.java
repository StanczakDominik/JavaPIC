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

        if (mainFrame.middleRightPanel.languageChange.getText().equals("PL")) {

            mainFrame.middleRightPanel.languageChange.setText("EN");
            mainFrame.middleRightPanel.screenCapture.setText("Zdjecie");

            mainFrame.energyPlot.lineGraph.setTitle("Wykres Energii");
            mainFrame.fieldPlot.lineGraph.setTitle("Wykres Pól");
            mainFrame.upperRightPanel.runButton.setText("Inicjuj");

            mainFrame.energyPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Czas");
            mainFrame.energyPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Energia");
            mainFrame.energyPlot.kineticEnergy1.setKey("Energia kinetyczna 1");
            mainFrame.energyPlot.kineticEnergy2.setKey("Energia kinetyczna 2");
            mainFrame.energyPlot.fieldEnergy.setKey("Energia pola elektrycznego");
            mainFrame.energyPlot.totalEnergy.setKey("Całkowita energia");

            mainFrame.fieldPlot.lineGraph.getXYPlot().getDomainAxis().setLabel("Położenie na siatce");
            mainFrame.fieldPlot.lineGraph.getXYPlot().getRangeAxis().setLabel("Amplituda");
            mainFrame.fieldPlot.dataSetField.setKey("Pole elektryczne");
            mainFrame.fieldPlot.dataSetDensity.setKey("Gęstość ładunku");

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

            mainFrame.lowerRightPanel.language = "pl";
            mainFrame.lowerRightPanel.phaseChart.setText("Wykres fazowy");
            mainFrame.lowerRightPanel.authors.setText("Autorzy");
            mainFrame.lowerRightPanel.instructions.setText("Instrukcja");
            mainFrame.lowerRightPanel.fieldCalculations.setText("Obliczanie pól");
            mainFrame.lowerRightPanel.plasmaPhysics.setText("Fizyka plazmy");

        } else {
            mainFrame.middleRightPanel.languageChange.setText("PL");
            mainFrame.middleRightPanel.screenCapture.setText("PrintScreen");

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

            mainFrame.lowerRightPanel.language = "en";
            mainFrame.lowerRightPanel.phaseChart.setText("Phase plot");
            mainFrame.lowerRightPanel.authors.setText("Authors");
            mainFrame.lowerRightPanel.instructions.setText("Instructions");
            mainFrame.lowerRightPanel.fieldCalculations.setText("Field calculations");
            mainFrame.lowerRightPanel.plasmaPhysics.setText("Plasma physics");
        }
    }
}

