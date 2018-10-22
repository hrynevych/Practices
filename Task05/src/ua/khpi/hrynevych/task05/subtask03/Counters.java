package ua.khpi.hrynevych.task05.subtask03;

/**
 * Class containing two counters.
 *
 * Also there are methods for increasing and getting of each.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public class Counters {

	/**
	 * First counter.
	 */
	private int counter1;

	/**
	 * Second counter.
	 */
	private int counter2;

	/**
	 * Creates new instance with both counters equal to 0.
	 */
	public Counters() {
		counter1 = 0;
		counter2 = 0;
	}

	/**
	 * Increments the first counter.
	 */
	public void increaseCounter1() {
		counter1++;
	}

	/**
	 * Increments the second counter.
	 */
	public void increaseCounter2() {
		counter2++;
	}

	/**
	 * Getter for the first counter.
	 *
	 * @return  first counter
	 */
	public int getCounter1() {
		return counter1;
	}

	/**
	 * Getter for the second counter.
	 *
	 * @return  second counter
	 */
	public int getCounter2() {
		return counter2;
	}
}
