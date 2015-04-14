
package view;

import java.awt.*;

import javafx.geometry.Orientation;
import java.awt.*;

public class CapacitorView extends CircuitComponent {
    public CapacitorView(int x, int y) {
        super(x, y, 16, 10);
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

            g.drawArc(x - hr, by - hr/2, hr, hr, 0, 360);
            g.drawArc(x + w, by - hr/2, hr, hr, 0, 360);

            if (name != null && name.length() > 0)
                g.drawString(name, x + 2, y - 2);

        } else {
            int bx = x + h/2;

            g.drawLine(bx, y, bx, y + d);

            g.drawLine(bx + hr, y + d, bx - hr, y + d);
            g.drawLine(bx + hr, y + hr/2 + d , bx -hr, y + hr/2 + d);

            g.drawLine(bx, y + w - d, bx, y + w);

            g.drawArc(bx - hr/2, y - hr,  hr, hr, 0, 360);
            g.drawArc(bx - hr/2, y+w ,  hr, hr, 0, 360);

            if (name != null && name.length() > 0)
                g.drawString(name, x + h + 2, y + w/2 + g.getFontMetrics().getHeight() / 2);

        }

    }
}

