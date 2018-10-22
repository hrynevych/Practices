package ua.khpi.hrynevych.task04.subtask04;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.khpi.hrynevych.task04.FileIO;

/**
 * Parses text on sentences.
 *
 * Sentence is the sequence of characters which starts with the
 * uppercase letter and ends with the dot.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
class Parser implements Iterable<String> {

	/**
	 * Text to be parsed.
	 */
	private String text;

	/**
	 * Constructs new parser providing that the text for parsing
	 * will be taken from the file in parameter.
	 *
	 * @param  fileName  file containing the text for parsing
	 * @param  encoding  encoding of the file
	 */
	Parser(final String fileName, final String encoding) {
		text = FileIO.getTextFromFile(fileName, encoding);
	}

	/**
	 * Returns new sentence iterator for this parser.
	 */
	@Override
	public Iterator<String> iterator() {
		return new SentenceIterator();
	}

	/**
	 * Allows to iterate text by sentences.
	 *
	 * @author   Hrynevych Pavlo
	 * @version  1.0, 30 Nov 2017
	 */
	private class SentenceIterator implements Iterator<String> {

		/**
		 * Pattern for extracting sentences.
		 */
		private Pattern pattern = Pattern.compile("[А-ЯЁA-Z](?s).*?[.]");

		/**
		 * Text of this parser in which matches will be looking for.
		 */
		private Matcher matcher = pattern.matcher(text);

		/**
		 * Returns true if the iteration has more sentences.
		 */
		@Override
		public boolean hasNext() {
			return matcher.find();
		}

		/**
		 * Returns the next sentence in this iteration.
		 */
		@Override
		public String next() {
			return matcher.group().replaceAll("[\n\r]", "");
		}

		/**
		 * This operation is unsupported for the sentence iterator.
		 *
		 * You can't remove sentences from the text of this parser.
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
