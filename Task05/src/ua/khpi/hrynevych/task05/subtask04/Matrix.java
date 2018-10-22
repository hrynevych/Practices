package ua.khpi.hrynevych.task05.subtask04;

/**
 * Class representing two-dimensional matrix and containing some
 * methods to work with it.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public class Matrix {

	/**
	 * Internal representation of the matrix.
	 */
	private final double[][] matrix;

	/**
	 * The number of rows.
	 */
	private final int rows;

	/**
	 * The number of columns.
	 */
	private final int columns;

	/**
	 * Creates new matrix of specified size.
	 *
	 * @param  m  number of rows
	 * @param  n  number of columns
	 */
	public Matrix(final int m, final int n) {
		rows = m;
		columns = n;
		matrix = new double[rows][columns];
	}

	/**
	 * Fills this matrix with random numbers between 0 and 1.
	 */
	public void fillMatrixRandomly() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix[i][j] = Math.random();
			}
		}
	}

	/**
	 * Allows to find the maximum element in this matrix
	 * comparing all of them alternately.
	 *
	 * Every comparison has delay of 1 millisecond.
	 *
	 * @return  maximum element in this matrix
	 */
	public double findMaxElement() {
		double result = matrix[0][0];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) { }
				if (matrix[i][j] > result) {
					result = matrix[i][j];
				}
			}
		}
		return result;
	}

	/**
	 * Allows to find the maximum element in this matrix
	 * using threads.
	 *
	 * There is a thread for each row of this matrix looking for
	 * the maximum element in this row. Then all of that maximums
	 * are compared to find the maximum element of the matrix.
	 * Every comparison has delay of 1 millisecond.
	 *
	 * @return  maximum element in this matrix
	 */
	public double findMaxElementFaster() {
		double result;
		RowThread[] threads = new RowThread[rows];

		for (int i = 0; i < rows; i++) {
			threads[i] = new RowThread(i);
		}

		for (RowThread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) { }
		}

		result = threads[0].getMax();
		for (int i = 1; i < rows; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) { }
			if (threads[i].getMax() > result) {
				result = threads[i].getMax();
			}
		}
		return result;
	}


	/**
	 * Thread that finds the maximum element in the specified
	 * row of this matrix.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 03 Dec 2017
	 */
	public class RowThread extends Thread {

		/**
		 * Number of the row to find maximum in.
		 */
		private int row;

		/**
		 * Maximum in the current row.
		 */
		private double max;

		/**
		 * Creates and starts new thread corresponding to the
		 * specified row.
		 *
		 * @param  rowNumber  the number of the row to find maximum in
		 */
		public RowThread(final int rowNumber) {
			row = rowNumber;
			this.start();
		}

		/**
		 * Finds maximum elment in the specified row.
		 */
		public void run() {
			max = matrix[row][0];

			for (int i = 1; i < columns; i++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) { }
				if (matrix[row][i] > max) {
					max = matrix[row][i];
				}
			}
		}

		/**
		 * Returns the maximum element in specified row.
		 *
		 * @return  the maximum element in specified row
		 */
		public double getMax() {
			return max;
		}
	}
}
