package ua.khpi.hrynevych.task03.subtask03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains methods for convering text.
 *
 * There is method allowing to set first letter of each word
 * from the input text to upper case.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 17 Nov 2017
 */
public final class Subtask03 {

	/**
	 * The utility class can't have instances.
	 */
	private Subtask03() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets first letter of each word in the input text to upper case.
	 *
	 * Resulting text is the same as in input with the exception of
	 * the specified change. If the text contains no words then the
	 * output is the same as input.
	 *
	 * @param   text  input text
	 * @return        input text with the first letter of each word in it
	 *                in upper case
	 */
	public static String firstLettersToUpperCase(final String text) {
		if (text == null) {
			System.out.println("No data");
			return null;
		}

		Pattern pattern = Pattern.compile("[^a-zA-Z][a-z]");
		Matcher matcher = pattern.matcher(text);
		StringBuffer result = new StringBuffer(text);

		if (Pattern.compile("[a-z].*").matcher(result).lookingAt()) {
			String temp = result.substring(0, 1).toUpperCase();

			result = result.replace(0, 1, temp);
		}
		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			String replacement = matcher.group().toUpperCase();

			result = result.replace(start, end, replacement);
		}
		return result.toString();
	}
}
