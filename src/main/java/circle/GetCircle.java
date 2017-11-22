package circle;

/*
    Gets all co-ordinates of the required circle
    Input: Center of circle, radius, number of co-ordinates required
 */

import java.util.ArrayList;

public class GetCircle {

    private int a, b, r;
    private int n;

    //n is the desired number of circles
    public GetCircle(int n, int centerX, int centerY, int radius) {
        this.n = n;
        a=centerX;
        b=centerY;
        r=radius;
    }

    /**
     * Computes the co-ordinates for given circle
     *
     * @return Array List of type CircleCoordinates
     */
    public ArrayList<CircleCoordinates> gettingCirclePoints(){

        ArrayList<CircleCoordinates> circleData = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            CircleCoordinates circle = new CircleCoordinates();
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            circle.setX(x);
            circle.setY(y);
            circleData.add(circle);
        }
        return circleData;
    }
}