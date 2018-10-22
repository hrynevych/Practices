package ua.khpi.hrynevych.task02;

import java.util.Iterator;

/**
 * Class MyListImpl represents a doubly-linked list and contains
 * basic methods that allow to operate it.
 *
 * The internal structure of the class was adopted from the java.util.LinkedList.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 13 Nov 2017
 * @see      java.util.LinkedList
 */
public class MyListImpl implements MyList, ListIterable {

	/**
	 * Current size of the list.
	 */
	private int size;

	/**
	 * First node of the list.
	 */
	private Node first;

	/**
	 * Last node of the list.
	 */
	private Node last;

	/**
	 * Constructs new empty list.
	 */
	public MyListImpl() { }

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param  e  element to be added
	 */
	@Override
	public void add(final Object e) {
		Node temp = last;
		Node newLast = new Node(last, e, null);

		last = newLast;
		if (size == 0) {
			first = newLast;
		} else {
			temp.next = newLast;
		}
		size++;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
        for (Node x = first; x != null;) {
            Node next = x.next;

            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = null;
        last = null;
        size = 0;
    }

	/**
	 * Removes the first occurrence of the specified element
	 * from this list.
	 *
	 * @param   o  element to be removed
	 * @return     true if such element was found and removed,
	 *             false - otherwise
	 */
	@Override
	public boolean remove(final Object o) {
		if (o == null) {
			for (Node x = first; x != null; x = x.next) {
				if (x.item == null) {
					unlink(x);
	                return true;
	            }
	        }
	    } else {
	        for (Node x = first; x != null; x = x.next) {
	            if (o.equals(x.item)) {
	                unlink(x);
	                return true;
	            }
	        }
	    }
	    return false;
	}

	/**
	 * Unlinks specified node from the list preserving its continuity.
	 *
	 * Assumed that node to be deleted is not null.
	 *
	 * @param  x  node to be deleted
	 */
	private void unlink(final Node x) {
        Node next = x.next;
        Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
	}

	/**
	 * Returns an array containing all of the elements
	 * in this list in proper sequence.
	 *
	 * @return  array of elements
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
        int i = 0;

        for (Node x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return  the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains the specified element.
	 *
	 * @param   o  element which presence is checking
	 * @return     true if this list contains the specified element,
	 * 			   false - otherwise
	 */
	@Override
	public boolean contains(final Object o) {
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return true;
                }
            }
        }
        return false;
	}

	/**
	 * Returns true if this list contains all of the elements
	 * of the specified list.
	 *
	 * @param   c  list of elements to find in this list
	 * @return     true if all of elements of the specified list
	 * 			   are present in this list, false - otherwise
	 */
	@Override
	public boolean containsAll(final MyList c) {
		for (Object x : c.toArray()) {
			if (!this.contains(x)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns formatted string representation of this list.
	 *
	 * Resulting string contains comma-separated string representations of
	 * the elements of the list in proper order, surrounded by square braces.
	 *
	 * @return  string representation of the list
	 */
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("[");

		for (Node x = first; x != null; x = x.next) {
			res.append(x.item.toString());
			if (x.next != null) {
				res.append(", ");
			}
		}
		return res.append("]").toString();
	}

	/**
	 * Returns new iterator that allows to move through elements of th list forwards
	 * (from first node to last).
	 *
	 * @return  new iterator for this list
	 */
	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	/**
	 * Returns an instance of the class {@link ListIteratorImpl} that is
	 * the list iterator for this list.
	 *
	 * @return  list iterator for this list
	 */
	@Override
	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}


	/**
	 * Class Node represents the model of node from this list.
	 *
	 * Encapsulates item, which is the element of the list, and links to
	 * the next and previous nodes in this list.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 13 Nov 2017
	 */
	private class Node {

		/**
		 * Element of the list.
		 */
        private Object item;

        /**
         * Link to the next node of the list.
         */
        private Node next;

        /**
         * Link to the previous node of the list.
         */
        private Node prev;

        /**
         * Constructs new node, containing the element, and appends it to
         * the specified place of the list, determined by links.
         *
         * @param  prevNode  previous node of the list
         * @param  element 	 element of the list (content of this node)
         * @param  nextNode  next node of the list
         */
        Node(final Node prevNode, final Object element, final Node nextNode) {
            this.item = element;
            this.next = nextNode;
            this.prev = prevNode;
        }
    }


	/**
	 * Nested iterator of the list in {@link MyListImpl}.
	 *
	 * Provides this list with iterator allowing iterate through
	 * elements of the list forwards.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 13 Nov 2017
	 */
	private class IteratorImpl implements Iterator<Object> {

		/**
		 * Position of current iteration.
		 */
		private int index = -1;

		/**
		 * Counter of calls of method {@link #next()}.
		 */
		private int nextCalls = 0;

		/**
		 * Returns true if the iteration has more elements.
		 *
		 * @return  true if the iteration has more elements,
		 * 			false - otherwise
		 */
		@Override
		public boolean hasNext() {
			return (index < (size - 1));
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return  the next element in the iteration.
		 */
		@Override
		public Object next() {
			nextCalls++;
			return MyListImpl.this.toArray()[++index];
		}

		/**
		 * Removes from the underlying collection the last element
		 * returned by this iterator.
		 *
		 * Assumed that method {@link #next()} should be called before
		 * every call of this method.
		 */
		@Override
		public void remove() {
			if (nextCalls == 0) {
				throw new IllegalStateException();
			}

			Node temp = first;

			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			MyListImpl.this.unlink(temp);
			index--;
			nextCalls = 0;
		}
	}


	/**
	 * Nested list iterator of the list in {@link MyListImpl}.
	 *
	 * Provides this list with iterator that allows to iterate
	 * both forwards and backwards.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 13 Nov 2017
	 */
	private class ListIteratorImpl extends IteratorImpl implements ListIterator {

		/**
		 * Counter of calls of method {@link #previous()}.
		 */
		private int prevCalls = 0;

		/**
		 * Returns true if this list iterator has more elements when
		 * traversing the list in the reverse direction.
		 *
		 * @return  true if the iteration has more elements, false - otherwise
		 */
		@Override
		public boolean hasPrevious() {
			return (super.index > -1);
		}

		/**
		 * Returns the previous element in the list and moves the cursor
		 * position backwards.
		 *
		 * @return  previous element in the list
		 */
		@Override
		public Object previous() {
			prevCalls++;
			return MyListImpl.this.toArray()[super.index--];
		}

		/**
		 * Replaces the last element returned by next or previous with
		 * the specified element.
		 *
		 * Assumed that calls of this method should not follow one by one.
		 * Thus one (or both) of the methods {@link #next()} or {@link #previous()}
		 * should be called between calls of this method.
		 *
		 * @param  e  element that replaces the last element returned
		 */
		@Override
		public void set(final Object e) {
			if ((super.nextCalls + prevCalls) == 0) {
				throw new IllegalStateException();
			}

			Node temp = first;

			for (int i = 0; i < super.index; i++) {
				temp = temp.next;
			}
			temp.item = e;
			super.index--;
			super.nextCalls = 0;
			prevCalls = 0;
		}

		/**
		 * Removes from the underlying collection the last element
		 * returned by next or previous.
		 *
		 * Assumed that calls of this method should not follow one by one.
		 * Thus one (or both) of the methods {@link #next()} or {@link #previous()}
		 * should be called between calls of this method.
		 */
		@Override
		public void remove() {
			if ((super.nextCalls + prevCalls) == 0) {
				throw new IllegalStateException();
			}

			Node temp = first;

			for (int i = 0; i < super.index; i++) {
				temp = temp.next;
			}
			MyListImpl.this.unlink(temp);
			super.index--;
			super.nextCalls = 0;
			prevCalls = 0;
		}
	}
}
