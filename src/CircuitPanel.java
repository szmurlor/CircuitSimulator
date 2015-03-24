import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                for (CircuitComponent cc: components) {
                    if (cc.isInside(e.getX(), e.getY()))
                        cc.setSelected(true);
                }

                repaint();
            }
        });
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

        graphics.drawString("Circuits Simulator v0.1Alpha", 5, 20);

        for (CircuitComponent cc: components) {
            if (cc.isSelected())
                graphics.setColor(Color.RED);
            else
                graphics.setColor(Color.BLACK);
            graphics.drawRect(cc.x, cc.y, cc.w, cc.h);
        }
    }
}
