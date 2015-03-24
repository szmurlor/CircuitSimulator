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
        components.add(new CircuitComponent(20, 20, 30, 40));
        components.add( new CircuitComponent(100,20,60,40));
        components.add(new CircuitComponent(20, 80, 10, 40));
        components.add(new CircuitComponent(200, 140, 50, 40));
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
    }
}
