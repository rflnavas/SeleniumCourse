package cursoSelenium;

import org.openqa.selenium.WebDriver;

import cursoSelenium.utils.DriverType;

public class ProfilesDemo {

	public static void main(String[] args) {
		
		WebDriver driver = DriverType.FIREFOX.getDriver();
		String url = "http://www.google.es";
		
		driver.get(url);
		driver.manage().window().maximize();
		System.out.println("OO");
	}

}
