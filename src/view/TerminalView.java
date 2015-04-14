package view;

import java.awt.*;

/**
 * Created by GR6 on 14.04.15.
 */
public class TerminalView {
    CircuitComponent parent;
    int dx;
    int dy;

    public TerminalView(CircuitComponent parent, int dx, int dy) {
        this.parent = parent;
        this.dx = dx;
        this.dy = dy;
    }

    public void paint(Graphics g) {

        g.drawArc(getX(), getY(),
                2*CircuitSimulator.TERMINAL_R, 2*CircuitSimulator.TERMINAL_R,
                0, 360);
    }

    private int getY() {
        if (parent.getOrientation() == CircuitComponent.Orientation.Horizontal)
            return parent.y + dy;
        else
            return parent.y + dx;
    }

    private int getX() {
        if (parent.getOrientation() == CircuitComponent.Orientation.Horizontal)
            return parent.x + dx;
        else
            return parent.x + dy;
    }
}
