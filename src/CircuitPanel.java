import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitPanel extends JPanel {

    java.util.List<CircuitComponent> components = new ArrayList<CircuitComponent>();
    java.util.List<CircuitConnection> connections = new ArrayList<CircuitConnection>();

    public CircuitPanel() {
        buildTestComponents();
        addActions();
    }

    private void addActions() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            int oldx, oldy;

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                oldx = e.getX();
                oldy = e.getY();

                for (CircuitComponent cc: components) {
                    if (!e.isShiftDown())
                        cc.setSelected(false);
                    if (cc.isInside(e.getX(), e.getY()))
                        if (e.isShiftDown())
                            cc.setSelected(!cc.isSelected());
                        else
                            cc.setSelected(true);
                }

                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                int dx = e.getX() - oldx;
                int dy = e.getY() - oldy;

                for (CircuitComponent cc: components) {
                    if (cc.isSelected()) {
                        cc.x += dx;
                        cc.y += dy;
                    }
                }

                oldx = e.getX();
                oldy = e.getY();

                repaint();
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private void buildTestComponents() {
        CircuitComponent c1, c2, c3, c4;
        components.add(c1 = new CircuitComponent(20, 20, 30, 40));
        components.add(c2 = new CircuitComponent(100,20,60,40));
        components.add(c3 = new CircuitComponent(20, 80, 10, 40));
        components.add(c4 = new CircuitComponent(200, 140, 50, 40));

        connections.add(new CircuitConnection(c1, c2));
        connections.add(new CircuitConnection(c3, c2));
        connections.add(new CircuitConnection(c3, c4));
        connections.add(new CircuitConnection(c1, c4));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawString("Circuits Simulator " + Main.VERSION, 5, 20);

        for (CircuitComponent cc: components) {
            if (cc.isSelected())
                graphics.setColor(Color.RED);
            else
                graphics.setColor(Color.BLACK);
            graphics.drawRect(cc.x, cc.y, cc.w, cc.h);
        }

        graphics.setColor(Color.BLUE);
        for (CircuitConnection con: connections) {
            graphics.drawLine(
                    con.src.getMidX(), con.src.getMidY(),
                    con.dest.getMidX(), con.dest.getMidY()
                    );
        }
    }
}
