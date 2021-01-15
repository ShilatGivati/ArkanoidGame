/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Game flow.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    /**
     * Constructor function.
     *
     * @param ar - AnimationRunner.
     * @param ks - KeyboardSensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
    }

    /**
     * Flows levels.
     *
     * @param levels - the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int score = 0;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score);
            level.initialize();

            while ((level.getNumberOfBalls() > 0) && (level.getNumberOfBlocks() > 0)) {
                level.run();
            }

            if (level.getNumberOfBalls() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.keyboardSensor, false, level.getScore(), this.animationRunner)));
                break;
            }
            score = level.getScore();
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.keyboardSensor, true, score, this.animationRunner)));
    }
}