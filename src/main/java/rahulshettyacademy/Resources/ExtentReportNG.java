package rahulshettyacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	// Write all the extent report related data inside this class and create
	// index.html file.

	public static ExtentReports getReportData() {
		String file = System.getProperty("user.dir") + "\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(file);

		reporter.config().setDocumentTitle("Web Automation Test");

		reporter.config().setReportName("Test Result");

		// Main class.
		ExtentReports extent = new ExtentReports();

		extent.attachReporter(reporter);

		extent.setSystemInfo("Tester", "Shrikant Tembhe");

		return extent;
	}

}
