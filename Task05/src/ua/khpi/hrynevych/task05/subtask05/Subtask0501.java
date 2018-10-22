package ua.khpi.hrynevych.task05.subtask05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class organizing reading and writing using standard synchronized block.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Subtask0501 {

	/**
	 * The number of cycles of reading and writing.
	 */
	private static final int ITERATION_NUMBER = 3;

	/**
	 * The number of readers.
	 */
	private static final int READERS_NUMBER = 3;

	/**
	 * Shared resource (not thread-safe).
	 */
	private static final StringBuilder BUFFER = new StringBuilder();

	/**
	 * The length of the buffer.
	 */
	private static final int BUFFER_LENGTH = 5;

	/**
	 * Speed parameter.
	 */
	private static final int PAUSE = 5;

	/**
	 * Counter of readers that had read information.
	 */
	private static int counter;

	/**
	 * Stop signal.
	 */
	private static boolean stop;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask0501() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Signals that the specified thread got read the information
	 * from the buffer.
	 *
	 * @param   threadName            name of the thread that reads information
	 * @throws  InterruptedException  if specified thread was interrupted
	 */
	private static void read(final String threadName) throws InterruptedException {
		final int pause = 5;

		System.out.printf("Reader %s: ", threadName);
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			System.out.print(BUFFER.charAt(j));
		}
		System.out.println();
		Thread.sleep(pause);
	}

	/**
	 * Writes randomly generated text to the buffer.
	 *
	 * @throws  InterruptedException  if the writing thread was interrupted
	 */
	private static void write() throws InterruptedException {
		final int lettersNumber = 26;
		final int pause = 5;

		// clear buffer
		BUFFER.setLength(0);

		// write to buffer
		System.err.print("Writer writes: ");

		Random random = new Random();
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			char ch = (char) ('A' + random.nextInt(lettersNumber));
			System.err.print(ch);
			BUFFER.append(ch);
		}
		System.err.println();
		Thread.sleep(pause);
	}

	/**
	 * Demonstrates results of the work current part of Subtask 05.
	 *
	 * Writer writes to the buffer and readers alternately read information
	 * from it. After all the readers got read the information new cycle begins.
	 * The number of such cycles is determined by the field ITERATION_NUMBER
	 * of the class.
	 *
	 * @param   args                  command line parameters
	 * @throws  InterruptedException  if something went wrong with the working threads
	 */
	public static void main(final String[] args) throws InterruptedException {
		final int pause = 10;

		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start readers
		Thread.sleep(pause);
		for (Thread reader : readers) {
			reader.start();
		}

		// start writer
		Thread.sleep(pause);
		writer.start();

		// main thread is waiting for the child threads
		writer.join();
		for (Thread reader : readers) {
			reader.join();
		}
		stop = false;
	}


	/**
	 * Class simulating reader.
	 *
	 * It is a thread trying to read information from the buffer
	 * if it is possible. If the buffer is empty or the information
	 * in it was already read then the reader waits a signal from the writer.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 03 Dec 2017
	 */
	private static class Reader extends Thread {

		/**
		 * Starts attempts of the reader to read information.
		 */
		public void run() {
			while (!stop) {
				try {
					synchronized (BUFFER) {
						BUFFER.wait();

						// read from the buffer
						read(getName());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					counter++;
				}
			}
		}
	}


	/**
	 * Class simulating writer.
	 *
	 * It is a thread trying to write information to the buffer
	 * if it is possible. If there is at least one reader that haven't
	 * read the information yet, then the writer waits a signal from the
	 * last reader.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 03 Dec 2017
	 */
	private static class Writer extends Thread {

		/**
		 * Starts attempts of the writer to write information.
		 */
		public void run() {
			int tact = 0;
			counter = READERS_NUMBER;

			while (!stop) {
				synchronized (BUFFER) {
					if (counter == READERS_NUMBER) {
						try {
							// write to the buffer
							write();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							counter = 0;
							BUFFER.notifyAll();
							if (++tact == ITERATION_NUMBER) {
								stop = true;
							}
						}
					}
				}
			}
		}
	}
}
