package view;

import java.awt.*;

/**
 * Created by GR4 on 31.03.15.
 */
public class ResistorView extends CircuitComponent {
    public ResistorView(int x, int y) {
        super(x, y, 40, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        int d = 5;
        int hr = 3;

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

            g.drawArc(x - 2 * hr, by - hr, 2 * hr, 2 * hr, 0, 360);
            g.drawArc(x + w, by - hr, 2 * hr, 2 * hr, 0, 360);
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

            g.drawArc(bx - hr, y - 2*hr, 2 * hr, 2 * hr, 0, 360);
            g.drawArc(bx - hr, y+w, 2 * hr, 2 * hr, 0, 360);
        }
    }
}
