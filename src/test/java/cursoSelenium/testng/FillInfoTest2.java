package cursoSelenium.testng;

import java.util.Calendar;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import cursoSelenium.pages.SearchPage;
import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.LogUtils;
import cursoSelenium.utils.Screenshot;
import cursoSelenium.utils.WaitUtils;

public class FillInfoTest2 {

	private SearchPage spf;
	private static Logger log = Logger.getLogger(FillInfoTest2.class);

	/*
	 * The order in which @Test methods are executed is alphabetical by default,
	 * i.e method foo() will be executed after bar() To change the default
	 * order, we could set priority to alter the order of test executions
	 */
	@BeforeSuite
	public void beforeSuite() {
		this.spf = new SearchPage(DriverType.CHROME,
				"https://www.expedia.com");
		LogUtils.prepareConfig(this.getClass());
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
		spf.clickFlightTab()
			.fillOriginCity("New York")
			.fillDestinationCity("New York")
			.selectNumberOfChildren(numberOfChildren)
			.setRandomAgesForChildren(numberOfChildren);
		Reporter.log("Selected number of children " + numberOfChildren, true);
	}

	@Test(dependsOnMethods={"fillBasicInformation"})
	public void fillAdvancedInformation() {
		String flightClass = "first";
		spf.clickAdvancedOptions();
		spf.selectFlightClass(flightClass);
		spf.clickOnNonFlightStop();
		
		//Select select = new Select(spf.getFlightChildren());
		//String numChildren = select.getFirstSelectedOption().getAttribute("value");
		//Assert.assertEquals(numChildren, String.valueOf(numberOfChildren));
		
		spf.submit();
		WebElement layoutError = null;
		try {
			layoutError = WaitUtils.fluentWaitPresence(spf.getDriver(), By.id("flight-errors"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Assert.assertEquals(layoutError != null, true);
		Reporter.log("Advanced info [flightClass=" + flightClass + 
				" non-flight-stop=" + spf.getNonFlightStop().isSelected() + "]", true);
	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("Beggining a test...");
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) {
		String msg = String.format("Test ended at %s", DateFormatUtils.format(Calendar.getInstance(), "dd/MM/yyyy HH:mm:ss"));
		log.info(msg);
		Reporter.log(msg, true);
		if(testResult.getStatus() == ITestResult.SUCCESS){
			Screenshot.takeScreenshot(spf.getDriver(), testResult.getName());
		}
	}

}
