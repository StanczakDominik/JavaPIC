package pic;

import javax.swing.*;
import java.awt.*;


/**
 * Author: Mateusz
 */
class LowerRightPanel extends JPanel {

    String Language = "en";
    JButton Instructions, Authors, FieldCalculations, PlasmaPhysics, PhaseChart;

    LowerRightPanel(){

        setLayout(new GridLayout(5,0,10,10));

        PhaseChart = new JButton("Phase plot");
        this.add(PhaseChart);

        FieldCalculations = new JButton("Field calculations");
        this.add(FieldCalculations);

        PlasmaPhysics = new JButton("Plasma physics");
        this.add(PlasmaPhysics);

        Instructions = new JButton("Instructions");
        this.add(Instructions);

        Authors = new JButton("Authors");
        this.add(Authors);

        PhaseChart.addActionListener(e -> {
            TextFrameConstructor phase;
            if (Language.equals("en")) {
                phase = new TextFrameConstructor(3);
            } else {
                phase = new TextFrameConstructor(2);

            }
            phase.setVisible(true);
        });
        FieldCalculations.addActionListener(e -> {
            TextFrameConstructor field;
            if (Language.equals("en")) {
                field = new TextFrameConstructor(5);
            } else {
                field = new TextFrameConstructor(4);

            }
            field.setVisible(true);
        });
        PlasmaPhysics.addActionListener(e -> {
            TextFrameConstructor plasma;
            if (Language.equals("en")) {
                plasma = new TextFrameConstructor(1);
            } else {
                plasma = new TextFrameConstructor(0);

            }
            plasma.setVisible(true);
        });
        Instructions.addActionListener(e -> {
            TextFrameConstructor instructions;
            if (Language.equals("en")) {
                instructions = new TextFrameConstructor(7);
            } else {
                instructions = new TextFrameConstructor(6);

            }
            instructions.setVisible(true);
        });
        Authors.addActionListener(e -> {
            TextFrameConstructor auth;
            if (Language.equals("en")) {
                auth = new TextFrameConstructor(9);
            } else {
                auth = new TextFrameConstructor(8);

            }
            auth.setVisible(true);
        });
    }
}