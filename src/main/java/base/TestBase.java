package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import report.ExtentTestManager;
import utilities.Constants;
import utilities.DriverFactory;
import utilities.TestDataCollector;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class TestBase {
	protected static WebDriver driver;
    public static Properties prop;
    protected static Map<String, String> testData;
	private String TestName;
	private static String browserType;
    protected static final Logger LOGGER = Logger.getLogger(TestBase.class);

    static {
	prop = readProperties("./src/test/resources/config.properties");
	testData = TestDataCollector.getinstance().getTestData();
    }

    public static Properties readProperties(String filePath) {

	try (FileInputStream testProperties = new FileInputStream(filePath)) {
	    Properties tempProp = new Properties();
	    tempProp.load(testProperties);
	    LOGGER.info("Properties file reading done: " + filePath);
	    return tempProp;
	} catch (IOException e) {
	    LOGGER.error("Properties file error: " + e.getMessage());
	}
	return null;
    }

    @BeforeClass
    public static void openBrowser() {
	browserType = prop.getProperty("browser.type");
	String url = prop.getProperty("web.url");
	driver = DriverFactory.getInstance().initializeDriver(browserType);
	LOGGER.info("Opening Browser [" + browserType + "] with url [" + url + "]");
	driver.manage().window().maximize();
	driver.get(prop.getProperty("web.url"));
    }

    @AfterClass
    public void closeBrowser() {
		try {
			LOGGER.info("Close Browser");
			driver.close();
			driver.quit();
			DriverFactory.getInstance().closeDriver(browserType);
			File report = new File(Constants.USER_DIR + File.separator + "report" + File.separator + getTestName() + ".html");
			if (report.exists())
				Desktop.getDesktop().open(report);
			} catch (IOException e) {
				LOGGER.error("Failed to open Report " + e.getMessage(), e);
			} catch (Exception ex) {
				LOGGER.error("Close browser " + ex.getMessage(), ex);
			}

		}
	public static WebDriver getDriver() {
		return driver;
	}

	public String getTestName() {
		return TestName;
	}

	public void setTestName(String testName) {
		TestName = testName;
	}
}
