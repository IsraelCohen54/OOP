import java.awt.geom.Line2D;
import java.util.List;

/**
 * The Line class define how do we work with line.
 * we start with two points at the two end of the line, - start, end
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * The constructor of Line class.
     *
     * @param start = we get x, y value of the first point
     * @param end   = we get x, y value of the last point
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getx(), start.gety());
        this.end = new Point(end.getx(), end.gety());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect = getting rectangle
     * @return null or closestintersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line2 = new Line(this.start, this.end);
        List<Point> pointIntersectList = rect.intersectionPoints(line2);
        if (pointIntersectList == null) {
            return null;
        }
        Point closest = pointIntersectList.get(0);
        //compare by value of the intersction points by powered the values by 2 of 2 points, onepoint = start point of
        //the line, and the other points are the intersection point.
        double closestInter = ((this.start.getx() - closest.getx()) * (this.start.getx() - closest.getx())
                + (this.start.gety() - closest.gety()) * (this.start.gety() - closest.gety()));
        for (int i = 1; i < pointIntersectList.size(); i++) {
            Point tempClosest = pointIntersectList.get(i);
            if (tempClosest != null) {
                double compareClosestInter = ((this.start.getx() - tempClosest.getx())
                        * (this.start.getx() - tempClosest.getx())
                        + (this.start.gety() - tempClosest.gety()) * (this.start.gety() - tempClosest.gety()));

                if (closestInter >= compareClosestInter) {
                    closest.setx(pointIntersectList.get(i).getx());
                    closest.sety(pointIntersectList.get(i).gety());
                }
            }
        }
        return closest;
    }

    /**
     * The constructor of Line class.
     *
     * @param x1 = we get x value for first point
     * @param y1 = we get y value for first point
     * @param x2 = we get x value for second point
     * @param y2 = we get y value for second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line.
     */

    public double length() {
        return (Math.sqrt((start.getx() - end.getx()) * (start.getx() - end.getx())
                + (start.gety() - end.gety()) * (start.gety() - end.gety())));
    }

    /**
     * Returns the middle point of the line.
     *
     * @return mid
     */
    public Point middle() {
        double x = ((this.start.getx() + this.end.getx()) / 2);
        double y = ((this.start.gety() + this.end.gety()) / 2);
        Point mid = new Point(x, y);
        return mid;
    }

    /**
     * @return the start point of the saved line in the class.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the saved line in the class.
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param line = getting line
     * @return the start point of recieved line.
     */
    public Point start(Line line) {
        return line.start;
    }

    /**
     * @param line = getting line
     * @return the end point of recieved line.
     */
    public Point end(Line line) {
        return line.end;
    }

    /**
     * this method checks if a "hypothetical" intersecting point
     * is in the line segment.
     *
     * @param point the point to check about.
     * @return true if the is in the line, false otherwise.
     */
    public boolean isInBounds(Point point) {
        double thisMaxX = Math.max(this.start.getx(), this.end.getx());
        double thisMinX = Math.min(this.start.getx(), this.end.getx());
        double thisMaxY = Math.max(this.start.gety(), this.end.gety());
        double thisMinY = Math.min(this.start.gety(), this.end.gety());
        return point.getx() >= thisMinX && point.getx() <= thisMaxX
                && point.gety() >= thisMinY && point.gety() <= thisMaxY;
    }

    /**
     * this method checks if two lines are intersecting.
     *
     * @param other the other line to check intersection with.
     * @return true if the two lines are intersecting, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double aOther = other.end.gety() - other.start.gety();
        double bOther = other.start.getx() - other.end.getx();
        double cOther = aOther * other.start.getx()
                + bOther * other.start.gety();
        double aThis = this.end.gety() - this.start.gety();
        double bThis = this.start.getx() - this.end.getx();
        double cThis = aThis * this.start.getx() + bThis * this.start.gety();
        double det = aThis * bOther - aOther * bThis;
        if (det == 0) {
            // Lines are parallel - no intersection
            return false;
        } else {
            double intersectionX = (bOther * cThis - bThis * cOther) / det;
            double intersectionY = (aThis * cOther - aOther * cThis) / det;
            Point intersection = new Point(intersectionX, intersectionY);
            return (this.isInBounds(intersection)
                    && other.isInBounds(intersection));
        }
    }

    /**
     * this method calculating intersection point of two lines.
     *
     * @param other the other line to check intersection with.
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double aOther = other.end.gety() - other.start.gety();
        double bOther = other.start.getx() - other.end.getx();
        double cOther = aOther * other.start.getx()
                + bOther * other.start.gety();
        double aThis = this.end.gety() - this.start.gety();
        double bThis = this.start.getx() - this.end.getx();
        double cThis = aThis * this.start.getx() + bThis * this.start.gety();
        double det = aThis * bOther - aOther * bThis;
        if (det == 0) {
            // Lines are parallel - no intersection
            return null;
        } else {
            double intersectionX = (bOther * cThis - bThis * cOther) / det;
            double intersectionY = (aThis * cOther - aOther * cThis) / det;
            Point intersection =
                    new Point(intersectionX, intersectionY);
            if (this.isInBounds(intersection)
                    && other.isInBounds(intersection)) {
                return intersection;
            } else {
                return null;
            }
        }
    }

    /**
     * this method checks if two lines are the same.
     *
     * @param other another line.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * this method checks if this line containing a given point.
     *
     * @param point the given point.
     * @return true if the line contains the point, false otherwise.
     */
    public boolean isContainingPoint(Point point) {
        if (point == null) {
            return false;
        }
        Line tmpLine = new Line(this.start, point);
        Point tmpPoint = tmpLine.intersectionWith(this);
        return tmpPoint == null && isInBounds(point);
    }
}