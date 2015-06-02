package pic;

import javax.swing.*;
import java.awt.*;


/**
 * Author: Mateusz
 */
public class PhaseChartFrame extends JFrame {

   public PhaseChartFrame() throws HeadlessException{
//       JFrame chartFrame = new JFrame();
//       chartFrame.setTitle("Phase Chart Information");
//       chartFrame.setSize(250,200);
//       chartFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       setTitle("Phase chart information");
       setSize(600, 600);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);

       JTextArea text = new JTextArea("The upper plot is called a phase plot. On the horizontal axis, we have marked the position of each particle, and on the vertical axis, its velocity. Our simulation is one dimensional and thus we can show the complete phase plot - go 2D and you're going to need four perpendicular axes.\n" +
               "\n" +
               "As you can see, the system starts out with precisely known velocities and a homogeneous (up to a small perturbation) spread of positions - thus the thin horizontal line going through the whole system. It's a position of unstable equilibrium - like a ping pong ball on top of a football, any sufficiently strong deviation from that position gets amplified and results in the system landing somewhere completely different.\n" +
               "\n" +
               "The initial phase of our simulation is a period of linear oscillations - the system can be neatly described by writing out the first two terms of a Taylor series, which we leave as a trivial exercise for the Reader.\n" +
               "\n" +
               "After a time, you can observe that the plot gets squeezed at one point and a kink grows at another - it's a break point between the regimes of unstable and stable equilibria. The assumption of small deviations no longer holds and the instability begins.\n" +
               "\n" +
               "Some important facts to notice - the velocity distribution after the instability begins is approximately gaussian about the mean value. The instability accelerates some particles to great speeds, even five times larger than the initial value (this is the so called tail of the distribution). A frequent phenomenon is a \"hole\" in phase space, a stable structure devoid of particles.");
       add(text);
       text.setVisible(true);
   }

    public static void main(String[] args) {

        PhaseChartFrame phaseChartFrame = new PhaseChartFrame();
        //phaseChartFrame.setVisible(true);
    }
}