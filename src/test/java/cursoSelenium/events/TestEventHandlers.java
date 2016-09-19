package cursoSelenium.events;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cursoSelenium.utils.DriverType;

public class TestEventHandlers {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		String url = "https://www.expedia.es/";
		driver = DriverType.CHROME.getDriver();
		
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		HandleEvents he = new HandleEvents();
		eDriver.register(he);
		
		eDriver.get(url);
		eDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		eDriver.findElement(By.id("tab-flight-tab")).click();
		
		eDriver.findElement(By.id("flight-origin")).sendKeys("Sevilla");
		eDriver.findElement(By.id("flight-destination")).sendKeys("Roma");
		eDriver.findElement(By.id("flight-departing")).sendKeys("12/03/2017");
		WebElement returning = eDriver.findElement(By.id("flight-returning"));
		returning.clear();
		returning.sendKeys("18/03/2017");
		Thread.sleep(500);
		eDriver.findElement(By.id("advanced-options")).click();
		Thread.sleep(1000);
		List<WebElement> checkboxes = eDriver.findElements(By.cssSelector("input[type='checkbox']"));
		
		for (WebElement webElement : checkboxes) {
			webElement.click();
			Thread.sleep(300);
		}
	}

}
