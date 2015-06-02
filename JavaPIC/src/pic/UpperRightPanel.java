package pic;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Dominik Stañczak
 */
public class UpperRightPanel extends JPanel {

    JLabel mass1label, mass2label, timeSteplabel, cellParticleDensitylabel, initialVelocitylabel, gridPointNumberlabel, fieldErrorTolerancelabel, perturbationAmplitudelabel, charge1label, charge2label;
    JButton runButton;
    private JTextField mass1, mass2, timeStep, cellParticleDensity, initialVelocity, gridPointNumber, fieldErrorTolerance, perturbationAmplitude, charge1, charge2;

    UpperRightPanel(MainFrame mainFrame) {
        setPreferredSize(new Dimension(100, 1000));
        setLayout(new BorderLayout());

        JPanel ParametersChangePanel = new JPanel(new GridLayout(10,2));
        this.add(ParametersChangePanel, BorderLayout.CENTER);

        mass1 = new JTextField("1");
        mass1.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(mass1);
        mass1label = new JLabel("Masa 1");
        ParametersChangePanel.add(mass1label);

        mass2 = new JTextField("1");
        mass2.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(mass2);
        mass2label = new JLabel("Masa 2");
        ParametersChangePanel.add(mass2label);

        charge1 = new JTextField("-10");
        charge1.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(charge1);
        charge1label = new JLabel("Lad. 1");
        ParametersChangePanel.add(charge1label);

        charge2 = new JTextField("-10");
        charge2.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(charge2);
        charge2label = new JLabel("Lad. 2");
        ParametersChangePanel.add(charge2label);

        timeStep = new JTextField("0.5");
        timeStep.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(timeStep);
        timeSteplabel = new JLabel("Krok T");
        ParametersChangePanel.add(timeSteplabel);

        cellParticleDensity = new JTextField("60");
        cellParticleDensity.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(cellParticleDensity);
        cellParticleDensitylabel = new JLabel("N/dx");
        ParametersChangePanel.add(cellParticleDensitylabel);

        initialVelocity = new JTextField("0.1");
        initialVelocity.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(initialVelocity);
        initialVelocitylabel = new JLabel("Vinit");
        ParametersChangePanel.add(initialVelocitylabel);

        gridPointNumber = new JTextField("64");
        gridPointNumber.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(gridPointNumber);
        gridPointNumberlabel = new JLabel("N siatki");
        ParametersChangePanel.add(gridPointNumberlabel);

        fieldErrorTolerance = new JTextField("1e-10");
        fieldErrorTolerance.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(fieldErrorTolerance);
        fieldErrorTolerancelabel = new JLabel("DeltaE");
        ParametersChangePanel.add(fieldErrorTolerancelabel);

        perturbationAmplitude = new JTextField("0.01");
        perturbationAmplitude.setPreferredSize(new Dimension(40, 20));
        ParametersChangePanel.add(perturbationAmplitude);
        perturbationAmplitudelabel = new JLabel("Scatter");
        ParametersChangePanel.add(perturbationAmplitudelabel);

        runButton = new JButton("Initiate");
        this.add(runButton, BorderLayout.SOUTH);
        runButton.addActionListener(e -> {
            mainFrame.parameters = generateParameters();
            mainFrame.restart();
        });
    }

    private double getDouble(JTextField field) {
        return Double.parseDouble(field.getText());
    }

    private int getInt(JTextField field) {
        return Integer.parseInt(field.getText());
    }

    private Parameters generateParameters() {
        return new Parameters(getDouble(timeStep), getInt(cellParticleDensity), getDouble(initialVelocity), getInt(gridPointNumber), getDouble(fieldErrorTolerance), getDouble(perturbationAmplitude), getDouble(charge1), getDouble(charge2), getDouble(mass1), getDouble(mass2));
    }
}
