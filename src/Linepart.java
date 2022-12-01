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
}
