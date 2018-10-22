package ua.khpi.hrynevych.task04.subtask02;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class, containing useful methods for work with arrays of integers.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class Utility {

	/**
	 * The utility class can't have instances.
	 */
	private Utility() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Allows to extract integers from the text in the input string.
	 *
	 * If input string contains no digits then an empty array is returned.
	 *
	 * @param   input  input text, probably containing integers
	 * @return  	   array of integers extracted from the input string
	 */
	public static int[] getArrayFromString(final String input) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(input);
		int[] result;

		while (matcher.find()) {
			array.add(new Integer(matcher.group()));
		}
		result = new int[array.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = array.get(i);
		}
		return result;
	}

	/**
	 * Sorts array using Shell's algorithm.
	 *
	 * It's important to note that input array remains unchanged.
	 * This method returns new array of the same length and with
	 * the same elements but in the proper order.
	 *
	 * @param   unsorted  input unsorted array
	 * @return  		  resulting sorted array
	 */
	public static int[] shellSort(final int[] unsorted) {
		int[] result = unsorted;
		int temp;

		for (int k = result.length / 2; k > 0; k /= 2) {
	        for (int i = k; i < result.length; i++) {
	        	int j;

	            temp = result[i];
	            for (j = i; j >= k; j -= k) {
	                if (temp < result[j - k]) {
	                    result[j] = result[j - k];
	                } else {
	                    break;
	                }
	            }
	            result[j] = temp;
	        }
		}
		return result;
	}
}
