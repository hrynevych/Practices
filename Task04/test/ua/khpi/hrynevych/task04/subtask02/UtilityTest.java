package ua.khpi.hrynevych.task04.subtask02;

import static org.junit.Assert.assertArrayEquals;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

/**
 * Tests proper work of the utility methods.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class UtilityTest {

	/**
	 * Object of the utility class can't be obtained.
	 *
	 * Creating the object is unsupported operation so that's why such
	 * exception can be expected.
	 *
	 * @throws  Throwable  if there is a problem with creating the object
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void constructorTest() throws Throwable {
		Class<Utility> cl = Utility.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Tests whether the array is extracted from the string properly.
	 */
	@Test
	public void getArrayFromStringTest() {
		final String input = "0 12 48 2    2 df3d4  045";
		final int[] expected = {0, 12, 48, 2, 2, 3, 4, 45};

		assertArrayEquals(expected, Utility.getArrayFromString(input));
	}

	/**
	 * Tests whether an empty array is extracted from the string containing no digits.
	 */
	@Test
	public void getArrayFromStringEmptyTest() {
		String input = "   no numbers here!  ";
		int[] expected = {};

		assertArrayEquals(expected, Utility.getArrayFromString(input));
	}

	/**
	 * Tests extracting an array from the null string.
	 *
	 * Actually NullPointerException can be expected.
	 */
	@Test(expected = NullPointerException.class)
	public void getArrayFromStringNullTest() {
		Utility.getArrayFromString(null);
	}

	/**
	 * Tests whether the array is sorted properly using the Shell sort.
	 */
	@Test
	public void shellSortTest() {
		final int[] unsorted = {12, 4, 3, 2, 2, 48, 1, 4, 13, 9};
		final int[] expected = {1, 2, 2, 3, 4, 4, 9, 12, 13, 48};

		assertArrayEquals(expected, Utility.shellSort(unsorted));
	}

	/**
	 * Tests Shell sort for an empty array.
	 *
	 * Actually an empty array should be returned.
	 */
	@Test
	public void shellSortEmptyTest() {
		int[] empty = {};

		assertArrayEquals(empty, Utility.shellSort(empty));
	}

	/**
	 * Tests sorting of the null array.
	 *
	 * Actually NullPointerException can be expected.
	 */
	@Test(expected = NullPointerException.class)
	public void shellSortNullTest() {
		Utility.shellSort(null);
	}

}
