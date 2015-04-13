package view;

import java.awt.*;

/**
 * Created by GR4 on 31.03.15.
 */
public class Terminal {
    int dx, dy, r;
    CircuitComponent parent;
    private boolean hover;

    public int getX() {
        if (parent.getOrientation().equals(CircuitComponent.Orientation.Horizontal))
            return parent.x + dx;
        else
            return parent.x + dy;

    }

    public int getY() {
        if (parent.getOrientation().equals(CircuitComponent.Orientation.Horizontal))
            return parent.y + dy;
        else
            return parent.y + dx;
    }

    public boolean isInside(int x, int y) {
        int xmin, xmax, ymin, ymax;

        xmin = getX() - 2 * r;
        xmax = getX() + 2 * r;
        ymin = getY() - 2 * r;
        ymax = getY() + 2 * r;

        return (x >= xmin && x <= xmax && y >= ymin && y <= ymax);
    }

    public Terminal(CircuitComponent parent, int dx, int dy, int r) {
        this.parent = parent;
        this.dx = dx;
        this.dy = dy;
        this.r = r;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (hover) {
            g2d.setStroke(new BasicStroke(2));
            g.setColor(Color.BLUE);
        } else {
            if (parent.isSelected()){
                g.setColor(Color.RED);
            } else {
                g2d.setStroke(new BasicStroke(1));
                g.setColor(Color.BLACK);
            }
        }

        if (parent.getOrientation().equals(CircuitComponent.Orientation.Horizontal)) {
            g.drawArc(parent.x + dx, parent.y + dy, 2 * r, 2 * r, 0, 360);
        } else {
            g.drawArc(parent.x + dy, parent.y + dx, 2 * r, 2 * r, 0, 360);
        }
        g2d.setStroke(new BasicStroke(1));
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isHover() {
        return hover;
    }

    public CircuitComponent getParent() {
        return parent;
    }
}
