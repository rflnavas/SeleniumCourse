package cursoSelenium.utils;

import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;

public class LogUtils {
	
	private LogUtils() {
	}
	
	public static void prepareConfig(Class<?> clazz){
		InputStream is = clazz.getClassLoader()
				.getResourceAsStream("log4j.properties");
		PropertyConfigurator.configure(is);
	}
	
	public static int getLineNumber(){
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
	
}
