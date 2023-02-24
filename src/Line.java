import java.awt.*;
import java.util.ArrayList;

/**
 * Klasse erstellt und kommentiert von Niclas Leroy
 */
public class Line {
    // Enum zur Auswahl des Linientyps
    public enum linetypes{
        STRAIGHT,
        ANGLED
    }
    private ArrayList<Linepart> linepartList = new ArrayList<>(); // Liste von Linienteilen, aus denen die Linie besteht

    /**
     * Konstruktor der Line-Klasse. Handelt unteranderm das erstellen der Lineparts
     * @param rect1 Erstes Rechteck
     * @param rect2 Zweites Rechteck
     * @param linetype der zu erstellende Linientyp
     */
    public Line(Rectangle rect1, Rectangle rect2, linetypes linetype){
        // Koordinaten & Größe der beiden Rechtecke zur vereinfachung der If-Anweisungen
        int r1_X = rect1.getPosition().x;
        int r1_Y = rect1.getPosition().y;
        int r1_H = rect1.getHeight();
        int r2_X = rect2.getPosition().x;
        int r2_Y = rect2.getPosition().y;
        int r2_H = rect1.getHeight();

        // Abhängig vom Linientyp wird die Linie erstellt
        switch (linetype){
            case STRAIGHT:
                createStraightLine(rect1, rect2);
                break;
            case ANGLED:
                // Prüfung, ob die Linie horizontal oder vertikal sein soll
               if(r1_Y == r2_Y ||
                       (r1_Y < r2_Y && r1_Y + r1_H > r2_Y) ||
                       (r1_Y < r2_Y + r2_H && r1_Y + r1_H > r2_Y + r2_H)){
                   // Horizontale Linie
                   if(r1_X < r2_X ){
                       // Vertikale Linie von oben nach unten
                       createAngledHorizontalLine(rect1, rect2);
                   }else{
                       // Vertikale Linie von unten nach oben
                       createAngledHorizontalLine(rect2, rect1);
                   }
                }else if(r1_Y < r2_Y){
                    createAngledVerticalLine(rect1,rect2);
                }else if(r1_Y > r2_Y){
                    createAngledVerticalLine(rect2,rect1);
                }
                break;
        }
    }

    /**
     * Methode zur Erstellung einer geraden Linie
     * @param rect1 Erstes Rechteck
     * @param rect2 Zweites Rechteck
     */
    private void createStraightLine(Rectangle rect1, Rectangle rect2){
        Point start;
        Point end;

        start = rect1.getMidPoint();
        end = rect2.getMidPoint();

        // Hinzufügen eines neuen Linienteils, welches sich auf die jeweiligen Mittelpunkte der Rechtecke bezieht
        linepartList.add(new Linepart(start, end));
    }

