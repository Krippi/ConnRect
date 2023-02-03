//M
import java.awt.*;

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
        double dist = distPointFromLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, p.x, p.y);
        return (dist <= toleranz && dist >= -toleranz);
    }
    

    /**
     * Ermittelt, wie weit der übergebene Punkt von der übergebenen Linie entfernt ist
     * @return Abstand/Distanz zur Linie als Float
     * @param startX x-Wert des Startpunktes der Geraden
     * @param startY y-Wert des Startpunktes der Geraden
     * @param endX x-Wert des Endpunktes der Geraden
     * @param endY y-Wert des Endpunktes der Geraden
     * @param pointX x-Wert des Punktes
     * @param pointY y-Wert des Punktes
     */
    public double distPointFromLine(double startX, double startY, double endX, double endY, double pointX, double pointY){
        // Berechne die Differenzen der x- und y-Koordinaten der beiden Endpunkte der Linie
        double Xdiff = endX - startX;
        double Ydiff = endY - startY;
        
        // Berechne u, den normalisierten Abstand des Projektionsvektors des Punkts zur Linie
        double skalar = ((pointX - startX) * Xdiff + (pointY - startY) * Ydiff) / (Xdiff * Xdiff + Ydiff * Ydiff);
        
        // Überprüfe, ob u größer als 1 ist (der Punkt befindet sich außerhalb des Linienabschnitts)
        if (skalar > 1) {
            skalar = 1;
        }
        // Überprüfe, ob u kleiner als 0 ist (der Punkt befindet sich außerhalb des Linienabschnitts)
        if (skalar < 0) {
            skalar = 0;
        }
        
        // Berechne die x- und y-Koordinaten des projizierten Punkts auf der Linie
        double projX = startX + skalar * Xdiff;
        double projY = startY + skalar * Ydiff;
        
        // Berechne die Differenzen zwischen den Koordinaten des Punkts und des projizierten Punkts auf der Linie
        double XdiffPointProj = pointX - projX;
        double YdiffPointProj = pointY - projY;
        
        // Berechne die Entfernung zwischen Punkt und projiziertem Punkt auf der Linie mit dem Pythagoras-Theorem
        return Math.sqrt(XdiffPointProj * XdiffPointProj + YdiffPointProj * YdiffPointProj);
    }
}
