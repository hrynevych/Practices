package ua.khpi.hrynevych.task01;

/**
 *
 */
public final class Subtask04 {

	/**
	 *
	 */
	private Subtask04() {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	private static void usage() {
		System.out.println("Usage: java " + "ua.khpi.hrynevych.task01.Subtask04 X");
	}

	/**
	 *
	 * @param s fill it
	 * @return fill it
	 */
	public static int count(final String s) {
		String temp;
		int sum = 0;

		for (int i = 0; i < s.length(); i++) {
			temp = s.substring(i, i + 1);
			sum += Integer.parseInt(temp);
		}
		return sum;
	}

	/**
	 *
	 * @param args fill it
	 */
	public static void main(final String[] args) {
		if (args.length != 1) {
			Subtask04.usage();
			return;
		}
		int sum = count(args[0]);

		System.out.println("Sum of digits of the number " + args[0] + " is " + sum);
	}
}
