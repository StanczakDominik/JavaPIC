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

        timeStep = new JTextField("0.5");
        timeStep.setPreferredSize(new Dimension(40, 20));
        this.add(timeStep);
        JLabel timeSteplabel = new JLabel("Krok T");
        this.add(timeSteplabel);

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

    Parameters generateParameters() {
        return new Parameters();
        //return new Parameters(5e-1d, 60, 0.1d, 64, 1e-10, 1e-2d, -10, -10, Double.parseDouble(mass1.getText()), Double.parseDouble(mass2.getText()));
        //To siê wykrzacza w bardzo zabawny sposób
    }
}
