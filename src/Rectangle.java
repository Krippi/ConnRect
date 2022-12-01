import java.awt.Point;

public class Rectangle{
    private Point position;
    private int WIDTH = 30;  // statisch, ggf. dynamisch als Erweiterung
    private int HEIGHT = 30; // statisch, ggf. dynamisch als Erweiterung
    public Rectangle(Point position){
        this.position = (Point)position.clone();
    }
    public Point getPosition(){
        return position;
    }
    public Integer getWidth(){
        return WIDTH;
    }
    public Integer getHeight(){
        return HEIGHT;
    }
    // gibt Koordinaten vom Mittelpunkt wieder
    // für Verbindung Straight Line
    public Point getMidPoint(){
        Point midPoint = new Point();
        midPoint.x = position.x + WIDTH/2;
        midPoint.y = position.y + HEIGHT/2;
        return midPoint;
    }
    // return true, wenn Punkt im Rechteck liegt
    public boolean pointInRect(Point p){
        return p.x <= this.position.x+WIDTH && 
               p.x >= this.position.x && 
               p.y <= this.position.y+HEIGHT && 
               p.y >= this.position.y;
    }
}
