package cursoSelenium.upload;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.RandomGenerator;
import cursoSelenium.utils.WaitUtils;

public class FileUploadTest {
	
	
	WebDriver driver;
	String url;
	
	@BeforeSuite
	public void beforeSuite(){
		driver = DriverType.CHROME.getDriver();
		url = "https://accounts.google.com";
		driver.manage().window().setSize(new Dimension(1000, 650));
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	@Test
	public void fileUpload() throws InterruptedException {
		
		WebElement emailBox = driver.findElement(By.name("Email"));
		String email = "rafanavas2005@gmail.com";
		emailBox.sendKeys(email);
		driver.findElement(By.id("next")).click();
		
		WebElement password = driver.findElement(By.id("Passwd"));
		password.sendKeys("");
		driver.findElement(By.id("signIn")).click();
		
		//Si al logarnos en gmail nos salta la pantalla de configuración de cuenta
		Thread.sleep(500);
		//Boton de seleccion de aplicacion de google
		WebElement btnSelApp = WaitUtils.fluentWaitPresence(driver, By.xpath("//a[@class='gb_b gb_Vb']"));
		if(btnSelApp != null){
			btnSelApp.click();
		}
		WebElement btnGmail = WaitUtils.fluentWaitPresence(driver, By.xpath("(//li[@class='gb_Z']//a[@class='gb_O']//span[@class='gb_3'])[6]"));
		if(btnGmail != null){
			btnGmail.click();
		}
		
		WebElement btnRedactar = WaitUtils.fluentWaitPresence(driver, 
				By.xpath("//div[text()='REDACTAR']"));
		btnRedactar.click();
		
		WebElement destination = WaitUtils.fluentWaitPresence(driver, 
				By.xpath("//div[@class='wO nr l1']//textarea"));
		
		destination.sendKeys(email);
		
		WebElement subjectBox = driver.findElement(By.name("subjectbox"));
		
		subjectBox.sendKeys("Automated test " + RandomGenerator.getInt(0, 2_000_000));
		
		WebElement content = driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']"));
		content.sendKeys("No responder. Sólo con fines de prueba de automatización con Selenium");
		
		WebElement buttonAttach = driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']"));
		buttonAttach.click();
		
		Thread.sleep(500);
		
		StringSelection selection = new StringSelection("C:\\Test\\prueba.txt");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(700);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(1500);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		WebElement btnSend = driver.findElement(By.xpath("//div[text()='Enviar']"));
		btnSend.click();
	}

	@BeforeTest
	public void beforeTest() {
		
	}

}
