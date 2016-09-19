package cursoSelenium.utils;

import java.nio.file.Paths;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * A factory for creating ExtentReports objects.
 */
public final class ExtentReportsFactory {

	static {
		FilesHelper.createFolderIfNotExists(Constants.REPORT_PATH);
	}

	/**
	 * Instantiates a new extent reports factory.
	 */
	private ExtentReportsFactory() {
	}

	/**
	 * Gets the single instance of ExtentReportsFactory.
	 *
	 * @param reportName
	 *            the report name
	 * @return single instance of ExtentReportsFactory
	 */
	public static ExtentReports getInstance(final String reportName) {

		ExtentReports extReports = new ExtentReports(Paths.get(
				Constants.REPORT_PATH.toString(), reportName).toString(), false); // We
																					// do
																					// not
																					// want
																					// the
																					// report
																					// be
																					// overwritten.
		extReports.addSystemInfo("Java", "7");
		return extReports;
	}
}
