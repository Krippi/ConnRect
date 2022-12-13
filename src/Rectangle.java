import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rectangle{
    private Point position;
    private int WIDTH = 50;  // statisch, ggf. dynamisch als Erweiterung
    private int HEIGHT = 50; // statisch, ggf. dynamisch als Erweiterung
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

    public void setPosition(Point newPos){
        this.position = newPos;
    }
    
    public boolean rectsOverlap(Rectangle rect){
        Point tl = rect.getPosition();
        Point tr = new Point(rect.getPosition().x+rect.WIDTH,rect.getPosition().y);
        Point bl = new Point(rect.getPosition().x,rect.getPosition().y+HEIGHT);
        Point br = new Point(rect.getPosition().x+rect.WIDTH,rect.getPosition().y+HEIGHT);

        // check top left      check top right    check bottom left  check bottom right
        if (pointInRect(tl) || pointInRect(tr) || pointInRect(bl) || pointInRect(br)){
            return true;
        }
        return false;
    }

}
