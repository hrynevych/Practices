package ua.khpi.hrynevych.task03.subtask05;

/**
 * Contains methods for converting numbers between decimal
 * and roman notations.
 *
 * Operating range of the class methods consists of numbers
 * between 1 and 100 inclusive in decimal notation. Working with
 * numbers not from specified range may cause unexpected results.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 17 Nov 2017
 */
public final class Subtask05 {

	/**
	 * The smallest number that can be processed by methods of the class.
	 */
	private static final int LOWER_LIMIT = 1;

	/**
	 * The largest number that can be processed by methods of the class.
	 */
	private static final int UPPER_LIMIT = 100;

	/**
	 * The utility class can't have instances.
	 */
	private Subtask05() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns representation of specified number of units in a specified
	 * digit place in roman notation.
	 *
	 * Having some number splitted into digits, you can get roman notation
	 * of each digit place using this method. Parameter number correspond to
	 * the number of units in digit place, roman notation of which is needed.
	 * E.g. number 43 has 4 units in tens digit place and 3 units in units digit
	 * place. Parameters current, next5 and next10 define roman representation
	 * of needed digit place. Parameter current defines a char that represents
	 * one unit of needed digit place in roman notation. Parameters next5 and
	 * next10 correspond to five and ten units of current digit place, respectively.
	 * E.g. for getting roman notation of units in the number 43 you use
	 * current = 'I', next5 = 'V', next10 = 'X', number = 3. And for getting tens:
	 * current = 'X', next5 = 'L', next10 = 'C', number = 4.
	 *
	 * @param   current  symbol representing one unit of current digit place in roman notation
	 * @param   next5    symbol representing five units of current digit place in roman notation
	 * @param   next10   symbol representing ten units of current digit place in roman notation
	 * @param   number   number of units in current digit place to represent in roman notation
	 * @return           roman notation of specified digit place
	 */
	private static String getPart(final char current, final char next5, final char next10, final int number) {
		final int four = 4;
		final int five = 5;
		final int nine = 9;
		StringBuffer result = new StringBuffer();

		if (number == four) {
			result.append(current);
			result.append(next5);
		} else if (number == nine) {
			result.append(current);
			result.append(next10);
		} else {
			if (number > four) {
				result.append(next5);
			}
			for (int i = 0; i < (number % five); i++) {
				result.append(current);
			}
		}
		return result.toString();
	}

	/**
	 * Returns roman representation of the decimal number in parameter.
	 *
	 * Method works for numbers between 1 and 100 inclusively.
	 *
	 * @param   x  number to represent in roman notation
	 * @return     roman notation of specified number
	 */
	public static String decimal2Roman(final int x) {
		final int ten = 10;
		final int hundred = 100;

		if ((x < LOWER_LIMIT) || (x > UPPER_LIMIT)) {
			System.out.println("Out of range");
			return null;
		}

		int units = x % ten;
		int tens = (x / ten) % ten;
		StringBuffer result = new StringBuffer();

		if (x == hundred) {
			result.append("C");
		} else {
			result.append(getPart('X', 'L', 'C', tens));
			result.append(getPart('I', 'V', 'X', units));
		}
		return result.toString();
	}

	/**
	 * Returns decimal value that character in the parameter
	 * represent in roman notation.
	 *
	 * Method works for symbols representing numbers between 1 and
	 * 100 inclusively. Other symbols will result 0.
	 *
	 * @param   input  symbol of roman notation
	 * @return         decimal number corresponding to the specified symbol
	 */
	private static int getValue(final char input) {
		final char[] chars = {'I', 'V', 'X', 'L', 'C'};
		final int[] values = {1, 5, 10, 50, 100};

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == input) {
				return values[i];
			}
		}
		return 0;
	}

	/**
	 * Returns decimal value corresponding to the roman number in the parameter.
	 *
	 * If input string is null, empty or can't be considered as a roman number
	 * then -1 is returned. Also -1 is returned when result is bigger then 100 or
	 * less then 1.
	 *
	 * @param   s  roman number
	 * @return     decimal value of specified roman number
	 */
	public static int roman2Decimal(final String s) {
		if ((s == null) || (s.length() == 0)) {
			System.out.println("No data");
			return -1;
		}

		if ((!s.toUpperCase().matches("[IVXLC]+"))
				|| (!s.toUpperCase().matches("((X?C)|(X?L)|(LX{1,4})|(X{1,4}))?"
						+ "((IX)|(I?V)|(VI{1,3})|(I{1,3}))?"))) {
			System.out.println("Bad format");
			return -1;
		}

		String roman = s.toUpperCase();
		int prev = getValue(s.charAt(0));
		int current;
		int result = prev;

		for (int i = 1; i < roman.length(); i++) {
			current = getValue(s.charAt(i));
			if (current > prev) {
				result -= 2 * prev;
			}
			result += current;
			prev = current;
		}
		if ((result > UPPER_LIMIT) || (result < LOWER_LIMIT)) {
			System.out.println("Bad format");
			return -1;
		}
		return result;
	}
}
