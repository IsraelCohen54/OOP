import biuoop.DrawSurface;

/**
 * The defining Ball class.
 * the class has point of the middle of the circle
 * its raduis
 * its color
 */
public class Ball implements Sprite {
    private Point point; //the center point of the ball.
    private int r;
    private java.awt.Color color;
    private Velocity vel;
    private int xhorizonBound;
    private int yverticalBound;
    private int startxhorizonBound = 0;
    private int startyverticalBound = 0;
    private GameEnvironment game;
    private int counter = 0;

    /**
     * Constructor of Ball with game environment.
     *
     * @param ballcenter the ballcenter
     * @param r          is the radius
     * @param color      is the  color of the ball
     * @param game       the game
     */
    public Ball(Point ballcenter, int r, java.awt.Color color, GameEnvironment game) {
        this.point = ballcenter;
        this.r = r;
        this.color = color;
        this.vel = new Velocity(0, 0);
        this.game = game;
    }


    /**
     * The constructor of ball class.
     *
     * @param xval           = we get x value of the circle
     * @param yval           = we get y value of the circle
     * @param r              = we get the radius of the circle
     * @param color          = we get the color of the circle
     * @param yverticalBound the vertical Bound of the ball.
     * @param xhorizonBound  the horizontal Bound of the ball.
     */
    public Ball(int xval, int yval, int r, java.awt.Color color, int yverticalBound, int xhorizonBound) {
        if ((xval - r) <= 0) {
            xval = r;
        }

        if ((xval + r) >= xhorizonBound) {
            xval = xhorizonBound - r;
        }

        if ((yval - r) <= 0) {
            yval = r;
        }

        if ((yval + r) >= yverticalBound) {
            yval = yverticalBound - r;
        }

        this.point = new Point(xval, yval);
        this.r = r;
        this.color = color;
        this.yverticalBound = yverticalBound;
        this.xhorizonBound = xhorizonBound;
    }

    /**
     * notify the ball that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
        this.game.ballStuck(this);

    }
    /**
     * set vel values.
     *
     * @param begginxhorizonBound  the start horizontal Bound of the ball.
     * @param begginyverticalBound the start vertical Bound of the ball.
     */
    /**
     * set vel values.
     *
     * @param v = velocity of a ball
     */
    public void setVelocity(Velocity v) {
        this.vel = new Velocity(v.getvelx(), v.getvely());
    }


    /**
     * Sets ball center.
     *
     * @param xCenter the x center
     * @param yCenter the y center
     */
    public void setBallCenter(double xCenter, double yCenter) {
        this.point.setx(xCenter);
        this.point.sety(yCenter);
    }

    /**
     * Sets ball center if it goes wrong.
     */
    public void setBallCenterIfWrong() {
        this.point.setx(this.xhorizonBound / 2);
        this.point.sety((this.yverticalBound / 2) + 250);
    }

    /**
     * set vel values by x and y values.
     *
     * @param dx = x value
     * @param dy = y value
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * get vel values.
     *
     * @return vel value
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * this method generates a line that represent the ball course.
     *
     * @return the ball course.
     */
    public Line trajectory() {
        Point startOfTrajectory = new Point(
                Math.floor(this.point.getx()), Math.floor(this.point.gety()));
        Point endOfTrajectory = new Point(
                Math.floor(this.point.getx() + this.vel.getvelx()),
                Math.floor(this.point.gety() + this.vel.getvely()));
        return new Line(startOfTrajectory, endOfTrajectory);
    }

