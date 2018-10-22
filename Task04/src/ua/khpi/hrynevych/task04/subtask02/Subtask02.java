package ua.khpi.hrynevych.task04.subtask02;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Contains methods for filling the file with the array of integers and
 * sorting array from the specified file.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class Subtask02 {

	/**
	 * File containing unsorted array.
	 */
	private static final String RAW_DATA = "part2.txt";

	/**
	 * Encoding of tested files.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * File containing sorted array.
	 */
	private static final String SORTED_DATA = "part2_sorted.txt";

	/**
	 * Amount of numbers in the array.
	 */
	private static final int NUMBER = 10;

	/**
	 * Maximum number that can occur in the array.
	 */
	private static final int MAX = 50;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask02() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Fills the file with the array of integers with the specified parameters.
	 *
	 * @param  rawFile  file in which the array of integers should appear
	 * @param  number   amount of numbers in the array
	 * @param  max      maximum number that can occur in the array
	 */
	public static void getRandomNumbers(final String rawFile, final int number, final int max) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < number; i++) {
			result.append((int) (Math.random() * (max + 1)));
			result.append(" ");
		}

		FileIO.printTextToFile(result.toString().trim(), rawFile);
	}

	/**
	 * Sorts array from the rawFile and prints sorted variant to the sortedFile.
	 *
	 * @param  rawFile      file containing the unsorted array
	 * @param  rawEncoding  encoding of the rawFile
	 * @param  sortedFile   file in which the sorted array should appear
	 */
	public static void sort(final String rawFile, final String rawEncoding, final String sortedFile) {
		String text = FileIO.getTextFromFile(rawFile, rawEncoding);
		int[] arr = Utility.getArrayFromString(text);
		arr = Utility.shellSort(arr);
		StringBuffer result = new StringBuffer();

		for (int i : arr) {
			result.append(i);
			result.append(" ");
		}

		FileIO.printTextToFile(result.toString().trim(), sortedFile);
	}

	/**
	 * Demonstrates results of work of the methods.
	 *
	 * Two new files appear after performing this method.
	 *
	 * @param  args  command line parameters
	 */
	public static void main(final String[] args) {
		getRandomNumbers(RAW_DATA, NUMBER, MAX);
		sort(RAW_DATA, ENCODING, SORTED_DATA);

		System.out.println("input  ==> " + FileIO.getTextFromFile(RAW_DATA, ENCODING));
		System.out.println("output ==> " + FileIO.getTextFromFile(SORTED_DATA, ENCODING));
	}

}
