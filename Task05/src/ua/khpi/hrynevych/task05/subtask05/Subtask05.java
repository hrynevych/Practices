package ua.khpi.hrynevych.task05.subtask05;

/**
 * Demonstrates results of solving of all parts of the Subtask 05.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Subtask05 {

	/**
	 * The utility class can't have instances.
	 */
	private Subtask05() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Demonstrates three types of read-write organization.
	 *
	 * @param   args 				  command line parameters
	 * @throws  InterruptedException  if problem with threads occurred
	 */
	public static void main(final String[] args) throws InterruptedException {
		System.out.println("============Subtask0501");
		Subtask0501.main(args);

		System.out.println("============Subtask0502");
		Subtask0502.main(args);

		System.out.println("============Subtask0503");
		Subtask0503.main(args);
	}
}
