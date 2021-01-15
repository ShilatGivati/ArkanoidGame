/**
 * @Author Shilat Givati
 * ID 206377038
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Key Press Stoppable Animation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private String key;
    private KeyboardSensor keyboard;
    private boolean isPressed;
    private boolean stop;

    /**
     * Constructor function.
     *
     * @param sensor    - keyboard sensor.
     * @param key       - the key.
     * @param animation - the current animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.isPressed = true;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }

    /**
     * @param d - the current surface.
     */
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed(this.key) && !this.isPressed) {
            this.stop = true;
        }
        if (!this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.isPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    /**
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
