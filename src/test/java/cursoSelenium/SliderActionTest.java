package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cursoSelenium.utils.DriverType;

public class SliderActionTest extends BasicConfig{

	@Before
	public void setUp() throws Exception {
		init(DriverType.CHROME, "https://jqueryui.com/slider/");
	}

	@Test
	public void test() {
		driver.switchTo().frame(0);
		WebElement slider = driver.findElement(By.cssSelector("#slider > span"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, 250, 0).perform();
	}
	
	@After
	public void tearDown() throws Exception {
	}


}
