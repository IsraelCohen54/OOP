/**
 * Defining Ball vellosity class.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double velx;
    private double vely;

    /**
     * The constructor of ball class.
     *
     * @param dx = we get x velocity of the circle
     * @param dy = we get y velocity of the circle
     */
    public Velocity(double dx, double dy) {
        this.velx = dx;
        this.vely = dy;
    }

    /**
     * funciton to return velx.
     *
     * @return velx
     */
    public double getvelx() {
        return this.velx;
    }

    /**
     * another function to define velocity.
     *
     * @param angle = getting angle of the ball
     * @param speed = getting the ball speed
     * @return Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double scaleofX = Math.sin(Math.toRadians(angle));
        double scaleofY = Math.cos(Math.toRadians(angle));
        double dx = (speed * scaleofX);
        double dy = (speed * scaleofY);
        return new Velocity(dx, dy);
    }

    /**
     * funciton to return vely.
     *
     * @return vely
     */
    public double getvely() {
        return this.vely;
    }

    /**
     * funciton to set velx new value.
     *
     * @param v = velocity
     */
    public void setvelx(double v) {
        this.velx = v;
    }

    /**
     * funciton to set vely new value.
     *
     * @param v = velocity
     */
    public void setvely(double v) {
        this.vely = v;
    }

    /**
     * The constructor of ball class.
     *
     * @param p = we get point and change its x,y value for velocity change.
     *          Take a point with position (x,y) and return a new point
     *          with position (x+dx, y+dy)
     * @return p = point with changed x, y value
     */
    public Point applyToPoint(Point p) {
        p.setx(this.velx + p.getx());
        p.sety(this.vely + p.gety());
        return p;
    }
}