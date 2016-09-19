package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ElementStateTest {

	WebDriver webdriver;
	String url;
	
	@Before
	public void init(){
		this.url = "https://www.expedia.es/";
		this.webdriver = new FirefoxDriver();
		this.webdriver.manage().window().maximize();
		this.webdriver.get(this.url);
	}
	@Test
	public void test() {
		webdriver.get(url);
		System.out.println(String.format("Title : %s .Current page %s", webdriver.getTitle(), webdriver.getCurrentUrl()));
		
		WebElement lname = webdriver.findElement(By.name("lname"));
		System.out.println(lname.toString() + " is disabled " + !lname.isEnabled());
		System.out.println(lname.toString() + " is displayed " + !lname.isDisplayed());
		WebElement fname = webdriver.findElement(By.name("fname"));
		System.out.println(fname.toString() + " is disabled " + !fname.isEnabled());
		System.out.println(fname.toString() + " is displayed " + !fname.isEnabled());
		
		Navigation nav = webdriver.navigate();
		nav.to("http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
		
		WebElement comboCars = webdriver.findElement(By.tagName("select"));
		System.out.println("combo car " + comboCars.getAttribute("name") + " selected = " + comboCars.isSelected()); 
		
	}
	

	@After
	public void end(){
		this.webdriver.quit();
	}

}
