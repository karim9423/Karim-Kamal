package report.listeners;

import base.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import report.ExtentManager;
import report.ExtentTestManager;

import java.util.Objects;

public class TestListener extends TestBase implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
	return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
	LOGGER.info("I am in onStart method " + iTestContext.getName());
	iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
	LOGGER.info("I am in onFinish method " + iTestContext.getName());
	// Do tier down operations for ExtentReports reporting!
	ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
	LOGGER.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
	LOGGER.info(getTestMethodName(iTestResult) + " test is succeed.");
	// ExtentReports log operation for passed tests.
	ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
	LOGGER.info(getTestMethodName(iTestResult) + " test is failed.");
	// Get driver from BaseTest and assign to local webdriver variable.
	Object testClass = iTestResult.getInstance();
//	WebDriver driver = ((TestBase) )testClass.getDriver();
	// Take base64Screenshot screenshot for extent reports
        if (driver!=null) {
            String base64Screenshot = "data:image/png;base64,"
                    + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
            // ExtentReports log and screenshot operations for failed tests.
            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed", ExtentTestManager.getTest()
                    .addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
	LOGGER.info(getTestMethodName(iTestResult) + " test is skipped.");
	// ExtentReports log operation for skipped tests.
	ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	LOGGER.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
