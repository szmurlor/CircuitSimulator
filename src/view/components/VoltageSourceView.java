package view.components;

import java.awt.*;
import javafx.geometry.Orientation;
import view.CircuitComponent;
import view.CircuitSimulator;
import view.TerminalView;

public class VoltageSourceView extends CircuitComponent {
    public VoltageSourceView(int x, int y) {
        super(x, y, 30, 30, "Napięcie: ", "[V]");

        int r = CircuitSimulator.TERMINAL_R;
        getTerminals().add( new TerminalView(this, -2*r, h/2-r, -2*r, -3*r) );
        getTerminals().add(new TerminalView(this, w, h / 2 - r, 4*r, 5*r));
    }

    @Override
    public String getTypeCode() {
        return "VS";
    }

    @Override
    public String getDoc() {
        return "<html><b>Uwaga!</b> Obecna implemntacja umożliwia definicję wyłącznie źródeł stałych.";
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorIfSelected(g);

        int d = 5;
        int rd = 20;
        int hr = 6;

        if (getOrientation() == Orientation.Horizontal) {
            int by = y + h / 2;
            g.drawLine(x, by , x + d, by);
            g.drawArc(x + d, by - rd / 2, rd, rd, 0, 360);
            g.drawLine(x + d + rd, by, x + rd + 2 * d, by);
            g.drawLine(x + d + 4, by, x + d + rd - 4, by);
            g.drawLine(x + d + rd - 4, by, x + rd + d - 8, by - 4);
            g.drawLine(x + d + rd - 4, by, x + rd + d - 8, by + 4);
        } else {
            int bx = x + h /2;
            g.drawLine(bx, y, bx, y + d);
            g.drawArc(bx - rd / 2, y + d, rd, rd, 0, 360);
            g.drawLine(bx, y + d + rd, bx, y + rd + 2 * d);
            g.drawLine(bx, y + d + 4, bx, y + d + rd - 4);
            g.drawLine(bx + hr /2, y + d + rd - 4, bx - 4 , y + rd + d - 8);
            g.drawLine(bx , y + d + rd - 4, bx + 4 , y + rd + d - 8);
        }

        super.paintComponent(g);
    }
}