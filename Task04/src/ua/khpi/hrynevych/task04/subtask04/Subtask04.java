package ua.khpi.hrynevych.task04.subtask04;

/**
 * Demonstrates the work of {@link Parser} using text from
 * the specified file.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class Subtask04 {

	/**
	 * File containing text to test.
	 */
	private static final String FILE_NAME = "part4.txt";

	/**
	 * Encoding of tested files.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * The utility class can't have instances.
	 */
	private Subtask04() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Prints separate sentences from the text in the specified file to the console.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		Parser parser = new Parser(FILE_NAME, ENCODING);
		for (String str : parser) {
			System.out.println(str);
		}
	}

}
