package view;

import gui.ElectricalComponentDialog;
import gui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitPanel extends JPanel implements ActionListener {

    java.util.List<CircuitComponent> components = new ArrayList<CircuitComponent>();
    java.util.List<CircuitConnection> connections = new ArrayList<CircuitConnection>();
    private CircuitConnection temporaryConnection = null;

    public CircuitPanel() {
        buildTestComponents();
        addActions();
    }

    private void addActions() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            int oldx, oldy;

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

                requestFocusInWindow();

                TerminalView t = findTerminal(e.getX(), e.getY());
                if (t != null) {
                    EmptyComponent ec = new EmptyComponent(e.getX(), e.getY());
                    TerminalView temporaryTerminal = ec.getTerminals().get(0);
                    temporaryConnection = new CircuitConnection(t, temporaryTerminal);
                } else {
                    oldx = e.getX();
                    oldy = e.getY();

                    for (CircuitComponent cc : components) {
                        if (!e.isShiftDown())
                            cc.setSelected(false);
                        if (cc.isInside(e.getX(), e.getY()))
                            if (e.isShiftDown())
                                cc.setSelected(!cc.isSelected());
                            else
                                cc.setSelected(true);
                    }
                }

                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                if (temporaryConnection != null) {
                    temporaryConnection.dest.parent.x = e.getX();
                    temporaryConnection.dest.parent.y = e.getY();

                    TerminalView t = findTerminalAndClearHover(e.getX(), e.getY());
                    if (t != null)
                        t.setHovered(true);
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

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                TerminalView t = findTerminalAndClearHover(mouseEvent.getX(), mouseEvent.getY());
                if (t != null)
                    t.setHovered(true);

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (temporaryConnection != null) {
                    TerminalView t = findTerminal(e.getX(), e.getY());
                    if (t != null) {
                        temporaryConnection.dest = t;
                        connections.add(temporaryConnection);
                    }

                    temporaryConnection = null;
                }
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                System.out.println("jakis klawisz: " + keyEvent.getKeyChar());

                if (keyEvent.getKeyChar() == KeyEvent.VK_DELETE) {
                    Iterator<CircuitComponent> it = components.iterator();
                    while (it.hasNext()) {
                        CircuitComponent cc = it.next();
                        if (cc.isSelected()) {
                            connections.removeAll(findConnections(cc));
                            it.remove();;
                        }
                    }
                }

                repaint();
            }

            @Override public void keyTyped(KeyEvent keyEvent) { }
            @Override public void keyReleased(KeyEvent keyEvent) {}
        });

        setFocusable(true);
    }

    private Collection<CircuitConnection> findConnections(CircuitComponent cc) {
        List<CircuitConnection> res = new LinkedList<CircuitConnection>();
        for (CircuitConnection con: connections) {
            if (con.src.parent == cc || con.dest.parent == cc)
                res.add(con);
        }
        return res;
    }

    private TerminalView findTerminalAndClearHover(int x, int y) {
        return _findTerminal(x, y, true);
    }

    private TerminalView findTerminal(int x, int y) {
        return _findTerminal(x,y, false);
    }

    private TerminalView _findTerminal(int x, int y, boolean clearHover) {
        TerminalView t = null;

        for (CircuitComponent c: components) {
            for (TerminalView tv :c.getTerminals()) {
                if (clearHover)
                    tv.setHovered(false);
                if (tv.isInside(x,y)) {
                    t = tv;
                }
            }
        }

        return t;
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
        CircuitComponent c1, c2, c3, c4, c5, c6;
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

        connections.add(new CircuitConnection(c1.getTerminals().get(0), c2.getTerminals().get(0)));
        connections.add(new CircuitConnection(c3.getTerminals().get(0), c2.getTerminals().get(0)));
        connections.add(new CircuitConnection(c3.getTerminals().get(0), c4.getTerminals().get(0)));
        connections.add(new CircuitConnection(c1.getTerminals().get(0), c4.getTerminals().get(0)));
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
            con.paintConnection(graphics);
        }

        if (temporaryConnection != null) {
            graphics.setColor(Color.BLUE);
            temporaryConnection.paintConnection(graphics);
            graphics.setColor(Color.BLACK);
        }
    }

    public void addCircuitComponent(CircuitComponent comp) {
        components.add(comp);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("repaint")) {
            repaint();
        }
    }
}
