import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    public Frame(String title) {
        setTitle(title);
        setSize(800,600);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel();
        add(panel);

    }

}
