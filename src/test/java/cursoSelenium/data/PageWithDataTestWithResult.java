package cursoSelenium.data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cursoSelenium.pages.SearchPage;
import cursoSelenium.testng.AbstractTest;
import cursoSelenium.utils.Constants;
import cursoSelenium.utils.Constants.ResultType;
import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.ExcelHelper;
import cursoSelenium.utils.ExcelReader;
import cursoSelenium.utils.PageUtils;
import cursoSelenium.utils.WaitUtils;

public class PageWithDataTestWithResult extends AbstractTest{

	private SearchPage searchPageFactory;
	private ExcelReader xlsReader;
	
	private int rowCounter = Constants.BEGIN_ROW_INDEX;
	private String [][] data = null;
	//Will be executed only once 
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		searchPageFactory = new SearchPage(DriverType.CHROME, "https://www.expedia.es");
		xlsReader = new ExcelReader(Constants.XLS_DATA_PATH_2.toString());
		data = ExcelHelper.getTestData("SEARCH_PAGE", xlsReader.getSheet("Hoja1"));
	}
	
	@Test
	public void testUsingExcel() throws InterruptedException {
		
		searchPageFactory.clickFlightTab();
		Thread.sleep(1500);
		searchPageFactory.fillOriginCity(data[rowCounter][0]);
		searchPageFactory.fillDestinationCity(data[rowCounter][1]);
		searchPageFactory.fillDepartingDate(data[rowCounter][2]);
		searchPageFactory.fillReturningDate(data[rowCounter][3]);
		searchPageFactory.submit();
		WebElement layerError = WaitUtils.fluentWaitPresence(searchPageFactory.getDriver(), By.id("flight-errors"), 2000);
		System.out.println(layerError);
		ResultType resType = null;
		if(layerError != null){
			resType = ResultType.WARN;
		}else{
			//searchPageFactory.getDriver().navigate().back();
			Thread.sleep(1000);
			resType = ResultType.OK;
		}
		
		ExcelHelper.setDataCell(xlsReader.getSheet("Hoja1"), resType.toString(), 
				++rowCounter, Constants.COL_INDEX_RESULT_SEARCHPAGE);
		
		PageUtils.clearInputTextFields(searchPageFactory.getDriver());
		Assert.assertTrue(layerError != null);
	}

	@AfterClass
	public void tearDown() throws Exception {
		
	}

	@Override
	public WebDriver getDriver() {
		return searchPageFactory.getDriver();
	}

}
