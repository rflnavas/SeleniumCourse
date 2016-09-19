package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cursoSelenium.utils.DriverType;

public class DragAndDropActionTest extends BasicConfig {

	@Before
	public void setUp() throws Exception {
		init(DriverType.CHROME, "https://jqueryui.com/droppable/");
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		
		WebElement draggable = this.driver.findElement(By.id("draggable"));
		WebElement droppable = this.driver.findElement(By.id("droppable"));
		
		Actions action = new Actions(driver);
		
		//Drag and drop
		//action.dragAndDrop(draggable, droppable);
		
		//Click and hold, move to element, release, build, perform
		
		action.clickAndHold(draggable)
			.moveToElement(droppable)
			.release()
			/*
			 * build() is needed when performing more than one 
			 * action at once, it is also allowed to use with 
			 * a single action.
			 */
			.build()
			.perform();
		
	}

	@After
	public void tearDown() throws Exception {
	}
}
