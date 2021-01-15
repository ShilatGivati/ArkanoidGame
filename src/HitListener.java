/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * HitListener interface.
 */
public interface HitListener {

    /**
     * @param beingHit - this method is called whenever the beingHit object is hit.
     * @param hitter   - the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