    /**
     * this method moves the ball's center according to it's velocity.
     */
    public void moveOneStep() {
        if (this.game.getClosestCollision(this.trajectory()) != null) {
            this.setVelocity(this.game.getClosestCollision(
                    this.trajectory()).collisionObject().hit(this,
                    this.game.getClosestCollision(this.trajectory()).collisionPoint(), this.getVelocity()));
        }
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * Horizon int.
     *
     * @return the int
     */
    public int horizon() {
        return this.xhorizonBound;
    }

    /**
     * yvertical int.
     *
     * @return the int
     */
    public int vertical() {
        return this.yverticalBound;
    }

    /**
     * move the ball one step.
     *
     * @param a = to choose rectangle
     */
    public void moveOneStep1(int a) {
        if (a == 1) {
            if (((getX() + this.r + this.vel.getvelx()) >= 500) && (0 < this.vel.getvelx())) {
                setVelocity(-this.vel.getvelx(), this.vel.getvely());
            }
            if (((this.r + getY() + this.vel.getvely()) >= 500) && (0 < this.vel.getvely())) {
                setVelocity(this.vel.getvelx(), -this.vel.getvely());
            }
            if (((getX() - getSize() + this.vel.getvelx()) <= 50)
                    && (50 > this.vel.getvelx())) {
                setVelocity(-this.vel.getvelx(), this.vel.getvely());
            }
            if (((getY() - getSize() + this.vel.getvely()) <= 50)
                    && (50 > this.vel.getvely())) {
                setVelocity(this.vel.getvelx(), -this.vel.getvely());
            }
            this.point = this.getVelocity().applyToPoint(this.point);
        }
        if (a == 2) {
            if (((getX() + this.r + this.vel.getvelx()) >= 600) && (0 < this.vel.getvelx())) {
                setVelocity(-this.vel.getvelx(), this.vel.getvely());
            }
            if (((this.r + getY() + this.vel.getvely()) >= 600) && (0 < this.vel.getvely())) {
                setVelocity(this.vel.getvelx(), -this.vel.getvely());
            }
            if (((getX() - getSize() + this.vel.getvelx()) <= 450)
                    && (450 > this.vel.getvelx())) {
                setVelocity(-this.vel.getvelx(), this.vel.getvely());
            }
            if (((getY() - getSize() + this.vel.getvely()) <= 450)
                    && (450 > this.vel.getvely())) {
                setVelocity(this.vel.getvelx(), -this.vel.getvely());
            }
            this.point = this.getVelocity().applyToPoint(this.point);
        }
    }

    /**
     * The accessor of ball class for its x value.
     *
     * @return x value
     */
    public int getX() {
        int k = (int) this.point.getx();
        return k;
    }

    /**
     * Gets .
     *
     * @return the center of the ball
     */
    public Point getballcenter() {
        return this.point;
    }

    /**
     * The method set the game environment of the ball.
     *
     * @param gameEnv it the GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.game = gameEnv;
    }
    //TODO check if the function below is needed - removeFromGame.

    /**
     * This mathod removes this ball from game g.
     *
     * @param g the game from which the ball is removed
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * The accessor of ball class for its y value.
     *
     * @return y value
     */
    public int getY() {
        int k = (int) this.point.gety();
        return k;
    }

    /**
     * The accessor of ball class for its radius value.
     *
     * @return radius value
     */
    public int getSize() {
        return this.r;
    }

    /**
     * The accessor of ball class for its color.
     *
     * @return circle color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface - get the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.point.getx(), (int) this.point.gety(), this.r);
    }
}
/**
 * Trajectory line.
 * run from ball center to the end of the intersection with the window.
 *
 * @return the line
 */
   /* public Line trajectory() {
        Point tempStartLineP = new Point(this.point.getx(), this.point.gety());
        Point nextStepLine = new Point(this.point.getx()
                + this.vel.getvelx() * 2, this.point.gety() + this.vel.getvely() * 2);
        Line traj = new Line(tempStartLineP, nextStepLine);
        return traj;
        /*Point tempStartLineP = new Point(this.point.getx(), this.point.gety());
        while (this.point.getx() >= 0 && this.point.getx() + this.vel.getvelx() <= this.xhorizonBound
                && this.point.gety() >= 0 && this.point.gety() + this.vel.getvely() <= this.yverticalBound) {
            this.point.setx(this.point.getx() + this.vel.getvelx());
            this.point.sety(this.point.gety() + this.vel.getvely());
        }
        this.point.setx(this.point.getx() + this.vel.getvelx());
        this.point.sety(this.point.gety() + this.vel.getvely());
        Line ballTraj = new Line(tempStartLineP, this.point);
        Line upperLine = new Line(new Point(this.startxhorizonBound, this.startyverticalBound),
                new Point(this.xhorizonBound, this.startyverticalBound));
        Point upperIntersection = ballTraj.intersectionWith(upperLine);
        Line leftLine = new Line(new Point(this.startxhorizonBound, this.startyverticalBound),
                new Point(this.startyverticalBound, this.yverticalBound));
        Point leftIntersection = ballTraj.intersectionWith(leftLine);
        Line rightLine = new Line(new Point(this.xhorizonBound, this.startyverticalBound),
                new Point(this.xhorizonBound, this.yverticalBound));
        Point rightIntersection = ballTraj.intersectionWith(rightLine);
        Line buttomLine = new Line(new Point(this.startxhorizonBound, this.yverticalBound),
                new Point(this.xhorizonBound, this.yverticalBound));
        Point buttomIntersection = ballTraj.intersectionWith(buttomLine);
        if (upperIntersection != null) {
            ballTraj = new Line(tempStartLineP, upperIntersection);
        }
        if (leftIntersection != null) {
            ballTraj = new Line(tempStartLineP, leftIntersection);
        }
        if (rightIntersection != null) {
            ballTraj = new Line(tempStartLineP, rightIntersection);
        }
        if (buttomIntersection != null) {
            ballTraj = new Line(tempStartLineP, buttomIntersection);
        }
        this.point.setx(tempStartLineP.getx());
        this.point.sety(tempStartLineP.gety());
        return ballTraj;*/
//  }
//}

/*else {
            if (((getX() + this.r + this.vel.getvelx()) >= yverticalBound)
                    && (startxhorizonBound < this.vel.getvelx())) {
                setVelocity(-this.vel.getvelx(), this.vel.getvely());
            }
            if (((this.r + getY() + this.vel.getvely()) >= xhorizonBound)
                    && (startyverticalBound < this.vel.getvely())) {
                setVelocity(this.vel.getvelx(), -this.vel.getvely());
            }
            if (((getX() - getSize() + this.vel.getvelx()) <= startxhorizonBound)
                    && (startxhorizonBound > this.vel.getvelx())) {
                setVelocity(-this.vel.getvelx(), this.vel.getvely());
            }
            if (((getY() - getSize() + this.vel.getvely()) <= startyverticalBound)
                    && (startyverticalBound > this.vel.getvely())) {
                setVelocity(this.vel.getvelx(), -this.vel.getvely());
            }
            this.point = this.getVelocity().applyToPoint(this.point);
        }*/