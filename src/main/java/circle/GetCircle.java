package circle;

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

    public ArrayList<CirclePoints> gettingCirclePoints(){

        ArrayList<CirclePoints> circleData = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            CirclePoints circle = new CirclePoints();
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