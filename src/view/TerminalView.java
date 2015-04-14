package view;

import java.awt.*;

/**
 * Created by GR6 on 14.04.15.
 */
public class TerminalView {
    CircuitComponent parent;
    int dx;
    int dy;

    boolean hovered;

    public TerminalView(CircuitComponent parent, int dx, int dy) {
        this.parent = parent;
        this.dx = dx;
        this.dy = dy;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke currentStroke = g2d.getStroke();
        if (hovered) {
            g.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(2));
        } else {
            g.setColor(Color.BLACK);
        }

        g.drawArc(getX(), getY(),
                2*CircuitSimulator.TERMINAL_R, 2*CircuitSimulator.TERMINAL_R,
                0, 360);

        g2d.setStroke(currentStroke);
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

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public boolean isInside(int x, int y) {
        int d = 2*CircuitSimulator.TERMINAL_R;
        return x >= getX() && x <= (getX() + d) && y >= getY() && y <= (getY()+d);
    }
}
