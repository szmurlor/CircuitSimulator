package view;

import java.awt.*;

/**
 * Created by GR4 on 31.03.15.
 */
public class Terminal {
    int dx, dy, r;
    CircuitComponent parent;
    private boolean hover;

    public boolean isInside(int x, int y) {
        int xmin, xmax, ymin, ymax;

        if (parent.getOrientation().equals(CircuitComponent.Orientation.Horizontal)) {
            xmin = parent.x + dx - 2 * r;
            xmax = parent.x + dx + 2 * r;
            ymin = parent.y + dy - 2 * r;
            ymax = parent.y + dy + 2 * r;
        } else {
            xmin = parent.x + dy - 2 * r;
            xmax = parent.x + dy + 2 * r;
            ymin = parent.y + dx - 2 * r;
            ymax = parent.y + dx + 2 * r;
        }

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
        if (parent.isSelected()){
            g.setColor(Color.RED);
        } else {
            if (hover) {
                g2d.setStroke(new BasicStroke(2));
                g.setColor(Color.BLUE);
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
}
