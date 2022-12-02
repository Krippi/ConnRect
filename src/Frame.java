import javax.swing.JFrame;

public class Frame extends JFrame{
    public Frame(String title) {
        setTitle(title);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel();
        add(panel);
    }

}
