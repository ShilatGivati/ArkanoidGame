/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class is displaying a score is achieved.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private String levelName;

    private static final int START_X = 20;
    private static final int LEVEL_X_POS = 320;
    private static final int START_Y = 20;
    private static final int SIZE_OF_TEXT = 15;

    /**
     * Constructor function.
     *
     * @param score - the score.
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
    }

    /**
     * Constructor function.
     *
     * @param score     - the score.
     * @param levelName - the level name
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.levelName = levelName;
        this.currentScore = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(START_X, START_Y, "Score: " + this.currentScore.getValue(), SIZE_OF_TEXT);
        d.drawText(LEVEL_X_POS, START_Y, "Level Name: " + this.levelName, SIZE_OF_TEXT);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
