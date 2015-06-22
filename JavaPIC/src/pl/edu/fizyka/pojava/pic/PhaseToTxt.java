package pl.edu.fizyka.pojava.pic;

import java.util.Formatter;

/**
 * Created by MATEUSZ on 2015-06-21.
 */
public class PhaseToTxt {

    private Formatter txtFile;

    public void openFile(){
        try{
            txtFile = new Formatter("Phase.txt");
        }
        catch(Exception e){
            System.out.println("Error");
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
