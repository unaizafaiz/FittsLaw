package circle;

/*
 * Custom JPanel that implements the iteration of circles for Fitts Law experiment
 * Executes cases for 2 targetSize and 3 Distance
 *
 */

import utility.TestCases;
import utility.MovementTimeData;
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

public class DrawCircles extends JPanel {

    private int trackClickCount=0, iteratingList=0, numberOfCircles=6;
    private int globalX=250, globalY=250, globalRadius, localRadius;
    private Shape circle;
    private Dimension dim = new Dimension(500, 500);
    StopWatch stopWatch = new StopWatch();
    long pairTime;
    private int index, i;
    private HashSet<Integer> caseIndex = new HashSet();
    ArrayList<TestCases> inputList = new ArrayList<>();
    ArrayList<CircleCoordinates> circleData = new ArrayList<>();
    ArrayList<MovementTimeData> dataToBeSaved = new ArrayList<>();
    MovementTimeData pairData = new MovementTimeData();
    int circleLastPoint;
    private boolean done=false;

    public DrawCircles(int globalRadius, int localRadius) {
       this.globalRadius=globalRadius;
       this.localRadius=localRadius;
       String intro = "INSTRUCTIONS: Please click on the red circles, until you reach the grey circle which marks the end of data collection. ";
       JOptionPane.showMessageDialog(null,  intro, "Fitt's Law - Instructions", JOptionPane.INFORMATION_MESSAGE);
       createInputList();
       initialiseCircle();
       mouseClickListener();
    }

    /**
     *
     * Creates a list for the 2 targetSize and 3 distance combinations
     * W = [25, 45]
     * D = [100,150,200]
     *
     * */

    private void createInputList() {
        for(int i=0;i<6;i++){
            TestCases input = new TestCases();
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


    /**
     * Implements mouseListener
     */
    private void mouseClickListener(){
        stopWatch.start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //If end of data reached then draw gray circle and save data to file
                if(trackClickCount==circleLastPoint && iteratingList==inputList.size()){
                    done=true;
                    circle = new Ellipse2D.Double((250-50),(250-50),100,100);
                    repaint();
                    SavingData saveData = new SavingData(dataToBeSaved);
                    JOptionPane.showMessageDialog(null,  "End of task. Thank You for participating", "Fitts Law", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                //Increment click count if it's the first click
                if (trackClickCount == 0) {
                    trackClickCount = 1;
                }

                //moving to the next combination of targetSize and distance
                if(trackClickCount==circleLastPoint && iteratingList!=inputList.size()){
                    initialiseCircle();
                } else {
                    if (circle.contains(e.getPoint())) {
                        pairData.setDataSetIndex(index);
                        if (trackClickCount%2==0) {
                            drawCircleOne();
                        }
                        else {
                            drawCircleTwo();
                        }
                        trackClickCount++;
                    }

                }


                repaint();

            }
        });

    }

    /** initialising the UI for the next possible set of targetSize and distance */

    private void initialiseCircle() {
        //saving last pair time
        getMovementTime();
        dataToBeSaved.add(pairData);

        //re-intialising new values for next case
        trackClickCount=0;
        i=0;
        findRandomIndex();
        globalRadius=inputList.get(index).getGlobalradius();
        localRadius=inputList.get(index).getLocalRadius();
        GetCircle newCircle = new GetCircle(numberOfCircles, globalX,globalY,globalRadius);
        circleData = newCircle.gettingCirclePoints();
        circleLastPoint = circleData.size();
        circle = new Ellipse2D.Double(circleData.get(i).getX()-localRadius, circleData.get(i).getY()-localRadius, localRadius * 2, localRadius * 2);
        pairData.setDataSetIndex(index);
        iteratingList++;
    }

    /** gets a random index for the case to be executed next */

    private void findRandomIndex() {
        Random rand = new Random();
        index = rand.nextInt(inputList.size());
        while (!caseIndex.add(index)){
            index = rand.nextInt(inputList.size());
        }
        while (caseIndex.add(index));
        for(Integer a: caseIndex){
        }
    }

    /**
     * Calculates the elapsed time between two pair of targetSircles
     */
    private void getMovementTime() {
        pairTime=stopWatch.getElapsedTime();
        pairData.setTime(pairTime);
        stopWatch.stop();
    }

    /**
     * draws the first circle in the next pair
     *
     */
    private void drawCircleOne() {
        getMovementTime();
        dataToBeSaved.add(pairData);
        circle = new Ellipse2D.Double(circleData.get(i).getX() - localRadius, circleData.get(i).getY() - localRadius, localRadius * 2, localRadius * 2);
        pairData=new MovementTimeData();
    }

    /**
     *
     * draws the second circle in the corresponding pair
     *
     */


    private void drawCircleTwo() {
        stopWatch.start();
        int index = i + (numberOfCircles / 2);
        circle = new Ellipse2D.Double(circleData.get(index).getX() - localRadius, circleData.get(index).getY() - localRadius, localRadius * 2, localRadius * 2);
        i++;
    }

    /**
     * Update the GUI with the new values
     * @param grphcs
     */

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


