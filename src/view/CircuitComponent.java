package view;

import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * Created by GR5 on 24.03.15.
 */
public abstract class CircuitComponent {

    public enum Orientation {Horizontal, Vertical}

    public int x,y,w,h;
    public String name;

    private Orientation orientation = Orientation.Horizontal;
    private boolean selected;

    private java.util.List<Terminal> terminals = new LinkedList<Terminal>();

    public CircuitComponent(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Terminal getFirstTerminal() {
        if (getTerminals().size() > 0)
            return getTerminals().get(0);

        return null;
    }

    public boolean isInside(int px, int py) {
        if (getOrientation() == Orientation.Horizontal)
            return px >= x && px <= (x+w) && py >= y && py <= (y+h);
        else
            return px >= x && px <= (x+h) && py >= y && py <= (y+w);
    }

    public Terminal getHoveredTerminal() {
        for (Terminal t: terminals)
            if (t.isHover())
                return t;

        return null;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getMidX() {
        return x + w/2;
    }

    public int getMidY() {
        return y + h/2;
    }

    public void paintComponent(Graphics graphics) {
        setColorIfSelected(graphics);
        graphics.drawRect(x, y, w, h);

        if (name != null && name.length() > 0)
            graphics.drawString(name, x + 2, y - 2);
    }

    protected void setColorIfSelected(Graphics graphics) {
        if (isSelected())
            graphics.setColor(Color.RED);
        else
            graphics.setColor(Color.BLACK);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public java.util.List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(java.util.List<Terminal> terminals) {
        this.terminals = terminals;
    }
}
