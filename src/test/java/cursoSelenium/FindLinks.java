package cursoSelenium;

import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cursoSelenium.pages.SearchPage;
import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.PageUtils;

public class FindLinks{


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SearchPage spf = new SearchPage(DriverType.CHROME, "https://www.expedia.es");
		WebDriver driver = spf.getDriver();
		spf.clickFlightTab();
		List<WebElement> links = PageUtils.clickableLinks(driver);
		
		for (WebElement webElement : links) {
			String href = webElement.getAttribute("href");
			try {
				System.out.println("URL " + href + " returned " + PageUtils.linkStatus(new URL(href)));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		
	}
	@After
	public void tearDown() throws Exception {
	}


}
