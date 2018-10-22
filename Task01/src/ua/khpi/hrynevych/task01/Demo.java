package ua.khpi.hrynevych.task01;

/**
 *
 */
public final class Demo {

	/**
	 *
	 */
	private Demo() {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param args fill it
	 */
	public static void main(final String[] args) {
		Subtask01.main(args);
		Subtask02.main(new String[] {"2.7", "24.45"});
		Subtask03.main(new String[] {"762", "594"});
		Subtask04.main(new String[] {"120048"});
		Subtask05.main(new String[] {"ABZ"});
	}
}
