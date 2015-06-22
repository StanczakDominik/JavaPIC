package pl.edu.fizyka.pojava.pic;

import java.io.IOException;
import java.util.Formatter;

/**
 * Author: Mateusz
 *
 * Klasa pozwala na zapisanie pozycji i prędkości każdej cząstki w danym momencie.
 */
public class PhaseToTxt {

    private Formatter txtFile;

    public void openFile(int NumberOfFiles){
        try{
            txtFile = new Formatter("Phase"+ NumberOfFiles +".txt");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void closeFile(){txtFile.close();}

    public void addData(double[] Velocities1, double []Velocities2, double[] Positions1, double[] Positions2, int numberOfParticles){

        txtFile.format("%s\t%s\t%s\t\t%s\n", "Velocities 1", "Velocities 2", "Position 1", "Positions 2");

        for(int i=0; i<numberOfParticles; i++){
            txtFile.format("%f\t\t%f\t\t%f\t\t%f\n", Velocities1[i],Velocities2[i], Positions1[i], Positions2[i]);
        }
    }
}
