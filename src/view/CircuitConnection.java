package view;

import java.awt.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitConnection {
    CircuitComponentView src;
    CircuitComponentView dest;

    public CircuitConnection(CircuitComponentView src, CircuitComponentView dest) {
        this.src = src;
        this.dest = dest;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.drawLine(src.getMidX(), src.getMidY(), dest.getMidX(), dest.getMidY());
    }
}
