//M
import java.awt.*;
import java.awt.geom.Line2D;

public class Linepart {
    private Point startPoint;
    private Point endPoint;


    public Linepart(Point startPoint, Point endPoint) {
        this.startPoint = (Point)startPoint.clone();
        this.endPoint = (Point)endPoint.clone();
    }

    /**
     * @return Gibt den Endpunkt des Linienparts zurück.
     * @see Point 
     */
    public Point getEndPoint() {
        return endPoint;
    }

    /**
     * @return Gibt den Startpunkt des Linienparts zurück.
     * @see Point 
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Prüft, ob der übergebene Punkt auf dem Linienpart liegt, indem die Entfernung vom Punkt zum Linienpart geprüft wird.
     * @return Gibt true zurück, wenn der Punkt auf dem Linienpart liegt (inkl. Toleranz)
     * @param p zu prüfender Punkt
     * @see Point 
     */
    public boolean pointInLinepart(Point p){
        final int toleranz = 5;
        double dist = Line2D.ptSegDist(startPoint.x, startPoint.y, endPoint.x, endPoint.y, p.x, p.y);
        return (dist <= toleranz && dist >= -toleranz);
    }
}
