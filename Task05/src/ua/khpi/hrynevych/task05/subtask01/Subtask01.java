package ua.khpi.hrynevych.task05.subtask01;

/**
 * Contains two threads printing their names to the console simultaneously.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Subtask01 {

	/**
	 * Time interval between the print operations (in milliseconds).
	 */
	private static final int PAUSE = 500;

	/**
	 * Time during which threads are printing their names (in milliseconds).
	 */
	private static final int TIMEOUT = 5000;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask01() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Demonstrates the result of simultaneousle print of two threads.
	 *
	 * Time of execution must be not very longer then specified
	 * in the field TIMEOUT.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		MyThread first = new MyThread();
		MyThread2 temp = new MyThread2();
		Thread second = new Thread(temp);

		first.start();
		second.start();
		try {
			first.join();
			second.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	  * Thread that extends class {@link Thread} and prints its name to the console.
	  *
	  * @author   Hrynevych Pavlo
	  * @version  1.0, 03 Dec 2017
	 */
	public static class MyThread extends Thread {

		/**
		 * Method executing in the separate thread if the
		 * method {@link MyThread#start() start()} of this object was invoked.
		 */
		public void run() {
			for (int i = 0; i < (TIMEOUT / PAUSE); i++) {
				System.out.println(this.getName());
				try {
					Thread.sleep(PAUSE);
				} catch (InterruptedException e) { }
			}
		}
	}


	/**
	  * Thread that implements interface {@link Runnable} and prints its name to the console.
	  *
	  * @author   Hrynevych Pavlo
	  * @version  1.0, 03 Dec 2017
	 */
	public static class MyThread2 implements Runnable {

		/**
		 * Method executing in the separate thread if the
		 * method {@link MyThread#start() start()} of this object was invoked.
		 */
		public void run() {
			for (int i = 0; i < (TIMEOUT / PAUSE); i++) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(PAUSE);
				} catch (InterruptedException e) { }
			}
		}
	}
}
