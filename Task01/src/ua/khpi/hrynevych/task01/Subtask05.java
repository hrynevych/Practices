package ua.khpi.hrynevych.task01;

/**
 *
 */
public final class Subtask05 {

	/**
	 *
	 */
	private static final int BASE = 26;

	/**
	 *
	 */
	private static final int SHIFT = 64;

	/**
	 *
	 */
	private Subtask05() {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	private static void usage() {
		System.out.println("Usage: java " + "ua.khpi.hrynevych.task01.Subtask05 X");
	}

	/**
	 *
	 * @param s fill it
	 * @return fill it
	 */
	public static int charsToDigits(final String s) {
		int temp;
		int base = 1;
		int sum = 0;

		for (int i = s.length() - 1; i > -1; i--) {
			temp = s.charAt(i) - SHIFT;
			sum += temp * base;
			base *= BASE;
		}
		return sum;
	}

	/**
	 *
	 * @param num fill it
	 * @return fill it
	 */
	private static int[] getNumberOfLettersAndBase(final int num) {
		int temp = BASE + 1;
		int n = 0;
		int currentBase = 1;

		while (temp <= num) {
			currentBase *= BASE;
			temp += currentBase * BASE;
			n++;
		}
		return new int[] {++n, currentBase};
	}

	/**
	 *
	 * @param number fill it
	 * @param correctZeros fill it
	 * @return fill it
	 */
	private static int[] numTo26thSystem(final int number, final boolean correctZeros) {
		int num = number;
		int n = getNumberOfLettersAndBase(num)[0];
		int base = getNumberOfLettersAndBase(num)[1];
		int[] result = new int[n];

		for (int i = 0; i < result.length; i++) {
			result[i] = num / base;
			num -= result[i] * base;
			base /= BASE;
		}

		if (correctZeros) {
			for (int i = result.length - 1; i > 0; i--) {
				if (result[i] <= 0) {
					result[i - 1] -= 1;
					result[i] += BASE;
				}
			}
		}
		return result;
	}

	/**
	 *
	 * @param num fill it
	 * @return fill it
	 */
	public static String digitsToChars(final int num) {
		int[] mas = numTo26thSystem(num, true);
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < mas.length; i++) {
			result.append((char) (mas[i] + SHIFT));
		}
		return result.toString();
	}

	/**
	 *
	 * @param s fill it
	 * @return fill it
	 */
	public static String rightColumn(final String s) {
		int temp = charsToDigits(s) + 1;
		return digitsToChars(temp);
	}

	/**
	 *
	 * @param args fill it
	 */
	public static void main(final String[] args) {
		if (args.length != 1) {
			Subtask05.usage();
			return;
		}
		int n = Subtask05.charsToDigits(args[0]);
		String s1 = Subtask05.digitsToChars(n);
		String s2 = Subtask05.rightColumn(s1);

		System.out.println("Column " + args[0] + " has number " + n);
		System.out.println("Column with number " + n + " has notation " + s1);
		System.out.println("Next column to " + s1 + " is " + s2);
	}
}
