import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasse erstellt und kommentiert von Marius Waterkotte & Niclas Leroy
 */
public class Frame extends JFrame{
    private Panel panel;
    /**
     * Konstruktor der Frame-Klasse, der einen Fenstertitel entgegennimmt
     * @param title Fenstertitel
     */
    public Frame(String title) {
        //M
        setTitle(title);                                    // Setzen des Fenstertitels
        setLayout(new BorderLayout());                      // Festlegen des Layouts des Fensters
        setVisible(true);                                   // Setzen des Fensters auf sichtbar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Festlegen des Verhaltens des Fensters beim Schließen
        //N
        panel = new Panel();                                // Erzeugen eines neuen Panels
        add(panel);                                         // Hinzufügen des Panels zum Frame
        pack();                                             // Anpassen der Größe des Frames an die Größe des Panels
        setLocationRelativeTo(null);                        // Zentrieren des Frames auf dem Bildschirm
        setSize(800,600);                       // Festlegen der Größe des Fensters auf 800x600 Pixel


        //M
        JMenuBar menuBar = new JMenuBar();                  // Erzeugen einer Menübar
        setJMenuBar(menuBar);                               // Hinzufügen der Menübar zum Frame
        JMenu helpMenu = new JMenu("Help");              // Erzeugen eines Menüpunktes mit Titel "Help"
        menuBar.add(helpMenu);                              // Hinzufügen des Menüpunktes zur Menübar

        // Abfrage, ob mit linker Maustaste auf Help-Menü geklickt wurde, um die Hilfe anzuzeigen
        helpMenu.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1){
                    JOptionPane.showMessageDialog(Frame.this, "1. Mit einer beliebigen Maustaste auf die leere Zeichenfläche klicken, um ein Rechteck zu erstellen.\n"+
                    "2. Erneut mit einer beliebigen Maustaste auf die leere Zeichenfläche klicken, um ein zweites Rechtecke zu erstellen.\n"+
                    "3. Zum Ändern der Linienform mit einer beliebigen Maustaste auf die Linie klicken.\n\n"+
                    "Die Rechtecke können per Drag&Drop verschoben werden.","Information", 1);
                }
            }
        });

    }

}
