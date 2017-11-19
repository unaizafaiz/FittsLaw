package circle;

import javax.swing.*;
import java.util.ArrayList;

public class GetCircle extends JPanel {

    private static final int SIZE = 256;
    // private int a = SIZE / 2;
    // private int b = a;
    //  private int r = 4 * SIZE / 5;
    private int a, b, r;
    private int n;

    /** @param n  the desired number of circles. */
    public GetCircle(int n, int centerX, int centerY, int radius) {
        super(true);
        this.n = n;
        a=centerX;
        b=centerY;
        r=radius;
    }

    public ArrayList<CirclePoints> gettingCirclePoints(){

        ArrayList<CirclePoints> circleData = new ArrayList<>();
       // System.out.println("For the global center "+a+","+b+" and radius "+r);
        for (int i = 0; i < n; i++) {
            CirclePoints circle = new CirclePoints();
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            circle.setX(x);
            circle.setY(y);
           // System.out.println(i+ "-- x= "+x+" y="+y);
            circleData.add(circle);
        }
        return circleData;
    }
}