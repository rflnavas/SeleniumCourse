package cursoSelenium.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * The Class PageUtils.
 */
public class PageUtils {

	/**
	 * Instantiates a new page utils.
	 */
	private PageUtils(){
		
	}

	
	/**
	 * Clickable links.
	 *
	 * @param driver the driver
	 * @return the list
	 */
	public static List<WebElement> clickableLinks(WebDriver driver){
		List<WebElement> links = driver.findElements(By.tagName("a"));
		List<WebElement> elements = new ArrayList<>();
		
		for(WebElement we : links){
			if(we.getAttribute("href") != null){
				elements.add(we);
			}
		}
		System.out.println(links.size() + " clickable links were found");
		return elements;
		
	}
	
	/**
	 * Link status.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String linkStatus(URL url){
		try {
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.connect();
			String responseMessage = http.getResponseMessage();
			http.disconnect();
			return responseMessage;
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Checks if is stale.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return true, if is stale
	 */
	public static boolean isStale(WebDriver driver, WebElement element) {
		return ExpectedConditions.stalenessOf(element).apply(driver);
	}
	
	/**
	 * Replace if stale by.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param locator the locator
	 * @return the web element
	 */
	public static WebElement replaceIfStaleBy(WebDriver driver, WebElement element, By locator){
		if(isStale(driver, element)){
			return driver.findElement(locator);
		}
		return element;
	}
	
	/**
	 * Clear input text fields.
	 *
	 * @param driver the driver
	 */
	public static void clearInputTextFields(WebDriver driver){
		List<WebElement> inputs = driver.findElements(By.cssSelector("input[type='text']"));
		for (WebElement input : inputs) {
			input.clear();
		}
	}
	
	/**
	 * Select.
	 *
	 * @param element the element
	 * @return the select
	 */
	public static Select select(WebElement element){
		return new Select(element);
	}
	
	/**
	 * Value.
	 *
	 * @param element the element
	 * @return the string
	 */
	public static String value(WebElement element){
		return element.getAttribute("value");
	}
}
