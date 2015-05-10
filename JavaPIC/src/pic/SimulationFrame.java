package pic;

import java.awt.*;

import javax.swing.*;

public class SimulationFrame extends JFrame {

	int W = 500, H = 500;
	public XVPlotPanel phasePlot;
	SimulationEngine engine;
	public SimulationFrame(SimulationEngine engine) throws HeadlessException {
		setSize(W,H);		
		this.engine=engine;
		setVisible(true);
	}
	public void createPlot()
	{
		phasePlot= new XVPlotPanel(engine);
		phasePlot.setPreferredSize(new Dimension(400,400));
		add(phasePlot);
	}
	public void updatePlot(SimulationEngine iEngine)
	{
		this.engine = iEngine;
		phasePlot.update(engine);;
	}
}
