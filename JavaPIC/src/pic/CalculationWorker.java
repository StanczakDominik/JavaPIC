package pic;

import org.apache.commons.lang.SerializationUtils;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dominik on 2015-05-19.
 */
public class CalculationWorker extends SwingWorker<SimulationEngine, Void> {

    private SimulationEngine engine;
    private MainFrame frame;

    public CalculationWorker(MainFrame frame)
    {
        this.frame = frame;
        this.engine = frame.getEngine();
    }

    protected SimulationEngine doInBackground() throws Exception
    {
        engine.step();
        return engine;
    }

    protected void done()
    {
        System.out.println("Finished");
        try {
            frame.updatePlots(get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
