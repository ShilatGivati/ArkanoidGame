/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * This class is tracking the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    private static final int SCORE_INCREASE = 5;

    /**
     * constructor function.
     *
     * @param scoreCounter - create the value of the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @param beingHit - this method is called whenever the beingHit object is hit.
     * @param hitter   - the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SCORE_INCREASE);
    }
}