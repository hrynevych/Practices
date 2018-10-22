package ua.khpi.hrynevych.task04;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import ua.khpi.hrynevych.task04.subtask01.Subtask01;
import ua.khpi.hrynevych.task04.subtask02.Subtask02;
import ua.khpi.hrynevych.task04.subtask03.Subtask03;
import ua.khpi.hrynevych.task04.subtask04.Subtask04;
import ua.khpi.hrynevych.task04.subtask05.Subtask05;

/**
 * Demonstrates results of solving subtasks 1-5.
 *
 * Contains the entry point to the console application - public method {@link #main(String[]) main}.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class Demo {

	/**
	 * Standard input stream.
	 */
	private static final InputStream STD_IN = System.in;

	/**
	 * Encoding of tested files.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * System-dependent line separator.
	 */
	private static final String LS = System.lineSeparator();

	/**
	 * The utility class can't have instances.
	 */
	private Demo() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Contains console output of the solutions of subtasks 1-5.
	 *
	 * @param   args         command line parameters
	 * @throws  IOException  if the field ENCODING contains wrong encoding name
	 */
	public static void main(final String[] args) throws IOException {
		System.out.println("=========================== PART1");
		Subtask01.main(args);

		System.out.println("=========================== PART2");
		Subtask02.main(args);

		System.out.println("=========================== PART3");

		// set the mock input
		System.setIn(new ByteArrayInputStream("char|String|int|double".replace("|", LS).getBytes(ENCODING)));
		Subtask03.main(args);

		// restore the standard input
		System.setIn(STD_IN);

		System.out.println("=========================== PART4");
		Subtask04.main(args);

		System.out.println("=========================== PART5");

		// set the mock input
		System.setIn(new ByteArrayInputStream(
				"table ru|table en|apple ru".replace("|", LS).getBytes(ENCODING)));
		Subtask05.main(args);

		// restore the standard input
		System.setIn(STD_IN);
	}
}
