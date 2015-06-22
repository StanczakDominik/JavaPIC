package pl.edu.fizyka.pojava.pic;

import java.io.IOException;
import java.util.Formatter;

/**
 * Author: Mateusz
 *
 * Klasa pozwala na zapisanie wykresu pola dla danego momentu czasu..
 */
public class FieldToTxt {

    private Formatter txtFile;

    public void openFile(int NumberOfFiles){
        try{
            txtFile = new Formatter("Field"+ NumberOfFiles +".txt");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void closeFile(){txtFile.close();}

    public void addData(double[] gridPoints, double[] eField, double[] density, int gridPointsNumber){

        txtFile.format("%s\t\t%s\t%s\n", "Grid Point", "Electric Field", "Density");

        for(int i=0; i<gridPointsNumber; i++){
            txtFile.format("%f\t\t%f\t\t%f\n", gridPoints[i],eField[i], density[i]);
        }
    }
}

