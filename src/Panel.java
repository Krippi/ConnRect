import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Panel extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList <Rectangle> rectList = new ArrayList<>();
    private Line connectionLine;
    private Line.linetypes activeLinetype = Line.linetypes.STRAIGHT;
    private Rectangle movingRect;
    private boolean movingRectState = false;
    private Point dragPoint;
    private Point lineClickedPoint;

    JComboBox<Line.linetypes> menu;

    public Panel(){
        addMouseListener(this);
        addMouseMotionListener(this);

        menu = new JComboBox<>(Line.linetypes.values());

        menu.setVisible(false);
        // get linetypes & print in Combo-Box, nicht sichtbar
        menu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    lineClickedPoint = null;
                    activeLinetype = (Line.linetypes)e.getItem();
                    menu.setVisible(false);
                    repaint();
                }
            }
        });
        add(menu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        for (Rectangle rect:rectList) {
            ((Graphics2D)g).setStroke(new BasicStroke(2));

            g.fillRect(rect.getPosition().x, rect.getPosition().y, rect.getHeight(), rect.getWidth());
        }

        if(connectionLine != null){


            if(rectList.get(0).pointInRect(rectList.get(1).getPosition())
                && rectList.get(0).pointInRect(rectList.get(1).getPosition())){

            }

            connectionLine = new Line(rectList.get(0),rectList.get(1),activeLinetype);
            g.setColor(Color.BLACK);
            for (Linepart part : connectionLine.getLinepartList()) {
                ((Graphics2D)g).setStroke(new BasicStroke(3));
                ((Graphics2D)g).setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g.drawLine(part.getStartPoint().x, part.getStartPoint().y,part.getEndPoint().x, part.getEndPoint().y);
            }
        }

        //System.out.println(menu.getLocation());

        if(lineClickedPoint != null){
            menu.setLocation(lineClickedPoint);
            menu.setVisible(true);
        }

    }

    /*private void drawRectangle(Rectangle rect){
        Graphics2D graphics2D = (Graphics2D)getGraphics();
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.setColor(Color.CYAN);
        graphics2D.fillRect(rect.getPosition().x, rect.getPosition().y, rect.getHeight(), rect.getWidth());
        //graphics2D.drawRect(rect.getPosition().x, rect.getPosition().y, rect.getHeight(), rect.getWidth());
    }

    private void drawLine(){
        connectionLine = new Line(rectList.get(0),rectList.get(1),activeLinetype);
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

    private void showLinetypes(Point location) {
        menu.setLocation(location);
        menu.setVisible(true);

    } */

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lineClickedPoint = null;
        menu.setVisible(false);
        if(rectList.size() <= 1){
            Rectangle newRectangle = new Rectangle(e.getPoint());
            rectList.add(newRectangle);
            repaint();
        }
        if(rectList.size() == 2 && connectionLine == null){
            connectionLine = new Line(rectList.get(0),rectList.get(1), activeLinetype);
        }else if(rectList.size() == 2){
            for (Rectangle rectangle : rectList) {
                if(rectangle.pointInRect(e.getPoint())){
                    movingRectState = true;
                    movingRect = rectangle;
                    dragPoint = new Point(e.getPoint().x - movingRect.getPosition().x, e.getPoint().y - movingRect.getPosition().y);
                    return;
                }
            }

            for (Linepart part: connectionLine.getLinepartList()) {
                if (part.pointInLinepart(e.getPoint())) {
                    lineClickedPoint = e.getPoint();
                    repaint();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(movingRectState){
            movingRectState = false;

            int x = e.getPoint().x - dragPoint.x;
            int y = e.getPoint().y - dragPoint.y;
            movingRect.setPosition( new Point(x,y));

            repaint();

            dragPoint = null;
            movingRect = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(movingRectState){
            int x = e.getPoint().x - dragPoint.x;
            int y = e.getPoint().y - dragPoint.y;
            movingRect.setPosition( new Point(x,y));

            repaint();
            
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
