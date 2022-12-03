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

    // noch zu prüfen, Linie sehr schwer anzuklicken :/
    public boolean pointInLinepart(Point p){
        final int toleranz = 2;
        return p.x >= startPoint.x+toleranz && 
               p.x <= endPoint.x+toleranz && 
               p.y >= startPoint.y+toleranz &&
               p.y <= endPoint.y+toleranz;
    }
}
