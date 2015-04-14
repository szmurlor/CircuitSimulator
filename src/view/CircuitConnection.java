package view;

import java.awt.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitConnection {
    TerminalView src;
    TerminalView dest;
    private boolean selected;

    public boolean isInside(int x, int y) {
        double x0 = x;
        double y0 = y;
        double x1 = src.getX();
        double y1 = src.getY();
        double x2 = dest.getX();
        double y2 = dest.getY();
        double d = Math.abs( (y2-y1)*x0-(x2-x1)*y0 + x2*y1 - y2*x1 ) / Math.sqrt((y2-y1)*(y2-y1) + (x2-x1)*(x2-x1));

        return d < CircuitSimulator.CONNECTION_DISTANCE;
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
}
