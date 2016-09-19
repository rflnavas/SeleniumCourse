package cursoSelenium;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SwitchWindowTest {

	WebDriver webdriver;
	String url;
	
	@Before
	public void setUp() throws Exception {
		this.url = "http://letskodeit.teachable.com/pages/practice";
		this.webdriver = new FirefoxDriver();
		this.webdriver.manage().window().maximize();
		this.webdriver.get(this.url);
	}

	@Test
	public void test() throws InterruptedException {
		
		//Get the handle for the current window
		String parentHandle = webdriver.getWindowHandle();
		
		WebElement openWindow = webdriver.findElement(By.id("openwindow"));
		openWindow.click();
		
		//Get all handles
		Set<String> handles = webdriver.getWindowHandles();
		
		//Show all the handles
		for (String handle : handles) {
			System.out.println(handle);
			if(!handle.equals(parentHandle)){
				webdriver.switchTo().window(handle);
				
				Thread.sleep(2000);
			}
		}
	}

	
	@After
	public void tearDown() throws Exception {
	}

	
}
