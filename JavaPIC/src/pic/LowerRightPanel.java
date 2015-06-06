package pic;

import javax.swing.*;
import java.awt.*;


/**
 * Author: Mateusz
 * Panel zawierający przyciski z wytłumaczeniami, co tu się w tym programie w ogóle dzieje.
 *
 * addListeners nadaje sens klikaniu w te przyciski.
 */
class LowerRightPanel extends JPanel {

    String language = "en";
    JButton instructions, authors, fieldCalculations, plasmaPhysics, phaseChart;

    LowerRightPanel(){

        setLayout(new GridLayout(5,0,10,10));

        phaseChart = new JButton("Phase plot");
        this.add(phaseChart);

        fieldCalculations = new JButton("Field calculations");
        this.add(fieldCalculations);

        plasmaPhysics = new JButton("Plasma physics");
        this.add(plasmaPhysics);

        instructions = new JButton("Instructions");
        this.add(instructions);

        authors = new JButton("Authors");
        this.add(authors);

        addListeners();
    }

    private void addListeners() {
        phaseChart.addActionListener(e -> {
            TextFrameConstructor phase;
            if (language.equals("en")) {
                phase = new TextFrameConstructor(3);
            } else {
                phase = new TextFrameConstructor(2);

            }
            phase.setVisible(true);
        });
        fieldCalculations.addActionListener(e -> {
            TextFrameConstructor field;
            if (language.equals("en")) {
                field = new TextFrameConstructor(5);
            } else {
                field = new TextFrameConstructor(4);

            }
            field.setVisible(true);
        });
        plasmaPhysics.addActionListener(e -> {
            TextFrameConstructor plasma;
            if (language.equals("en")) {
                plasma = new TextFrameConstructor(1);
            } else {
                plasma = new TextFrameConstructor(0);

            }
            plasma.setVisible(true);
        });
        instructions.addActionListener(e -> {
            TextFrameConstructor instructions;
            if (language.equals("en")) {
                instructions = new TextFrameConstructor(7);
            } else {
                instructions = new TextFrameConstructor(6);

            }
            instructions.setVisible(true);
        });
        authors.addActionListener(e -> {
            TextFrameConstructor auth;
            if (language.equals("en")) {
                auth = new TextFrameConstructor(9);
            } else {
                auth = new TextFrameConstructor(8);

            }
            auth.setVisible(true);
        });
    }
}