package utility;

import java.io.File;
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

        for(CirclePairsData p: mouseClickData){
            System.out.println();
                System.out.println("Circle1 x="+p.getCircleOne().getX()+" y="+p.getCircleOne().getY());
                System.out.println("Circle2 x="+p.getCircleTwo().getX()+" y="+p.getCircleTwo().getY());
                System.out.println("Point clicked x="+p.getCircleOneClicked().getX()+" y="+p.getCircleOneClicked().getY());
                System.out.println("Time take "+p.getTime());

        }


        try {
            File file = new File("output.txt");
            PrintWriter pw = new PrintWriter(file);
            for(CirclePairsData p: mouseClickData){
                pw.println((p.getDataSetIndex()+1)+":"
                        +p.getCircleOne().getX()+","+p.getCircleOne().getY()+":"
                        +p.getCircleTwo().getX()+","+p.getCircleTwo().getY()+":"
                        +p.getCircleOneClicked().getX()+","+p.getCircleOneClicked().getY()+":"
                        +p.getCircleTwoClicked().getX()+","+p.getCircleTwoClicked().getY()+":"
                        +p.getTime());
            }
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
