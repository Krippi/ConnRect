import java.awt.*;
import java.awt.geom.Line2D;

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

    public boolean pointInLinepart(Point p){
        final int toleranz = 5;
        double dist = Line2D.ptLineDist(startPoint.x, startPoint.y, endPoint.x, endPoint.y, p.x, p.y);
        return (dist <= toleranz && dist >= -toleranz);
    }
}
