package ua.khpi.hrynevych.task04.subtask03;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Contains methods for extracting chars, strings, integers and
 * floating-point numbers from the text in the specified file.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class Subtask03 {

	/**
	 * File with the information of different types.
	 */
	private static final String FILE_NAME = "part3.txt";

	/**
	 * Encoding of the specified file.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * The utility class can't have instances.
	 */
	private Subtask03() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Extracts chars from the information in the specified file.
	 *
	 * Char is the letter in any case.
	 *
	 * @param   file      file containing information of different types
	 * @param   encoding  encoding of the specified file
	 * @return            chars from the information in the file
	 */
	public static String getChars(final String file, final String encoding) {
		String rawData = FileIO.getTextFromFile(file, encoding);
		Pattern pattern = Pattern.compile("[а-яА-ЯЁёa-zA-Z]+");
		Matcher matcher = pattern.matcher(rawData);
		StringBuffer result = new StringBuffer();

		while (matcher.find()) {
			if (matcher.group().length() == 1) {
				result.append(matcher.group());
				result.append(" ");
			}
		}
		return result.toString().trim();
	}

	/**
	 * Extracts strings from the information in the specified file.
	 *
	 * String is the sequence of two letters or more.
	 *
	 * @param   file      file containing information of different types
	 * @param   encoding  encoding of the specified file
	 * @return            strings from the information in the file
	 */
	public static String getStrings(final String file, final String encoding) {
		String rawData = FileIO.getTextFromFile(file, encoding);
		Pattern pattern = Pattern.compile("[а-яА-ЯЁёa-zA-Z]{2,}");
		Matcher matcher = pattern.matcher(rawData);
		StringBuffer result = new StringBuffer();

		while (matcher.find()) {
			result.append(matcher.group());
			result.append(" ");
		}
		return result.toString().trim();
	}

	/**
	 * Extracts integers from the information in the specified file.
	 *
	 * @param   file      file containing information of different types
	 * @param   encoding  encoding of the specified file
	 * @return            integers from the information in the file
	 */
	public static String getInts(final String file, final String encoding) {
		String rawData = FileIO.getTextFromFile(file, encoding);
		Pattern pattern = Pattern.compile("[0-9.]+");
		Matcher matcher = pattern.matcher(rawData);
		StringBuffer result = new StringBuffer();

		while (matcher.find()) {
			try {
				result.append(Integer.parseInt(matcher.group()));
				result.append(" ");
			} catch (NumberFormatException e) { }
		}
		return result.toString().trim();
	}

	/**
	 * Extracts floating-point numbers from the information in the specified file.
	 *
	 * @param   file      file containing information of different types
	 * @param   encoding  encoding of the specified file
	 * @return            floating-point numbers from the information in the file
	 */
	public static String getDoubles(final String file, final String encoding) {
		String rawData = FileIO.getTextFromFile(file, encoding);
		Pattern pattern = Pattern.compile("[0-9.]+");
		Matcher matcher = pattern.matcher(rawData);
		StringBuffer result = new StringBuffer();

//		while (matcher.find()) {
//			if (Pattern.compile("[.]").matcher(matcher.group()).find()) {
//				try {
//					result.append(Double.valueOf(matcher.group()));
//					result.append(" ");
//				} catch (NumberFormatException e) { }
//			}
//		}

		while (matcher.find()) {
			try {
				Integer.parseInt(matcher.group());
			} catch (NumberFormatException e) {
				try {
					result.append(Double.parseDouble(matcher.group()));
					result.append(" ");
				} catch (NumberFormatException ex) { }
			}
		}

		return result.toString().trim();
	}

	/**
	 * Obtains command from the console and prints information
	 * of corresponding type back to console.
	 *
	 * Send "char" to get chars, "String" to get strings, "int"
	 * to get integers and "double" to get floating-point numbers.
	 * Information of any other type can't be obtained. Print "stop"
	 * to terminate the application. Any other command will be ignored.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String command = in.nextLine();
			if (command.equals("char")) {
				System.out.println(getChars(FILE_NAME, ENCODING));
			} else if (command.equals("String")) {
				System.out.println(getStrings(FILE_NAME, ENCODING));
			} else if (command.equals("int")) {
				System.out.println(getInts(FILE_NAME, ENCODING));
			} else if (command.equals("double")) {
				System.out.println(getDoubles(FILE_NAME, ENCODING));
			} else if (command.equals("stop")) {
				break;
			}
		}
		in.close();
	}
}
