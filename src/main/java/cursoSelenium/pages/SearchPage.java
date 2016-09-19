package cursoSelenium.pages;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import cursoSelenium.utils.Constants;
import cursoSelenium.utils.Constants.Visibility;
import cursoSelenium.utils.DriverType;
import cursoSelenium.utils.ExtentReportsFactory;
import cursoSelenium.utils.LogUtils;
import cursoSelenium.utils.PageUtils;
import cursoSelenium.utils.RandomGenerator;
import cursoSelenium.utils.WaitUtils;

public class SearchPage extends ReportablePage{
	
	private static Logger log = Logger.getLogger(SearchPage.class);
	
	@FindBy(id="header-history")
	private WebElement headerHistory;
	//IE tiene problemas para localizarlo a tiempo
	@FindBy(id="tab-flight-tab")
	private WebElement flightTab;
	
	@FindBy(id="flight-origin")
	private WebElement flightOrigin;
	
	@FindBy(id="flight-destination")
	private WebElement flightDestination;
	
	@FindBy(id="flight-departing")
	private WebElement departureDate;
	
	@FindBy(id="flight-returning")
	private WebElement returnDate;
	
	@FindBy(xpath="//a[@href='#advanced-options-fields']")
	private WebElement advancedOptions;
	
	// This field does not exists unless you first click advanced option
//	@FindBy(id="flight-advanced-preferred-class")
//	WebElement flightClass;
	
//	@FindBy(id="flight-children")
	//Cannot instantiate a select with generated proxy webelement
//	WebElement flightChildren;
	
	@FindBy(id="flight-add-hotel-checkbox")
	private WebElement addHotel;
	
	private Select selectFlight;
	
	@FindBy(id="search-button")
	private WebElement submitSearch;
	
//	@FindBy(id="flight-type-roundtrip-label")
//	WebElement roundTrip;
//	
//	@FindBy(id="flight-type-multi-dest-label")
//	WebElement multipleDestination;
		
	public SearchPage(DriverType driverType, String url) {
		super(driverType, url);
		this.report = ExtentReportsFactory.getInstance("searchFlightForm.html");
		this.test = report.startTest("Verify search flights");
		PageFactory.initElements(driver, this);
	}
	
