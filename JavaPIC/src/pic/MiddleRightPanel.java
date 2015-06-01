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
        setLayout(new GridLayout(3, 1));

        JPanel SimulationControl = new JPanel(new GridLayout(3, 1, 0, 20));
        this.add(SimulationControl);

        JButton Start = new JButton(">");
        SimulationControl.add(Start);
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.loop.start();
            }
        });

        JButton Stop = new JButton("||");
        SimulationControl.add(Stop);
        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.loop.stop();
            }
        });

        JButton Restart = new JButton("R");
        SimulationControl.add(Restart);
        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.restart();
            }
        });

        JButton LanguageChange = new JButton("EN");
        this.add(LanguageChange);

        LanguageChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(LanguageChange.getText()=="PL"){
                    LanguageChange.setText("EN");
                }
                else{
                    LanguageChange.setText("PL");
                    mainFrame.UpperRightPanel.mass1label.setText("Mass 1");
                }
            }
        });

    }

}
