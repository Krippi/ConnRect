import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
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
        Panel panel = new Panel();                          // Erzeugen eines neuen Panels
        add(panel);                                         // Hinzufügen des Panels zum Frame
        pack();                                             // Anpassen der Größe des Frames an die Größe des Panels
        setLocationRelativeTo(null);                        // Zentrieren des Frames auf dem Bildschirm
        setSize(800,600);                       // Festlegen der Größe des Fensters auf 800x600 Pixel

    }

}
