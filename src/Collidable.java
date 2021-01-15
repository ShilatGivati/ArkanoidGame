/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          - ball hitter.
     * @param collisionPoint  - the collisionPoint.
     * @param currentVelocity - the current velocity.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * add to the game.
     *
     * @param gameLevel - the current game.
     */
    void addToGame(GameLevel gameLevel);
}
