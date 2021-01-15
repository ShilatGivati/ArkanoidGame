/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * The class have the info of the collision.
 */
public class CollisionInfo {
    //members
    private Point point;
    private Collidable collidable;

    /**
     * constructor function.
     *
     * @param point      - the point of the collision.
     * @param collidable - the object involved in the collision
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.collidable = collidable;
    }

    /**
     * @return - the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
