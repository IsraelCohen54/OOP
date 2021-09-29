import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.Random;

/**
 * generating random lines.
 */
public class AbstractArtDrawing {

    /**
     * draw lines, intersection and middle line.
     */
    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Lines"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] line = new Line[10]; //for middle point
        Point[] temp1 = new Point[10]; //for intersection
        Point[] temp2 = new Point[10]; //for intersection
        for (int i = 0; i < 10; ++i) {
            int x = rand.nextInt(400) + 1; // get integer in range 1-400
            int y = rand.nextInt(300) + 1; // get integer in range 1-300
            int r = 5 * (rand.nextInt(4) + 1); // get integer in 5,10,15,20
            int z = rand.nextInt(300) + 1; // get integer in range 1-300
            Line temp = new Line(x, y, r, z);
            line[i] = temp;
            Point temp3 = new Point(x, y);
            temp1[i] = temp3;
            temp3 = new Point(r, z);
            temp2[i] = temp3;
            d.setColor(Color.BLACK);
            d.drawLine(x, y, r, z);
            d.setColor(Color.BLUE);
            d.fillCircle((int) line[i].middle().getx(), (int) line[i].middle().gety(), 4);
        }
        isIntersectingthandraw(temp1, temp2, line, d); //for intersections
        gui.show(d);
    }

    /**
     * draw isIntersection.
     *
     * @param temp1 - array of 10 Points.
     * @param temp2 - array of 10 Points.
     * @param line  - array of 10 lines from the points in temp1 and temp2.
     * @param d     - for drawing
     */
    public static void isIntersectingthandraw(Point[] temp1, Point[] temp2, Line[] line, DrawSurface d) {
        for (int q = 0; q < 10; ++q) {
            Line2D line1 = new Line2D.Double(temp1[q].getx(), temp1[q].gety(), temp2[q].getx(), temp2[q].gety());
            for (int t = 0; t < 10; ++t) {
                if (q != t) {
                    Line2D line2 = new Line2D.Double(temp1[t].getx(),
                            temp1[t].gety(), temp2[t].getx(), temp2[t].gety());
                    boolean result = line2.intersectsLine(line1);
                    if (result) {
                        d.setColor(Color.RED);
                        int xval = (int) line[q].intersectionWith(line[t]).getx();
                        int yval = (int) line[q].intersectionWith(line[t]).gety();
                        d.fillCircle(xval, yval, 4);
                    }
                }
            }
        }
    }

    /**
     * the main run AbstractArtDrawing method.
     *
     * @param args = string.
     */
    public static void main(String[] args) {
        AbstractArtDrawing line = new AbstractArtDrawing();
        line.drawRandomLines();

    }
}