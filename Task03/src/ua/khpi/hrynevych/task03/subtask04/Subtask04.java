package ua.khpi.hrynevych.task03.subtask04;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class contains methods for hashing information.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 17 Nov 2017
 */
public final class Subtask04 {

	/**
	 * The utility class can't have instances.
	 */
	private Subtask04() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns hash of the first string parameter obtained by applying
	 * the algorithm defined in the second parameter.
	 *
	 * First parameter should consist of the string, the hash of which is needed.
	 * The second one should contain proper algorithm name. Output is the
	 * string of hexadecimal digits: two digits per each byte of resulting hash.
	 *
	 * @param   input                     string, the hash of which is needed
	 * @param   algorithm                 hashing algorithm name
	 * @return                            hash of the input string
	 * @throws  NoSuchAlgorithmException  if there is no hashing algorithm with
	 * 									  the name specified in parameter
	 * 									  algorithm
	 */
	public static String hash(final String input, final String algorithm) throws NoSuchAlgorithmException {
		if ((input == null) || (algorithm == null)) {
			System.out.println("No data");
			return null;
		}

		final int shift = 4;
		final int unitHalfByte = 0x0F;
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(input.getBytes());
		byte[] hash = digest.digest();
		StringBuffer result = new StringBuffer();

		for (byte i : hash) {
			result.append(Integer.toHexString((i >> shift) & unitHalfByte).toUpperCase());
			result.append(Integer.toHexString(i & unitHalfByte).toUpperCase());
		}
		return result.toString();
	}
}
