package pic;

public class XVScatterPlot extends Application { 

	private XYChart.Series series1;
	private XYChart.Series series2;
	
	
	public void update(Species[] listOfSpecies)
	{
		series1 = new XYChart.Series();
        series1.setName(listOfSpecies[0].name);
        for (int i=0; i<listOfSpecies[0].numberOfParticles; i++)
        {
        	series1.getData().add(new XYChart.Data(listOfSpecies[0].position[i], listOfSpecies[0].velocity[i]));
        }
        
		series2 = new XYChart.Series();
        series2.setName(listOfSpecies[1].name);
        for (int i=0; i<listOfSpecies[1].numberOfParticles; i++)
        {
        	series1.getData().add(new XYChart.Data(listOfSpecies[1].position[i], listOfSpecies[1].velocity[i]));
        }
        
   	}
	
	   @Override public void start(Stage stage) {
	        stage.setTitle("Phase plot");
	        final NumberAxis xAxis = new NumberAxis(0, pic.Parameters.gridSize, pic.Parameters.gridStep);
	        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);        
	        final ScatterChart<Number,Number> sc = new
	            ScatterChart<Number,Number>(xAxis,yAxis);
	        xAxis.setLabel("X position [m]");                
	        yAxis.setLabel("X velocity [m/s]");
	        sc.setTitle("X/V phase plot");
	       
	        update();
	 
	        sc.getData().addAll(series1, series2);
	        Scene scene  = new Scene(sc, 500, 400);
	        stage.setScene(scene);
	        stage.show();
	    }
}
