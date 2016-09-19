package cursoSelenium.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Screenshot {
	
	public static final Path LOCATION = Paths.get(Constants.DEFAULT_ROOT, "Test", "Screenshots");
	
	static {
		FilesHelper.createFolderIfNotExists(LOCATION);
	}
	
	private static Logger log = Logger.getLogger(Screenshot.class);
	
	private Screenshot() {
	}
	
	public static void takeScreenshot(WebDriver driver) {
		takeScreenshot(driver, "");
	}
	
	/*
	 * File imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd HHmmss");
		String screenshotName = null;
		if(StringUtils.isBlank(customName)){
			screenshotName = String.format("%s.png", df.format(new Date()));
		}else{
			screenshotName = String.format("%s_%s.png", customName, df.format(new Date()));
		}
		String targetDestination = Paths.get(LOCATION.toString(), screenshotName).toString();
		
	 */
	public static String takeScreenshot(WebDriver driver, String customName) {
		
		File imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Path screenshotPath = getImageFileDestination(customName);
		//String targetDestination = Paths.get(LOCATION.toString(), screenshotName).toString();
		try {
			Files.copy(Paths.get(imageFile.getAbsolutePath()), screenshotPath);
		} catch (IOException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			throw new RuntimeException(e);
		}
		return screenshotPath.toString();
	}
	
	private static Path getImageFileDestination(String customName){
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd HHmmss");
		String screenshotName = null;
		if(StringUtils.isBlank(customName)){
			screenshotName = String.format("%s.png", df.format(new Date()));
		}else{
			screenshotName = String.format("%s_%s.png", customName, df.format(new Date()));
		}
		return Paths.get(LOCATION.toString(), screenshotName);
	}
}
