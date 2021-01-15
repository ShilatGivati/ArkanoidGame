/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBall;

    /**
     * constructor function.
     *
     * @param gameLevel     - the current game.
     * @param remainingBall - the counter.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBall) {
        this.gameLevel = gameLevel;
        this.remainingBall = remainingBall;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit - this method is called whenever the beingHit object is hit.
     * @param hitter   - the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBall.decrease(1);
    }
}
