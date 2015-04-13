package view;

import gui.ElectricalComponentDialog;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitPanel extends JPanel implements ActionListener {

    java.util.List<CircuitComponent> components = new ArrayList<CircuitComponent>();
    java.util.List<CircuitConnection> connections = new ArrayList<CircuitConnection>();

    CircuitConnection newConnection = null;

    public CircuitPanel() {
        buildTestComponents();
        addActions();
    }

    private void addActions() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            int oldx, oldy;

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!e.isShiftDown()) {
                    CircuitComponent c = findComponent(e.getX(), e.getY());
                    clearTerminalsHover();
                    if (c == null) {
                        Terminal t = findTerminal(e.getX(), e.getY());

                        if (t != null) {
                            t.setHover(true);
                        }
                    }
                }

                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() % 2 == 0 && !mouseEvent.isConsumed()) {
                    CircuitComponent cc = findComponent(mouseEvent.getX(),mouseEvent.getY());

                    if (cc != null) {
                        ElectricalComponentDialog dlg = new ElectricalComponentDialog(cc);
                        dlg.addActionListener( CircuitPanel.this );
                        dlg.pack();
                        dlg.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                oldx = e.getX();
                oldy = e.getY();

                if (!e.isShiftDown()) {
                    Terminal t = findHoveredTerminal();
                    if (t != null) {
                        CircuitComponent endComponent = new EmptyComponent(e.getX(), e.getY());
                        newConnection = new CircuitConnection(t, endComponent.getTerminals().get(0));
                    }
                }

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
            public void mouseReleased(MouseEvent e) {
                Terminal t = findHoveredTerminal();
                if (t == null) {
                    newConnection = null;
                } else {
                    newConnection.dest = t;
                    connections.add(newConnection);
                    newConnection = null;
                }

                repaint();

                super.mouseReleased(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                if (newConnection != null) {
                    CircuitComponent c = findComponent(e.getX(), e.getY());
                    clearTerminalsHover();
                    if (c == null) {
                        Terminal t = findTerminal(e.getX(), e.getY());

                        if (t != null) {
                            t.setHover(true);
                        }
                    }

                    newConnection.dest.getParent().x = e.getX();
                    newConnection.dest.getParent().y = e.getY();
                } else {

                    int dx = e.getX() - oldx;
                    int dy = e.getY() - oldy;

                    for (CircuitComponent cc : components) {
                        if (cc.isSelected()) {
                            cc.x += dx;
                            cc.y += dy;
                        }
                    }

                    oldx = e.getX();
                    oldy = e.getY();
                }



                repaint();
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private Terminal findHoveredTerminal() {
        for (CircuitComponent c: components) {
            Terminal t = c.getHoveredTerminal();
            if (t != null)
                return t;
        }
        return null;
    }

    private Terminal findTerminal(int x, int y) {
        for (CircuitComponent cc: components) {
            for (Terminal t: cc.getTerminals()) {
                if (t.isInside(x,y))
                    return t;
            }
        }
        return null;
    }

    private void clearTerminalsHover() {
        for (CircuitComponent cc: components) {
            for (Terminal t: cc.getTerminals()) {
                t.setHover(false);
            }
        }
    }

    private CircuitComponent findComponent(int x, int y) {
        for (CircuitComponent cc: components) {
            if (cc.isInside(x,y)) {
                return cc;
            }
        }
        return null;
    }

    private void buildTestComponents() {
        CircuitComponent c1, c2, c3, c4, c5, c6, c7;
        components.add(c1 = new EmptyComponent(20, 20));
        components.add(c2 = new EmptyComponent(100,20));
        components.add(c3 = new EmptyComponent(20, 80));
        components.add(c4 = new EmptyComponent(200, 140));
        c4.name = "Prostokącik";
        components.add(c5 = new ResistorView(150, 200));
        components.add(c6 = new ResistorView(10, 250));
        c6.setOrientation(CircuitComponent.Orientation.Vertical);

        c5.name = "R5";
        c6.name = "R6";

        components.add(c7 = new CapacitorView(30, 300));

        connections.add(new CircuitConnection(c1.getFirstTerminal(), c2.getFirstTerminal()));
        connections.add(new CircuitConnection(c3.getFirstTerminal(), c2.getFirstTerminal()));
        connections.add(new CircuitConnection(c3.getFirstTerminal(), c4.getFirstTerminal()));
        connections.add(new CircuitConnection(c1.getFirstTerminal(), c4.getFirstTerminal()));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawString("Circuits Simulator " + Main.VERSION, 5, 20);

        for (CircuitComponent cc: components) {
            cc.paintComponent(graphics);
        }

        graphics.setColor(Color.BLUE);
        for (CircuitConnection con: connections) {
            graphics.drawLine(
                    con.src.getX(), con.src.getY(),
                    con.dest.getX(), con.dest.getY()
                    );
        }

        if (newConnection != null) {
            graphics.drawLine(
                    newConnection.src.getX(), newConnection.src.getY(),
                    newConnection.dest.getX(), newConnection.dest.getY()
            );
        }
    }

    public void addCircuitComponent(CircuitComponent c) {
        components.add(c);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("repaint")) {
            repaint();
        }
    }
}
