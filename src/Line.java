import java.awt.*;
import java.util.ArrayList;

public class Line {
    private String linetype = "straight";
    private ArrayList<Linepart> linepartList = new ArrayList<>();

    public Line(Rectangle r1, Rectangle r2){

        Point start;
        Point end;

        switch (linetype){
            case "straight":
                start = r1.getMidPoint();
                end = r2.getMidPoint();

                linepartList.add(new Linepart(start, end));
                break;
            case "angled":
                //First Line

                break;
        }

    }

    public ArrayList<Linepart> getLinepartList() {
        return linepartList;
    }
}
