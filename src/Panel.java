import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Klasse erstellt und kommentiert von Niclas Leroy
 */
public class Panel extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList <Rectangle> rectList = new ArrayList<>();         // Liste mit allen Rechtecken auf dem Panel
    private Line connectionLine;                                        // Linie, die die Rechtecke miteinander verbindet
    private Line.linetypes activeLinetype = Line.linetypes.STRAIGHT;    // Aktueller Linientyp der Verbindungslinie
    private Rectangle movingRect;                                       // Rechteck, das per drag verschoben wird
    private boolean movingRectState = false;                            // Flag, das angibt, ob ein Rechteck verschoben wird
    private Point dragPoint;                                            // Punkt, an dem das verschobene Rechteck gepackt wurde
    private Point lineClickedPoint;                                     // Punkt, an dem die Verbindungslinie geklickt wurde

    JComboBox<Line.linetypes> menu;                                     // Dropdown-Menu mit verschiedenen Linientypen

    /**
     * Konstruktor der Panel-Klasse. Fügt die benötigten Maus-Listener hinzu und erstellt das Dropdown-Menu.
     */
    public Panel(){
        addMouseListener(this);         // Listener für Mausklicks zum Panel hinzufügen
        addMouseMotionListener(this);   // Listener für Mausbewegeungen zum Panel hinzufügen

        menu = new JComboBox<>(Line.linetypes.values()); // Dropdown-Menu mit allen verfügbaren Linientypen erstellen

        menu.setVisible(false); // Dropdown-Menu standardmäßig ausblenden

        // Listener hinzufügen, um Änderungen am Dropdown-Menu zu erkennen
        menu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    lineClickedPoint = null;                        // Zurücksetzen des geklickten Punkts, da dieser nun nicht mehr benötigt wird
                    activeLinetype = (Line.linetypes)e.getItem();   // Aktuellen Linientyp setzen
                    menu.setVisible(false);                         // Dropdown-Menu wieder ausblenden
                    repaint();                                      // Panel neu zeichnen
                }
            }
        });
        add(menu); // Dropdown-Menu zum Panel hinzufügen
    }

    /**
     * Diese Methode wird aufgerunfen, wenn das Panel neu gezeichnet wird. Sie löscht zuerst
     * den alten Inhalt und zeichnet dann alle Rechtecke sowie die aktive Linie, wenn es eine gibt.
     * Sollte der Benutzer auf die Linie klicken, wird auch die ComboBox an der geklickten Stelle angezeigt.
     * @param g Grafik Objekt (In diesem Fall wird die Panel Grafik genommen)
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight()); // Lösche den alten Inhalt
        super.paintComponent(g);

        // Zeichnet alle Rechtecke aus rectList
        g.setColor(Color.CYAN);
        for (Rectangle rect:rectList) {
            ((Graphics2D)g).setStroke(new BasicStroke(2)); // Setze die Linienstärke auf 2

            g.fillRect(rect.getPosition().x, rect.getPosition().y, rect.getHeight(), rect.getWidth()); // Zeichnet das Rechteck auf dem Panel
        }

        // Wenn eine Verbindungslinie vorhanden ist und die Rechtecke nicht überlappen
        if(connectionLine != null && !rectList.get(0).rectsOverlap(rectList.get(1))){
            connectionLine = new Line(rectList.get(0),rectList.get(1),activeLinetype); // Erstelle eine neue Linie zwischen den Rechtecken

            // Zeichnet jeden Linienpart der Verbindungslinie
            g.setColor(Color.BLACK);
            for (Linepart part : connectionLine.getLinepartList()) {
                ((Graphics2D)g).setStroke(new BasicStroke(3)); // Setze die Linienstärke auf 3

                // Aktiviere das Antialiasing für glattere Linien
                ((Graphics2D)g).setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g.drawLine(part.getStartPoint().x, part.getStartPoint().y,part.getEndPoint().x, part.getEndPoint().y); // Zeichnet die Linie auf dem Panel
            }
        }

        // Wenn auf die Verbindungslinie geklickt wurde
        if(lineClickedPoint != null){
            menu.setLocation(lineClickedPoint); // Setze die Position der ComboBox auf die geklickte Stelle
            menu.setVisible(true);              // Zeige die ComboBox an
        }

    }

    /**
     * mouseClicked Methode (nicht benötigt)
     * @param e Mausereignis
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Maustaste gedrückt wird.
     * Sie ist zuständig, für die erstellung der Rechtecke und der Verbindungslinie.
     * @param e Mausereignis
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Setze lineClickedPoint auf null und verberge das Menü, da keine Linie geklickt wurde
        lineClickedPoint = null;
        menu.setVisible(false);

        // Wenn es weniger als 2 Rechtecke gibt, wird ein neues Rechteck erstellt und zur Liste hinzugefügt
        if(rectList.size() <= 1){
            Rectangle newRectangle = new Rectangle(e.getPoint());
            rectList.add(newRectangle);
            repaint();
        }

        // Wenn es genau 2 Rechtecke gibt und noch keine Verbindungslinie vorhanden ist, wird eine Verbindungslinie erstellt
        if(rectList.size() == 2 && connectionLine == null){
            connectionLine = new Line(rectList.get(0),rectList.get(1), activeLinetype);
        }
        // Wenn es genau 2 Rechtecke gibt und eine Verbindungslinie vorhanden ist
        else if(rectList.size() == 2){
            // Überprüfe, ob der Benutzer auf ein Rechteck geklickt hat
            for (Rectangle rectangle : rectList) {
                if(rectangle.pointInRect(e.getPoint())){
                    movingRectState = true;
                    movingRect = rectangle;

                    // Berechne die Differenz zwischen der Position des Rechtecks und dem Punkt, auf den geklickt wurde
                    dragPoint = new Point(e.getPoint().x - movingRect.getPosition().x, e.getPoint().y - movingRect.getPosition().y);

                    return;
                }
            }

            // Überprüfe, ob der Benutzer auf eine Linie geklickt hat
            for (Linepart part: connectionLine.getLinepartList()) {
                //M
                if (part.pointInLinepart(e.getPoint())) {
                    lineClickedPoint = e.getPoint();

                    // Zeichne das Menü an der Stelle, an der die Linie geklickt wurde
                    repaint();
                }
            }
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Maustaste losgelassen wird.
     * Wenn sich ein Rechteck im "verschiebenden" Zustand befindet, wird die Position entsprechend angepasst.
     * @param e Mausereignis
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // Wenn sich ein Rechteck bewegt hat, aktualisiere seine Position
        if(movingRectState){
            movingRectState = false;

            // Berechne die neue Position des Rechtecks anhand der Differenz zwischen der Position des Rechtecks und dem ursprünglichen Klickpunkt
            int x = e.getPoint().x - dragPoint.x;
            int y = e.getPoint().y - dragPoint.y;
            movingRect.setPosition( new Point(x,y));

            repaint();

            dragPoint = null;
            movingRect = null;
        }
    }

    /**
     * mouseEntered Methode (nicht benötigt)
     * @param e Mausereignis
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * mouseExited Methode (nicht benötigt)
     * @param e Mausereignis
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Wird aufgerufen, wenn der Mauszeiger mit gedrückter Maustaste bewegt wird.
     * Wenn sich das Rechteck im "verschiebenden" Zustand befindet,
     * wird seine Position an die aktuelle Mauszeigerposition verschoben.
     * @param e Mausereignis
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        //Wenn sich ein Rechteck im Bewegungsstatus befindet
        if(movingRectState){
            //Die Kooridinanten des REchtecks werden auf die Mausposition verschoben.
            int x = e.getPoint().x - dragPoint.x;
            int y = e.getPoint().y - dragPoint.y;
            movingRect.setPosition( new Point(x,y));

            repaint();
            
        }
    }

    /**
     * mouseMoved Methode (nicht benötigt)
     * @param e Mausereignis
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
