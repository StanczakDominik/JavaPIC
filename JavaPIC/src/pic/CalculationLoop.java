package pic;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

class CalculationLoop {

    private SimulationEngine engine;
    private MainFrame frame;
    private SwingWorker worker;
    private boolean started = false;

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
        started = false;
        //worker.cancel(true);
    }

    private void scheduleWorkerIteration() {
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
                frame.iteration+=1;
                try {
                    frame.updatePlots(get());
                } catch (InterruptedException | ExecutionException e) {
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
