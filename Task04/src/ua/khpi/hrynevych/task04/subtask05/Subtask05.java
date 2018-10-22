package ua.khpi.hrynevych.task04.subtask05;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Contains methods for getting value corresponding to specified key
 * in specified localization.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class Subtask05 {

	/**
	 * End-of-input limiter.
	 */
	private static final String EOI = "stop";

	/**
	 * Basic resource package.
	 */
	private static final String BASE_NAME = "resources";

	/**
	 * The utility class can't have instances.
	 */
	private Subtask05() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the value corresponding to the key in the specified resource package
	 * and localization.
	 *
	 * Specified resource package should exist. Also key from the parameter should exist
	 * in specified base. On the other hand, if there is no language as in l10n parameter
	 * then default locale is used.
	 *
	 * @param   key       key corresponding to the wanted value.
	 * @param   baseName  resource package name
	 * @param   l10n      needed localization
	 * @return            value corresponding to the specified key
	 */
	public static String getValue(final String key, final String baseName, final String l10n) {
		Locale locale = new Locale(l10n);
		ResourceBundle currentRB = ResourceBundle.getBundle(baseName, locale);
		return currentRB.getString(key);
	}

	/**
	 * Obtains command from the console and prints the value
	 * corresponding to the parameters of command.
	 *
	 * Command consists of two words separated with whitespace. First one
	 * corresponds to the key, and the second one - to the language.
	 * E.g. command "table en" contains key "table" and locale "en".
	 * So that's why value "table" can be expected in the result.
	 * Any other format of command or unacceptable values will cause
	 * a message "Bad format". Application will work untill the EOI
	 * will be received.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String input = in.nextLine();

			if (input.equals(EOI)) {
				break;
			}
			String[] inputArr = input.split(" ");
			try {
				System.out.println(getValue(inputArr[0], BASE_NAME, inputArr[1]));
			} catch (Exception e) {
				System.out.println("Bad format");
			}
		}
		in.close();
	}

}
