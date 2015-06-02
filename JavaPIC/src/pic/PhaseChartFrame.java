package pic;

import javax.swing.*;
import java.awt.*;


/**
 * Author: Mateusz
 */
public class PhaseChartFrame extends JFrame {

   public PhaseChartFrame() throws HeadlessException{
       JFrame chartFrame = new JFrame();
       chartFrame.setTitle("Phase Chart Information");
       chartFrame.setSize(250,200);
       chartFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

   }

    public static void main(String[] args) {

        PhaseChartFrame phaseChartFrame = new PhaseChartFrame();
        phaseChartFrame.setVisible(true);
    }
}