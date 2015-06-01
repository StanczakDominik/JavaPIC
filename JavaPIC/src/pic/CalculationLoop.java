package pic;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Autor: Dominik
 * Klasa odpowiadaj¹ca za cykl obliczeniowy - w ka¿dym cyklu rozpoczyna wykonywanie obliczeñ i aktualizuje wykresy
 * dla animacji.
 * Opiera siê to na SwingWorkerze wykonuj¹cym jedn¹ iteracjê cyklu obliczeniowego, w du¿ej mierze oparte na
 * https://github.com/jbzdak/pojava-unittest/blob/master/src/main/java/cx/ath/jbzdak/pojava/
 * zderzenia/SwingWorkerAnimationProvider.java
 * (z podziêkowaniami za udostêpnienie kodu)
 * <p>
 * Metody:
 * konstruktor, jaki jest ka¿dy widzi
 * start - uruchamia pêtlê obliczeniow¹ przez odwo³anie do scheduleWorkerIteration
 * stop - przerywa pêtle obliczeniow¹ bez przerywania obecnego cyklu obliczeniowego (zapobiega rozpoczynaniu kolejnych)
 * scheduleWorkerIteration - rozpoczyna przy pomocy swingworkera kolejny cykl obliczeniowy (w DoInBackground)
 * po zakoñczeniu (w Done) popycha czas (liczbê iteracji) o 1 do przodu (wa¿ne dla wykresu energii
 * i rozpoczyna kolejn¹ iteracjê
 */
class CalculationLoop {



    private SimulationEngine engine;
    private MainFrame frame;
    private boolean started = false;

    CalculationLoop(MainFrame frame)
    {
        this.frame=frame;
        this.engine=frame.getEngine();
    }

    public void start()
    {
        if (!started) {
            started = true;
            scheduleWorkerIteration();
        }
    }

    public void stop() {
        started = false;
        //worker.cancel(true);
    }

    private void scheduleWorkerIteration() {
        final SimulationEngine runningEngine = engine;
        SwingWorker worker = new SwingWorker<SimulationEngine, Void>() {
            @Override
            protected SimulationEngine doInBackground() throws Exception {
                runningEngine.step();
                return runningEngine;
            }

            @Override
            protected void done() {
                frame.iteration += 1;
                try {
                    frame.updatePlots(get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                if (started) {
                    scheduleWorkerIteration();
                }

            }
        };
        worker.execute();

    }
}
