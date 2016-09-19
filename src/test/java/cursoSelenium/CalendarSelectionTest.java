package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cursoSelenium.utils.DriverType;

public class CalendarSelectionTest extends BasicConfig{

	@Before
	public void setUp() throws Exception {
		init(DriverType.CHROME, "https://www.expedia.es/");
	}

	@Test
	public void test() {
		WebElement day = driver.findElement(By.cssSelector(
					"a.datepicker-cal-date[data-day='5'][data-month='8']"));
		//"div.datepicker-cal-month:nth-of-type(2)"
		// //div[@class='cal-month'][position()=1]//a[text()='5']
	}

	@After
	public void tearDown() throws Exception {
	}


}
