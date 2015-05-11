package pic;

import org.apache.commons.lang.SerializationUtils;

import javax.swing.SwingWorker;
import java.util.concurrent.ExecutionException;
/**
 * Created by Dominik on 2015-05-11.
 */
public class SwingWorkerAnimationProvider {
    protected XVPlotPanel phasePlot;
    protected SimulationEngine engine;
    protected boolean started = false;

    public XVPlotPanel getPhasePlot() {
        return phasePlot;
    }

    public SimulationEngine getEngine() {
        return engine;
    }

    public boolean isStarted() {
        return started;
    }

    public void setPhasePlot(XVPlotPanel phasePlot) {
        this.phasePlot = phasePlot;
    }

    public void setEngine(SimulationEngine engine) {
        this.engine = engine;
    }

    public void stop() {
        started = false;
    }
    public void start() {
        started = true;
//        scheduleWorkerIteration();
    }

    SwingWorker<SimulationEngine, Void> currentWorker;

//    void scheduleWorkerIteration()
//    {
//        //final SimulationEngine workingCopy = SerializationUtils.clone(engine);
//
//        currentWorker = new SwingWorker<SimulationEngine, Void>()
//        {
//            @Override
//            protected SimulationEngine doInBackground() throws Exception
//            {
//
//            }
//        };
//
//    }
}
