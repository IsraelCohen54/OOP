import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 * the class has the point of the upperleft of the rectangle,
 * its width, and its height.
 */
public class Rectangle {
    private Point upperleftrec;
    private double widthrec;
    private double heightrec;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft = getting the upperLeft Point of the rec.
     * @param width     = getting the rec width.
     * @param height    = getting the rec height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperleftrec = upperLeft;
        this.widthrec = width;
        this.heightrec = height;
    }

    /**
     * Upper line.
     *
     * @return the line
     */
    public Line upper() {
        Line upperl = new Line(upperleftrec, new Point(upperleftrec.getx(), upperleftrec.gety()));
        return upperl;
    }

    /**
     * bottom line.
     *
     * @return the line
     */
    public Line bottom() {
        Line bottoml = new Line(new Point(upperleftrec.getx(), upperleftrec.gety() + getHeight()),
                new Point(upperleftrec.getx() + getWidth(), upperleftrec.gety() + getHeight()));
        return bottoml;
    }

    /**
     * left line.
     *
     * @return the line
     */
    public Line left() {
        Line leftl = new Line(new Point(upperleftrec.getx(), upperleftrec.gety()),
                new Point(upperleftrec.getx(), upperleftrec.gety() + getHeight()));
        return leftl;
    }

    /**
     * right line.
     *
     * @return the line
     */
    public Line right() {
        Line rightl = new Line(new Point(upperleftrec.getx() + getWidth(), upperleftrec.gety()),
                new Point(upperleftrec.getx() + getWidth(), upperleftrec.gety() + getHeight()));
        return rightl;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * starting by defining 4 lines of specific rectangle.
     *
     * @param line = ball proceeding line.
     * @return List of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointIntersectList = new ArrayList<>();
        Point upperrightrec = new Point((this.upperleftrec.getx() + this.widthrec), this.upperleftrec.gety());
        Point downleftrec = new Point(this.upperleftrec.getx(), (this.heightrec + upperleftrec.gety()));
        Point downrightrec = new Point(upperleftrec.getx() + this.widthrec, this.heightrec + upperleftrec.gety());
        Line upperrecline = new Line(this.upperleftrec, upperrightrec);
        Line leftrecline = new Line(this.upperleftrec, downleftrec);
        Line bottomrecline = new Line(downleftrec, downrightrec);
        Line rightrecline = new Line(upperrightrec, downrightrec);
        //currently, line is in the class line, getting it now by this.start etc.
        boolean isInter = line.isIntersecting(upperrecline), isInterTwo, isInterThree, isInterFour;
        if (isInter) {
            pointIntersectList.add(line.intersectionWith(upperrecline));
        }
        isInterTwo = line.isIntersecting(leftrecline);
        if (isInterTwo /*&& isInterTwo != isInter*/) {
            pointIntersectList.add(line.intersectionWith(leftrecline));
        }
        isInterThree = line.isIntersecting(bottomrecline);
        if (isInterThree /*&& isInterThree != isInterTwo*/) {
            pointIntersectList.add(line.intersectionWith(bottomrecline));
        }
        isInterFour = line.isIntersecting(rightrecline);
        if (isInterFour /*&& isInterFour != isInter && isInterFour != isInterThree*/) {
            pointIntersectList.add(line.intersectionWith(rightrecline));
        }
        boolean k = pointIntersectList.isEmpty();
        if (k) {
            return null;
        }
        return pointIntersectList;
    }

    /**
     * Gets width.
     *
     * @return the width
     * @returns the width of the rectangle.
     */
    public double getWidth() {
        return this.widthrec;
    }

    /**
     * Gets height.
     *
     * @return the height
     * @returns the Height of the rectangle.
     */
    public double getHeight() {
        return this.heightrec;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     * @returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperleftrec;
    }

    /**
     * this method checks if a given point is this rectangle area.
     *
     * @param point the given point.
     * @return true if the point is in the rectangle's area, false otherwise.
     */
    public boolean isContainingPoint(Point point) {
        return point.getx() >= this.getUpperLeft().getx()
                && point.getx() <= this.getUpperLeft().getx() + this.getWidth()
                && point.gety() >= this.getUpperLeft().gety()
                && point.gety() <= this.getUpperLeft().gety() + this.getHeight();
    }
    /**
     * check if a point is is within this rectangle area.
     *
     * @param point the given point.
     * @return true if the point is in the rectangle's area, false otherwise.
     */
    /*public boolean checkPointWithinRec(Point point) {
        if (point.getx() >= this.getUpperLeft().getx() && point.getx() <= this.getUpperLeft().getx() + this.widthrec
                && point.gety() >= this.getUpperLeft().gety()
                && point.gety() <= this.getUpperLeft().gety() + this.heightrec) {
            return true;
        }
        return false;
    }*/
}