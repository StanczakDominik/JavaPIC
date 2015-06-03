package pic;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Autor: Dominik
 * Klasa odpowiadająca za cykl obliczeniowy - w każdym cyklu rozpoczyna wykonywanie obliczeń i aktualizuje wykresy
 * dla animacji.
 * Opiera się to na SwingWorkerze wykonującym jedną iterację cyklu obliczeniowego, w dużej mierze oparte na
 * https://github.com/jbzdak/pojava-unittest/blob/master/src/main/java/cx/ath/jbzdak/pojava/
 * zderzenia/SwingWorkerAnimationProvider.java
 * (z podziękowaniami za udostępnienie kodu)
 * <p>
 * Metody:
 * konstruktor, jaki jest każdy widzi
 * start - uruchamia pętlę obliczeniową przez odwołanie do scheduleWorkerIteration
 * stop - przerywa pętle obliczeniowę bez przerywania obecnego cyklu obliczeniowego (zapobiega rozpoczynaniu kolejnych)
 * scheduleWorkerIteration - rozpoczyna przy pomocy swingworkera kolejny cykl obliczeniowy (w DoInBackground)
 * po zakończeniu (w Done) popycha czas (liczbę iteracji) o 1 do przodu (ważne dla wykresu energii)
 * i rozpoczyna kolejną iterację
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
