/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * constructor, initialazed to 0.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * constructor.
     *
     * @param value - the value for initializing the counter.
     */
    public Counter(int value) {
        this.counter = value;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.counter;
    }

    /**
     * set function for the counter.
     *
     * @param value = set counter to given number.
     */
    public void setValue(int value) {
        this.counter = value;
    }
}