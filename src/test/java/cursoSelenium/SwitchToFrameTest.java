package cursoSelenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cursoSelenium.utils.DriverType;

public class SwitchToFrameTest {
	
	WebDriver driver;
	String url;
	
	@Before
	public void setUp() throws Exception {
		this.url = "https://letskodeit.teachable.com/pages/practice";
		
		this.driver = DriverType.CHROME.getDriver();
		this.driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		this.driver.get(this.url);
	}
	
	@Test
	public void test() throws InterruptedException {
		Thread.sleep(2000);
		//driver.switchTo().frame("courses-iframe"); //swith to frame by id
		//driver.switchTo().frame("iframe-name"); //swith to frame by name
		//We can select an iframe by an integer
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		WebElement srchbox = driver.findElement(By.id("search-courses"));
		srchbox.sendKeys("javascript");
		driver.switchTo().defaultContent();
		WebElement inputName = driver.findElement(By.name("name"));
		inputName.sendKeys("Test successful");
		Thread.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		//driver.quit();
	}

}
