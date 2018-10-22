package ua.khpi.hrynevych.task05;

import ua.khpi.hrynevych.task05.subtask01.Subtask01;
import ua.khpi.hrynevych.task05.subtask02.Subtask02;
import ua.khpi.hrynevych.task05.subtask03.Subtask03;
import ua.khpi.hrynevych.task05.subtask04.Subtask04;
import ua.khpi.hrynevych.task05.subtask05.Subtask05;
import ua.khpi.hrynevych.task05.subtask06.Subtask06;

/**
 * Demonstrates results of solving Task 05.
 *
 * Contains the entry point to the console application - public method {@link #main(String[]) main}.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Demo {

	/**
	 * The utility class can't have instances.
	 */
	private Demo() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Contains console output of the solutions of subtasks 1-6.
	 *
	 * @param   args       command line parameters
	 * @throws  Exception  if something went wrong during execution of
	 * 					   one of the main methods
	 */
	public static void main(final String[] args) throws Exception {
		System.out.println("~~~~~~~~~~~~Part1");
		Subtask01.main(args);

		System.out.println("~~~~~~~~~~~~Part2");
		Subtask02.main(args);

		System.out.println("~~~~~~~~~~~~Part3");
		Subtask03.main(args);

		System.out.println("~~~~~~~~~~~~Part4");
		Subtask04.main(args);

		System.out.println("~~~~~~~~~~~~Part5");
		Subtask05.main(args);

		System.out.println("~~~~~~~~~~~~Part6");
		Subtask06.main(args);
	}
}
