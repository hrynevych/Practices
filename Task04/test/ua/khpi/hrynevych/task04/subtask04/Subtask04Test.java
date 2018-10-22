package ua.khpi.hrynevych.task04.subtask04;

import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Tests class solving the Subtask04.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class Subtask04Test {

	/**
	 * The file into which the information intercepted from the console is written.
	 */
	private static final String FILE = "test.txt";

	/**
	 * Encoding of the specified file.
	 */
	private static final String ENCODING = "UTF8";

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
		Class<Subtask04> cl = Subtask04.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Tests proper output of the {@link Subtask04#main(String[]) main} method.
	 *
	 * @throws  FileNotFoundException  if print stream for interception of the
	 * 								   console output can't be created
	 */
	@Test
	public void mainTest() throws FileNotFoundException {
		String actual;
		String pattern = "|[А-ЯЁA-Z].*?[.]|([А-ЯЁA-Z].*?[.](\\n|\\r|\\r\\n)){1,}[А-ЯЁA-Z].*?[.]";
		PrintStream holder = System.out;
		File to = new File(FILE);
		PrintStream temp = new PrintStream(to);

		System.setOut(temp);
		Subtask04.main(null);
		System.out.close();
		System.setOut(holder);

		actual = FileIO.getTextFromFile(FILE, ENCODING);
		if (!actual.matches(pattern)) {
			fail();
		}
		new File(FILE).delete();
	}

}
