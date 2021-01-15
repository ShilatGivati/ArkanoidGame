import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d - the current surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return if the game should stop.
     */
    boolean shouldStop();
}
