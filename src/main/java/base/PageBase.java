package base;

import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import report.ExtentTestManager;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class PageBase {

    protected WebDriver driver;
    private WebDriverWait wait;
    private static final Logger LOGGER = Logger.getLogger(PageBase.class);

    public PageBase(WebDriver driver) {
	this(driver, 60);
    }

    public PageBase(WebDriver driver, long waitInSeconds) {
	this.driver = driver;
	this.wait = new WebDriverWait(driver, waitInSeconds);
    }

    // =================== Mouse/Keyboard actions ===========================

    public boolean WaitForElementToBeVisible(By elementLocator) {
	try {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
	    LOGGER.info("Element matching this locator [ " + elementLocator + " ] is visible");
		ExtentTestManager.getTest().log(Status.INFO,"Element matching this locator [ " + elementLocator + " ] is visible");
	    return true;
	} catch (Exception e) {
	    LOGGER.error("Element matching this locator [ " + elementLocator + " ] is not visible");
		ExtentTestManager.getTest().log(Status.FAIL,"Element matching this locator [ " + elementLocator + " ] is not visible");
	    return false;
	}
    }

    public boolean isElementDisplayed(By elementLocator) {
	    boolean isDisplayed= driver.findElement(elementLocator).isDisplayed();
		if (isDisplayed) {
			LOGGER.info("Element matching this locator [ " + elementLocator + " ] is visible");
			ExtentTestManager.getTest().log(Status.INFO,"Element matching this locator [ " + elementLocator + " ] is  visible");
			return true;
		}
		else{
			LOGGER.info("Element matching this locator [ " + elementLocator + " ] is Invisible");
			ExtentTestManager.getTest().log(Status.INFO,"Element matching this locator [ " + elementLocator + " ] is not visible");
			return false;
		}
    }

    public boolean isElementPresent(By elementLocator) {
	try {
	    wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
	    LOGGER.info("Element locator " + elementLocator + " is visible");
	    return true;
	} catch (Exception e) {
	    LOGGER.error("Element locator " + elementLocator + " is not visible");
	    return false;
	}
    }

    public boolean isElementClickable(By elementLocator) {
	try {
	    wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
	    LOGGER.info("Element matching this locator [" + elementLocator + "] is Clickable.");
	    return true;
	} catch (Exception e) {
	    LOGGER.error("Element matching this locator [" + elementLocator + "] is not Clickable.");
	    return false;
	}
    }

    public void setElement(By elementLocator, String value) {

	WaitForElementToBeVisible(elementLocator);
	try {
	    driver.findElement(elementLocator).clear();
	    driver.findElement(elementLocator).sendKeys(value);
	    LOGGER.info("Type " + value);
		ExtentTestManager.getTest().log(Status.INFO,"Type "+value);
	} catch (Exception e) {
	    LOGGER.error("Can't type :" + value);
		ExtentTestManager.getTest().log(Status.FAIL,"Can't type "+value);
	}

    }

    public void clearElement(By elementLocator) {

	WaitForElementToBeVisible(elementLocator);
	try {
	    WebElement element = driver.findElement(elementLocator);
	    element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	    driver.findElement(By.xpath("//body")).click();
	    LOGGER.info("Element locator is cleared ");
	} catch (Exception e) {
	    LOGGER.error("Can't clear element locator: " + elementLocator);
	}

    }

    public void selectItemFromList(By elementLocator, String value) {

	WaitForElementToBeVisible(elementLocator);
	try {
	    new Select(driver.findElement(elementLocator)).selectByVisibleText(value);
	    LOGGER.info("Select " + value + " :  from drop down list");
		ExtentTestManager.getTest().log(Status.INFO,"Select " + value + " :  from drop down list");
	} catch (Exception e) {
	    LOGGER.error("Can't select :" + value);
		ExtentTestManager.getTest().log(Status.FAIL,"Can't select :" + value);
	}
    }


    public String getElementText(By elementLocator) {
	String elementText;
	if (WaitForElementToBeVisible(elementLocator)) {
	    elementText = driver.findElement(elementLocator).getText();
	    LOGGER.info("Element locator [ " + elementLocator + " ] has text: " + elementText);
	    return elementText;
	} else {
	    LOGGER.error("Error to get text for element locator [ " + elementLocator + " ] ");
	    return "";
	}
    }

    public void setElementPassword(By elementLocator, byte[] value, String elementName) {

	boolean isDisplayed = WaitForElementToBeVisible(elementLocator);

	if (isDisplayed) {
	    WebElement element = driver.findElement(elementLocator);
	    element.clear();
	    element.sendKeys(new String(value, StandardCharsets.UTF_8));
	    LOGGER.info("Set " + elementName + ": ********");
		ExtentTestManager.getTest().log(Status.INFO,"Set " + elementName + ": ********");
	} else {
	    LOGGER.error("Can't Set Element: " + elementName);
		ExtentTestManager.getTest().log(Status.FAIL,"Can't Set Element: " + elementName);
	}
    }

    public void clickElement(By elementLocator) {

	if (isElementClickable(elementLocator)) {
		driver.findElement(elementLocator).click();
	    LOGGER.info("Click on element locator: " + elementLocator);
		ExtentTestManager.getTest().log(Status.INFO,"Click on element locator: " + elementLocator);
	} else {
	    LOGGER.error("Cannot Click on element locator: " + elementLocator);
		ExtentTestManager.getTest().log(Status.FAIL,"Cannot Click on element locator: " + elementLocator);

	}
    }

    public List<WebElement> getElements(By by) {
	return driver.findElements(by);
    }

}
