package view;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GR4 on 31.03.15.
 */
public class ResistorView extends CircuitComponent {

    private int d = 5;
    private int hr = 3;

    public ResistorView(int x, int y) {
        super(x, y, 40, 10);

        getTerminals().add(new Terminal(this, -2 * hr, h / 2 - hr, hr));
        getTerminals().add(new Terminal(this, w, h / 2 - hr, hr));
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        if (getOrientation() == Orientation.Horizontal) {
            int by = y + h/2;
            g.drawLine(x, by, x + d, by);
            g.drawLine(x + d, by, x + d + hr, by + hr);
            g.drawLine(x + d + hr, by + hr, x + d + 2 * hr, by - hr);
            g.drawLine(x + d + 2 * hr, by - hr, x + d + 3 * hr, by + hr);
            g.drawLine(x + d + 3 * hr, by + hr, x + d + 4 * hr, by - hr);
            g.drawLine(x + d + 4 * hr, by - hr, x + d + 5 * hr, by + hr);
            g.drawLine(x + d + 5 * hr, by + hr, x + d + 6 * hr, by - hr);
            g.drawLine(x + d + 6 * hr, by - hr, x + d + 7 * hr, by + hr);
            g.drawLine(x + d + 7 * hr, by + hr, x + d + 8 * hr, by - hr);
            g.drawLine(x + d + 8 * hr, by - hr, x + d + 9 * hr, by + hr);
            g.drawLine(x + d + 9 * hr, by + hr, x + d + 10 * hr, by);

            g.drawLine(x + w - d, by, x + w, by);

            if (name != null && name.length() > 0)
                g.drawString(name, x + 2, y - 2);
        } else {
            int bx = x + h/2;
            g.drawLine(bx, y, bx, y+d);
            g.drawLine(bx, y+d, bx+hr, y + d + hr);
            g.drawLine(bx + hr, y + d + hr, bx - hr, y + d + 2 * hr);
            g.drawLine(bx - hr, y + d + 2 * hr, bx + hr, y + d + 3 * hr);
            g.drawLine(bx + hr, y + d + 3 * hr, bx - hr, y + d + 4 * hr);
            g.drawLine(bx - hr, y + d + 4 * hr, bx + hr, y + d + 5 * hr);
            g.drawLine(bx + hr, y + d + 5 * hr, bx - hr, y + d + 6 * hr);
            g.drawLine(bx - hr, y + d + 6 * hr, bx + hr, y + d + 7 * hr);
            g.drawLine(bx + hr, y + d + 7 * hr, bx - hr, y + d + 8 * hr);
            g.drawLine(bx - hr, y + d + 8 * hr, bx + hr, y + d + 9 * hr);
            g.drawLine(bx + hr, y + d + 9 * hr, bx, y + d + 10 * hr);

            g.drawLine(bx, y + w - d, bx, y + w);

            if (name != null && name.length() > 0)
                g.drawString(name, x + h + 2, y + w/2 + g.getFontMetrics().getHeight() / 2);
        }

        for (Terminal t: getTerminals()) {
            t.paintComponent(g);
        }
    }

}
