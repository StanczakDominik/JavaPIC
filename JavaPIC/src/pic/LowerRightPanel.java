package pic;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Author: Mateusz
 */
public class LowerRightPanel extends JPanel {

    JButton Instructions, Designers, FieldCalculations, PlasmaPhysics, PhaseChart;

    LowerRightPanel(){

        setLayout(new GridLayout(5,0,10,10));

        PhaseChart = new JButton("Phase Chart");
        this.add(PhaseChart);

        FieldCalculations = new JButton("Field Calculations");
        this.add(FieldCalculations);

        PlasmaPhysics = new JButton("Plasma Physics");
        this.add(PlasmaPhysics);

        Instructions = new JButton("Instructions");
        this.add(Instructions);

        Designers = new JButton("Designers");
        this.add(Designers);

        PhaseChart.addActionListener(e -> {
            TextFrameConstructor textFrameConstructor = new TextFrameConstructor("Fizyka Plazmy");
            textFrameConstructor.setVisible(true);
        });
    }
}
