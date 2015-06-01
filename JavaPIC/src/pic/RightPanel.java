package pic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Autor: Dominik Stañczak
 */
public class RightPanel extends JPanel {

    JTextField mass1, mass2, timeStep, cellParticleDensity, initialVelocity, gridPointNumber, fieldErrorTolerance, perturbationAmplitude, charge1, charge2;

    RightPanel(MainFrame mainFrame) {
        setPreferredSize(new Dimension(100, 1000));
        setLayout(new FlowLayout());

        mass1 = new JTextField("1");
        mass1.setPreferredSize(new Dimension(40, 20));
        this.add(mass1);
        JLabel mass1label = new JLabel("Masa 1");
        this.add(mass1label);

        mass2 = new JTextField("1");
        mass2.setPreferredSize(new Dimension(40, 20));
        this.add(mass2);
        JLabel mass2label = new JLabel("Masa 2");
        this.add(mass2label);

        charge1 = new JTextField("-10");
        charge1.setPreferredSize(new Dimension(40, 20));
        this.add(charge1);
        JLabel charge1label = new JLabel("Lad. 1");
        this.add(charge1label);

        charge2 = new JTextField("-10");
        charge2.setPreferredSize(new Dimension(40, 20));
        this.add(charge2);
        JLabel charge2label = new JLabel("Lad. 2");
        this.add(charge2label);

        timeStep = new JTextField("0.5");
        timeStep.setPreferredSize(new Dimension(40, 20));
        this.add(timeStep);
        JLabel timeSteplabel = new JLabel("Krok T");
        this.add(timeSteplabel);

        cellParticleDensity = new JTextField("60");
        cellParticleDensity.setPreferredSize(new Dimension(40, 20));
        this.add(cellParticleDensity);
        JLabel cellParticleDensitylabel = new JLabel("N/dx");
        this.add(cellParticleDensitylabel);

        initialVelocity = new JTextField("0.1");
        initialVelocity.setPreferredSize(new Dimension(40, 20));
        this.add(initialVelocity);
        JLabel initialVelocitylabel = new JLabel("Vinit");
        this.add(initialVelocitylabel);

        gridPointNumber = new JTextField("64");
        gridPointNumber.setPreferredSize(new Dimension(40, 20));
        this.add(gridPointNumber);
        JLabel gridPointNumberlabel = new JLabel("N siatki");
        this.add(gridPointNumberlabel);

        fieldErrorTolerance = new JTextField("1e-10");
        fieldErrorTolerance.setPreferredSize(new Dimension(40, 20));
        this.add(fieldErrorTolerance);
        JLabel fieldErrorTolerancelabel = new JLabel("DeltaE");
        this.add(fieldErrorTolerancelabel);

        perturbationAmplitude = new JTextField("0.01");
        perturbationAmplitude.setPreferredSize(new Dimension(40, 20));
        this.add(perturbationAmplitude);
        JLabel perturbationAmplitudelabel = new JLabel("Scatter");
        this.add(perturbationAmplitudelabel);

        JButton runButton = new JButton("Hit it!");
        this.add(runButton);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.parameters = generateParameters();
                mainFrame.restart();
            }
        });
    }

    double getDouble(JTextField field) {
        return Double.parseDouble(field.getText());
    }

    int getInt(JTextField field) {
        return Integer.parseInt(field.getText());
    }

    Parameters generateParameters() {
        return new Parameters(getDouble(timeStep), getInt(cellParticleDensity), getDouble(initialVelocity), getInt(gridPointNumber), getDouble(fieldErrorTolerance), getDouble(perturbationAmplitude), getDouble(charge1), getDouble(charge2), getDouble(mass1), getDouble(mass2));
    }
}
