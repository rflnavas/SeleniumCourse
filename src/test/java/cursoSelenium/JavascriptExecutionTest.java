package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.JsUtils;

public class JavascriptExecutionTest extends BasicConfig {
	
	private JavascriptExecutor js;
	@Before
	public void setUp() throws Exception {
		init(DriverType.CHROME);
	}

	@Test
	public void testJavascriptExecution() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.location = 'https://www.expedia.es'");
		
		WebElement flightTab = driver.findElement(By.id("tab-flight-tab"));
		flightTab.click();
		
		Thread.sleep(1000);
		
		WebElement flightOrigin = null;
		flightOrigin = (WebElement)js.executeScript(JsUtils.getElementById("flight-origin"));
		//"return document.getElementById('flight-origin');");
		flightOrigin.sendKeys("Sevilla");
		driver.findElement(By.id("header-history")).click();
		
		Thread.sleep(2000);
		
		WebElement flightDestination = null;
		flightDestination = (WebElement)js.executeScript("return document.getElementById('flight-destination');");
		flightDestination.sendKeys("Madrid");
		
		driver.findElement(By.id("flight-departing")).sendKeys("11/09/2016");
		driver.findElement(By.id("flight-returning")).clear();
		driver.findElement(By.id("flight-returning")).sendKeys("18/09/2016");
		
		//Changing style ...
		js.executeScript("document.getElementById('flight-destination').style.borderColor='#FF0000'");
		
		//Getting attributes...
		String attribute = (String) js.executeScript("return document.getElementById('flight-origin').getAttribute('type');");
		System.out.println(String.format("The attr is : %s", attribute));
		
		//Size of window
		long size = (Long)js.executeScript("return window.innerHeight;");
		System.out.println(String.format("Window height is : %d", size));
		
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
