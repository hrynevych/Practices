package ua.khpi.hrynevych.task05.subtask05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class organizing reading and writing using only reentrant lock.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public final class Subtask0503 {

	/**
	 * Object locking the access to the buffer.
	 */
	private static final ReentrantLock LOCK_BUFFER = new ReentrantLock();

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
	 * List of readers that have already read information from the buffer.
	 */
	private static ArrayList<Thread> read = new ArrayList<Thread>();

	/**
	 * Counter of readers that had read information.
	 */
	private static int counter = READERS_NUMBER;

	/**
	 * Stop signal.
	 */
	private static boolean stop;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask0503() {
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
	 * in it was already read then the reader skips attempt to read
	 * the information. Otherwise the reader reads it.
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
				LOCK_BUFFER.lock();
				if ((counter != READERS_NUMBER) && (!read.contains(Thread.currentThread()))) {
					try {
						// read from the buffer
						read(getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						read.add(Thread.currentThread());
						counter++;
					}
				}
				LOCK_BUFFER.unlock();
			}
		}
	}


	/**
	 * Class simulating writer.
	 *
	 * It is a thread trying to write information to the buffer
	 * if it is possible. If there is at least one reader that haven't
	 * read the information yet, then the writer skips attempts to write
	 * the information to the buffer. Otherwise the writer writes it.
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
				LOCK_BUFFER.lock();
				if (counter == READERS_NUMBER) {
					try {
						// write to the buffer
					write();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						if (++tact == ITERATION_NUMBER) {
							stop = true;
						}
						counter = 0;
						read.clear();
					}
				}
				LOCK_BUFFER.unlock();
			}
		}
	}
}
