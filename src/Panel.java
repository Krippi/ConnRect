import java.awt.*;
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
        Graphics2D graphics2D = (Graphics2D)getGraphics();
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.setColor(Color.CYAN);
        graphics2D.fillRect(rect.getPosition().x, rect.getPosition().y, rect.getHeight(), rect.getWidth());
        //graphics2D.drawRect(rect.getPosition().x, rect.getPosition().y, rect.getHeight(), rect.getWidth());
    }

    private void drawLine(){
        for (Linepart part : connectionLine.getLinepartList()) {
            Graphics2D graphics2D = (Graphics2D)getGraphics();
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawLine(part.getStartPoint().x, part.getStartPoint().y,part.getEndPoint().x, part.getEndPoint().y);
        }
    }

    private void redraw(){
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
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
                if (part.pointInLinepart(e.getPoint())) {
                    showLinetypes();
                }
            }
        }
    }

    public void showLinetypes() {
        System.out.println("Click on Line");
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(movingRectState){
            redraw();
            movingRectState = false;
            movingRect.setPosition(e.getPoint());
            for (Rectangle rect:rectList) {
                drawRectangle(rect);
            }

            connectionLine = new Line(rectList.get(0),rectList.get(1));
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
