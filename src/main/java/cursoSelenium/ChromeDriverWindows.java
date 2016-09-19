package cursoSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverWindows {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver webdriver;
		String baseUrl = "http://www.google.es";
		// http://chromedriver.storage.googleapis.com/index.html
		System.setProperty("webdriver.chrome.driver",
				"D:\\MisProgramas\\Desarrollos\\selenium-2.53.1\\libs\\chromedriver.exe");
		
		webdriver = new ChromeDriver();
		webdriver.get(baseUrl);
		/*An example of xPath:
			.//*[@id='hlogo']
		*/
	}

}
