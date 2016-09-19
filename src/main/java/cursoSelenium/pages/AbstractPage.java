package cursoSelenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import cursoSelenium.utils.DriverType;

public abstract class AbstractPage {
	protected WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}
	
	protected AbstractPage(DriverType driverType, String url){
		this.driver = driverType.getDriver();
		this.driver.manage().window().maximize();
		/**
		 * Specifies the amount of time the driver should wait when searching
		 * for an element if it is not immediately present.
		 * 
		 * When searching for a single element, the driver should poll the page
		 * until the element has been found, or this timeout expires before
		 * throwing a NoSuchElementException. When searching for multiple
		 * elements, the driver should poll the page until at least one element
		 * has been found or this timeout has expired.
		 */
		this.driver.manage().timeouts().implicitlyWait(driverType.implicitWait(), TimeUnit.MILLISECONDS);
		if(url != null && url.trim().length() != 0){
			this.driver.get(url);
		}
	}
	
}
