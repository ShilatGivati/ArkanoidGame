/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.util.List;

/**
 * Interface - the level of the information.
 */
public interface LevelInformation {

    /**
     * @return - the number of balls.
     */
    int numberOfBalls();

    /**
     * @return - The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return - paddleSpeed.
     */
    int paddleSpeed();

    /**
     * @return - the location pf the paddle.
     */
    Point paddleLocation();

    /**
     * @return - paddleWidth.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
