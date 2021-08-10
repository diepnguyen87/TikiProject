package vn.tiki.common;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import vn.tiki.config.Configuration;

public class AbstractTest {

	private WebDriver driver;
	protected final Log log;
	public static Configuration conf;

	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	public void startEnvironment(String environmentName) {
		ConfigFactory.setProperty("environment", environmentName);
		conf = ConfigFactory.create(Configuration.class);
	}

	public WebDriver getBrowserDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// ChromeOptions chromeProfile = new ChromeOptions();
			// String chromeProfilePath = "C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data";
			// chromeProfile.addArguments("user-data-dir=" + chromeProfilePath);
			/*
			 * ChromeOptions options = new ChromeOptions(); options.addArguments("--disable-web-security", "--allow-running-insecure-content");
			 * 
			 * ChromeOptions options = new ChromeOptions(); options.addArguments("start-maximized"); options.setExperimentalOption("useAutomationExtension",
			 * false); options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			 * options.addArguments("--disable-blink-features=AutomationControlled");
			 */

			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("chrome-headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("firefox-headless")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		return driver;
	}
}
