package cursoSelenium;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import cursoSelenium.utils.DriverType;


public class ScreenShotTest extends BasicConfig {

	@Before
	public void setUp() throws Exception {
		init(DriverType.CHROME, "https://www.expedia.es/");
	}
	
	@Test
	public void test() {
		
		WebElement flightTab = driver.findElement(By.id("tab-flight-tab"));
		flightTab.click();
		
		WebElement flightOrigin = driver.findElement(By.id("flight-origin"));
		WebElement flightDestination = driver.findElement(By.id("flight-destination"));
		WebElement departureDate =  driver.findElement(By.id("flight-departing"));
		WebElement arrivalDate =  driver.findElement(By.id("flight-returning"));
		WebElement search = driver.findElement(By.id("search-button"));
	
		flightOrigin.sendKeys("Madrid");
		flightDestination.sendKeys("Madrid");
		departureDate.sendKeys("12/09/2016");
		arrivalDate.sendKeys("17/09/2016");
		search.click();
	}

	@After
	public void tearDown() throws Exception {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Iterable<Path> roots = FileSystems.getDefault().getRootDirectories();
		Path root = roots.iterator().next();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Path imageDest = Paths.get(root.toString(), "test", "screenShot" + df.format(new Date()) + ".png");
		FileUtils.copyFile(srcFile, imageDest.toFile());
	}

}
