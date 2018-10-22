package ua.khpi.hrynevych.task05.subtask06;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class that creates new file and writes digits to it.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public class Subtask06 extends Thread {

	/**
	 * Monitor that is used to synchronize operations of writing.
	 */
	private static final Object MONITOR = new Object();

	/**
	 * The number of threads writing information to the file.
	 */
	private static final int THREADS_NUMBER = 10;

	/**
	 * The number of columns for every digit.
	 */
	private static final int COLUMNS = 20;

	/**
	 * Length of the system dependent line separator.
	 */
	private static final int EOL_LENGTH = System.lineSeparator().length();

	/**
	 * The name of the newly created file.
	 */
	private static final String FILE_NAME = "test.txt";

	/**
	 * The only object of RandomAccessFile used to write information.
	 */
	private static RandomAccessFile out;

	/**
	 * Amount of the created threads.
	 */
	private static int amount;

	/**
	 * The number of the current thread.
	 */
	private int number;

	/**
	 * Beginning of the needed row in the file.
	 */
	private int position;

	/**
	 * The number of the characters written by this thread.
	 */
	private int written;

	/**
	 * Creates new thread and defines the number for it to write and
	 * the position where to write.
	 *
	 * @throws  IOException  if there are some problems with access
	 * 						 to the file
	 */
	public Subtask06() throws IOException {
		synchronized (MONITOR) {
			if (out == null) {
				out = new RandomAccessFile(FILE_NAME, "rw");
			}
			number = amount;
			amount++;
			position = number * (COLUMNS + EOL_LENGTH);
		}
	}

	/**
	 * Makes the current thread to write digits to the specified place in the file.
	 */
	public void run() {
		while (written != COLUMNS) {
			synchronized (MONITOR) {
				try {
					out.seek(position + written);
					out.write('0' + number);
					written++;
					if ((number != (THREADS_NUMBER - 1)) && (written == COLUMNS)) {
						out.writeBytes(System.lineSeparator());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Creates threads that write digits to the file and after it is done
	 * prints the content of the file to the console.
	 *
	 * @param   args                  command line parameters
	 * @throws  IOException           if there are some problems with access
	 * 						 		  to the file
	 * @throws  InterruptedException  if there are some problems with working threads
	 */
	public static void main(final String[] args) throws IOException,
			InterruptedException {
		String s;
		Subtask06[] mas = new Subtask06[THREADS_NUMBER];

		for (int i = 0; i < THREADS_NUMBER; i++) {
			mas[i] = new Subtask06();
			mas[i].start();
		}
		for (int i = 0; i < THREADS_NUMBER; i++) {
			mas[i].join();
		}

		out.seek(0);
		s = out.readLine();
		while (s != null) {
			System.out.println(s);
			s = out.readLine();
		}
	}
}
