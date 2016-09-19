package cursoSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import cursoSelenium.utils.DriverType;

public abstract class BasicConfig {
	
	protected WebDriver driver;
	protected String url;
	
	public void init(DriverType driverType){
		init(driverType, null);
	}
	
	public void init(DriverType driverType, String url){
		this.url = url;
		this.driver = driverType.getDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		if(this.url != null && this.url.trim().length() != 0){
			this.driver.get(this.url);
		}
	}
	
	
}
