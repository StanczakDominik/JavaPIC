package pic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
 
public class XVPhasePlot extends Application {
	
	XYChart.Series series1 = new XYChart.Series(), series2 = new XYChart.Series();;
	public void update(Species[] listOfSpecies)
	{
		for (int i=0; i<pic.Parameters.numberOfParticles; i++)
		{
			series1.setName("Beam 1");
			series2.setName("Beam 2");
			series1.getData().add(new XYChart.Data(listOfSpecies[0].position[i], listOfSpecies[0].velocity[i]));
			series2.getData().add(new XYChart.Data(listOfSpecies[1].position[i], listOfSpecies[0].velocity[i]));
		}
	}
 
    @Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);        
        final ScatterChart<Number,Number> sc = 
            new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Position");                
        yAxis.setLabel("Velocity");
        sc.setTitle("Phase plot");
                             
        sc.setPrefSize(500, 400);
        sc.getData().addAll(series1, series2);

        Scene scene  = new Scene(sc, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}