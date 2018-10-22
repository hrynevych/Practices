package ua.khpi.hrynevych.task03.subtask01;

/**
 * Class containing methods for converting input data that
 * match the format "Login;Name;Email\n".
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 17 Nov 2017
 */
public class Subtask01 {

	/**
	 * Array of logins extracted from the input data.
	 */
	private String[] logins;

	/**
	 * Array of names extracted from the input data.
	 */
	private String[] names;

	/**
	 * Array of emails extracted from the input data.
	 */
	private String[] emails;

	/**
	 * Array of unique domains extracted from the input data.
	 */
	private String[] domains;

	/**
	 * Constructs new object and fills its fields according to
	 * the string with input data in the parameter.
	 *
	 * @param  input  input data
	 */
	public Subtask01(final String input) {
		InputConverting.setData(input);
		logins = InputConverting.getLogins();
		names = InputConverting.getNames();
		emails = InputConverting.getEmails();
		domains = InputConverting.getDomains();
	}

	/**
	 * Returns result in which for every user there is a line
	 * containing login and email that corresponds to it.
	 *
	 * @return  string containing output in specified format
	 */
	public String convert1() {
		StringBuffer result = new StringBuffer();

		if ((logins == null) || (emails == null)) {
			System.out.println("No data");
			return null;
		}

		for (int i = 0; (i < logins.length) && (i < emails.length); i++) {
			result.append(logins[i]);
			result.append(" ==> ");
			result.append(emails[i]);
			result.append("\n");
		}
		return result.toString().trim();
	}

	/**
	 * Returns result in which for every user there is a line
	 * containing surname and name of the user (in such sequence) and
	 * his or her email.
	 *
	 * @return  string containing output in specified format
	 */
	public String convert2() {
		StringBuffer result = new StringBuffer();

		if ((names == null) || (emails == null)) {
			System.out.println("No data");
			return null;
		}

		for (int i = 0; (i < names.length) && (i < emails.length); i++) {
			String[] temp = names[i].split(" ");

			result.append(temp[1]);
			result.append(" ");
			result.append(temp[0]);
			result.append(" (email: ");
			result.append(emails[i]);
			result.append(")\n");
		}
		return result.toString().trim();
	}

	/**
	 * Returns result in which for every domain from the input data there is a line
	 * containing logins of all users, whose emails are registered in this domain.
	 *
	 * Each domain occurs only once in the output.
	 *
	 * @return  string containing output in specified format
	 */
	public String convert3() {
		StringBuffer result = new StringBuffer();

		if ((logins == null) || (emails == null) || (domains == null)) {
			System.out.println("No data");
			return null;
		}

		for (String dom : domains) {
			result.append(dom);
			result.append(" ==> ");
			for (int i = 0; (i < logins.length) && (i < emails.length); i++) {
				if (dom.equals(InputConverting.getDomain(emails[i]))) {
					result.append(logins[i]);
					result.append(", ");
				}
			}
			result.replace(result.length() - 2, result.length(), "\n");
		}
		return result.toString().trim();
	}

	/**
	 * Randomly generates password containing only digits.
	 *
	 * @return  randomly generated password
	 */
	private static String generatePassword() {
		final int passwordLength = 4;
		final int numberOfDigits = 10;
		StringBuffer password = new StringBuffer();

		for (int i = 0; i < passwordLength; i++) {
			password.append((int) (Math.random() * numberOfDigits));
		}
		return password.toString();
	}

	/**
	 * Returns result which is the same as input data with the exception
	 * of the new column with passwords for every user.
	 *
	 * @return  string containing output in specified format
	 */
	public String convert4() {
		StringBuffer result = new StringBuffer();

		result.append("Login;Name;Email;Password\n");
		if ((logins != null) && (names != null) && (emails != null)) {
			for (int i = 0; (i < logins.length) && (i < names.length) && (i < emails.length); i++) {
				result.append(logins[i]);
				result.append(";");
				result.append(names[i]);
				result.append(";");
				result.append(emails[i]);
				result.append(";");
				result.append(generatePassword());
				result.append("\n");
			}
		}
		return result.toString().trim();
	}
}
