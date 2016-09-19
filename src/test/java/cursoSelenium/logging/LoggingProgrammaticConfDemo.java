package cursoSelenium.logging;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggingProgrammaticConfDemo {

	private static Logger log = Logger.getLogger(LoggingProgrammaticConfDemo.class);
	
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		
		Properties log4Props = new Properties();
		log4Props.setProperty("log4j.rootLogger", "DEBUG, CA");
		log4Props.setProperty("log4j.appender.CA", "org.apache.log4j.ConsoleAppender");
		log4Props.setProperty("log4j.appender.CA.layout", "org.apache.log4j.PatternLayout");
		log4Props.setProperty("log4j.appender.CA.layout.ConversionPattern", 
				"%d{yyyy-MM-dd HH:mm:ss} -- %10p %c - %m%n");
		PropertyConfigurator.configure(log4Props);
		
		log.debug("hola");
		
		log.info("adios");
		
	}

}
