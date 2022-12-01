import java.awt.*;
import java.util.ArrayList;

public class Line {
    public static enum linetypes{
        STRAIGHT,
        ANGLED,
    };
    private linetypes linetype = linetypes.STRAIGHT;
    private ArrayList<Linepart> linepartList = new ArrayList<>();

    public Line(Rectangle r1, Rectangle r2){

        Point start;
        Point end;

        switch (linetype){
            case STRAIGHT:
                start = r1.getMidPoint();
                end = r2.getMidPoint();

                linepartList.add(new Linepart(start, end));
                break;
            case ANGLED:
                //First Line

                break;
        }

    }

    public ArrayList<Linepart> getLinepartList() {
        return linepartList;
    }
}
