package ua.khpi.hrynevych.task02;

import java.util.Iterator;

/**
 * This interface is a kind of a template for the list iterators.
 *
 * Provides implementing classes with a set of methods extended
 * from java.util.Iterator interface. Also contains methods for
 * iterating backwards and replacing elements.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 13 Nov 2017
 */
public interface ListIterator extends Iterator<Object> {

	/**
	 * Returns true if this list iterator has more elements when
	 * traversing the list in the reverse direction.
	 *
	 * @return  true if the iteration has more elements, false - otherwise
	 */
	boolean hasPrevious();

	/**
	 * Returns the previous element in the list and moves the cursor
	 * position backwards.
	 *
	 * @return  previous element in the list
	 */
	Object previous();

	/**
	 * Replaces the last element returned by next or previous with
	 * the specified element.
	 *
	 * @param  e  element that replaces the last element returned
	 */
	void set(Object e);

	/**
	 * Removes the last element returned by next or previous.
	 */
	void remove();
}
