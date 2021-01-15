/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Second Level.
 */
public class WideEasy implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 10;
    private static final int ANGLE = 0;
    private static final int SPEED = 5;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 15;

    private static final int BLOCK_WIDTH = 650;

    //Colors.
    private static final int DEFAULT_BG_R = 74;
    private static final int DEFAULT_BG_G = 33;
    private static final int DEFAULT_BG_B = 73;

    //Paddle.
    private static final int PADDLE_X = 75;
    private static final int PADDLE_Y = 560;


    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_BALLS / 2; i++) {
            velocity.add(Velocity.fromAngleAndSpeed(ANGLE + i * 15, SPEED));
        }
        for (int i = 1; i <= NUMBER_OF_BALLS / 2; i++) {
            velocity.add(Velocity.fromAngleAndSpeed(ANGLE + i * (-15), SPEED));
        }
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return SPEED;
    }

    @Override
    public int paddleWidth() {
        return BLOCK_WIDTH;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600),
                new Color(DEFAULT_BG_R, DEFAULT_BG_G, DEFAULT_BG_B));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        for (int j = 0; j < NUMBER_OF_BLOCKS_TO_REMOVE; j++) {
            blocks.add(new Block(new Rectangle(new Point(30 + (49.25 * j), 170), 49.25, 17),
                    Color.PINK));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_TO_REMOVE;
    }

    /**
     * @return the location of the paddle.
     */
    public Point paddleLocation() {
        return new Point(PADDLE_X, PADDLE_Y);
    }
}
