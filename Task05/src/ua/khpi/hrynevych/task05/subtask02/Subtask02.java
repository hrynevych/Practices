package ua.khpi.hrynevych.task05.subtask02;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Demonstrates the work of the class {@link Spam}.
 *
 * Time of execution must be not very longer then specified
 * in the field TIMEOUT.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Subtask02 {

	/**
	 * Time intervals for test (in milliseconds).
	 */
	public static final long[] TIME = {500, 1000, 1000, 500, 2000, 300};

	/**
	 * Messages to print as a spam.
	 */
	public static final String[] TEXT = {"Hello", "My", "Name", "Is", "Spam", "Bye"};

	/**
	 * Time during which Spam is printing messages (in milliseconds).
	 */
	private static final int TIMEOUT = 5000;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask02() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Involves instance of the class {@link Spam} to print messages
	 * to the console and terminates execution after the time specified
	 * in the TIMEOUT field.
	 *
	 * @param   args       command line parameters
	 * @throws  Exception  if object of Spam can't be created
	 */
	public static void main(final String[] args) throws Exception {
		// save standard input
		InputStream stdIn = System.in;

		// create input stream with line terminator (=ENTER)
		ByteArrayInputStream bais = new
		ByteArrayInputStream(System.lineSeparator().getBytes());

		// move internal pointer of input stream to the end of input
		bais.skip(System.lineSeparator().length());

		// assign new value of standard input
		System.setIn(bais);

		// main functionality
		Spam.main(args);

		// waith for 5 sec
		Thread.sleep(TIMEOUT);
		System.out.println("Try to send enter to standard input");

		// move internal pointer to begin of input
		bais.reset();
		Thread.sleep(1);				//only for the correct output in Demo

		// restore standard input
		System.setIn(stdIn);
	}
}
