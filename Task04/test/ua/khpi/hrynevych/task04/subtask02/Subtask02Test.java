package ua.khpi.hrynevych.task04.subtask02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Tests class solving the Subtask02.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class Subtask02Test {

	/**
	 * File for testing the work of the methods.
	 */
	private static final String FILE = "testFile.txt";

	/**
	 * File for testing the work of the methods.
	 */
	private static final String FILE2 = "testFile2.txt";

	/**
	 * Encoding of the newly created files.
	 */
	private static final String ENCODING = "UTF8";

	/**
	 * Empty string.
	 */
	private static final String EMPTY = "";

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
		Class<Subtask02> cl = Subtask02.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Tests filling specified file with the random numbers.
	 */
	@Test
	public void getRandomNumbersTest() {
		final int number = 27;
		final int max = 9;
		String result;
		String pattern = "([0-9] ){26}[0-9]";

		Subtask02.getRandomNumbers(FILE, number, max);
		result = FileIO.getTextFromFile(FILE, ENCODING);

		if (!result.matches(pattern)) {
			fail();
		}
		new File(FILE).delete();
	}

	/**
	 * Tests filling specified file with the empty array.
	 *
	 * Specified file actually should be empty.
	 */
	@Test
	public void getRandomNumbersEmptyTest() {
		final int param = 0;
		String result;

		Subtask02.getRandomNumbers(FILE, param, param);
		result = FileIO.getTextFromFile(FILE, ENCODING);

		assertEquals(EMPTY, result);
		new File(FILE).delete();
	}

	/**
	 * Tests sorting array from the one file and writing it to the second one.
	 */
	@Test
	public void sortTest() {
		final String input = "12 4 3 2 2 48 1 4 13 9";
		final String expected = "1 2 2 3 4 4 9 12 13 48";
		String actual;

		FileIO.printTextToFile(input, FILE);
		Subtask02.sort(FILE, ENCODING, FILE2);
		actual = FileIO.getTextFromFile(FILE2, ENCODING);

		assertEquals(expected, actual);
		new File(FILE).delete();
		new File(FILE2).delete();
	}

	/**
	 * Tests how array from the empty file is sorted.
	 *
	 * Actually both files must be empty. Method should perform
	 * properly, not throwing exceptions or errors.
	 */
	@Test
	public void sortEmptyTest() {
		String actual;

		FileIO.printTextToFile(EMPTY, FILE);
		Subtask02.sort(FILE, ENCODING, FILE2);
		actual = FileIO.getTextFromFile(FILE2, ENCODING);

		assertEquals(EMPTY, actual);
		new File(FILE).delete();
		new File(FILE2).delete();
	}

	/**
	 * Tests proper output of the {@link Subtask02#main(String[]) main} method.
	 *
	 * @throws  IllegalAccessException  if access to the declared field can't be obtained
	 * @throws  NoSuchFieldException    if there is no field with such name
	 * @throws  FileNotFoundException   if print stream for interception of the
	 * 								    console output can't be created
	 *
	 */
	@Test
	public void mainTest() throws NoSuchFieldException,
			IllegalAccessException, FileNotFoundException {
		int number = getIntField("NUMBER");
		int maxLength = Integer.toString(getIntField("MAX")).length();
		String raw = getStringField("RAW_DATA");
		String sorted = getStringField("SORTED_DATA");

		String actual;
		String pattern = "input  ==> ([0-9]{1," + maxLength + "} ){" + (number - 1)
				+ "}[0-9]{1," + maxLength + "}(\\n|\\r|\\r\\n)output ==> ([0-9]{1," + maxLength
				+ "} ){" + (number - 1) + "}[0-9]{1," + maxLength + "}";
		PrintStream holder = System.out;
		File to = new File(FILE);
		PrintStream temp = new PrintStream(to);

		System.setOut(temp);
		Subtask02.main(null);
		System.out.close();
		System.setOut(holder);

		actual = FileIO.getTextFromFile(FILE, ENCODING);
		if (!actual.matches(pattern)) {
			fail();
		}
		new File(raw).delete();
		new File(sorted).delete();
	}

	/**
	 * Gets value of the integer field by its name.
	 *
	 * @param   name                    the name of the needed field
	 * @return                          the value of the needed field
	 * @throws  NoSuchFieldException    if there is no field with such name
	 * @throws  IllegalAccessException  if access to the declared field can't be obtained
	 */
	private static int getIntField(final String name) throws NoSuchFieldException,
			IllegalAccessException {
		Class<Subtask02> cl = Subtask02.class;
		Field field = cl.getDeclaredField(name);

		field.setAccessible(true);
		return field.getInt(Subtask02.class);
	}

	/**
	 * Gets value of the string field by its name.
	 *
	 * @param   name                    the name of the needed field
	 * @return                          the value of the needed field
	 * @throws  NoSuchFieldException    if there is no field with such name
	 * @throws  IllegalAccessException  if access to the declared field can't be obtained
	 */
	private static String getStringField(final String name) throws NoSuchFieldException,
			IllegalAccessException {
		Class<Subtask02> cl = Subtask02.class;
		Field field = cl.getDeclaredField(name);

		field.setAccessible(true);
		return (String) (field.get(Subtask02.class));
	}
}
