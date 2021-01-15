/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class will be a collection of collidable objects.
 * The ball will know the game environment, and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructor function.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c - the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable from environment.
     *
     * @param c - the collidable we want to add.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * The function Assume an object moving from line.start() to line.end().
     *
     * @param trajectory - the trajectory of the current object.
     * @return - if this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle tempRectangle;
        Collidable closest = null;
        Point closestPoint, minPoint = null;
        double minDis = Double.POSITIVE_INFINITY, curDis;
        for (Collidable c : collidables) {
            tempRectangle = c.getCollisionRectangle();
            closestPoint = trajectory.closestIntersectionToStartOfLine(tempRectangle);
            if (closestPoint != null) {
                curDis = closestPoint.distance(trajectory.start());
                if (curDis < minDis) {
                    minDis = curDis;
                    closest = c;
                    minPoint = closestPoint;
                }
            }
        }
        if (closest == null) {
            return null;
        }
        return new CollisionInfo(new Point(minPoint), closest);
    }
}
