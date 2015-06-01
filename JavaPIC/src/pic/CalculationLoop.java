package pic;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Autor: Dominik
 * Klasa odpowiadaj�ca za cykl obliczeniowy - w ka�dym cyklu rozpoczyna wykonywanie oblicze� i aktualizuje wykresy
 * dla animacji.
 * Opiera si� to na SwingWorkerze wykonuj�cym jedn� iteracj� cyklu obliczeniowego, w du�ej mierze oparte na
 * https://github.com/jbzdak/pojava-unittest/blob/master/src/main/java/cx/ath/jbzdak/pojava/
 * zderzenia/SwingWorkerAnimationProvider.java
 * (z podzi�kowaniami za udost�pnienie kodu)
 * <p>
 * Metody:
 * konstruktor, jaki jest ka�dy widzi
 * start - uruchamia p�tl� obliczeniow� przez odwo�anie do scheduleWorkerIteration
 * stop - przerywa p�tle obliczeniow� bez przerywania obecnego cyklu obliczeniowego (zapobiega rozpoczynaniu kolejnych)
 * scheduleWorkerIteration - rozpoczyna przy pomocy swingworkera kolejny cykl obliczeniowy (w DoInBackground)
 * po zako�czeniu (w Done) popycha czas (liczb� iteracji) o 1 do przodu (wa�ne dla wykresu energii
 * i rozpoczyna kolejn� iteracj�
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
