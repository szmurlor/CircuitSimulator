package view;

import java.awt.*;
import javafx.geometry.Orientation;

public class CurrentSourceView extends CircuitComponent {
    public CurrentSourceView(int x, int y) {
        super(x, y, 40, 40);
    }


    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        int d = 5;
        int rd = 20;
        int hr = 6;

        if (getOrientation() == Orientation.Horizontal) {
            int by = y + h / 2;

            g.drawLine(x, by + hr / 2, x + d, by + hr / 2);
            g.drawArc(x + d, by + hr / 2 - rd / 2, rd, rd, 0, 360);
            g.drawArc(x + d + 2, by + hr / 2 - (rd-4) / 2, rd-4 , rd-4 , 0, 360);
            g.drawLine(x + d + rd, by + hr / 2, x + rd + 2 * d, by + hr / 2);
            g.drawLine(x + d + 4, by + hr / 2, x + d + rd - 4, by + hr / 2);
            g.drawLine(x + d + rd - 4, by + hr / 2, x + rd + d - 8, by + hr / 2 - 4);
            g.drawLine(x + d + rd - 4, by + hr / 2, x + rd + d - 8, by + hr / 2 + 4);

            if (name != null && name.length() > 0)
                g.drawString(name, x + 2, y + 2);

            //g.drawArc(x - hr, by, hr, hr, 0, 360);
            //g.drawArc(x + rd + 2 * d, by, hr, hr, 0, 360);

        } else {
            int bx = x + h / 2;

            g.drawLine(bx + hr / 2, y, bx + hr / 2, y + d);
            g.drawArc(bx + hr / 2 - rd / 2, y + d, rd, rd, 0, 360);
            g.drawArc(bx + hr / 2 - (rd-4) / 2 , y + d + 2, rd-4 , rd-4 , 0, 360);
            g.drawLine(bx + hr / 2, y + d + rd, bx + hr / 2, y + rd + 2 * d);
            g.drawLine(bx + hr / 2, y + d + 4, bx + hr / 2, y + d + rd - 4);
            g.drawLine(bx + hr / 2, y + d + rd - 4, bx + hr / 2 - 4, y + rd + d - 8);
            g.drawLine(bx + hr / 2, y + d + rd - 4, bx + hr / 2 + 4, y + rd + d - 8);

            if (name != null && name.length() > 0)
                g.drawString(name, x + h + 2, y + w / 2 + g.getFontMetrics().getHeight() / 2);

            // terminale
            //g.drawArc(bx, y - hr, hr, hr, 0, 360);
            //g.drawArc(bx, y + rd + 2 * d, hr, hr, 0, 360);
        }
    }
}