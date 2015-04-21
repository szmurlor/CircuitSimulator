package view.components;

import java.awt.*;

import javafx.geometry.Orientation;
import view.CircuitComponent;
import view.CircuitSimulator;
import view.TerminalView;

/**
 * Created by kurty_000 on 2015-04-07.
 */
public class CoilView extends CircuitComponent {
    public CoilView(int x, int y){
        super(x, y, 40, 10, "Indukcyjność: ", "[H]");

        int r = CircuitSimulator.TERMINAL_R;
        getTerminals().add( new TerminalView(this, -2*r, h/2-r) );
        getTerminals().add( new TerminalView(this, w, h/2-r) );
    }


    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        int d = 5;
        int hr = 6;

        if (getOrientation() == Orientation.Horizontal) {
            int by = y + h/2;
            g.drawLine(x, by, x + d, by);

            g.drawArc(x + d, by - hr, 2 * hr, 2 * hr, 180, -240);
            g.drawArc(x + d + hr, by - hr, 2 * hr, 2 * hr, -60, 300);
            g.drawArc(x + d + 2*hr, by - hr, 2*hr, 2*hr, -60, 300);
            g.drawArc(x + d + 3*hr, by - hr, 2*hr, 2*hr, 240, -240);
            g.drawLine(x + w - d, by, x + w, by);

            //g.drawArc(x - hr, by - hr/2, hr, hr, 0, 360);
            //g.drawArc(x + w, by - hr/2, hr, hr, 0, 360);

            if (name != null && name.length() > 0)
                g.drawString(name, x + 2, y - 2);

        } else {
            int bx = x + h/2;

            g.drawLine(bx, y, bx , y + d);
            g.drawLine(bx, y + w, bx, y + w - d);

            g.drawArc(bx - hr, y + d, 2 * hr, 2 * hr, 90, -240);
            g.drawArc(bx - hr, y + d + hr, 2 * hr, 2 * hr, -150, 300);
            g.drawArc(bx - hr, y + d + 2*hr, 2 * hr, 2 * hr, -150, 300);
            g.drawArc(bx - hr, y + d + 3*hr, 2 * hr, 2 * hr, -90, 240);

            //g.drawArc(bx - hr/2, y - hr, hr, hr, 0, 360);
            //g.drawArc(bx - hr/2 , y + w, hr, hr, 0, 360);

            if (name != null && name.length() > 0)
                g.drawString(name, x + h + 2, y + w/2 + g.getFontMetrics().getHeight() / 2);

        }

        super.paintComponent(g);
    }
}

