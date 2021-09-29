/**
 * The Point class define how do we work with points.
 */
public class Point {
    private double x;
    private double y;

    /**
     * The constructor of Point class.
     *
     * @param x = x value of the point
     * @param y = y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other = other point, that from it we measure the distanse of the line
     * @return distance between two points
     */
    public double distance(Point other) {
        return (Math.sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y)));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other = we get 'other' point and check if its the same as the point we have
     * @return boolean if its equals.
     */
    public boolean equals(Point other) {
        if ((other.x == x) && (other.y == y)) {
            return true;
        }
        return false;
    }

    /**
     * @return the x value of this point.
     */
    public double getx() {
        return this.x;
    }

    /**
     * @return the y value of this point.
     */
    public double gety() {
        return this.y;
    }

    /**
     * Set x value of the point.
     *
     * @param value = we set the value of x with the function.
     */
    public void setx(double value) {
        this.x = value;
    }

    /**
     * Set y value of the point.
     *
     * @param value = we set the value of y with the function.
     */
    public void sety(double value) {
        this.y = value;
    }
}