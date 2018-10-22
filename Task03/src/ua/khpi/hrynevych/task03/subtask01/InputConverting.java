package ua.khpi.hrynevych.task03.subtask01;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Utility class, containing methods for parsing input data
 * like in the subtask 1.
 *
 * Input data must match the format "Login;Name;Email\n". This class
 * contains methods for extracting logins, names, emails and unique
 * domains from the data of such format.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 17 Nov 2017
 */
public final class InputConverting {

	/**
	 * List of logins extracted from the input data.
	 */
	private static ArrayList<String> logins = new ArrayList<>();

	/**
	 * List of names extracted from the input data.
	 */
	private static ArrayList<String> names = new ArrayList<>();

	/**
	 * List of emails extracted from the input data.
	 */
	private static ArrayList<String> emails = new ArrayList<>();

	/**
	 * List of unique domains extracted from the input data.
	 */
	private static LinkedHashSet<String> domains = new LinkedHashSet<>();

	/**
	 * The utility class can't have instances.
	 */
	private InputConverting() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Fills lists of logins, names, emails and domains using data
	 * of specified format from the string in parameter.
	 *
	 * Assumed that input data are properly formatted, that is contain
	 * all needed kinds of information about the every user. Otherwise
	 * incorrect (unexpected) output data can be received as a result.
	 *
	 * @param  input  input data of specified format
	 */
	public static void setData(final String input) {
		final int firstLoginPosition = 3;
		int index = 0;
		String[] raw = input.split("[;\n]");

		if (input.startsWith("Login;Name;Email\n")) {
			index = firstLoginPosition;
		}
		while (index < raw.length) {
			logins.add(raw[index++]);
			names.add(raw[index++]);
			emails.add(raw[index]);
			domains.add(raw[index++].split("@")[1]);
		}
	}

	/**
	 * Returns logins extracted from the input data.
	 *
	 * If input data didn't contain information about any user,
	 * then null is returned.
	 *
	 * @return  logins from the input data
	 */
	public static String[] getLogins() {
		if (logins.size() == 0) {
			return null;
		} else {
			return logins.toArray(new String[logins.size()]);
		}
	}

	/**
	 * Returns names extracted from the input data.
	 *
	 * If input data didn't contain information about any user,
	 * then null is returned.
	 *
	 * @return  names from the input data
	 */
	public static String[] getNames() {
		if (names.size() == 0) {
			return null;
		} else {
			return names.toArray(new String[names.size()]);
		}
	}

	/**
	 * Returns emails extracted from the input data.
	 *
	 * If input data didn't contain information about any user,
	 * then null is returned.
	 *
	 * @return  emails from the input data
	 */
	public static String[] getEmails() {
		if (emails.size() == 0) {
			return null;
		} else {
			return emails.toArray(new String[emails.size()]);
		}
	}

	/**
	 * Returns unique domains extracted from the input data.
	 *
	 * If input data didn't contain information about any user,
	 * then null is returned.
	 *
	 * @return  unique domains from the input data
	 */
	public static String[] getDomains() {
		if (domains.size() == 0) {
			return null;
		} else {
			return domains.toArray(new String[domains.size()]);
		}
	}

	/**
	 * Returns domain of the email in the method parameter.
	 *
	 * Assumed that input string is properly formatted email adress,
	 * containing symbol '@' between name and domain. Otherwise
	 * unexpected result can be received.
	 *
	 * @param   email  email, the domain of which is needed
	 * @return         domain of the email in parameter
	 */
	public static String getDomain(final String email) {
		return email.split("@")[1];
	}
}
