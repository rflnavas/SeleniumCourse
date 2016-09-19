package cursoSelenium.utils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/**
 * The Class WaitUtils.
 */
public class WaitUtils {
	
	/** The Constant DEFAULT_TIME_OUT. */
	private static final int DEFAULT_TIME_OUT = 30;
	
	/**
	 * Fluent when visible.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @param timeout the timeout
	 * @return the web element
	 */
	public static WebElement fluentWhenVisible(WebDriver driver, By locator, Integer timeout){
		if(checkValueIfLessThan(timeout, 0)){
			throw new IllegalArgumentException();
		}
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.pollingEvery(5, TimeUnit.SECONDS)
				.withTimeout(defaultIfNull(timeout, DEFAULT_TIME_OUT), TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement webElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		return webElement;
	}
	
	/**
	 * Fluent when all visible.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @param timeout the timeout
	 * @return the list
	 */
	public static List<WebElement> fluentWhenAllVisible(WebDriver driver, By locator, Integer timeout){
		if(checkValueIfLessThan(timeout, 0)){
			throw new IllegalArgumentException();
		}
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.pollingEvery(defaultIfNull(timeout, DEFAULT_TIME_OUT), TimeUnit.SECONDS)
				.withTimeout(10, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		List<WebElement> webElement = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
		return webElement;
	}
	
	/**
	 * Gets the when text matches.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @param timeout the timeout
	 * @param pattern the pattern
	 * @return the when text matches
	 */
	public static boolean getWhenTextMatches(WebDriver driver, By locator, Integer timeout, Pattern pattern){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.textMatches(locator, pattern));
	}
	
	
	/**
	 * Click when ready.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @param timeout the timeout
	 */
	public static void clickWhenReady(WebDriver driver, By locator, Integer timeout){
		if(checkValueIfLessThan(timeout, 0)){
			throw new IllegalArgumentException();
		}
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	/**
	 * Element to be selected.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @param timeout the timeout
	 * @return true, if successful
	 */
	public static boolean elementToBeSelected(WebDriver driver, By locator, Integer timeout){
		if(checkValueIfLessThan(timeout, 0)){
			throw new IllegalArgumentException();
		}
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeSelected(locator));	
	}
	
	/**
	 * Fluent wait.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @return the web element
	 */
	public static WebElement fluentWait(WebDriver driver, final By locator){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return element;
	}
	
	/**
	 * Fluent wait presence.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @param timeout the timeout
	 * @return the web element
	 */
	public static WebElement fluentWaitPresence(WebDriver driver, final By locator, Integer timeout){
		if(checkValueIfLessThan(timeout, 0)){
			throw new IllegalArgumentException();
		}
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(defaultIfNull(timeout, DEFAULT_TIME_OUT), TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * Fluent wait presence.
	 *
	 * @param driver the driver
	 * @param locator the locator
	 * @return the web element
	 */
	public static WebElement fluentWaitPresence(WebDriver driver, final By locator){
		return fluentWaitPresence(driver, locator, null);
	}
	
	/**
	 * Fluent wait stale.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return true, if successful
	 */
	public static boolean fluentWaitStale(WebDriver driver, WebElement element){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(8, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				//, StaleElementReferenceException.class
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.stalenessOf(element));
	}
	
	/**
	 * Gets the when stale.
	 *
	 * @param driver the driver
	 * @param webElement the web element
	 * @param timeout the timeout
	 * @return the when stale
	 */
	public static boolean getWhenStale(WebDriver driver, final WebElement webElement, Integer timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.stalenessOf(webElement));
	}
	
	/**
	 * Less than.
	 *
	 * @param value1 the value 1
	 * @param value2 the value 2
	 * @return true, if successful
	 */
	public static boolean lessThan(Integer value1, Integer value2){
		return value1.compareTo(value2) < 0;
	}
	
	/**
	 * Default if null.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the integer
	 */
	public static Integer defaultIfNull(Integer value, Integer defaultValue){
		return value == null ? defaultValue : value;
	}
	
	/**
	 * Default if null.
	 *
	 * @param value the value
	 * @return the integer
	 */
	public static Integer defaultIfNull(Integer value){
		return defaultIfNull(value, DEFAULT_TIME_OUT);
	}
	
	/**
	 * Check value if less than.
	 *
	 * @param value the value
	 * @param compared the compared
	 * @param defaultValue the default value
	 * @return true, if successful
	 */
	private static boolean checkValueIfLessThan(Integer value, Integer compared, final Integer defaultValue){
		value = defaultIfNull(value, defaultValue);
		return value.compareTo(compared) < 0;		
	}
	
	/**
	 * Check value if less than.
	 *
	 * @param value the value
	 * @param compared the compared
	 * @return true, if successful
	 */
	private static boolean checkValueIfLessThan(Integer value, Integer compared){
		return checkValueIfLessThan(value, compared, DEFAULT_TIME_OUT);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		checkValueIfLessThan(null, 0);
	}
}
