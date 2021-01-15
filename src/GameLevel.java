/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * A class that sets up the game and builds it.
 */
public class
GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocks;
    private Counter balls;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private boolean running;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    private static final int BORDER_SIZE = 30;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int START = 0;
    private static final int FULL_SCORE = 100;

    //Ball parameters.
    private static final int XSTART = 400;
    private static final int YSTART = 550;
    private static final int BALL_SIZE = 5;

    //Run loop.
    private static final int DEFAULT_BG_R = 74;
    private static final int DEFAULT_BG_G = 33;
    private static final int DEFAULT_BG_B = 73;

    /**
     * Constructor function.
     *
     * @param level           -           the current level.
     * @param keyboardSensor  - keyboard Sensor.
     * @param animationRunner - animation runner.
     * @param score           - the score
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     int score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocks = new Counter();
        this.balls = new Counter();
        this.score = new Counter();
        this.levelInformation = level;
        this.score.increase(score);
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.scoreIndicator = new ScoreIndicator(this.score, this.levelInformation.levelName());
        this.blockRemover = new BlockRemover(this, blocks);
        this.ballRemover = new BallRemover(this, balls);
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.running = true;
    }

    /**
     * @return - the number of balls.
     */
    public int getNumberOfBalls() {
        return this.balls.getValue();
    }

    /**
     * @return - the number of blocks.
     */
    public int getNumberOfBlocks() {
        return this.blocks.getValue();
    }

    /**
     * @return - the score.
     */
    public int getScore() {
        return this.score.getValue();
    }

    /**
     * add to the environment the Collidable object.
     *
     * @param c - the Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * @param s the sprite which is removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * @return the environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * @param c - remove from the environment c Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        int numOfBalls = this.levelInformation.numberOfBalls();
        List<Velocity> velocityList = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < numOfBalls; i++) {
            Ball ball = new Ball(new Point(XSTART, YSTART), BALL_SIZE, Color.WHITE);
            ball.setVelocity(velocityList.get(i).getDx(), velocityList.get(i).getDy());
            ball.addToGame(this);
            balls.increase(1);
        }

        Block recTop = new Block(new Rectangle(new Point(START, START), WIDTH, BORDER_SIZE), Color.BLACK);
        Block recLeft = new Block(new Rectangle(new Point(START, START), BORDER_SIZE, HEIGHT), Color.BLACK);
        Block recBot = new Block(new Rectangle(new Point(START, HEIGHT), WIDTH, 1),
                Color.DARK_GRAY);
        recBot.addHitListener(this.ballRemover);
        Block recRight = new Block(new Rectangle(new Point(WIDTH - BORDER_SIZE, START), BORDER_SIZE, HEIGHT),
                Color.BLACK);
        recTop.addToGame(this);
        recLeft.addToGame(this);
        recBot.addToGame(this);
        recRight.addToGame(this);
        this.scoreIndicator.addToGame(this);

        List<Block> blocksToRemove = this.levelInformation.blocks();
        for (Block block : blocksToRemove) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTrackingListener);
        }
        this.blocks.increase(this.levelInformation.numberOfBlocksToRemove());

        Paddle paddle = new Paddle(keyboard, this.levelInformation.paddleSpeed(), this.levelInformation.paddleWidth(),
                this.levelInformation.paddleLocation());
        paddle.addToGame(this);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }


    /**
     * @param d - the current surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.blocks.getValue() == 0) {
            this.score.increase(FULL_SCORE);
            this.running = false;
        }

        if (this.balls.getValue() == 0) {
            this.running = false;
        }

        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("פ")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }

        d.setColor(new Color(DEFAULT_BG_R, DEFAULT_BG_G, DEFAULT_BG_B));
        d.fillRectangle(START, START, WIDTH, HEIGHT);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * @return if we need to stop the game.
     */
    @Override
    public boolean shouldStop() {
        return !(this.running);
    }
}
