package cursoSelenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cursoSelenium.utils.DriverType;

public class SwithAlertTest {

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
	public void test1() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("name")).sendKeys("Rafa");
		driver.findElement(By.id("alertbtn")).click();
		Thread.sleep(2200);
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	@Test
	public void test2() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("name")).sendKeys("Rafa");
		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(2200);
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}


}
