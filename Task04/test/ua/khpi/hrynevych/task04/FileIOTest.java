package ua.khpi.hrynevych.task04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Contains tests of the correct input to the file and output
 * from the file.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class FileIOTest {

	/**
	 * Encoding of the test files.
	 */
	private static final String ENCODING = "UTF8";

	/**
	 * File from which information will be read.
	 */
	private static final String FILE_FROM = "testFrom.txt";

	/**
	 * File to which information will be written.
	 */
	private static final String FILE_TO = "testTo.txt";

	/**
	 * Nonexistent file.
	 */
	private static final String BAD_FILE = "badName.txt";

	/**
	 * Empty string.
	 */
	private static final String EMPTY = "";

	/**
	 * Information to be read, written and tested.
	 */
	private static final String EXPECTED = "Here is some information\r\n"
			+ "Здесь есть некоторая информация";

	/**
	 * Prints information to the file.
	 */
	@BeforeClass
	public static void prepFile() {
		FileIO.printTextToFile(EXPECTED, FILE_FROM);
	}

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
		Class<FileIO> cl = FileIO.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Tests whether the information received from the file matches the expected information.
	 */
	@Test
	public void getTextFromFileTest1() {
		String actual = FileIO.getTextFromFile(FILE_FROM, ENCODING);

		assertEquals(EXPECTED, actual);
	}

	/**
	 * Tests getting information from the nonexistent file.
	 *
	 * Actually such file should not be found.
	 *
	 * @throws  FileNotFoundException  if print stream for interception of the
	 * 								   error messages can't be created
	 */
	@Test
	public void getTextFromFileTest2() throws FileNotFoundException {
		PrintStream holder = System.err;
		File to = new File(FILE_TO);
		PrintStream temp = new PrintStream(to);

		System.setErr(temp);
		FileIO.getTextFromFile(BAD_FILE, ENCODING);
		System.err.close();
		System.setErr(holder);

		Scanner in = new Scanner(to, ENCODING);
		if (!(in.hasNextLine() && in.nextLine().startsWith("java.io.FileNotFoundException"))) {
			fail();
		}
		in.close();
		to.delete();
	}

	/**
	 * Test whether information written to the file matches the expected information.
	 */
	@Test
	public void printTextToFileTest1() {
		String actual;

		FileIO.printTextToFile(EXPECTED, FILE_TO);
		actual = FileIO.getTextFromFile(FILE_TO, ENCODING);

		assertEquals(EXPECTED, actual);
		new File(FILE_TO).delete();
	}

	/**
	 * Tests trying to write some information to the file, that
	 * can't be created.
	 *
	 * Actually this operation can't be performed.
	 *
	 * @throws  FileNotFoundException  if print stream for interception of the
	 * 								   error messages can't be created
	 */
	@Test
	public void printTextToFileTest2() throws FileNotFoundException {
		PrintStream holder = System.err;
		File to = new File(FILE_TO);
		PrintStream temp = new PrintStream(to);

		System.setErr(temp);
		FileIO.printTextToFile(EMPTY, EMPTY);
		System.err.close();
		System.setErr(holder);

		if (!FileIO.getTextFromFile(FILE_TO, ENCODING).startsWith("java.io.FileNotFoundException")) {
			fail();
		}
		to.delete();
	}

	/**
	 * Deletes created test file.
	 */
	@AfterClass
	public static void deleteFile() {
		new File(FILE_FROM).delete();
	}
}
