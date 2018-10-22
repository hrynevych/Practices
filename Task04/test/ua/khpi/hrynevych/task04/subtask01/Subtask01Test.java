package ua.khpi.hrynevych.task04.subtask01;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Tests class solving the Subtask01.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class Subtask01Test {

	/**
	 * Test string for conversion.
	 */
	private static final String TEST = "I want to test how this method works\r\n"
			+ "Здесь и слова для теста и даже ёж";

	/**
	 * Expected result.
	 */
	private static final String EXPECTED = "I WANT to TEST how THIS METHOD WORKS\r\n"
			+ "ЗДЕСЬ и СЛОВА для ТЕСТА и ДАЖЕ ёж";

	/**
	 * Empty string.
	 */
	private static final String EMPTY = "";

	/**
	 * The file into which the information intercepted from the console is written.
	 */
	private static final String FILE_TO = "testTo.txt";

	/**
	 * Encoding of the prepared files.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * Encoding of the newly created file.
	 */
	private static final String ECLIPSE_ENCODING = "UTF8";

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
		Class<Subtask01> cl = Subtask01.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Tests proper work of the method that puts big words to uppercase.
	 *
	 * Big word is the sequence of four or more letters.
	 */
	@Test
	public void bigWordsToUpperCaseTest() {
		assertEquals(EXPECTED, Subtask01.bigWordsToUpperCase(TEST));
	}

	/**
	 * Tests sending null as a parameter to the method
	 * {@link Subtask01#bigWordsToUpperCase(String) bigWordsToUpperCase}.
	 *
	 * Actually NullPointerException can be expected.
	 */
	@Test(expected = NullPointerException.class)
	public void bigWordsToUpperCaseNullTest() {
		Subtask01.bigWordsToUpperCase(null);
	}

	/**
	 * Text containing no big words should be returned without any changes.
	 *
	 * Big word is the sequence of four or more letters.
	 */
	@Test
	public void bigWordsToUpperCaseEmptyTest() {
		assertEquals(EMPTY, Subtask01.bigWordsToUpperCase(EMPTY));
	}

	/**
	 * Tests proper output to the console considering the file
	 * with initial information.
	 *
	 * @throws  IllegalAccessException  if access to the declared field can't be obtained
	 * @throws  NoSuchFieldException    if there is no field indicating the file name
	 * @throws  FileNotFoundException   if print stream for interception of the
	 * 								    console output can't be created
	 */
	@Test
	public void mainTest() throws NoSuchFieldException, IllegalAccessException,
			FileNotFoundException {
		String expected = getExpected();
		String actual;
		PrintStream holder = System.out;
		File to = new File(FILE_TO);
		PrintStream temp = new PrintStream(to);

		System.setOut(temp);
		Subtask01.main(null);
		System.out.close();
		System.setOut(holder);

		actual = FileIO.getTextFromFile(FILE_TO, ECLIPSE_ENCODING);
		assertEquals(expected, actual);
		to.delete();
	}

	/**
	 * Gets expected result for the main method considering the information
	 * in the specified file.
	 *
	 * @return                            expected result
	 * @throws  NoSuchFieldException      if there is no field indicating the file name
	 * @throws  IllegalAccessException    if access to the declared field can't be obtained
	 */
	private static String getExpected() throws NoSuchFieldException, IllegalAccessException {
		String expected;
		String fileName;
		Class<Subtask01> cl = Subtask01.class;
		Field field = cl.getDeclaredField("FILE_NAME");

		field.setAccessible(true);
		fileName = (String) (field.get(Subtask01.class));
		expected = FileIO.getTextFromFile(fileName, ENCODING);
		return Subtask01.bigWordsToUpperCase(expected);
	}

}
