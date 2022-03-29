package utilities;

import io.JsonParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverFactory {

    private static Map<String, WebDriver> drivers = new HashMap<>();
    private static DriverFactory factory = null;

    public DriverFactory() {

    }

    public WebDriver initializeDriver(String type) {
	return initializeBrowser(type);
    }

    public static DriverFactory getInstance() {
	if (factory == null)
	    factory = new DriverFactory();
	return factory;

    }

    /*
     * Factory method for getting browsers
     */
    public WebDriver initializeBrowser(String browser) {
		JSONObject cap;
	if (drivers.containsKey(browser))
	    return drivers.get(browser);

	if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
	    WebDriverManager.firefoxdriver().setup();
	    drivers.put(Constants.FIREFOX,
		    new FirefoxDriver(new FirefoxOptions()));
	} else if (browser.equalsIgnoreCase(Constants.IE)) {
	    WebDriverManager.iedriver().setup();
	    drivers.put(Constants.IE,
		    new InternetExplorerDriver(new InternetExplorerOptions()));
	}  else if (browser.equalsIgnoreCase(Constants.EDGE)) {
		cap = JsonParser.readJson(Constants.MECAPSPATH);
	    WebDriverManager.edgedriver().setup();
	    drivers.put(Constants.EDGE, new EdgeDriver(new EdgeOptions().merge(new DesiredCapabilities((Map<String, ?>) cap.get("desiredCapabilities")))));
	} else if (browser.equalsIgnoreCase(Constants.CHROME)) {
		cap = JsonParser.readJson(Constants.CHCAPSPATH);
	    WebDriverManager.chromedriver().setup();
	    drivers.put(Constants.CHROME,
		    new ChromeDriver(new ChromeOptions().merge(new DesiredCapabilities((Map<String, ?>) cap.get("desiredCapabilities"))).addArguments((List<String>) cap.get("chromeArguments"))));
	}
	return drivers.get(browser);
    }

	public void closeDriver(String browser)
	{
		drivers.remove(browser);
	}
    public static Map<String, WebDriver> getDrivers() {
	return drivers;
    }
}