package ua.khpi.hrynevych.task05.subtask04;

/**
 * Contains example of parallelization of finding
 * the maximum element in the matrix.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Subtask04 {

	/**
	 * The number of rows in the tested matrix.
	 */
	private static final int ROWS = 4;

	/**
	 * The number of columns in the tested matrix.
	 */
	private static final int COLUMNS = 100;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask04() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Demonstrates two ways of finding the maximum elements
	 * of the matrix.
	 *
	 * One way involves parallelization using threads, another - not.
	 * The first one should work faster and if the matrix has M rows
	 * then it should work M times faster than the second one.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		long time1;
		long time2;
		double result1;
		double result2;
		Matrix matr = new Matrix(ROWS, COLUMNS);
		matr.fillMatrixRandomly();

		time1 = System.nanoTime();
		result1 = matr.findMaxElement();
		time1 = System.nanoTime() - time1;

		time2 = System.nanoTime();
		result2 = matr.findMaxElementFaster();
		time2 = System.nanoTime() - time2;

		System.out.println("Without threads:");
		System.out.println("Max: " + result1);
		System.out.println("Time: " + time1 + "\n");
		System.out.println("With threads:");
		System.out.println("Max: " + result2);
		System.out.println("Time: " + time2);
	}
}
