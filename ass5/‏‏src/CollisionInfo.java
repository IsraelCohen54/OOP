/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collis;
    private Collidable collid;

    /**
     * Instantiates a new Collision info.
     *
     * @param collis the collis
     * @param collid the collid
     */
    public CollisionInfo(Point collis, Collidable collid) {
        this.collid = collid;
        this.collis = collis;
    }

    /**
     * Collision point point.
     * the point at which the collision occurs.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collis;
    }

    /**
     * Collision object collidable.
     * the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collid;
    }
}