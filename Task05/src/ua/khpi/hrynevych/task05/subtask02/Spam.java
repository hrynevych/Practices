package ua.khpi.hrynevych.task05.subtask02;

import java.io.IOException;

/**
 * Receives array of time intervals and array of messages and prints
 * messages to the console at specified intervals.
 *
 * Output should be terminated after pressing the key ENTER.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 03 Dec 2017
 */
public class Spam extends Thread {

	/**
	 * Received time intervals (in milliseconds).
	 */
	private long[] timeIntervals;

	/**
	 * Messages to print.
	 */
	private String[] spamMessages;

	/**
	 * Creates new Spam object with specified interavals and messages.
	 *
	 * @param   intervals  time intervals between printing messages
	 * @param   messages   messages to print to the console
	 * @throws  Exception  if arrays in the parameters are uncoordinated
	 */
	public Spam(final long[] intervals, final String[] messages) throws Exception {
		if (intervals.length != messages.length) {
			throw new Exception("Bad format of input data");
		}
		timeIntervals = intervals;
		spamMessages = messages;
	}

	/**
	 * Begins to send messages to the console in the separate thread.
	 */
	public void run() {
		for (int i = 0; i < timeIntervals.length; i++) {
			try {
				Thread.sleep(timeIntervals[i]);
			} catch (InterruptedException e) {
				break;
			}
			System.out.println(spamMessages[i]);
		}
	}

	/**
	 * Demonstrates functionality of the class Spam.
	 *
	 * Execution terminates after pressing the key ENTER.
	 * Otherwise the application will print all messages from the array
	 * and then will hang.
	 *
	 * @param  args       command line parameters
	 * @throws Exception  if object of Spam can't be created
	 */
	public static void main(final String[] args) throws Exception {
		final int bufferSize = 10;
		Spam t = new Spam(Subtask02.TIME, Subtask02.TEXT);

		t.setDaemon(true);
		t.start();

		new Thread() {
			public void run() {
				byte[] buffer = new byte[bufferSize];
				int count;

				try {
					do {
						while ((count = System.in.read(buffer)) == -1);
					} while (!System.lineSeparator().equals(new String(buffer, 0, count)));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				t.interrupt();										// unnecessary
				System.out.println("ENTER has been obtained");
			}
		}.start();
	}
}
