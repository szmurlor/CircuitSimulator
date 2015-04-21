package view.components;

import view.CircuitComponent;
import view.CircuitSimulator;
import view.TerminalView;

/**
 * Created by GR6 on 14.04.15.
 */
public class EmptyComponent extends CircuitComponent {

    public EmptyComponent(int x, int y) {
        super(x, y, 2* CircuitSimulator.TERMINAL_R, 2*CircuitSimulator.TERMINAL_R, "Brak:", "");

        getTerminals().add(new TerminalView(this,0,0));
    }
}
