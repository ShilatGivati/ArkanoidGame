/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;

/**
 * A Sprite is a game object that can be drawn to the screen (and which is not just a background image).
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d - the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add to the game.
     *
     * @param gameLevel - the current game.
     */
    void addToGame(GameLevel gameLevel);
}
