/**
 * Created by GR5 on 24.03.15.
 */
public class CircuitComponent {
    public int x,y,w,h;
    private boolean selected;

    public CircuitComponent(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean isInside(int px, int py) {
        return px >= x && px <= (x+w) && py >= y && py <= (y+h);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
