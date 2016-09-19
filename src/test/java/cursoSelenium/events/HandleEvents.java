package cursoSelenium.events;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class HandleEvents implements WebDriverEventListener {
	
	private static Logger log = Logger.getLogger(HandleEvents.class);
	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		log.debug("After changing value of " + element.toString() + " - " + element.getAttribute("value"));
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("After clicking on " + element.toString());
	}

	@Override
	public void afterFindBy(By locator, WebElement element, WebDriver driver) {
		System.out.println("After finding by " + locator.toString());
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Before changing value of " + element.toString() + " - " + element.getAttribute("value"));
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Before clicking on " + element.toString());
	}

	@Override
	public void beforeFindBy(By locator, WebElement element, WebDriver driver) {
		System.out.println("Before finding by " + locator.toString());
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
	}

}
