import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    public Frame(String title) {
        setTitle(title);
        setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel();
        panel.setBackground(new Color(0,255,0,64));
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setSize(800,600);

    }

}
