package utility;

import circle.CirclePoints;

public class CirclePairsData {

   private CirclePoints circleOne = new CirclePoints();
   private CirclePoints circleTwo = new CirclePoints();
   private CirclePoints circleOneClicked = new CirclePoints();
   private CirclePoints circleTwoClicked = new CirclePoints();
   private int dataSetIndex;
   long time;


    public int getDataSetIndex() {
        return dataSetIndex;
    }

    public void setDataSetIndex(int dataSetIndex) {
        this.dataSetIndex = dataSetIndex;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public CirclePoints getCircleOne() {
        return circleOne;
    }

    public void setCircleOne(CirclePoints circle1) {
        this.circleOne = circle1;
    }

    public CirclePoints getCircleTwo() {
        return circleTwo;
    }

    public void setCircleTwo(CirclePoints circle2) {
        this.circleTwo = circle2;
    }


    public CirclePoints getCircleOneClicked() {
        return circleOneClicked;
    }

    public void setCircleOneClicked(CirclePoints circleOneClicked) {
        this.circleOneClicked = circleOneClicked;
    }

    public CirclePoints getCircleTwoClicked() {
        return circleTwoClicked;
    }

    public void setCircleTwoClicked(CirclePoints circleTwoClicked) {
        this.circleTwoClicked = circleTwoClicked;
    }

}
