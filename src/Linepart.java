import java.awt.*;

public class Linepart {
    private Point startPoint;
    private Point endPoint;


    public Linepart(Point startPoint, Point endPoint) {
        this.startPoint = (Point)startPoint.clone();
        this.endPoint = (Point)endPoint.clone();
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    // Toleranz ok, 3. Part nicht klickbar -> mysterious?!
    public boolean pointInLinepart(Point p){
        final int toleranz = 5;
/*         int spxt = startPoint.x-toleranz;
        int epxt = endPoint.x+toleranz;
        int spyt = startPoint.y-toleranz;
        int epyt = endPoint.y+toleranz; */
        return p.x >= startPoint.x-toleranz && 
               p.x <= endPoint.x+toleranz && 
               p.y >= startPoint.y-toleranz &&
               p.y <= endPoint.y+toleranz;
    }
}
