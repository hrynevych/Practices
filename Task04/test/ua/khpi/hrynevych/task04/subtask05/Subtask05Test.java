package ua.khpi.hrynevych.task04.subtask05;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.MissingResourceException;
import org.junit.Test;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Tests class solving the Subtask05.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class Subtask05Test {

	/**
	 * The file into which the information intercepted from the console is written.
	 */
	private static final String FILE = "file.txt";

	/**
	 * Encoding of the specified file.
	 */
	private static final String ENCODING = "UTF8";

	/**
	 * System dependent line separator.
	 */
	private static final String LS = System.lineSeparator();

	/**
	 * Standard input stream.
	 */
	private static final InputStream STD_IN = System.in;

	/**
	 * Standard output stream.
	 */
	private static final PrintStream STD_OUT = System.out;

	/**
	 * The name of the basic resource package.
	 */
	private static final String BASE_NAME = "resources";

	/**
	 * Keys to test.
	 */
	private static final String[] KEYS = {"table", "apple"};

	/**
	 * Values to test.
	 */
	private static final String[][] VALUES = {{"table", "apple"}, {"стол", "яблоко"}};

	/**
	 * Localizations to test.
	 */
	private static final String[] L10N = {"en", "ru"};

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
		Class<Subtask05> cl = Subtask05.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Tests whether proper values are returned by the
	 * {@link Subtask05#getValue(String, String, String) getValue} method.
	 */
	@Test
	public void getValueTest() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				assertEquals(VALUES[j][i], Subtask05.getValue(KEYS[i], BASE_NAME, L10N[j]));
			}
		}
	}

	/**
	 * Tests nonexistent key.
	 */
	@Test(expected = MissingResourceException.class)
	public void getValueWrongKeyTest() {
		Subtask05.getValue("!!!bad key!!!", BASE_NAME, L10N[0]);
	}

	/**
	 * Tests nonexistent resorce package.
	 */
	@Test(expected = MissingResourceException.class)
	public void getValueWrongBaseNameTest() {
		Subtask05.getValue(KEYS[0], "no such base", L10N[0]);
	}

	/**
	 * Tests nonexistent localization.
	 *
	 * In this case default localization should be used.
	 */
	@Test
	public void getValueWrongL10nTest() {
		String expected = Subtask05.getValue(KEYS[0], BASE_NAME, Locale.getDefault().getLanguage());
		String actual = Subtask05.getValue(KEYS[0], BASE_NAME, "no such language");

		assertEquals(expected, actual);
	}

	/**
	 * Tests null key.
	 */
	@Test(expected = NullPointerException.class)
	public void getValueNullKeyTest() {
		Subtask05.getValue(null, BASE_NAME, L10N[0]);
	}

	/**
	 * Tests null resource package name.
	 */
	@Test(expected = NullPointerException.class)
	public void getValueNullBaseNameTest() {
		Subtask05.getValue(KEYS[0], null, L10N[0]);
	}

	/**
	 * Tests null localization.
	 */
	@Test(expected = NullPointerException.class)
	public void getValueNullL10nTest() {
		Subtask05.getValue(KEYS[0], BASE_NAME, null);
	}

	/**
	 * Tests proper output of the {@link Subtask05#main(String[]) main} method.
	 *
	 * @throws  FileNotFoundException         if print stream for interception of the
	 * 								          console output can't be created
	 * @throws  UnsupportedEncodingException  if there is no encoding such as specified
	 */
	@Test
	public void mainTest() throws FileNotFoundException, UnsupportedEncodingException {
		final String input = "table ru|apple en|NoSuchKey ru|table en|dou|table en|apple ru|stop";
		final String expected = "стол|apple|Bad format|table|Bad format|table|яблоко";
		String actual;
		PrintStream temp = new PrintStream(new File(FILE));

		System.setOut(temp);
		System.setIn(new ByteArrayInputStream(input.replace("|", LS).getBytes(ENCODING)));
		Subtask05.main(null);
		System.setIn(STD_IN);
		System.out.close();
		System.setOut(STD_OUT);

		actual = FileIO.getTextFromFile(FILE, ENCODING);
		assertEquals(expected.replace("|", LS), actual);
		new File(FILE).delete();
	}

	/**
	 * Tests proper output of the {@link Subtask05#main(String[]) main} method
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
		PrintStream temp = new PrintStream(new File(FILE));

		System.setOut(temp);
		System.setIn(new ByteArrayInputStream(EMPTY.getBytes(ENCODING)));
		Subtask05.main(null);
		System.setIn(STD_IN);
		System.out.close();
		System.setOut(STD_OUT);

		actual = FileIO.getTextFromFile(FILE, ENCODING);
		assertEquals(EMPTY, actual);
		new File(FILE).delete();
	}
}
