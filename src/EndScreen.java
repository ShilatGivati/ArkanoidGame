/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen class.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private AnimationRunner animationRunner;
    private boolean isWin;
    private int score;
    private boolean stop;

    /**
     * constructor function.
     *
     * @param k               - the key.
     * @param isWin           - is the player is win.
     * @param score           - the score in the end.
     * @param animationRunner -the animation.
     */
    public EndScreen(KeyboardSensor k, boolean isWin, int score, AnimationRunner animationRunner) {
        this.keyboard = k;
        this.animationRunner = animationRunner;
        this.isWin = isWin;
        this.score = score;
        this.stop = false;
    }

    /**
     * @param d - the current surface.
     */
    public void doOneFrame(DrawSurface d) {
        if (!this.isWin) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.animationRunner.getGui().close();
            this.stop = true;
        }
    }

    /**
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
