//M
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    public Frame(String title) {
        //M
        setTitle(title);
        setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //N
        Panel panel = new Panel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setSize(800,600);

    }

}
