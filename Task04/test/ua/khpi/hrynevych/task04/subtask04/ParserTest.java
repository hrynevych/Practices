package ua.khpi.hrynevych.task04.subtask04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Contains test for sentence parser from the Subtask04.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class ParserTest {

	/**
	 * System dependent line separator.
	 */
	private static final String LS = System.lineSeparator();

	/**
	 * File containing information for testing.
	 */
	private static final String FILE = "test.txt";

	/**
	 * Encoding of the specified file.
	 */
	private static final String ENCODING = "UTF8";

	/**
	 * String for testing.
	 */
	private static final String TEST = "Предложение. И еще " + LS + "одно. And also " + LS
			+ "one more sentence. не " + LS + "предложение. And now it is it. Ё.";

	/**
	 * Array of expected sentences.
	 */
	private static final String[] EXPECTED = {"Предложение.", "И еще одно.",
			"And also one more sentence.", "And now it is it.", "Ё."};

	/**
	 * Filling the file for testing with some information.
	 */
	@BeforeClass
	public static void prepFile() {
		FileIO.printTextToFile(TEST, FILE);
	}

	/**
	 * Tests whether parser object creates properly.
	 *
	 * @throws  IllegalAccessException  if access to the declared field can't be obtained
	 * @throws  NoSuchFieldException    if there is no field with such name
	 */
	@Test
	public void constructorTest() throws NoSuchFieldException, IllegalAccessException {
		Parser obj = null;

		try {
			obj = new Parser(FILE, ENCODING);
		} catch (Throwable ex) { }
		if (obj == null) {
			fail();
		} else {
			Class<Parser> cl = Parser.class;
			Field field = cl.getDeclaredField("text");

			field.setAccessible(true);
			assertEquals(TEST, field.get(obj));
		}
	}

	/**
	 * Tests whether the sentence iterator is actually returned by
	 * the method {@link Parser#iterator() iterator}.
	 */
	@Test
	public void iteratorTest() {
		Parser obj = new Parser(FILE, ENCODING);
		Iterator<String> it = obj.iterator();
		Class<Parser> cl = Parser.class;
		Class<?> senIt = cl.getDeclaredClasses()[0];

		if (!it.getClass().getName().equals(senIt.getName())) {
			fail();
		}
	}

	/**
	 * Tests whether iterations perform properly using the test information.
	 */
	@Test
	public void iterationTest() {
		Parser obj = new Parser(FILE, ENCODING);
		int i = 0;

		for (String s : obj) {
			if (!s.equals(EXPECTED[i])) {
				fail();
			}
			i++;
		}
	}

	/**
	 * Trying to perform removing.
	 *
	 * Operation of removing sentences is unsupported so that's why
	 * such exception can be expected.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void iteratorRemoveTest() {
		Iterator<String> it = new Parser(FILE, ENCODING).iterator();

		if (it.hasNext()) {
			it.next();
		}
		it.remove();
	}

	/**
	 * Deletes file created during the testing.
	 */
	@AfterClass
	public static void deleteFile() {
		new File(FILE).delete();
	}
}
