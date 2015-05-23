package pic;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dominik on 2015-05-23.
 */
public class CalculationLoop {

    protected SimulationEngine engine;
    protected MainFrame frame;
    SwingWorker worker;
    protected boolean started=false;

    CalculationLoop(MainFrame frame)
    {
        this.frame=frame;
        this.engine=frame.getEngine();
    }

    public void start()
    {
        started = true;
        scheduleWorkerIteration();
    }

    public void stop() {
        started = true;
        worker.cancel(true);
    }

    void scheduleWorkerIteration() {
        final SimulationEngine runningEngine = engine;
        worker = new SwingWorker<SimulationEngine, Void>() {
            @Override
            protected SimulationEngine doInBackground() throws Exception {
                runningEngine.step();
                return runningEngine;
            }

            @Override
            protected void done()
            {
                try {
                    frame.updatePlots(get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if(started)
                {
                    scheduleWorkerIteration();
                }

            }
        };
        worker.execute();

    }
}
