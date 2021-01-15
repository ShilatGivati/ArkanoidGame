/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int count;
    private boolean isFirstFrame;

    //colors.
    private static final int DEFAULT_BG_R = 74;
    private static final int DEFAULT_BG_G = 33;
    private static final int DEFAULT_BG_B = 73;

    /**
     * @param numOfSeconds - the numbers of the seconds.
     * @param countFrom    - the start counting.
     * @param gameScreen   - the screen game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.count = this.countFrom;
        this.isFirstFrame = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(DEFAULT_BG_R, DEFAULT_BG_G, DEFAULT_BG_B));
        d.drawText(d.getWidth() / 3 + 70, d.getHeight() / 2 + 100, Integer.toString(this.count), 200);
        this.count--;
        if (this.isFirstFrame) {
            this.isFirstFrame = false;
            return;
        }
        sleeper.sleepFor((long) (numOfSeconds / countFrom) * 1000);
    }

    @Override
    public boolean shouldStop() {
        return this.count < 0;
    }
}
