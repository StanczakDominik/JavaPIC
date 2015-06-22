package pl.edu.fizyka.pojava.pic;

import java.io.IOException;
import java.util.Formatter;
import java.util.List;

/**
 * Author: Mateusz
 *
 * Klasa pozwala na zapisanie danych z wykresu energii od czasu 0, do danego w którym został naciśnięty przycisk Export Data.
 */
public class EnergyToTxt {

    private Formatter txtFile;

    public void openFile(int NumberOfFiles){
        try{
            txtFile = new Formatter("Energy"+ NumberOfFiles +".txt");
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }
    public void closeFile(){txtFile.close();}

    public void addData(List<Double> fieldEnergyToExport, List<Double> kineticEnergyToExport1, List<Double> kineticEnergyToExport2, List<Double> totalEnergyToExport, List<Double> timeStepToExport){

            txtFile.format("%s\n","FieldEnergy:"+fieldEnergyToExport);
            txtFile.format("%s\n", "Kinetic Energy 1:"+kineticEnergyToExport1);
            txtFile.format("%s\n", "Kinetic Energy 2:"+kineticEnergyToExport2);
            txtFile.format("%s\n", "Total Energy:"+totalEnergyToExport);
            txtFile.format("%s\n", "Time:"+timeStepToExport);

    }
}

