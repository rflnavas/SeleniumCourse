package cursoSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InternetExplorerDriverTest {
	public static void main(String[] args) {
		WebDriver webdriver;
		String baseUrl = "http://www.google.es";
		// http://chromedriver.storage.googleapis.com/index.html
		System.setProperty("webdriver.ie.driver",
				"D:\\MisProgramas\\Desarrollos\\selenium-2.53.1\\libs\\IEDriverServer.exe");
		
		webdriver = new InternetExplorerDriver();
		webdriver.get(baseUrl);
	}
}
