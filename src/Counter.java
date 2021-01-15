/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * Counter class.
 */
public class Counter {
    private int counter;

    /**
     * constructor function.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * @param number - add number to current count.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * @param number - subtract number from current count.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * @return - get current count.
     */
    public int getValue() {
        return this.counter;
    }
}