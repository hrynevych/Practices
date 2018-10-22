package ua.khpi.hrynevych.task02;

/**
 * Provides a class that implements this interface with a list iterator.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 13 Nov 2017
 */
public interface ListIterable {

	/**
	 * This method should return an instance of the class that implements
	 * {@link ListIterator} interface.
	 *
	 * @return  list iterator of the class that implements this interface
	 */
	ListIterator listIterator();
}
