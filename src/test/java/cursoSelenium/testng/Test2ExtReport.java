package cursoSelenium.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cursoSelenium.utils.ExtentReportsFactory;

public class Test2ExtReport {

	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void beforeClass() {
		this.report = ExtentReportsFactory.getInstance("searchFlightForm.html");
		this.test = report.startTest("Test2ExtReport --> Verification");
		this.test.log(LogStatus.INFO, "Browser started ...");
	}

	@Test
	public void f() {
		String clazz = this.getClass().getName();
		this.test.log(LogStatus.INFO, clazz + " start");
		this.test.log(LogStatus.INFO, clazz + " continuing");
		this.test.log(LogStatus.INFO, clazz + " end");
	}

	@AfterTest
	public void afterTest() {
		report.endTest(test);
		report.flush();
	}

}
