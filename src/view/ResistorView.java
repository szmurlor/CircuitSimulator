package view;

import java.awt.*;

/**
 * Created by szmurlor on 31.03.15.
 */
public class ResistorView extends CircuitComponentView {

    public ResistorView(int x, int y) {
        super(x, y, 40, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        setColorForSelected(g);

        if (orientation == Orientation.Horizontal) {
            int by = y + (int) (0.5*h);
            int bh = 3;
            int bl = 5;

            drawLineHorizontal(g, x, by, x + bl, by);
            drawLineHorizontal(g, x, by, x + bl, by);

            drawLineHorizontal(g, x + bl, by, x + bl + bh, by + bh);
            drawLineHorizontal(g, x + bl + bh, by + bh, x + bl + 2 * bh, by - bh);
            drawLineHorizontal(g, x + bl + 2 * bh, by - bh, x + bl + 3 * bh, by + bh);
            drawLineHorizontal(g, x + bl + 3 * bh, by + bh, x + bl + 4 * bh, by - bh);
            drawLineHorizontal(g, x + bl + 4 * bh, by - bh, x + bl + 5 * bh, by + bh);
            drawLineHorizontal(g, x + bl + 5 * bh, by + bh, x + bl + 6 * bh, by - bh);
            drawLineHorizontal(g, x + bl + 6 * bh, by - bh, x + bl + 7 * bh, by + bh);
            drawLineHorizontal(g, x + bl + 7 * bh, by + bh, x + bl + 8 * bh, by - bh);
            drawLineHorizontal(g, x + bl + 8 * bh, by - bh, x + bl + 9 * bh, by + bh);
            drawLineHorizontal(g, x + bl + 9 * bh, by + bh, x + bl + 10 * bh, by);

            drawLineHorizontal(g, x + w - bl, by, x + w, by);

            g.drawArc(x-2*bh,by-bh,bh*2,bh*2,0,360);
            g.drawArc(x+w,by-bh,bh*2,bh*2,0,360);
        } else {
            int by = x + (int) (0.5*h);
            int bh = 3;
            int bl = 5;

            drawLineVertical(g, y, by, y + bl, by);
            drawLineVertical(g, y, by, y + bl, by);

            drawLineVertical(g, y + bl, by, y + bl + bh, by + bh);
            drawLineVertical(g, y + bl + bh, by + bh, y + bl + 2 * bh, by - bh);
            drawLineVertical(g, y + bl + 2 * bh, by - bh, y + bl + 3 * bh, by + bh);
            drawLineVertical(g, y + bl + 3 * bh, by + bh, y + bl + 4 * bh, by - bh);
            drawLineVertical(g, y + bl + 4 * bh, by - bh, y + bl + 5 * bh, by + bh);
            drawLineVertical(g, y + bl + 5 * bh, by + bh, y + bl + 6 * bh, by - bh);
            drawLineVertical(g, y + bl + 6 * bh, by - bh, y + bl + 7 * bh, by + bh);
            drawLineVertical(g, y + bl + 7 * bh, by + bh, y + bl + 8 * bh, by - bh);
            drawLineVertical(g, y + bl + 8 * bh, by - bh, y + bl + 9 * bh, by + bh);
            drawLineVertical(g, y + bl + 9 * bh, by + bh, y + bl + 10 * bh, by);

            drawLineVertical(g, y + w - bl, by, y + w, by);

            g.drawArc(by-bh, y-2*bh,bh*2,bh*2,0,360);
            g.drawArc(by-bh, y+w,bh*2,bh*2,0,360);
        }
    }

    private void drawLineVertical(Graphics graphics, int x1, int y1, int x2, int y2) {
        graphics.drawLine(y1,x1,y2,x2);
    }

    private void drawLineHorizontal(Graphics graphics, int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1,y1,x2,y2);
    }


}
