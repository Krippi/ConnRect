import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener {
    private ArrayList <Rectangle> rectList = new ArrayList<>();
    private Line connectionLine;
    private Rectangle movingRect;
    private boolean movingRectState = false;

    public Panel(){
        addMouseListener(this);
    }

    private void drawRectangle(Rectangle rect){
        getGraphics().drawRect(rect.getPosition().x, rect.getPosition().y, rect.getHeight(), rect.getWidth());
    }

    private void drawLine(){
        for (Linepart part : connectionLine.getLinepartList()) {
            getGraphics().drawLine(part.getStartPoint().x, part.getStartPoint().y,part.getEndPoint().x, part.getEndPoint().y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Click");
        if(rectList.size() <= 1){
            Rectangle newRectangle = new Rectangle(e.getPoint());
            rectList.add(newRectangle);
            drawRectangle(newRectangle);
        }
        if(rectList.size() == 2 && connectionLine == null){
            connectionLine = new Line(rectList.get(0),rectList.get(1));
            drawLine();
        }else if(rectList.size() == 2){
            for (Rectangle rectangle : rectList) {
                if(rectangle.pointInRect(e.getPoint())){
                    movingRectState = true;
                    movingRect = rectangle;
                    return;
                }
            }

            for (Linepart part: connectionLine.getLinepartList()) {
                //part.pointInRect()
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(movingRectState){
            movingRectState = false;
            //movingRect.setPosition = e.getPoint();
            drawRectangle(movingRect);
            drawLine();

            movingRect = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
