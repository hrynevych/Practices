package ua.khpi.hrynevych.task04.subtask03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Tests class solving the Subtask03.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class Subtask03Test {

	/**
	 * Standard input stream.
	 */
	private static final InputStream STD_IN = System.in;

	/**
	 * Standard output stream.
	 */
	private static final PrintStream STD_OUT = System.out;

	/**
	 * System dependent line separator.
	 */
	private static final String LS = System.lineSeparator();

	/**
	 * File containing information for testing methods of the class.
	 */
	private static final String FILE = "file.txt";

	/**
	 * The file into which the information intercepted from the console is written.
	 */
	private static final String FILE2 = "file2.txt";

	/**
	 * Encoding of the files of this class.
	 */
	private static final String ENCODING = "UTF8";

	/**
	 * Empty string.
	 */
	private static final String EMPTY = "";

	/**
	 * String for testing containing information of different types.
	 */
	private static final String TEST = "   !@# i K 23 ёж 43.37 string я 0.25 тест\r\n"
			+ "2&f*ck&ч.9 smeшно 8. Щ 284 43.32.3     0";

	/**
	 * String containing expected chars.
	 */
	private static final String EXPECTED_CHARS = "i K я f ч Щ";

	/**
	 * String containing expected strings.
	 */
	private static final String EXPECTED_STRINGS = "ёж string тест ck smeшно";

	/**
	 * String containing expected integers.
	 */
	private static final String EXPECTED_INTS = "23 2 284 0";

	/**
	 * String containing expected floating-point values.
	 */
	private static final String EXPECTED_DOUBLES = "43.37 0.25 0.9 8.0";

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
		Class<Subtask03> cl = Subtask03.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Prepares file filling it with the information for testing.
	 */
	@BeforeClass
	public static void prepFile() {
		FileIO.printTextToFile(TEST, FILE);
	}

	/**
	 * Compares expected chars with the actual result.
	 */
	@Test
	public void getCharsTest() {
		assertEquals(EXPECTED_CHARS, Subtask03.getChars(FILE, ENCODING));
	}

	/**
	 * Compares expected strings with the actual result.
	 */
	@Test
	public void getStringsTest() {
		assertEquals(EXPECTED_STRINGS, Subtask03.getStrings(FILE, ENCODING));
	}

	/**
	 * Compares expected integers with the actual result.
	 */
	@Test
	public void getIntsTest() {
		assertEquals(EXPECTED_INTS, Subtask03.getInts(FILE, ENCODING));
	}

	/**
	 * Compares expected floating-point numbers with the actual result.
	 */
	@Test
	public void getDoublesTest() {
		assertEquals(EXPECTED_DOUBLES, Subtask03.getDoubles(FILE, ENCODING));
	}

	/**
	 * Tests proper output of the {@link Subtask03#main(String[]) main} method.
	 *
	 * @throws  FileNotFoundException         if print stream for interception of the
	 * 								          console output can't be created
	 * @throws  UnsupportedEncodingException  if there is no encoding such as specified
	 */
	@Test
	public void mainTest() throws FileNotFoundException, UnsupportedEncodingException {
		String actual;
		final String input = "char|String|wrong|int|double|wrong|stop";
		final String pattern = "(([а-яА-ЯёЁa-zA-Z] ){1,}[а-яА-ЯёЁa-zA-Z]|[а-яА-ЯёЁa-zA-Z]|)"
				+ "(\\n|\\r|\\r\\n)"
				+ "(([а-яА-ЯёЁa-zA-Z]{2,} ){1,}[а-яА-ЯёЁa-zA-Z]{2,}|[а-яА-ЯёЁa-zA-Z]{2,}|)"
				+ "(\\n|\\r|\\r\\n)"
				+ "(([0-9]{1,} ){1,}[0-9]{1,}|[0-9]{1,}|)"
				+ "(\\n|\\r|\\r\\n)"
				+ "(([0-9]{1,}[.][0-9]{1,} ){1,}[0-9]{1,}[.][0-9]{1,}|[0-9]{1,}[.][0-9]{1,}|)";
		PrintStream temp = new PrintStream(new File(FILE2));

		System.setOut(temp);
		System.setIn(new ByteArrayInputStream(input.replace("|", LS).getBytes(ENCODING)));
		Subtask03.main(null);
		System.setIn(STD_IN);
		System.out.close();
		System.setOut(STD_OUT);

		actual = FileIO.getTextFromFile(FILE2, ENCODING);
		if (!actual.matches(pattern)) {
			fail();
		}
		new File(FILE2).delete();
	}

	/**
	 * Tests proper output of the {@link Subtask03#main(String[]) main} method
	 * if the console input will be empty.
	 *
	 * The result must be empty too.
	 *
	 * @throws  FileNotFoundException         if print stream for interception of the
	 * 								          console output can't be created
	 * @throws  UnsupportedEncodingException  if there is no encoding such as specified
	 */
	@Test
	public void mainEmptyTest() throws FileNotFoundException, UnsupportedEncodingException {
		String actual;
		PrintStream temp = new PrintStream(new File(FILE2));

		System.setOut(temp);
		System.setIn(new ByteArrayInputStream(EMPTY.getBytes(ENCODING)));
		Subtask03.main(null);
		System.setIn(STD_IN);
		System.out.close();
		System.setOut(STD_OUT);

		actual = FileIO.getTextFromFile(FILE2, ENCODING);
		assertEquals(EMPTY, actual);
		new File(FILE2).delete();
	}

	/**
	 * Deletes file created during the test.
	 */
	@AfterClass
	public static void deleteFiles() {
		new File(FILE).delete();
	}
}
