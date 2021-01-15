/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Second Level.
 */
public class Green3 implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 2;
    private static final int ANGLE1 = 30;
    private static final int ANGLE2 = 330;
    private static final int SPEED = 5;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 57;

    private static final int BLOCKS_WIDTH = 150;

    //Colors.
    private static final int DEFAULT_BG_R = 74;
    private static final int DEFAULT_BG_G = 33;
    private static final int DEFAULT_BG_B = 73;

    //Paddle.
    private static final int PADDLE_X = 320;
    private static final int PADDLE_Y = 560;

    //Color.
    private static final int BOUND_FOR_COLOR = 30;
    private static final int START_OF_COLOR = 800;
    private static final int DIVIDE_FOR_COLOR = 1000;
    private static final float SATURATION = 10.9f;
    private static final float BRIGHTNESS = 5.0f;

    //Blocks.
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int NUMBER_OF_ROWS = 6;
    private static final int BLOCKS_IN_ROW = 12;
    private static final int BLOCK_X_START = 170;
    private static final int BLOCK_Y_START = 120;


    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<>();
        velocity.add(Velocity.fromAngleAndSpeed(ANGLE1, SPEED));
        velocity.add(Velocity.fromAngleAndSpeed(ANGLE2, SPEED));
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return SPEED;
    }

    @Override
    public int paddleWidth() {
        return BLOCKS_WIDTH;
    }

    @Override
    public String levelName() {
        return "Green3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600),
                new Color(DEFAULT_BG_R, DEFAULT_BG_G, DEFAULT_BG_B));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Random random = new Random();
        float nextColor;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            nextColor = (float) (random.nextInt(BOUND_FOR_COLOR) + START_OF_COLOR) / DIVIDE_FOR_COLOR;
            for (int j = i; j < BLOCKS_IN_ROW; j++) {
                blocks.add(new Block(new Rectangle(new Point(BLOCK_X_START + BLOCK_WIDTH * j,
                        BLOCK_Y_START + BLOCK_HEIGHT * i),
                        BLOCK_WIDTH, BLOCK_HEIGHT), Color.getHSBColor(nextColor, SATURATION, BRIGHTNESS)));
            }
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
