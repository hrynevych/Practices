package ua.khpi.hrynevych.task03;

import ua.khpi.hrynevych.task03.subtask01.Subtask01;
import ua.khpi.hrynevych.task03.subtask02.Subtask02;
import ua.khpi.hrynevych.task03.subtask03.Subtask03;
import ua.khpi.hrynevych.task03.subtask04.Subtask04;
import ua.khpi.hrynevych.task03.subtask05.Subtask05;

/**
 * Class Demo demonstrates results of solving subtasks 1-5.
 *
 * Contains the entry point to the console application - public method {@link #main(String[]) main}.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 17 Nov 2017
 */
public final class Demo {

	/**
	 * Input data for the {@link Subtask01}.
	 */
	private static final String INPUT01 = "Login;Name;Email\n"
			+ "ivanov;Ivan Ivanov;ivanov@mail.ru\n"
			+ "petrov;Petr Petrov;petrov@google.com\n"
			+ "obama;Barack Obama;obama@google.com\n"
			+ "bush;George Bush;bush@mail.ru";

	/**
	 * Input data for the {@link Subtask02}.
	 */
	private static final String INPUT02 = "When I was younger, "
			+ "so much younger than today\n"
			+ "I never needed anybody's help in any way\n"
			+ "But now these days are gone, I'm not so self-assured\n"
			+ "Now I find I've changed my mind\n"
			+ "I've opened up the doors";

	/**
	 * Input data for the {@link Subtask03}.
	 */
	private static final String INPUT03 = "When I was younger\n"
					 					  + "I never needed";

	/**
	 * The utility class can't have instances.
	 */
	private Demo() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Contains console output of the solutions of subtasks 1-5.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		final int limit = 101;

		System.out.println("===Subtask01===\n");
		Subtask01 obj = new Subtask01(INPUT01);

		// 1
		System.out.println(obj.convert1());
		System.out.println();

		// 2
		System.out.println(obj.convert2());
		System.out.println();

		// 3
		System.out.println(obj.convert3());
		System.out.println();

		// 4
		System.out.println(obj.convert4());
		System.out.println();

		System.out.println("===Subtask02===\n");

		// Min
		System.out.println(Subtask02.getMinWords(INPUT02));

		// Max
		System.out.println(Subtask02.getMaxWords(INPUT02));
		System.out.println();

		System.out.println("===Subtask03===\n");

		// Puts the first letter of each word to uppercase
		System.out.println(Subtask03.firstLettersToUpperCase(INPUT03));
		System.out.println();

		System.out.println("===Subtask04===\n");
		String[] inputarr = {"password", "passwort"};

		for (String input : inputarr) {
			try {
				System.out.println(Subtask04.hash(input, "SHA-256"));
			} catch (java.security.NoSuchAlgorithmException e) {
				System.out.println("Bad algorithm name");
			}
		}
		System.out.println();

		System.out.println("===Subtask05===\n");
		for (int i = 1; i < limit; i++) {
			String temp = Subtask05.decimal2Roman(i);

			System.out.println(i + " ====> " + temp + " ====> " + Subtask05.roman2Decimal(temp));
		}
	}
}
