package cursoSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxXCartDemo {
	public static void main(String[] args) {
		
		WebDriver webDriver = new FirefoxDriver();
		
		String baseUrl = "http://demo.x-cart.com/demo/home.php";
		webDriver.manage().window().maximize();
		webDriver.get(baseUrl);
		
		//webDriver.findElement(By.xpath(".//*[@id='content-container']/div[1]/ul/li[2]/a")).click();
		//OR
		//Does not work
		//webDriver.findElement(By.linkText("Shopping Cart")).click();
		webDriver.findElement(By.partialLinkText("Contact")).click();
		//webDriver.findElement(By.className(".button.main-button")).click();
		System.out.println(webDriver.getPageSource()); 
	}
}
