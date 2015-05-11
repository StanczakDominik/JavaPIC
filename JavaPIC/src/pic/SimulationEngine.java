package pic;

import java.awt.*;
import java.io.IOException;
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
		positions1=Parameters.randomPosition(Parameters.numberOfParticles, Parameters.perturbationAmplitude);
		positions2=Parameters.randomPosition(Parameters.numberOfParticles, -Parameters.perturbationAmplitude);
		beam1 = new Species(Parameters.numberOfParticles, Parameters.charge, positions1, velocities1);
		beam2 = new Species(Parameters.numberOfParticles, Parameters.charge, positions2, velocities2);
		
		listOfSpecies[0]=beam1;
		listOfSpecies[1]=beam2;
		grid.update(listOfSpecies);
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

			//TODO: Replace with animation
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		frame.setVisible(false);
	}
}
