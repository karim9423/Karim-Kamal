package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public static synchronized ExtentReports createExtentReports(String reportName) {
	ExtentSparkReporter reporter = new ExtentSparkReporter("."+ File.separator+"report"+File.separator+reportName+".html");
	reporter.config().setReportName("Execution Report");
	extentReports.attachReporter(reporter);
	extentReports.setSystemInfo("Source", "Automation Framework");
	extentReports.setSystemInfo("Author", "Karim Kamal");
	return extentReports;
    }
}