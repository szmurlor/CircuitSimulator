package view;

import java.awt.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitConnection {
    protected TerminalView src;
    protected TerminalView dest;
    private boolean selected;

    public boolean isInside(int x, int y) {
        int r = CircuitSimulator.TERMINAL_R;
        double x0 = x;
        double y0 = y;
        double x1 = src.getX()+r;
        double y1 = src.getY()+r;
        double x2 = dest.getX()+r;
        double y2 = dest.getY()+r;
        double d = Math.abs( (y2-y1)*x0-(x2-x1)*y0 + x2*y1 - y2*x1 ) / Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

        double minx = Math.min(src.getX(), dest.getX()) - CircuitSimulator.CONNECTION_DISTANCE;
        double miny = Math.min(src.getY(), dest.getY()) - CircuitSimulator.CONNECTION_DISTANCE;
        double maxx = Math.max(src.getX(), dest.getX()) + CircuitSimulator.CONNECTION_DISTANCE;
        double maxy = Math.max(src.getY(), dest.getY()) + CircuitSimulator.CONNECTION_DISTANCE;

        boolean between = (x0 >= minx && x0 <= maxx && y0 >= miny && y0 <= maxy);

        return d <= CircuitSimulator.CONNECTION_DISTANCE && between;
    }

    public CircuitConnection(TerminalView src, TerminalView dest) {
        this.src = src;
        this.dest = dest;
    }

    public void paintConnection(Graphics graphics) {
        int r = CircuitSimulator.TERMINAL_R;
        graphics.drawLine(
                src.getX()+r, src.getY()+r,
                dest.getX()+r, dest.getY()+r
        );
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public TerminalView getSrc() {
        return src;
    }

    public TerminalView getDest() {
        return dest;
    }
}
