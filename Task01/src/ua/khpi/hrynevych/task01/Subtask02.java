package ua.khpi.hrynevych.task01;

/**
 *
 */
public final class Subtask02 {

	/**
	 *
	 */
	private Subtask02() {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	private static void usage() {
		System.out.println("Usage: java " + "ua.khpi.hrynevych.task01.Subtask02 X Y");
	}

	/**
	 *
	 * @param args fill it
	 */
	public static void main(final String[] args) {
		if (args.length != 2) {
			Subtask02.usage();
			return;
		}
		double a = Double.parseDouble(args[0]);
		double b = Double.parseDouble(args[1]);

		System.out.println(a + " + " + b + " = " + (a + b));
	}
}
