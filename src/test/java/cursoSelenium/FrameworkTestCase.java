package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cursoSelenium.pages.SearchPage;
import cursoSelenium.utils.DriverType;

public class FrameworkTestCase {
	
	private static SearchPage searchPageFactory;
	//Will be executed only once 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		searchPageFactory = new SearchPage(DriverType.CHROME, "https://www.expedia.es");
	}
	//Will be run for each @Test
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		searchPageFactory.clickFlightTab();
		searchPageFactory.fillOriginCity("Madrid");
		searchPageFactory.fillDestinationCity("New York");
		WebDriverWait wdw = new WebDriverWait(searchPageFactory.getDriver(), 4);
		WebElement depDate = wdw.until(ExpectedConditions.visibilityOf(searchPageFactory.getDepartureDate()));
		depDate.sendKeys("15/09/2016");
	}

	@After
	public void tearDown() throws Exception {
	}


}
