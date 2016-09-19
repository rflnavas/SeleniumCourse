package cursoSelenium.pages;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cursoSelenium.utils.DriverType;

public abstract class ReportablePage extends AbstractPage{
	
	protected ExtentReports report;
	protected ExtentTest test;
	
	public ReportablePage(DriverType driverType, String url) {
		super(driverType, url);
	}

	public ExtentTest getTest() {
		return test;
	}

	public ExtentReports getReport() {
		return report;
	}
	
}
