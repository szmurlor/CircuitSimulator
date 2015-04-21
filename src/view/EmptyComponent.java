package view;

/**
 * Created by GR6 on 14.04.15.
 */
public class EmptyComponent extends CircuitComponent {

    @Override
    public String getElectricalValueLabel() {
        return "Brak:";
    }

    @Override
    public String getElectricalValueUnit() {
        return "[-]";
    }

    public EmptyComponent(int x, int y) {
        super(x, y, 2*CircuitSimulator.TERMINAL_R, 2*CircuitSimulator.TERMINAL_R);

        getTerminals().add(new TerminalView(this,0,0));
    }
}
