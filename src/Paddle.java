/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys, and moves according
 * to the player key presses.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int move;
    private int widthPaddle;

    private static final int GUI_WIDTH = 800;
    private static final int GUI_BORDER = 30;
    private static final int MOVE = 7;
    private static final int XSTART = 350;
    private static final int YSTART = 560;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 10;
    private static final int PARTS_OF_PADDLE = 5;
    private static final int PADDLE_PART1 = 300;
    private static final int PADDLE_PART2 = 330;
    private static final int PADDLE_PART4 = 30;
    private static final int PADDLE_PART5 = 60;

    /**
     * Constructor function.
     *
     * @param keyboard    - the keyboard.
     * @param stepsMove   - numbers of steps.
     * @param widthPaddle - width paddle.
     * @param location    - location paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int stepsMove, int widthPaddle, Point location) {
        this.move = stepsMove;
        this.widthPaddle = widthPaddle;
        this.keyboard = keyboard;
        this.paddle = new Rectangle(location, this.widthPaddle, HEIGHT);
    }

    /**
     * make the move to left.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() - MOVE < GUI_BORDER) {
            this.paddle.getUpperLeft().setX(GUI_BORDER);
        }
        if (this.paddle.getUpperLeft().getX() > GUI_BORDER) {
            this.paddle.getUpperLeft().setX(this.paddle.getUpperLeft().getX() - MOVE);
        }
    }

    /**
     * make the move to right.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + MOVE > GUI_WIDTH - GUI_BORDER) {
            this.paddle.getUpperLeft().setX(GUI_WIDTH - GUI_BORDER - this.paddle.getWidth());
        }
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() < GUI_WIDTH - GUI_BORDER) {
            this.paddle.getUpperLeft().setX(this.paddle.getUpperLeft().getX() + MOVE);
        }
    }

    /**
     * check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * The function is draw the paddle.
     *
     * @param d - the surface we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.PINK);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * Get function - CollisionRectangle.
     *
     * @return the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * the function return the new velocity determined by the part in which the ball came in contact with the paddle.
     *
     * @param hitter          - ball hitter.
     * @param collisionPoint  - the collisionPoint.
     * @param currentVelocity - the current velocity.
     * @return the new velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = new Velocity(currentVelocity.getDx(), (currentVelocity.getDy()));
        double equallySpace = this.paddle.getWidth() / PARTS_OF_PADDLE;

        //the parts.
        double part1 = this.paddle.getUpperLeft().getX() + equallySpace;
        double part2 = part1 + equallySpace;
        double part3 = part2 + equallySpace;
        double part4 = part3 + equallySpace;
        double part5 = part4 + equallySpace;

        //the new velocity.
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX() && collisionPoint.getX() < part1) {
            return Velocity.fromAngleAndSpeed(PADDLE_PART1, v.fromVectorToSpeed());
        }
        if (collisionPoint.getX() >= part1 && collisionPoint.getX() < part2) {
            return Velocity.fromAngleAndSpeed(PADDLE_PART2, v.fromVectorToSpeed());
        }
        if (collisionPoint.getX() >= part2 && collisionPoint.getX() < part3) {
            return new Velocity(v.getDx(), v.getDy() * (-1));
        }
        if (collisionPoint.getX() >= part3 && collisionPoint.getX() < part4) {
            return Velocity.fromAngleAndSpeed(PADDLE_PART4, v.fromVectorToSpeed());
        }
        if (collisionPoint.getX() >= part4 && collisionPoint.getX() <= part5) {
            return Velocity.fromAngleAndSpeed(PADDLE_PART5, v.fromVectorToSpeed());
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - the current game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}