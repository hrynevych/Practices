package ua.khpi.hrynevych.task04.subtask01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Allows to get text information from the file, set all characters in words in it
 * that have more than three characters to uppercase, and print the result to the console.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class Subtask01 {

	/**
	 * File containing information for testing.
	 */
	private static final String FILE_NAME = "part1.txt";

	/**
	 * Encoding of tested file.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * The utility class can't have instances.
	 */
	private Subtask01() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets characters of every big word in the input to uppercase.
	 *
	 * Big word is the sequence of letters, containing more than three characters.
	 * If input string includes no big words then the resulting string is the same
	 * as the input one.
	 *
	 * @param   input  string for conversion
	 * @return  	   input string with the big words uppercased
	 */
	public static String bigWordsToUpperCase(final String input) {
		Pattern pattern = Pattern.compile("[а-яА-ЯЁёa-zA-Z]{4,}");
		Matcher matcher = pattern.matcher(input);
		StringBuffer result = new StringBuffer(input);

		while (matcher.find()) {
			String replacement = matcher.group().toUpperCase();
			result = result.replace(matcher.start(), matcher.end(), replacement);
		}
		return result.toString();
	}

	/**
	 * Demonstrates results of putting big words to uppercase using the
	 * text from specified file.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		String text = FileIO.getTextFromFile(FILE_NAME, ENCODING);
		System.out.println(bigWordsToUpperCase(text));
	}

}
