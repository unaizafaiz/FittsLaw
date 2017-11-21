package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SavingData {

    ArrayList<CirclePairsData> mouseClickData;

    public SavingData(ArrayList<CirclePairsData> dataToBeSaved) {
        mouseClickData=dataToBeSaved;
        printData();
    }

    private void printData(){
        try {
            File file = new File("output.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,true));
            for(CirclePairsData p: mouseClickData){
                pw.append((p.getDataSetIndex()+1)+":"
                        +p.getCircleOne().getX()+":"+p.getCircleOne().getY()+":"
                        +p.getCircleTwo().getX()+":"+p.getCircleTwo().getY()+":"
                        +p.getCircleOneClicked().getX()+":"+p.getCircleOneClicked().getY()+":"
                        +p.getCircleTwoClicked().getX()+":"+p.getCircleTwoClicked().getY()+":"
                        +p.getTime());
            }
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("logfile.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,true));
            int targetSize=0, distance=0;
            for(CirclePairsData p: mouseClickData){

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
