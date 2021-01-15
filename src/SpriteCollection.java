/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection class is a collection of the game object that can be drawn to the screen.
 * (and which is not just a background image).
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructor function.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * Adding the Sprite object to the current list.
     *
     * @param s - the object we want to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Adding the Sprite object to the current list.
     *
     * @param s - the object we want to add.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> newSprites = new ArrayList<>(this.spriteList);
        for (Sprite sprite : newSprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - is the DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}
