/*
 * 
 */
package cursoSelenium.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * The Enum DriverType.
 */
public enum DriverType {
	
	/** The firefox. */
	FIREFOX(1200){
		@Override
		public WebDriver getDriver() {
			FirefoxBinary binary = new FirefoxBinary(new File("D://Programas//firefox-45.3.0esr.win64.sdk//firefox.exe"));
			FirefoxProfile profile = new FirefoxProfile();
			return new FirefoxDriver(binary, profile);
		}
		
	}, 
	
	/** The chrome. */
	CHROME(1000){
		@Override
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver","D:\\Software\\chromedriver.exe");
			return new ChromeDriver();
		}
		
	},
	
	/** The ie. */
	IE(12000){
		@Override
		public WebDriver getDriver() {
			System.setProperty("webdriver.ie.driver","D:\\Software\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
	};
	
	/** The implicit wait. */
	private int implicitWait;
	
	/**
	 * Instantiates a new driver type.
	 *
	 * @param implicitWait the implicit wait
	 */
	private DriverType(int implicitWait) {
		this.implicitWait = implicitWait;
	}
	
	/**
	 * Implicit wait.
	 *
	 * @return the int
	 */
	public int implicitWait() {
		return implicitWait;
	}
	
	/**
	 * Sets the implicit wait.
	 *
	 * @param timeWait the new implicit wait
	 */
	public void setImplicitWait(int timeWait){
		this.implicitWait = timeWait;
	}
	
	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public abstract WebDriver getDriver();
}