	public SearchPage clickFlightTab(){
		//headerHistory.click();
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		this.flightTab = wait.until(
//		    ExpectedConditions.visibilityOfElementLocated(By.id("tab-flight-tab"))
//		);
		this.flightTab.click();
		return this;
	}
	
	public SearchPage clickAdvancedOptions(){
		advancedOptions.click();
		String msg = "Clicked on Advanced Options";
		log.info(msg);
		this.test.log(LogStatus.INFO, msg);
		return this;
	}
	
	
	
	public SearchPage submit(){
		submitSearch.click();
		log.info("Clicked on Submit");
		return this;
	}
	
	public SearchPage fillOriginCity(String origin){
		flightOrigin.sendKeys(origin);
		log.info(String.format("Line %d", LogUtils.getLineNumber()));
		log.info(String.format("Setting %s in %s", origin, flightOrigin.getAttribute("id")));
		this.test.log(LogStatus.INFO, "Filled origin city : " +
				PageUtils.value(this.getFlightOrigin()));
		
		return this;
	}
	
	public SearchPage fillDestinationCity(String dest){
		flightDestination.sendKeys(dest);
		log.info(String.format("Line %d", LogUtils.getLineNumber()));
		log.info(String.format("Setting %s in %s", dest, flightDestination.getAttribute("id")));
		this.test.log(LogStatus.INFO, "Filled destination city : " +
				PageUtils.value(this.getFlightDestination()));
		
		return this;
	}
	
	public SearchPage fillDepartingDate(String departingDate){
		departureDate.sendKeys(departingDate);
		log.info(String.format("Setting %s in %s", departingDate, this.departureDate.getAttribute("id")));
		
		return this;
	}
	
	public SearchPage fillReturningDate(String returningDate){
		returnDate.sendKeys(returningDate);
		log.info(String.format("Setting %s in %s", returningDate, this.returnDate.getAttribute("id")));
		return this;
	}
	
	public boolean isAdvancedOptionsEnabled(){
		List<WebElement> advanced = this.driver.findElements(By.id("advanced-options-fields"));
		return advanced.size() > 0;
	}
	
	public void toggleAdvancedOptions(Constants.Visibility vsb){
		boolean advOptOn = isAdvancedOptionsEnabled();
		if((!advOptOn && vsb == Visibility.SHOW)
				 || (advOptOn && vsb == Visibility.HIDDEN)){
			advancedOptions.click();
		}
	}
	
	public SearchPage selectFlightClass(String value){//"first"
		toggleAdvancedOptions(Visibility.SHOW);
		try {
			Thread.sleep(1600);
		} catch (InterruptedException e) {
		}
		WebElement element = this.driver.findElement(By.id("flight-advanced-preferred-class"));
		this.selectFlight = new Select(element);
		selectFlight.selectByValue(value);
		log.info(String.format("Setting %s in %s", value, element.getAttribute("id")));
		this.test.log(LogStatus.INFO, "Selected flight class : " + value);
//		if(isAdvancedOptionsEnabled()){
//			Select selectFlight = new Select(this.driver.findElement(By.id("flight-advanced-preferred-class")));
//			selectFlight.selectByValue(value);
//		}else{
//			log.error("Advanced options not enabled");
//			throw new IllegalStateException("Advanced options not enabled");
//		}
		return this;
	}
	
	public WebElement getFlightChildren(){
		return WaitUtils.fluentWait(this.driver, By.id("flight-children"));
	}
	
	public SearchPage selectNumberOfChildren(int value){
		WebElement flightChildren = getFlightChildren();
		Select selectChildren = new Select(flightChildren);
		selectChildren.selectByValue(String.valueOf(value));
		log.info(String.format("Setting number of children to %s", value));
		this.test.log(LogStatus.INFO, "Selected number of children " + value);
		return this;
	}
	
	public SearchPage setRandomAgesForChildren(int numberOfChildren){
		List<WebElement> comboAges = this.getDriver().findElements(
				By.cssSelector("select[id*=flight-age-select-]"));
		int[] randomAges = RandomGenerator.getInts(numberOfChildren,
				Constants.CHILDREN_MIN_AGE, Constants.CHILDREN_MAX_AGE);
		int i = 0;
		log.info("Selecting no of children with these ages : " + Arrays.toString(randomAges));
		for (WebElement comboAge : comboAges) {

			comboAge = PageUtils.replaceIfStaleBy(this.getDriver(), comboAge, 
					By.cssSelector("select[id*=flight-age-select-" + (i + 1) + "]"));
			Select select = new Select(comboAge);
			select.selectByValue(String.valueOf(randomAges[i++]));
		}
		return this;
	}
	
	public void toggleAddHotel(){
		
	}
//	public void clickRoundTrip(){
//		roundTrip.click();
//	}
//	
//	public void clickMultipleDestinationTab(){
//		multipleDestination.click();
//	}
	
	public SearchPage clickOnNonFlightStop(){
		WebElement nonStop = getNonFlightStop();
		nonStop.click();
		log.info("Non-stop flights clicked. Selected = " + nonStop.isSelected());
		return this;
	}
	
	public WebElement getNonFlightStop(){
		return WaitUtils.fluentWaitPresence(this.getDriver(), By.id("advanced-flight-nonstop"));
	}

	public WebElement getFlightOrigin() {
		return flightOrigin;
	}

	public WebElement getFlightDestination() {
		return flightDestination;
	}

	public WebElement getDepartureDate() {
		return departureDate;
	}

	public WebElement getReturnDate() {
		return returnDate;
	}
	
	public boolean isSearchError(){
		WebElement layoutError = null;
		try {
			layoutError = WaitUtils.fluentWaitPresence(this.getDriver(), By.id("flight-errors"));
		} catch (Exception e) {
			test.log(LogStatus.ERROR, Arrays.toString(e.getStackTrace()));
			e.printStackTrace();
		}
		return layoutError != null;
	}
}
