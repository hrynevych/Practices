package ua.khpi.hrynevych.task02;

import java.util.Iterator;

/**
 * Class Demo demonstrates functionality of the class {@link MyListImpl}.
 *
 * Contains the entry point to the console application - public method {@link #main(String[]) main}.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 13 Nov 2017
 */
public final class Demo {

	/**
	 * The utility class can't have instances.
	 */
	private Demo() {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method contains three blocks of tests.
	 *
	 * Tests of the first block refers to the methods of class {@link MyListImpl}.
	 * The second block tests the iterator of the specified class, and the third -
	 * list iterator.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		final int test1 = 1;
		final int test2 = 2;
		final int test3 = 3;
		final int test4 = 4;

		System.out.println("==== Part1");
		MyList list = new MyListImpl();

		// [A, A2]
		list.add("A");
		list.add("A2");
		System.out.println(list);

		// []
		list.clear();
		System.out.println(list);

		// [A, A3]
		list.add("A");
		list.add("A2");
		list.add("A3");
		list.remove("A2");
		System.out.println(list);

		// AA3
		for (Object el : list.toArray()) {
			System.out.print(el);
		}
		System.out.println();

		// 2
		System.out.println(list.size());

		// false
		System.out.println(list.contains("B"));

		// true
		System.out.println(list.contains("A3"));

		// true
		list.add("A2");
		MyList anotherList = new MyListImpl();
		anotherList.add("A");
		anotherList.add("A2");
		System.out.println(list.containsAll(anotherList));

		// false
		anotherList.add("B");
		System.out.println(list.containsAll(anotherList));

		// true
		list.add("B");
		System.out.println(list.containsAll(anotherList));

		System.out.println("==== Part2");
		list = new MyListImpl();
		list.add(test1);
		list.add(test2);
		list.add(test3);
		list.add(test4);

		// 1 2 3 4
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		// [1, 3, 4]
		it = list.iterator();
		it.next();
		it.next();
		it.remove();
		System.out.println(list);

		// 3
		System.out.println(it.next());

		// [1, 4]
		it.remove();
		System.out.println(list);

		// class java.lang.IllegalStateException
		try {
			it.remove();
		} catch (IllegalStateException ex) {
			System.out.println(ex.getClass());
		}

		System.out.println("==== Part3");
		list = new MyListImpl();
		list.add(test1);
		list.add(test2);
		list.add(test3);
		list.add(test4);

		// 1 2 3 4
		ListIterator lit = ((ListIterable) list).listIterator();
		while (lit.hasNext()) {
			System.out.print(lit.next() + " ");
		}
		System.out.println();

		// 4 3 2 1
		while (lit.hasPrevious()) {
			System.out.print(lit.previous() + " ");
		}
		System.out.println();

		list = new MyListImpl();
		lit = ((ListIterable) list).listIterator();

		// false
		System.out.println(lit.hasNext());

		// false
		System.out.println(lit.hasPrevious());

		// Elemenet
		list.add("Element");
		System.out.println(lit.next());

		// false
		System.out.println(lit.hasNext());

		// true
		System.out.println(lit.hasPrevious());
	}
}
