package cursoSelenium.testng;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import cursoSelenium.pages.PageFactory;
import cursoSelenium.pages.SearchPage;
import cursoSelenium.utils.Constants;
import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.PageUtils;
import cursoSelenium.utils.RandomGenerator;
import cursoSelenium.utils.WaitUtils;

public class FillInfoTest {

	SearchPage spf;
	static Logger log = Logger.getLogger(FillInfoTest.class);

	/*
	 * The order in which @Test methods are executed is alphabetical by default,
	 * i.e method foo() will be executed after bar() To change the default
	 * order, we could set priority to alter the order of test executions
	 */
	@BeforeSuite
	public void beforeSuite() {
//		this.spf = new SearchPage(DriverType.CHROME,
//				"https://www.expedia.com");
		this.spf = PageFactory.getSearchPage(DriverType.CHROME);
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("log4j.properties");
		PropertyConfigurator.configure(is);
	}

	@Test(priority = 1)
	public void fillBasicInformation() {
		spf.clickFlightTab();
		spf.fillOriginCity("New York");
		spf.fillDestinationCity("Miami");

	}

	@Test(priority = 2)
	public void fillAdvancedInformation() {
		spf.selectFlightClass("first");
		final int numberOfChildren = 3;
		spf.selectNumberOfChildren(numberOfChildren);
		List<WebElement> comboAges = spf.getDriver().findElements(
				By.cssSelector("select[id*=flight-age-select-]"));
		int[] randomAges = RandomGenerator.getInts(numberOfChildren,
				Constants.CHILDREN_MIN_AGE, Constants.CHILDREN_MAX_AGE);
		int i = 0;
		// for(WebElement comboAge : comboAges){
		// WaitUtils.fluentWaitStale(spf.getDriver(), comboAge);
		// }

		for (WebElement comboAge : comboAges) {

			comboAge = PageUtils.replaceIfStaleBy(spf.getDriver(), comboAge, 
					By.cssSelector("select[id*=flight-age-select-" + (i + 1) + "]"));
			Select select = new Select(comboAge);
			select.selectByValue(String.valueOf(randomAges[i++]));
// 			ExpectedCondition<Boolean> ec = ExpectedConditions.stalenessOf(comboAge);
//			boolean isStale = ec.apply(this.spf.getDriver());
//			if (!isStale) {
//				select = new Select(comboAge);
//			} else {
//				WebElement newComboAge = spf.getDriver().findElement(
//						By.cssSelector("select[id*=flight-age-select-" + (i + 1)
//								+ "]"));
//				select = new Select(newComboAge);
//			}
//			select.selectByValue(String.valueOf(randomAges[i++]));
		}
		
		WebElement nonStop = WaitUtils.fluentWaitPresence(spf.getDriver(), By.id("advanced-flight-nonstop"));
		nonStop.click();
		System.out.println(nonStop.isSelected());

	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("Beggining a test...");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Test ended");
		log.info("Test ended!");
	}

}
