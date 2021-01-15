/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * First Level Class.
 */
public class DirectHit implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 1;
    private static final int VELOCITY_DX = 0;
    private static final int VELOCITY_DY = 5;
    private static final Point POS = new Point(350, 100);
    private static final int BLOCK_WIDTH = 100;
    private static final int BLOCK_HEIGHT = 60;

    //Colors.
    private static final int DEFAULT_BG_R = 74;
    private static final int DEFAULT_BG_G = 33;
    private static final int DEFAULT_BG_B = 73;

    //Paddle.
    private static final int X_START = 350;
    private static final int Y_START = 560;

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<>();
        velocity.add(new Velocity(VELOCITY_DX, VELOCITY_DY));
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return VELOCITY_DY;
    }

    @Override
    public int paddleWidth() {
        return BLOCK_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600),
                new Color(DEFAULT_BG_R, DEFAULT_BG_G, DEFAULT_BG_B));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(POS, BLOCK_WIDTH, BLOCK_HEIGHT), Color.PINK));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * @return the location of the paddle.
     */
    public Point paddleLocation() {
        return new Point(X_START, Y_START);
    }
}
