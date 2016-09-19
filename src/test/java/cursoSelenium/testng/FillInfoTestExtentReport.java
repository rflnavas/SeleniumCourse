package cursoSelenium.testng;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cursoSelenium.pages.SearchPage;
import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.LogUtils;
import cursoSelenium.utils.PageUtils;
import cursoSelenium.utils.Screenshot;

public class FillInfoTestExtentReport{

	private SearchPage spf;
	private ExtentTest test;
	private static Logger log = Logger.getLogger(FillInfoTestExtentReport.class);
	private String [] cities = new String[]{"New York", "Madrid", "Roma", "Washington"};

	/**
	 * This method will be run before the first test method is executed.
	 */
	@BeforeClass
	public void beforeClass(){
		
		this.spf = new SearchPage(DriverType.CHROME,
				"https://www.expedia.com");
		this.test = this.spf.getTest();
		LogUtils.prepareConfig(this.getClass());
		
		this.spf.getTest().log(LogStatus.INFO, "Browser started ...");
	}
	

	//By default the tests will be run in alphabetical order so 
	//fillAdvancedInformation will be executed before fillBasicInformation
	//This time we choose another kind of testng-configuration based on xml instead of 
	//priority attribute in Test annotation as FillInfoTest does
	//
	//We might also use dependsOnMethods
	@Test
	public void fillBasicInformation() {
		final int numberOfChildren = 3;
		this.test.setStartedTime(new Date());
		this.test.setDescription("Filling basic input data : Cities and children");
		this.spf.clickFlightTab();
		
		this.spf.fillOriginCity(cities[0])
			.fillDestinationCity(cities[0]);
		
		this.spf.selectNumberOfChildren(numberOfChildren)
			.setRandomAgesForChildren(numberOfChildren);
		
		test.setEndedTime(new Date());
	}

	@Test(dependsOnMethods={"fillBasicInformation"})
	public void fillAdvancedInformation() {
		String flightClass = "first";
		test.setStartedTime(new Date());
		test.setDescription("Filling advanced input data : Flight class");
		this.spf.clickAdvancedOptions();
		this.spf.selectFlightClass(flightClass);
		this.spf.clickOnNonFlightStop();
		
		//Select select = new Select(spf.getFlightChildren());
		//String numChildren = select.getFirstSelectedOption().getAttribute("value");
		//Assert.assertEquals(numChildren, String.valueOf(numberOfChildren));
		
		this.spf.submit();
		boolean searchError = spf.isSearchError();
		test.setEndedTime(new Date());
	
		Assert.assertEquals(searchError, true);
		
	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("Beggining a test...");
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) {
		String msg = String.format("Test ended at %s", DateFormatUtils.format(Calendar.getInstance(), "dd/MM/yyyy HH:mm:ss"));
		test.setEndedTime(Calendar.getInstance().getTime());
		log.info(msg);
		if(testResult.getStatus() == ITestResult.SUCCESS){
			//Screenshot.takeScreenshot(this.spf.getDriver(), testResult.getName());
			String screenshotLocation = Screenshot.takeScreenshot(spf.getDriver(), testResult.getName());
			//addScreenCapture returns the equivalent html img snippet given the URI of the image
			String screenshotLocationPath = test.addScreenCapture(screenshotLocation);
			test.log(LogStatus.PASS, "Validation error triggered " + 
					PageUtils.value(this.spf.getFlightOrigin()) + " - " + 
					PageUtils.value(this.spf.getFlightDestination()), screenshotLocationPath);
		}
		
		test.log(LogStatus.INFO, "Test completed in " + 
				(test.getEndedTime().getTime() - test.getStartedTime().getTime())/1_000.0);
		
	}
	
	@AfterClass
	public void afterClass(){
		this.spf.getDriver().quit();
		//Don't forget to include these lines or the report will not be created
		this.spf.getReport().endTest(test);
		this.spf.getReport().flush();
	}

}
