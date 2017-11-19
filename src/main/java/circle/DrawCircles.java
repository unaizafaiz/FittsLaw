package circle;

import utility.InputData;
import utility.CirclePairsData;
import utility.SavingData;
import utility.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

//custom panel
public class DrawCircles extends JPanel {

    private int trackClickCount=0, iteratingList=0, numberOfCircles=20;
    private int globalX=250, globalY=250, globalRadius, localRadius;
    private Shape circle;
    private Dimension dim = new Dimension(500, 500);
    StopWatch stopWatch = new StopWatch();
    long oTime, pairTime;
    private int index, i;
    private HashSet<Integer> caseIndex = new HashSet();
    ArrayList<InputData> inputList = new ArrayList<>();
    ArrayList<CirclePoints> circleData = new ArrayList<>();
    ArrayList<CirclePairsData> dataToBeSaved = new ArrayList<>();
    CirclePairsData pairData = new CirclePairsData();
    int circleLastPoint;
    private boolean done=false;

    public DrawCircles(int globalRadius, int localRadius) {
       this.globalRadius=globalRadius;
       this.localRadius=localRadius;
       createInputList();
       /*GetCircle newCircle = new GetCircle(numberOfCircles, globalX,globalY,globalRadius);
       circleData = newCircle.gettingCirclePoints();
       circleLastPoint = circleData.size();*/
       initialiseCircle();
       mouseClickListener();
    }

    private void createInputList() {
        for(int i=0;i<6;i++){
            InputData input = new InputData();
            input.setGlobalradius(globalRadius);
            input.setLocalRadius(localRadius);
            inputList.add(input);
            if(i==2){
                globalRadius = 100;
                localRadius=localRadius+20;
            } else {
                globalRadius = globalRadius + 50;
            }

        }
    }

    private void mouseClickListener(){
      // initialiseCircle();
        stopWatch.start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //Adding Index of the input data
                pairData.setDataSetIndex(index);
               // pairData.setDistance(globalRadius);
               // pairData.setTargetSize(localRadius);

                //Increment click count if it's the first click
                if (trackClickCount == 0) {
                    trackClickCount = 1;
                }

                //if mouse click is less than the number of circles to be drawn
                if(trackClickCount<circleLastPoint+1) {
                    if (circle.contains(e.getPoint())) {
                        if(trackClickCount==20) {
                            circle = new Ellipse2D.Double(circleData.get(0).getX() - localRadius, circleData.get(0).getY() - localRadius, localRadius * 2, localRadius * 2);
                        }
                        else if (trackClickCount%2==0) {
                            System.out.println("Mouse clicked at x ="+e.getX()+" y="+e.getY());
                            drawCircleOne(e);
                        }
                        else {
                            drawCircleTwo(e);
                        }
                        trackClickCount++;
                    }

                }

                //moving to the next combination of targetSize and distance
                if(trackClickCount==circleLastPoint+1 && iteratingList!=inputList.size()){
                    initialiseCircle();
                }

                //If end of data reached then draw gray circle and save data to file
                if(trackClickCount==circleLastPoint+1 && iteratingList==inputList.size()){
                    done=true;
                    circle = new Ellipse2D.Double((250-50),(250-50),100,100);
                    SavingData saveData = new SavingData(dataToBeSaved);
                }
                repaint();

            }
        });

         /*  addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    super.mouseMoved(e);
                    long time = stopWatch.getElapsedTime();
                    System.out.println(" Mouse moving x,y = "+e.getPoint()+" time = "+time);

                }
            });*/
    }

    private void initialiseCircle() {
        trackClickCount=0;
        i=0;
        finRadnomIndex();
        //pair = 0 ;
        globalRadius=inputList.get(index).getGlobalradius();
        localRadius=inputList.get(index).getLocalRadius();
        GetCircle newCircle = new GetCircle(numberOfCircles, globalX,globalY,globalRadius);
        circleData = newCircle.gettingCirclePoints();
        circleLastPoint = circleData.size();
        circle = new Ellipse2D.Double(circleData.get(i).getX()-localRadius, circleData.get(i).getY()-localRadius, localRadius * 2, localRadius * 2);
        pairData.setCircleOne(circleData.get(i));
        pairData.setDataSetIndex(index);
        iteratingList++;
    }

    private void finRadnomIndex() {
        Random rand = new Random();
        //int index;
        index = rand.nextInt(inputList.size());
        while (!caseIndex.add(index)){
            index = rand.nextInt(inputList.size());
            System.out.println("in while "+index);
        }
        while (caseIndex.add(index));
        for(Integer a: caseIndex){
            System.out.println("Items in caseIndex"+a);
        }
        System.out.println("Selected: "+index+"--- "+inputList.get(index).getGlobalradius()+" -- "+inputList.get(index).getLocalRadius());
    }

    private void drawCircleOne(MouseEvent e) {
        System.out.println("Mouse clicked at x ="+e.getX()+" y="+e.getY());
        pairTime=stopWatch.getElapsedTime()-oTime;
        pairData.setTime(pairTime);
        oTime = stopWatch.getElapsedTime();
        stopWatch.pause();
        CirclePoints circleClicked = new CirclePoints();
        circleClicked.setX(e.getX());
        circleClicked.setY(e.getY());
        pairData.setCircleTwoClicked(circleClicked);
        dataToBeSaved.add(pairData);
        circle = new Ellipse2D.Double(circleData.get(i).getX() - localRadius, circleData.get(i).getY() - localRadius, localRadius * 2, localRadius * 2);
        pairData=new CirclePairsData();
        pairData.setCircleOne(circleData.get(i));
    }

    private void drawCircleTwo(MouseEvent e) {
        System.out.println("Mouse clicked at x ="+e.getX()+" y="+e.getY());
        CirclePoints circleClicked = new CirclePoints();
        circleClicked.setX(e.getX());
        circleClicked.setY(e.getY());
        pairData.setCircleOneClicked(circleClicked);
        stopWatch.resume();
        oTime = stopWatch.getElapsedTime();
        int index = i + (numberOfCircles / 2);
        circle = new Ellipse2D.Double(circleData.get(index).getX() - localRadius, circleData.get(index).getY() - localRadius, localRadius * 2, localRadius * 2);
        pairData.setCircleTwo(circleData.get(index));
        i++;
    }


    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setColor(Color.RED);
        if(done){
            g2d.setColor(Color.GRAY);
        }
        g2d.draw(circle);
        g2d.fill(circle);
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }

}


