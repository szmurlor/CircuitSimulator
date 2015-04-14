package view;

import java.awt.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitConnection {
    TerminalView src;
    TerminalView dest;

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
}
