package view;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by GR5 on 24.03.15.
 */
public class EmptyComponent extends CircuitComponent {

    public EmptyComponent(int x, int y) {
        super(x,y,0,0);

        getTerminals().add(new Terminal(this, 0,0, 3));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Terminal t: getTerminals()) {
            t.paintComponent(graphics);
        }
    }
}
