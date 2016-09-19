package cursoSelenium.listeners;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import cursoSelenium.testng.AbstractTest;
import cursoSelenium.utils.Screenshot;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener{
	
	private static Logger log = Logger.getLogger(TestListener.class);

	/**
	 * Belongs to ISuiteListener - It is executed once the suite test is finished
	 */
	@Override
	public void onFinish(ISuite testResult) {
		// TODO Auto-generated method stub
		log.info("onFinish suite - About to end execute suite " + testResult.getName());
	}
	
	/**
	 * Belongs to ISuiteListener - It is executed before the suite tests starts
	 */
	@Override
	public void onStart(ISuite testResult) {
		log.info("onStart suite - About to start to execute suite " + testResult.getName());
	}
	/**
	 * Belongs to ITestListener - It is executed once the set of tests is ended
	 */
	@Override
	public void onFinish(ITestContext testResult) {
		log.info("onFinish - About to end execute set of tests " + testResult.getName());
	}
	/**
	 * Belongs to ITestListener - It executes before starting set of tests
	 */
	@Override
	public void onStart(ITestContext testResult) {
		log.info("onStart suite - About to start to execute set of tests " + testResult.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult testResult) {
		String msg = testResult.getMethod() + " failed";
		log.error(msg);
		Reporter.log(msg, true);
		printTestResults(testResult);
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {
		printTestResults(testResult);
	}

	@Override
	public void onTestStart(ITestResult testResult) {
		String msg = String.format("Starting %s(%s)", 
				testResult.getMethod(), 
				Arrays.toString(testResult.getParameters()));
		log.info(msg);
		Reporter.log(msg, true);
		printTestResults(testResult);
	}

	/**
	 * Only when the test passes
	 */
	@Override
	public void onTestSuccess(ITestResult testResult) {
		String msg = String.format("Test successfully executed in %f", 
				(testResult.getEndMillis() - testResult.getStartMillis())/1_000.0);
		log.info(msg);
		Reporter.log(msg, true);
		
		WebDriver driver = ((AbstractTest)testResult.getInstance()).getDriver();
		Screenshot.takeScreenshot(driver, "Success");
	}
	
	private void printTestResults(ITestResult testResult){
		
		Reporter.log("Test Method resides in" + testResult.getTestClass().getName(), true);
		String status = "";
		switch(testResult.getStatus()){
			case ITestResult.SUCCESS:
				status = "OK";
				break;
			case ITestResult.FAILURE:
				status = "FAILED";
				break;
			case ITestResult.SKIP:
				status = "SKIPPED";
				break;
		}
		
		Reporter.log("Test status: " + status, true);
	}
	
	/**
	 * IInvokedMethodListener
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		String msg = String.format("After invoking %s", 
				getMethodName(method.getTestMethod()));
		Reporter.log(msg, true);
	}

	/**
	 * IInvokedMethodListener
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		String msg = String.format("About to begin to execute %s", 
				getMethodName(method.getTestMethod()));
		Reporter.log(msg, true);
	}
	
	private String getMethodName(ITestNGMethod ngMethod){
		return ngMethod.getRealClass().getSimpleName() + 
				"." + ngMethod.getMethodName();
	}

}
