package ua.khpi.hrynevych.task01;

/**
 *
 */
public final class Subtask03 {

	/**
	 *
	 */
	private Subtask03() {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	private static void usage() {
		System.out.println("Usage: java " + "ua.khpi.hrynevych.task01.Subtask03 X Y");
	}

	/**
	 *
	 * @param num1 fill it
	 * @param num2 fill it
	 * @return fill it
	 */
	public static int nod(final int num1, final int num2) {
		int x = num1;
		int y = num2;

		while (x != y) {
			if (x > y) {
				x -= y;
			} else {
				y -= x;
			}
		}
		return x;
	}

	/**
	 *
	 * @param args fill it
	 */
	public static void main(final String[] args) {
		if (args.length != 2) {
			Subtask03.usage();
			return;
		}
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);

		System.out.println("NOD of " + n + " and " + m + " is " + nod(n, m));
	}
}
