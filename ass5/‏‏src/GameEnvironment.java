import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * add the given collidable to the environment.
     *
     * @param c = the c means the collidable object
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOnrec(DrawSurface surface) {
        Rectangle r;
        for (Collidable c : collidables) {
            r = c.getCollisionRectangle();
            surface.drawRectangle((int) r.getUpperLeft().getx(),
                    (int) r.getUpperLeft().gety(), (int) r.getHeight(), (int) r.getWidth());
        }
    }

    /**
     * Collidables list.
     *
     * @return the list
     */
    public List<Collidable> theCollidables() {
        return collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisP = new Point(0, 0);
        for (int i = 0; i < collidables.size(); i++) {
            collisP = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            if (collisP != null) {
                return new CollisionInfo(collisP, collidables.get(i));
            }
        }
        return null;
    }

    /**
     * Remove collidabale.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * if the ball got stuck in a rectangle.
     * if it does got stuck, reset its location
     * to the bottom of the screen.
     *
     * @param ball the given ball.
     */
    public void ballStuck(Ball ball) {
        if (this.collidables.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.collidables.size(); i++) {
            if (this.collidables.get(i).getCollisionRectangle().
                    isContainingPoint(ball.getballcenter())) {
                ball.setBallCenter(400, 560);
                ball.setVelocity(Velocity.fromAngleAndSpeed(173.7, 4));
            }
        }
        /*if (this.collidables.isEmpty()) {
            return;
        }
        for (Collidable obj : collidables) {
            if (obj.getCollisionRectangle().checkPointWithinRec(ball.getballcenter())) {
                ball.setBallCenterIfWrong();
            }
        }*/
    }
}