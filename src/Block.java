/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block class is the obstacle on the screen.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    private static final double EP = Math.pow(10, -10);
    private static final int CHANGE_SIGN = -1;


    /**
     * Constructor function.
     *
     * @param rectangle - the block.
     * @param color     - the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = new Rectangle(rectangle);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the current rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Make a copy of the hitListeners before iterating over them.
     *
     * @param hitter - the ball which make the hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          - ball hitter.
     * @param collisionPoint  - the collisionPoint.
     * @param currentVelocity - the current velocity.
     * @return new velocity after hitting the block.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = new Velocity(currentVelocity.getDx(), (currentVelocity.getDy()));

        this.notifyHit(hitter);

        if (Math.abs(this.rectangle.getUpperLeft().getY() - collisionPoint.getY()) <= EP
                || Math.abs(this.rectangle.getUpperLeft().getY()
                + this.rectangle.getHeight() - collisionPoint.getY()) <= EP) {
            v.setDy(v.getDy() * CHANGE_SIGN);
        }
        if (Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) <= EP
                || Math.abs(this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth() - collisionPoint.getX()) <= EP) {
            v.setDx(v.getDx() * CHANGE_SIGN);
        }
        return v;
    }

    /**
     * The function draw the ball on the given DrawSurface.
     *
     * @param surface - the board we drew on it.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * The function add the current object - the block.
     *
     * @param gameLevel - the current game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * The function remove the current object - the block.
     *
     * @param gameLevel - the current game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
