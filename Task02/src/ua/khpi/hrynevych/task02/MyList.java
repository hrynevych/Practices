package ua.khpi.hrynevych.task02;

/**
 * Provides implementing classes with a least number of methods
 * which allow to use these classes as a lists.
 *
 * Suggets that implementing class has internal structure which
 * can be considered as a linked list of elements. This interface
 * contains methods for adding, removing and checking of presence
 * of elements in the list and also some methods for representing
 * the list or information about it.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 13 Nov 2017
 */
public interface MyList extends Iterable<Object> {

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param  e  element to be added
	 */
	void add(Object e);

	/**
	 * Removes all of the elements from this list.
	 */
	void clear();

	/**
	 * Removes the first occurrence of the specified element
	 * from this list.
	 *
	 * @param   o  element to be removed
	 * @return     true if such element was found and removed,
	 *             false - otherwise
	 */
	boolean remove(Object o);

	/**
	 * Returns an array containing all of the elements
	 * in this list in proper sequence.
	 *
	 * @return  array of elements
	 */
	Object[] toArray();

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return  the number of elements in this list
	 */
	int size();

	/**
	 * Returns true if this list contains the specified element.
	 *
	 * @param   o  element which presence is checking
	 * @return     true if this list contains the specified element,
	 * 			   false - otherwise
	 */
	boolean contains(Object o);

	/**
	 * Returns true if this list contains all of the elements
	 * of the specified list.
	 *
	 * @param   c  list of elements to find in this list
	 * @return     true if all of elements of the specified list
	 * 			   are present in this list, false - otherwise
	 */
	boolean containsAll(MyList c);
}
