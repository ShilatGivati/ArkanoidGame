/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;

/**
 * The Ball class has size, radius, color, and location.
 * In addition, balls can draw themselves on DrawSurface.
 */
public class Ball implements Sprite {
    private Point center;
    private int size;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private static final int SIDES_OF_REC = 4;
    private static final int CHANGE_SIGN = -1;


    /**
     * constructor function.
     *
     * @param center - the center X point of the circle.
     * @param r      - the center Y point of the circle.
     * @param color  - the size (radius).
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center);
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * constructor function.
     *
     * @param center - the center X point of the circle.
     * @param r      - the center Y point of the circle.
     * @param color  - the size (radius).
     * @param v      - r - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity v) {
        this.center = new Point(center);
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(v);
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * constructor function - duplicating.
     *
     * @param x     - the center X point of the circle.
     * @param y     - the center Y point of the circle.
     * @param r     - the size (radius).
     * @param color - the color of the ball.
     * @param v     - the velocity of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Velocity v) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(v);
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * constructor function - duplicating.
     *
     * @param x     the center X point of the circle.
     * @param y     the center Y point of the circle.
     * @param r     the size (radius).
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * @return the X center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the Y center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the size of the circle.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return the color of the circle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the Game Environment value.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * The function draw the ball on the given DrawSurface.
     *
     * @param surface - the board we drew on it.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * move one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Set function of the velocity.
     *
     * @param v - the new velocity we want to set up.
     */
    public void setVelocity(Velocity v) {
        this.velocity.setDx(v.getDx());
        this.velocity.setDy(v.getDy());
    }

    /**
     * Set function of the velocity.
     *
     * @param dx - the values we want to set in velocity.
     * @param dy - the values we want to set in velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * @return get the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The function make the move of the ball end validate that the move does not exceed the limits.
     *
     * @param startX - the start X value of the ball.
     * @param startY - the start Y value of the ball.
     * @param endX   - the end X value of the ball.
     * @param endY   - the end Y value of the ball.
     */
    public void moveOneStep(int startX, int startY, int endX, int endY) {
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        Line line = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(line);
        if (collisionInfo == null) {
            if (((this.center.getX() + dx + this.size) >= endX && this.velocity.getDx() > 0)
                    || ((this.center.getX() + dx - this.size) <= startX && this.velocity.getDx() < 0)) {
                this.velocity.setDx(this.velocity.getDx() * CHANGE_SIGN);
            }
            if (((this.center.getY() + dy + this.size) >= endY && this.velocity.getDy() > 0)
                    || ((this.center.getY() + dy - this.size <= startY) && this.velocity.getDy() < 0)) {
                this.velocity.setDy(this.velocity.getDy() * CHANGE_SIGN);
            }
        } else {
            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * The function make one move.
     */
    public void moveOneStep() {
        for (int i = 0; i < SIDES_OF_REC; i++) {
            Line line = new Line(this.center, this.getVelocity().applyToPoint(this.center));
            CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(line);
            if (collisionInfo == null) {
                break;
            } else {
                this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                        this.velocity);
            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * The function add the current object - the ball.
     *
     * @param gameLevel - the current game.
     */
    public void addToGame(GameLevel gameLevel) {
        this.gameEnvironment = gameLevel.getEnvironment();
        gameLevel.addSprite(this);
    }

    /**
     * The function remove the ball.
     *
     * @param gameLevel - the current game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
