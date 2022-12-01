import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel{
    ArrayList <Rectangle> rectList = new ArrayList<>();
    mouseclicked(){
        // Rechteck(e) erzeugen
        if (rectList.size() == 2){
        }else{
            rectList.add(new Rectangle(pos));
        }

    }
}
