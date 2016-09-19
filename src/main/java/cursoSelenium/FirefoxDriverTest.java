package cursoSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverTest {

	public static void main(String[] args) {
		WebDriver webDriver = new FirefoxDriver();
		
		String baseUrl = "http://www.wordreference.com/es/";
		webDriver.manage().window().maximize();
		webDriver.get(baseUrl);
		webDriver.findElement(By.cssSelector("#si")).sendKeys("happiness");
		WebElement submit = webDriver.findElement(By.cssSelector(".submit-button"));
		submit.click();
		
		By idFromBusq = By.xpath(".//*[@id='si']");
		WebElement formBusq = webDriver.findElement(idFromBusq);
		formBusq.sendKeys("joy");
		formBusq.clear();
		formBusq.sendKeys("enjoyment");
		//We can find an element by name through different ways
		//This line returns the same as the next line.
		//webDriver.findElement(By.cssSelector("input[name=B]")).click();
		webDriver.findElement(By.name("B")).click();
	}

}
