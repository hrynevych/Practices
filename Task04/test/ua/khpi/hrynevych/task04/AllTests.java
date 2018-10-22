package ua.khpi.hrynevych.task04;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.khpi.hrynevych.task04.subtask01.Subtask01Test;
import ua.khpi.hrynevych.task04.subtask02.Subtask02Test;
import ua.khpi.hrynevych.task04.subtask02.UtilityTest;
import ua.khpi.hrynevych.task04.subtask03.Subtask03Test;
import ua.khpi.hrynevych.task04.subtask04.Subtask04Test;
import ua.khpi.hrynevych.task04.subtask04.ParserTest;
import ua.khpi.hrynevych.task04.subtask05.Subtask05Test;

/**
 * Suite for testing Task04.
 *
 * Runs tests covering all the subtasks.
 *
 * @author   Hrynevych Pavlo
 * @version  1.0, 30 Nov 2017
 */
@RunWith(Suite.class)
@SuiteClasses({ DemoTest.class, FileIOTest.class, Subtask01Test.class, Subtask02Test.class, UtilityTest.class,
		Subtask03Test.class, Subtask04Test.class, ParserTest.class, Subtask05Test.class })
public class AllTests {

}
