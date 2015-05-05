
package view.components;

import java.awt.*;

import javafx.geometry.Orientation;
import view.CircuitComponent;
import view.CircuitSimulator;
import view.TerminalView;

import java.awt.*;

public class CapacitorView extends CircuitComponent {
    public CapacitorView(int x, int y) {
        super(x, y, 16, 10, "Pojemność: ", "[\u03BCF]");

        int r = CircuitSimulator.TERMINAL_R;
        getTerminals().add( new TerminalView(this, -2*r, h/2-r, -2*r, -3*r) );
        getTerminals().add( new TerminalView(this, w, h/2-r, 4*r, 5*r) );
    }

    @Override
    public String getTypeCode() {
        return "C";
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        int d = 6;
        int hr = 6;

        if (getOrientation() == Orientation.Horizontal) {
            int by = y + h/2;
            g.drawLine(x, by, x + d, by);

            g.drawLine(x + d, by + hr, x + d , by - hr);
            g.drawLine(x + w - d , by + hr, x + w - d, by -hr);

            g.drawLine(x + w - d, by, x + w, by);
        } else {
            int bx = x + h/2;

            g.drawLine(bx, y, bx, y + d);

            g.drawLine(bx + hr, y + d, bx - hr, y + d);
            g.drawLine(bx + hr, y + hr/2 + d , bx -hr, y + hr/2 + d);

            g.drawLine(bx, y + w - d, bx, y + w);
        }

        super.paintComponent(g);
    }
}

