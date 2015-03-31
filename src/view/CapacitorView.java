package view;

import java.awt.*;

/**
 * Created by GR4 on 31.03.15.
 */
public class CapacitorView extends CircuitComponent {
    int d = 5;
    int hr = 3;

    public CapacitorView(int x, int y) {
        super(x, y, 40, 10);

        getTerminals().add(new Terminal(this, -2 * hr, h / 2 - hr, hr));
        getTerminals().add(new Terminal(this, w, h / 2 - hr, hr));
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        if (getOrientation() == Orientation.Horizontal) {
            int by = y + h/2;
            g.drawLine(x, by, x + 3*d, by);
            g.drawLine(x + 3*d, by-2*d, x + 3*d, by + 2*d);
            g.drawLine(x + 5*d, by-2*d, x + 5*d, by + 2*d);
            g.drawLine(x+5*d, by, x + 8*d, by);

            if (name != null && name.length() > 0)
                g.drawString(name, x + 2, y - 8);
        } else {
            int bx = x + h/2;
            g.drawLine(bx, y, bx, y+3*d);
            g.drawLine(bx -2*d, y+3*d, bx + 2*d, y + 3*d);
            g.drawLine(bx -2*d, y+5*d, bx + 2*d, y + 5*d);
            g.drawLine(bx, y+5*d, bx, y+8*d);

            if (name != null && name.length() > 0)
                g.drawString(name, x + h + 8, y + w/2 + g.getFontMetrics().getHeight() / 2);

        }

        for (Terminal t: getTerminals()) {
            t.paintComponent(g);
        }
    }
}