    /**
     * Methode zur Erstellung einer geknickten horizontalen Linie
     * @param rect1 Erstes Rechteck
     * @param rect2 Zweites Rechteck
     */
    private void createAngledHorizontalLine(Rectangle rect1, Rectangle rect2){
        Point start;            // Startpunkt der aktuellen Linie
        Point end;              // Endpunkt der aktuellen Linie
        int x;                  // X-Wert des aktuellen Punkts
        int y;                  // Y-Wert des aktuellen Punkts
        int xDistanceBetween;   // Horizontalen Abstands zwischen den beiden Rechtecken
        Point startThird;       // Startpunkt der dritten Linie
        Point endThird;         // Endpunkt der dritten Linie

        // Berechnung des horizontalen Abstands zwischen den beiden Rechtecken
        xDistanceBetween = rect2.getPosition().x -  rect1.getWidth() - rect1.getPosition().x;
        xDistanceBetween /= 2;

        // Erste Linie
        {
            // Startpunkt ist die rechte Mitte des ersten Rechtecks
            x = rect1.getPosition().x + rect1.getWidth();
            y = rect1.getPosition().y + (rect1.getHeight() / 2);
            start = new Point(x,y);

            // Endpunkt ist der horizontale Mittelpunkt zwischen den beiden Rechtecken
            x = x + xDistanceBetween;
            end = new Point(x - 1,y);

            // Startpunkt der dritten Linie entspricht dem Endpunkt der ersten Linie
            startThird = (Point) end.clone();

            linepartList.add(new Linepart(start, end));
        }

        //Zweite Linie
        {
            // Startpunkt ist die linke Mitte des zweiten Rechtecks
            x = rect2.getPosition().x;
            y = rect2.getPosition().y + (rect2.getHeight() / 2);
            start = new Point(x - 1, y);

            // Endpunkt ist der horizontale Mittelpunkt zwischen den beiden Rechtecken
            x = x - xDistanceBetween;
            end = new Point(x - 1, y);

            // Endpunkt der dritten Linie entspricht dem Startpunkt der ersten Linie
            endThird = (Point) end.clone();
            endThird.x = startThird.x;

            linepartList.add(new Linepart(start, end));
        }

        //Dritte Linie
        {
            // Füge die dritte Linie zur Liste der Linienteile hinzu
            linepartList.add(new Linepart(startThird, endThird));
        }
    }

    /**
     * Methode zur Erstellung einer geknickten vertikalen Linie
     * @param rect1 Erstes Rechteck
     * @param rect2 Zweites Rechteck
     */
    private void createAngledVerticalLine(Rectangle rect1, Rectangle rect2){
        Point start;            // Startpunkt der aktuellen Linie
        Point end;              // Endpunkt der aktuellen Linie
        int x;                  // X-Wert des aktuellen Punkts
        int y;                  // Y-Wert des aktuellen Punkts
        int yDistanceBetween;   // Veritkaler Abstands zwischen den beiden Rechtecken
        Point startThird;       // Startpunkt der dritten Linie
        Point endThird;         // Endpunkt der dritten Linie

        // Berechnung des vertikalen Abstands zwischen den beiden Rechtecken
        yDistanceBetween = rect2.getPosition().y -  rect1.getHeight() - rect1.getPosition().y;
        yDistanceBetween /= 2;

        //Erste Linie
        {
            // Startpunkt ist der Mittelpunkt der Unterseite des ersten Rechtecks
            x = rect1.getPosition().x + (rect1.getWidth() / 2);
            y = rect1.getPosition().y + rect1.getHeight();
            start = new Point(x,y);

            // Endpunkt ist der Punkt direkt unter dem Startpunkt, um die halbe Distanz zwischen den Rechtecken versetzt
            y = y + yDistanceBetween;
            end = new Point(x,y -1);

            // Startpunkt der dritten Linie entspricht dem Endpunkt der ersten Linie
            startThird = (Point) end.clone();

            linepartList.add(new Linepart(start, end));
        }

        //Zweite Linie
        {
            // Startpunkt ist der Mittelpunkt der Oberseite des zweiten Rechtecks
            x = rect2.getPosition().x + (rect2.getWidth() / 2);
            y = rect2.getPosition().y;
            start = new Point(x,y - 1);

            // Endpunkt ist der Punkt direkt über dem Startpunkt, um die halbe Distanz zwischen den Rechtecken versetzt
            y = y - yDistanceBetween;
            end = new Point(x,y);

            // Endpunkt der dritten Linie entspricht dem Startpunkt der ersten Linie
            endThird = (Point)end.clone();
            endThird.y = startThird.y;

            linepartList.add(new Linepart(start, end));
        }

        //Dritte Linie
        {
            // Füge die dritte Linie zur Liste der Linienteile hinzu
            linepartList.add(new Linepart(startThird, endThird));
        }
    }

    /**
     * Gibt eine Liste mit den jeweiligen Lineparts zurück
     * @return Lineparts-Liste
     */
    public ArrayList<Linepart> getLinepartList() {
        return linepartList;
    }

}
