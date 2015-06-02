package pic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Autor: Mateusz Kaczorek
 */
public class MiddleRightPanel extends JPanel {

    JButton LanguageChange, ScreenCapture;

    MiddleRightPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(1, 1));

        JPanel SimulationControl = new JPanel(new GridLayout(5, 1, 0, 20));
        this.add(SimulationControl);

        JButton StartAndStop = new JButton("||");
        SimulationControl.add(StartAndStop);
        StartAndStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(StartAndStop.getText().equals("||")){

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


        LanguageChange = new JButton("PL");
        SimulationControl.add(LanguageChange);

        ScreenCapture = new JButton("Print Screen");
        SimulationControl.add(ScreenCapture);

        LanguageChange.addActionListener(new LanguageChangeListener(mainFrame));

        ScreenCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.takeSnapshots();
            }
        });

    }

}
