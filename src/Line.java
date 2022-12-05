import java.awt.*;
import java.util.ArrayList;

public class Line {
    //private String[] linetypes = {"STRAIGHT", "ANGLED"};
    public enum linetypes{
        STRAIGHT,
        ANGLED
    }
    private ArrayList<Linepart> linepartList = new ArrayList<>();

    public Line(Rectangle rect1, Rectangle rect2, linetypes linetype){
        switch (linetype){
            case STRAIGHT:
                createStraightLine(rect1, rect2);
                break;
            case ANGLED:
                if( rect1.getPosition().x < rect2.getPosition().x &&
                        (rect1.getPosition().y == rect2.getPosition().y ||
                                (rect1.getPosition().y < rect2.getPosition().y && rect1.getPosition().y + rect1.getHeight() > rect2.getPosition().y) ||
                                (rect1.getPosition().y < rect2.getPosition().y + rect2.getHeight() && rect1.getPosition().y + rect1.getHeight() > rect2.getPosition().y + rect2.getHeight()))) {
                    createAngledHorizontalLine(rect1, rect2);
                }else if( rect1.getPosition().x > rect2.getPosition().x &&
                        (rect1.getPosition().y == rect2.getPosition().y ||
                                (rect1.getPosition().y < rect2.getPosition().y && rect1.getPosition().y + rect1.getHeight() > rect2.getPosition().y) ||
                                (rect1.getPosition().y < rect2.getPosition().y + rect2.getHeight() && rect1.getPosition().y + rect1.getHeight() > rect2.getPosition().y + rect2.getHeight()))) {
                    createAngledHorizontalLine(rect2, rect1);
                }else if(rect1.getPosition().y < rect2.getPosition().y){
                    createAngledVerticalLine(rect1,rect2);
                }else if(rect1.getPosition().y > rect2.getPosition().y){
                    createAngledVerticalLine(rect2,rect1);
                }
                break;
        }
    }

    private void createStraightLine(Rectangle rect1, Rectangle rect2){
        Point start;
        Point end;

        start = rect1.getMidPoint();
        end = rect2.getMidPoint();

        linepartList.add(new Linepart(start, end));
    }

    private void createAngledHorizontalLine(Rectangle rect1, Rectangle rect2){
        Point start;
        Point end;
        int x;
        int y;
        int xDistanceBetween;
        Point startThird;
        Point endThird;

        xDistanceBetween = rect2.getPosition().x -  rect1.getWidth() - rect1.getPosition().x;
        xDistanceBetween /= 2;

        //First Line
        {
            x = rect1.getPosition().x + rect1.getWidth();
            y = rect1.getPosition().y + (rect1.getHeight() / 2);
            start = new Point(x,y);

            x = x + xDistanceBetween;
            end = new Point(x - 1,y);
            startThird = (Point) end.clone();

            linepartList.add(new Linepart(start, end));
        }

        //Second Line
        {
            x = rect2.getPosition().x;
            y = rect2.getPosition().y + (rect2.getHeight() / 2);
            start = new Point(x - 1,y);

            x = x - xDistanceBetween;
            end = new Point(x - 1,y);
            endThird = (Point)end.clone();

            linepartList.add(new Linepart(start, end));
        }

        //Third Line
        {
            linepartList.add(new Linepart(startThird, endThird));
        }
    }

    private void createAngledVerticalLine(Rectangle rect1, Rectangle rect2){
        Point start;
        Point end;
        int x;
        int y;
        int yDistanceBetween;
        Point startThird;
        Point endThird;

        yDistanceBetween = rect2.getPosition().y -  rect1.getHeight() - rect1.getPosition().y;
        yDistanceBetween /= 2;

        //First Line
        {
            x = rect1.getPosition().x + (rect1.getWidth() / 2);
            y = rect1.getPosition().y + rect1.getHeight();
            start = new Point(x,y);

            y = y + yDistanceBetween;
            end = new Point(x,y -1);
            startThird = (Point) end.clone();
            startThird.y += 1;

            linepartList.add(new Linepart(start, end));
        }

        //Second Line
        {
            x = rect2.getPosition().x + (rect2.getWidth() / 2);
            y = rect2.getPosition().y;
            start = new Point(x,y - 1);

            y = y - yDistanceBetween;
            end = new Point(x,y);
            endThird = (Point)end.clone();
            endThird.y -= 1;

            linepartList.add(new Linepart(start, end));
        }

        //Third Line
        {
            linepartList.add(new Linepart(startThird, endThird));
        }
    }


    public ArrayList<Linepart> getLinepartList() {
        return linepartList;
    }

}
