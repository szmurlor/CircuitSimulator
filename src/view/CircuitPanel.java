package view;

import gui.CicuitComponentDialog;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitPanel extends JPanel {

    java.util.List<CircuitComponentView> components = new ArrayList<CircuitComponentView>();
    java.util.List<CircuitConnection> connections = new ArrayList<CircuitConnection>();

    public CircuitPanel() {
        buildTestComponents();
        addActions();
    }

    private void addActions() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            int oldx, oldy;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() % 2 == 0 && !e.isConsumed()) {
                    CicuitComponentDialog dialog = new CicuitComponentDialog();
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                oldx = e.getX();
                oldy = e.getY();

                for (CircuitComponentView cc: components) {
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

                for (CircuitComponentView cc: components) {
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
        CircuitComponentView c1, c2, c3, c4, c5, c6;
        components.add(c1 = new CircuitComponentView(20, 20, 30, 40));
        components.add(c2 = new CircuitComponentView(100,20,60,40));
        components.add(c3 = new CircuitComponentView(20, 80, 10, 40));
        components.add(c4 = new CircuitComponentView(200, 140, 50, 40));
        components.add(c5 = new ResistorView(100, 240));
        components.add(c6 = new ResistorView(130, 280));
        c6.setOrientation(CircuitComponentView.Orientation.Vertical);

        connections.add(new CircuitConnection(c1, c2));
        connections.add(new CircuitConnection(c3, c2));
        connections.add(new CircuitConnection(c3, c4));
        connections.add(new CircuitConnection(c1, c4));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawString("Circuits Simulator " + Main.VERSION, 5, 20);

        for (CircuitComponentView cc: components) {
            cc.paintComponent(graphics);
        }

        for (CircuitConnection con: connections) {
            con.paint(graphics);
        }
    }

    public void addElectricalComponent(ResistorView r) {
        components.add(r);
    }
}
