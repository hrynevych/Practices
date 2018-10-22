package ua.khpi.hrynevych.task05.subtask03;

/**
 * Shows the difference between synchronized and unsynchronized code.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Subtask03 {

	/**
	 * Number of threads that work with the common counters simultaneously.
	 */
	private static final int NUMBER_OF_THREADS = 3;

	/**
	 * Pause in milliseconds between setting the values of counters.
	 */
	private static final int PAUSE = 10;

	/**
	 * Shows how many times one every thread increases counters.
	 */
	private static final int NUMBER_OF_ITERATIONS = 10;

	/**
	 * Resource containing common counters.
	 */
	private static Counters monitor;

	/**
	 * Flag that shows synchronically or not threads will work.
	 */
	private static boolean synchronically;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask03() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Starts the specified number of threads that increase common counters.
	 *
	 * Synchronically or not threads will work depends on the boolean value
	 * in the parameter.
	 *
	 * @param  flag  determines synchronically or not threads will work,
	 *               if true - synchronically, if false - not
	 */
	public static void startThreads(final boolean flag) {
		monitor = new Counters();
		synchronically = flag;
		MyThread[] arr = new MyThread[NUMBER_OF_THREADS];
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			arr[i] = new MyThread();
		}

		for (MyThread mt : arr) {
			mt.start();
		}
		for (MyThread mt : arr) {
			try {
				mt.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Demonstrates two types of interaction with shared resources.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		startThreads(false);
		System.out.println();
		startThreads(true);
	}


	/**
	 * Thread that increases common counters.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 03 Dec 2017
	 */
	public static class MyThread extends Thread {

		/**
		 * Executes {@link MyThread#perform() perform()} method.
		 *
		 * Can work either synchronically or not. It depends on
		 * the field synchronically of the {@link Subtask03} class.
		 */
		public void run() {
			for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
				if (synchronically) {
					synchronized (monitor) {
						perform();
					}
				} else {
					perform();
				}
			}
		}

		/**
		 * Provides increasing of both of counters with a specified pause.
		 */
		private static void perform() {
			int temp1 = monitor.getCounter1();
			int temp2 = monitor.getCounter2();

			System.out.println(Thread.currentThread().getName()
					+ ": counter1 (" + temp1 + ") == counter2 (" + temp2 + "): "
					+ (temp1 == temp2));
			monitor.increaseCounter1();
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) { }
			monitor.increaseCounter2();
		}
	}
}
