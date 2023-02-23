//M
import java.awt.Point;

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

    /**
     * Gibt Koordinaten vom Mittelpunkt eines Rechtecks wieder.
     * @return Mittelpunkt als Point
     * @see Point 
     */
    public Point getMidPoint(){
        Point midPoint = new Point();
        midPoint.x = position.x + WIDTH/2;
        midPoint.y = position.y + HEIGHT/2;
        return midPoint;
    }

    /**
     * Prüft, ob ein Punkt im Rechteck liegt.
     * @return Gibt true zurück, wenn der übergebene Punkt im Rechteck liegt.
     * @param p zu prüfender Punkt
     * @see Point 
     */
    public boolean pointInRect(Point p){
        return p.x <= this.position.x+WIDTH && 
               p.x >= this.position.x && 
               p.y <= this.position.y+HEIGHT && 
               p.y >= this.position.y;
    }

    /**
     * Übergebe neue Position vom Rechteck
     * @param newPos neuer Punkt
     * @see Point 
     */
    public void setPosition(Point newPos){
        this.position = newPos;
    }
    
    /**
     * Prüft, ob das übergebene Rechteck überlappt
     * @return Gibt true zurück, wenn das übergebene Rechteck an mindestens einer Stelle mit dem Rechteck überlappt.
     * @param rect Objekt vom Typ Rectangle
     * @see Point 
     */
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
