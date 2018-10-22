package ua.khpi.hrynevych.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Utility class containing methods for interacting with files.
 *
 * Allows get text from file or write text to file quicklier.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public final class FileIO {

	/**
	 * The utility class can't have instances.
	 */
	private FileIO() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets information from specified file as a string.
	 *
	 * Specified file should exist. If it exists and empty then empty string is returned.
	 * Parameter encoding should contain name of encoding, in which information in file
	 * is written. Otherwise unexpected result can be obtained.
	 *
	 * @param   fileName  file with the needed information
	 * @param   encoding  encoding of information in the specified file
	 * @return            text information from the file
	 */
	public static String getTextFromFile(final String fileName, final String encoding) {
		StringBuffer text = new StringBuffer();
		try {
			Scanner in = new Scanner(new File(fileName), encoding);
			while (in.hasNextLine()) {
				text.append(in.nextLine()).append(System.lineSeparator());
			}
			in.close();
			return text.toString().trim();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return text.toString();
	}

	/**
	 * Prints text information to the specified file.
	 *
	 * If specified file doesn't exist, it creates one. Parameter fileName should contain
	 * acceptable file name, otherwise file wouldn't be created. If parameter text
	 * is empty, then file defined by fileName parameter will be empty too. If there is
	 * file with some information, then after applying this method to the file initial
	 * information will be wholly overwritten.
	 *
	 * @param  text      text to be written to the file
	 * @param  fileName  name of the file to which information will be written
	 */
	public static void printTextToFile(final String text, final String fileName) {
		try {
			FileWriter toFile = new FileWriter(new File(fileName));
			toFile.write(text);
			toFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
