package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SavingData {

    ArrayList<MovementTimeData> dataToBeSaved;

    public SavingData(ArrayList<MovementTimeData> dataToBeSaved) {
        this.dataToBeSaved=dataToBeSaved;
        printData();
    }

    private void printData(){

        try {
            File file = new File("data_dump.log");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,true));
            int targetSize=0, distance=0;
            for(MovementTimeData p: dataToBeSaved){

                switch (p.getDataSetIndex()){

                    case 0: targetSize=25;
                            distance=100;
                            break;

                    case 1: targetSize=25;
                            distance=150;
                            break;

                    case 2:targetSize=25;
                        distance=200;
                        break;

                    case 3:targetSize=45;
                        distance=100;
                        break;

                    case 4:targetSize=45;
                        distance=150;
                        break;

                    case 5:targetSize=45;
                        distance=200;
                        break;

                }

                pw.append(targetSize+":"+distance+":" +p.getTime());
                pw.println();
            }
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
