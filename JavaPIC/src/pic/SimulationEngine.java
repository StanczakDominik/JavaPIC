package pic;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.*;
public class SimulationEngine {

	Grid grid = new Grid();
	Species beam1, beam2;
	Species[] listOfSpecies = new Species[2];
	double[] velocities1;
	double[] velocities2;
	double[] positions1;
	double[] positions2;
	public SimulationEngine()
	{
		velocities1=Parameters.uniformVelocity(Parameters.numberOfParticles, Parameters.initialVelocity);
		velocities2=Parameters.uniformVelocity(Parameters.numberOfParticles, -Parameters.initialVelocity);		
		//positions1=Parameters.cosinePosition(Parameters.numberOfParticles, Parameters.perturbationAmplitude);
		//positions2=Parameters.cosinePosition(Parameters.numberOfParticles, -Parameters.perturbationAmplitude);
		positions1=Parameters.randomPosition(Parameters.numberOfParticles, Parameters.perturbationAmplitude);
		positions2=Parameters.randomPosition(Parameters.numberOfParticles, -Parameters.perturbationAmplitude);
		beam1 = new Species("Beam 1", Color.RED, Parameters.numberOfParticles, Parameters.protonRestMass, Parameters.charge, positions1, velocities1);
		beam2 = new Species("Beam 2", Color.GREEN, Parameters.numberOfParticles, Parameters.electronRestMass, Parameters.charge, positions2, velocities2);
		
		listOfSpecies[0]=beam1;
		listOfSpecies[1]=beam2;
		grid.update(listOfSpecies);
		
/*		plot = new SimulationFrame(this);
		plot.createPlot();
		plot.phasePlot.update(this);*/
	}


	public void step()
	{
		for(int i=0; i<2; i++)
		{
			listOfSpecies[i].step(grid);
		}
		grid.update(listOfSpecies);
	}
	
	public static void main(String[] args)
	{
		SimulationEngine engine = new SimulationEngine();

		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);

		GridLayout layout = new GridLayout(2,1);
		frame.setLayout(layout);

		XVPlotPanel phasePlot = new XVPlotPanel(engine);
		frame.add(phasePlot);
		phasePlot.setSize(new Dimension(1000, 500));
		phasePlot.update(engine);
		phasePlot.setVisible(true);

		FieldPlot fieldPlot = new FieldPlot(engine);
		frame.add(fieldPlot);
		fieldPlot.setSize(new Dimension(1000, 500));
		fieldPlot.update(engine);
		fieldPlot.setVisible(true);

		frame.setTitle("Plots. Density - red, potential - blue, field - green");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);


		for(int t=0; t<Parameters.iterations; t++)
		{
			if(Parameters.printIterations) System.out.println("Iteration " + t);
			engine.step();

			phasePlot.update(engine);
			fieldPlot.update(engine);

			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		frame.setVisible(false);
	}
}
