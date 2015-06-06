package pic;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Mateusz Kaczorek
 *
 * Środkowy panel z przyciskami odpowiadającymi za robienie zdjęć, zmianę języka, restartowanie całej symulacji
 *              bez zmiany parametrów.
 */
class MiddleRightPanel extends JPanel {

    JButton languageChange, screenCapture;

    MiddleRightPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(1, 1));

        JPanel SimulationControl = new JPanel(new GridLayout(5, 1, 0, 20));
        this.add(SimulationControl);

        JButton StartAndStop = new JButton("||");
        SimulationControl.add(StartAndStop);
        StartAndStop.addActionListener(e -> {

            if (StartAndStop.getText().equals("||")) {

                StartAndStop.setText(">");
                mainFrame.loop.stop();
            } else {
                StartAndStop.setText("||");
                mainFrame.loop.start();
            }
        });

        JButton Restart = new JButton("Restart");
        SimulationControl.add(Restart);
        Restart.addActionListener(e -> mainFrame.restart());


        languageChange = new JButton("PL");
        SimulationControl.add(languageChange);

        screenCapture = new JButton("Print Screen");
        SimulationControl.add(screenCapture);

        languageChange.addActionListener(new LanguageChangeListener(mainFrame));

        screenCapture.addActionListener(e -> mainFrame.takeSnapshots());

    }

}
