//N
import java.awt.*;
import java.util.ArrayList;

public class Line {
    public enum linetypes{
        STRAIGHT,
        ANGLED
    }
    private ArrayList<Linepart> linepartList = new ArrayList<>();

    public Line(Rectangle rect1, Rectangle rect2, linetypes linetype){
        int r1_X = rect1.getPosition().x;
        int r1_Y = rect1.getPosition().y;
        int r1_H = rect1.getHeight();
        int r2_X = rect2.getPosition().x;
        int r2_Y = rect2.getPosition().y;
        int r2_H = rect1.getHeight();

        switch (linetype){
            case STRAIGHT:
                createStraightLine(rect1, rect2);
                break;
            case ANGLED:
               if(r1_Y == r2_Y ||
                       (r1_Y < r2_Y && r1_Y + r1_H > r2_Y) ||
                       (r1_Y < r2_Y + r2_H && r1_Y + r1_H > r2_Y + r2_H)){
                   if(r1_X < r2_X ){
                       createAngledHorizontalLine(rect1, rect2);
                   }else{
                       createAngledHorizontalLine(rect2, rect1);
                   }
                }else if(r1_Y < r2_Y){
                    createAngledVerticalLine(rect1,rect2);
                }else if(r1_Y > r2_Y){
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
            endThird.x = startThird.x;

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
            endThird.y = startThird.y;

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
