package cursoSelenium.logging;

import java.util.logging.ConsoleHandler;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TRACE
 * DEBUG
 * INFO
 * WARN
 * ERROR
 * FATAL
 * 
 * VM Argument : -Dlog4j.debug=true
 * @author RafaW7
 *
 */
public class AppendersTest {

	static Logger log = Logger.getLogger(AppendersTest.class);
	static Appender appender;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		final PatternLayout layout = new PatternLayout();
		/*
		 * %c{n} class name. {n} stands for the last names in the fully qualified 
		 * name class, i.e. {1} shows the class name, {2} show the class name and its 
		 * package and so on.
		 * 
		 * %m method 
		 * %n new line
		 */
		layout.setConversionPattern("%d {yyyy-MM-dd HH:mm:ss} --%-10p %c{2} - %m%n");
		appender = new ConsoleAppender(layout);
		ConsoleHandler ch = new ConsoleHandler();
		System.out.println("LEVEL " + ch.getLevel().intValue());
		log.addAppender(appender);

		log.info("Running before method");
	}

	@Test
	public void test() {
		log.debug("Running test method");
	}

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		log.info("Running after method");
	}

}
