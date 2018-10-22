package ua.khpi.hrynevych.task06.subtask01;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Subtask01 {

	private static final String ENCODING = "Cp1251";
	
	private static final String INPUT = "asd asdf asd asdf asdf 43 asdsf 43 43 434 stop";
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		InputStream stdIn = System.in;
		System.setIn(new ByteArrayInputStream(INPUT.getBytes(ENCODING)));
		WordContainer.main(args);
		System.setIn(stdIn);
	}
}
