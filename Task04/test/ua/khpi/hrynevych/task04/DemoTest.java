package ua.khpi.hrynevych.task04;

import static org.junit.Assert.fail;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

/**
 * Tests Demo class.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
public class DemoTest {

	/**
	 * Timeout for the {@link Demo#main(String[]) main} method.
	 */
	private static final int TIMEOUT = 1000;

	/**
	 * Object of the utility class can't be obtained.
	 *
	 * Creating the object is unsupported operation so that's why such
	 * exception can be expected.
	 *
	 * @throws  Throwable  if there is a problem with creating the object
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void constructorTest() throws Throwable {
		Class<Demo> cl = Demo.class;
		Constructor<?> con = cl.getDeclaredConstructors()[0];

		con.setAccessible(true);
		try {
			con.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	/**
	 * Tests {@link Demo#main(String[]) main} method for waiting
	 * for the input from the console.
	 *
	 * In fact, this method should work without user intervention.
	 */
	@Test(timeout = TIMEOUT)
	public void mainTest() {
		try {
			Demo.main(null);
		} catch (IOException e) {
			fail();
		}
	}

}
