package cursoSelenium.data;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cursoSelenium.pages.SearchPage;
import cursoSelenium.utils.Constants;
import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.ExcelHelper;
import cursoSelenium.utils.ExcelReader;
import cursoSelenium.utils.PageUtils;

public class PageWithDataTest {

	private static SearchPage searchPageFactory;
	private static ExcelReader xlsReader;
	//Will be executed only once 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		searchPageFactory = new SearchPage(DriverType.CHROME, "https://www.expedia.es");
		xlsReader = new ExcelReader(Constants.XLS_DATA_PATH.toString());
	}
	
	@DataProvider(name="searchPage")
	public Object[][] dataInputProvider(){
		Object[][] testData = //xlsReader.getDataFromLabel("searchPage");
					ExcelHelper.getTestData("SEARCH_PAGE", xlsReader.getSheet("Hoja1"));
		return testData;
	}
	@Test(dataProvider="searchPage")
	public void testUsingExcel(String cityFrom,	String cityTo, String depdate, String retdate) throws InterruptedException {
		searchPageFactory.clickFlightTab();
		Thread.sleep(1500);
		searchPageFactory.fillOriginCity(cityFrom);
		searchPageFactory.fillDestinationCity(cityTo);
		searchPageFactory.fillDepartingDate(depdate);
		searchPageFactory.fillReturningDate(retdate);
		PageUtils.clearInputTextFields(searchPageFactory.getDriver());
		Assert.assertTrue(true);
	}

	@AfterClass
	public void tearDown() throws Exception {
		
	}

}
