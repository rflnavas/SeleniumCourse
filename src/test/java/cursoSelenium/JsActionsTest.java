package cursoSelenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cursoSelenium.utils.DriverType;

public class JsActionsTest {
	
	WebDriver driver;
	String url;
	JavascriptExecutor jse;
	
	@Before
	public void setUp() throws Exception {
		this.url = "https://letskodeit.teachable.com/pages/practice";
		this.driver = DriverType.CHROME.getDriver();
		this.driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void test() throws InterruptedException {
		this.driver.get(this.url);
		jse.executeScript("window.scrollBy(0, 600)");
		Thread.sleep(2000);
		
		WebElement mainElement = driver.findElement(By.id("mousehover"));
		
		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();
		Thread.sleep(2000);
		
		//WebElement subElement = driver.findElement(By.xpath("//div[@class='mouse-hover-content']//a[text()='Top']"));
		//subElement.click();
		action.moveToElement(mainElement).click().perform();
	}

	@After
	public void tearDown() throws Exception {
	}


}
