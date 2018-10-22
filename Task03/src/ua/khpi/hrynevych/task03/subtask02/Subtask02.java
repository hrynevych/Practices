package ua.khpi.hrynevych.task03.subtask02;

import java.util.LinkedHashSet;

/**
 * Contains methods for text analysis.
 *
 * There are methods extracting words of minimum and maximum length
 * and also some additional methods.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 17 Nov 2017
 */
public final class Subtask02 {

	/**
	 * The utility class can't have instances.
	 */
	private Subtask02() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Extracts words from the text in input.
	 *
	 * Assumed that word is a sequence of characters containing only letters.
	 * If word appears in the text more than once - resulting array will contain
	 * only one sample of such word.
	 *
	 * @param   text  input text
	 * @return        array of words of the text, which is empty
	 * 				  if text contains no words
	 */
	public static String[] getWords(final String text) {
		LinkedHashSet<String> result = new LinkedHashSet<String>();
		String[] wordArray = text.split("[^a-zA-Z]+");

		for (String s : wordArray) {
			if (s.length() != 0) {
				result.add(s);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	/**
	 * Returns the length of the smallest word in the input array.
	 *
	 * Returns 0 if the input array is empty. In fact method returns
	 * length of the smallest string in the array, so if it contains
	 * empty string (which is not actually a word) result also will be 0.
	 *
	 * @param   words  array containing words
	 * @return         length of the smallest word in array
	 */
	public static int getMinLength(final String[] words) {
		int result = 0;

		if (words.length > 0) {
			result = words[0].length();
			for (int i = 1; i < words.length; i++) {
				if (words[i].length() < result) {
					result = words[i].length();
				}
			}
		}
		return result;
	}

	/**
	 * Returns list of the smallest words from the input text.
	 *
	 * Format of output is as follows: prefix "Min: " which is
	 * followed by the comma-separated words of minimum length
	 * from the text. Output contains only one sample of each
	 * word, even if such word appears in the text twice or more.
	 * If text contains no words then only prefix is returned.
	 *
	 * @param   text  input text
	 * @return        list of the smallest words in specified format
	 */
	public static String getMinWords(final String text) {
		String[] words = getWords(text);
		int minLength = getMinLength(words);
		StringBuffer result = new StringBuffer("Min: ");

		for (String s : words) {
			if (s.length() == minLength) {
				result.append(s);
				result.append(", ");
			}
		}
		if (result.lastIndexOf(", ") != -1) {
			result.delete(result.length() - 2, result.length());
		}
		return result.toString();
	}

	/**
	 * Returns the length of the largest word in the input array.
	 *
	 * Returns 0 if the input array is empty. In fact method returns
	 * length of the largest string in the array, so if it contains
	 * only empty strings (which are not actually words) result also will be 0.
	 *
	 * @param   words  array containing words
	 * @return         length of the largest word in array
	 */
	public static int getMaxLength(final String[] words) {
		int result = 0;

		if (words.length > 0) {
			result = words[0].length();
			for (int i = 1; i < words.length; i++) {
				if (words[i].length() > result) {
					result = words[i].length();
				}
			}
		}
		return result;
	}

	/**
	 * Returns list of the largest words from the input text.
	 *
	 * Format of output is as follows: prefix "Max: " which is
	 * followed by the comma-separated words of maximum length
	 * from the text. Output contains only one sample of each
	 * word, even if such word appears in the text twice or more.
	 * If text contains no words then only prefix is returned.
	 *
	 * @param   text  input text
	 * @return        list of the largest words in specified format
	 */
	public static String getMaxWords(final String text) {
		String[] words = getWords(text);
		int maxLength = getMaxLength(words);
		StringBuffer result = new StringBuffer("Max: ");

		for (String s : words) {
			if (s.length() == maxLength) {
				result.append(s);
				result.append(", ");
			}
		}
		if (result.lastIndexOf(", ") != -1) {
			result.delete(result.length() - 2, result.length());
		}
		return result.toString();
	}
}
