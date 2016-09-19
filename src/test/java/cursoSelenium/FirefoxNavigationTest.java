package cursoSelenium;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxNavigationTest {
	
	WebDriver webdriver;
	String url;
	
	@Before
	public void init(){
		this.url = "http://demo.x-cart.com/demo/home.php";
		this.webdriver = new FirefoxDriver();
		this.webdriver.manage().window().maximize();
		this.webdriver.get(this.url);
	}
	
	@Test
	public void test() throws InterruptedException {
		//webdriver.findElement(By.partialLinkText("Contact")).click();
		webdriver.get(url);
		System.out.println(String.format("current page : %s Title %s", webdriver.getTitle(), webdriver.getCurrentUrl()));
		
		Navigation nav = webdriver.navigate();
		nav.to("http://www.facebook.com");
		System.out.println(String.format("current page : %s Title %s", webdriver.getTitle(), webdriver.getCurrentUrl()));
		WebElement email = webdriver.findElement(By.name("email"));
		email.sendKeys("rafanavas2005@gmail.com");
		Thread.sleep(1000);
		email.clear();
		Thread.sleep(500);
		
		nav.to("http://www.aemet.es/es/portada");
		System.out.println(String.format("current page : %s Title %s", webdriver.getTitle(), webdriver.getCurrentUrl()));
		Thread.sleep(600);
		nav.back();
		System.out.println(String.format("current page : %s Title %s", webdriver.getTitle(), webdriver.getCurrentUrl()));
		Thread.sleep(600);
		nav.refresh();
		System.out.println(String.format("current page : %s Title %s", webdriver.getTitle(), webdriver.getCurrentUrl()));
		nav.forward();
		System.out.println(String.format("current page : %s Title %s", webdriver.getTitle(), webdriver.getCurrentUrl()));
		
		List<WebElement> owlItems = webdriver.findElements(By.cssSelector(".owl-item>div>a"));
		
		for(WebElement oItem : owlItems){
			System.out.println(oItem.getAttribute("title"));
		}
		//System.out.println(webdriver.getPageSource()); 
		
	}
	
	@After
	public void end(){
		this.webdriver.quit();
	}

}
