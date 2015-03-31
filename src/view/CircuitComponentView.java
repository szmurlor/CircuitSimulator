package view;

import java.awt.*;

/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitComponentView {
    public enum Orientation {Vertical, Horizontal}
    public int x,y,w,h;
    private boolean selected;
    public Orientation orientation = Orientation.Horizontal;

    public CircuitComponentView(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean isInside(int px, int py) {
        if (orientation == Orientation.Horizontal)
            return px >= x && px <= (x+w) && py >= y && py <= (y+h);
        else
            return px >= x && px <= (x+h) && py >= y && py <= (y+w);
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
        setColorForSelected(graphics);
        graphics.drawRect(x, y, w, h);
    }

    protected void setColorForSelected(Graphics graphics) {
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
}
