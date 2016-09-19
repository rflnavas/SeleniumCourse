package cursoSelenium.data;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cursoSelenium.pages.SearchPage;
import cursoSelenium.testng.AbstractTest;
import cursoSelenium.utils.DriverType;

public class PageWithParameters extends AbstractTest{
	
	private static SearchPage searchPageFactory;
	//Will be executed only once 
	@Parameters({"browserType"})
	@BeforeClass
	public static void setUpBeforeClass(String browserType) throws Exception {
		if (browserType != null && !"".equals(browserType.trim())) {
			searchPageFactory = new SearchPage(
					DriverType.valueOf(browserType.toUpperCase()),
					"https://www.expedia.es");
		}
	}
	
	@Parameters({"cityFrom", "cityTo", "depdate", "retdate"})
	@Test
	public void testUsingData(String cityFrom,	String cityTo, String depdate, String retdate) throws InterruptedException {
		
		searchPageFactory.clickFlightTab();
		
		Thread.sleep(1500);
		searchPageFactory.fillOriginCity(cityFrom);
		searchPageFactory.fillDestinationCity(cityTo);
		searchPageFactory.fillDepartingDate(depdate);
		searchPageFactory.getReturnDate().clear();
		searchPageFactory.fillReturningDate(retdate);
		//PageUtils.clearInputTextFields(searchPageFactory.getDriver());
		Assert.assertTrue(true);
	}

	@AfterClass
	public void tearDown() throws Exception {
		
	}

	@Override
	public WebDriver getDriver() {
		return searchPageFactory.getDriver();
	}

}
