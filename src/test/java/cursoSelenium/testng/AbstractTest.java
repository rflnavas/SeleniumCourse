package cursoSelenium.testng;

import org.openqa.selenium.WebDriver;

public abstract class AbstractTest {
	protected WebDriver driver;

	public abstract WebDriver getDriver();
	
}
