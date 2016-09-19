package cursoSelenium.logging;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggingDemoProperties {
	
	static Logger log = Logger.getLogger(LoggingDemoProperties.class);
	public static void main(String[] args) {
		InputStream is = new LoggingDemoProperties().getClass().getClassLoader().getResourceAsStream("log4j.properties");
		PropertyConfigurator.configure(is);
		
		log.info("this message is an info");
		log.debug("Debugging...");
	}

}
