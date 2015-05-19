package pic;

import org.apache.commons.lang.SerializationUtils;

import javax.swing.*;
import java.util.List;

/**
 * Created by Dominik on 2015-05-19.
 */
public class CalculationWorkerAlternate extends SwingWorker<Void, SimulationEngine> {

    private SimulationEngine engine;
    private MainFrame frame;
    private boolean running = false;

    public CalculationWorkerAlternate(MainFrame frame)
    {
        this.frame = frame;
        this.engine = frame.getEngine();
    }

    protected Void doInBackground() throws Exception
    {
        while(!isCancelled()){
            engine.step();
            publish(engine);
//            frame.updatePlots(engine);
        }
        return null;
    }

    protected void process(List<SimulationEngine> engines)
    {
        System.out.println(engines.size());
        SimulationEngine lastEngine = engines.get(engines.size()-1);
        frame.updatePlots(lastEngine);
    }
//    protected void done()
//   {
//        frame.updatePlots(engine);
//    }
}
