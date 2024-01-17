package com.tafu.browserFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tafu.baseSetup.AppLogger;
import com.tafu.browserFactory.ChromeOptionsFactory;
import com.tafu.driverHelper.DriverConfig;
import com.tafu.helper.OSUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverFactory extends AWebDriverFactory implements IWebDriverFactory {
	public static Logger log = AppLogger.getLogger(ChromeDriverFactory.class);
	
	public static WebDriver createNativeDriver() {
//		WebDriverManager.getInstance(CHROME).setup();
		if (OSUtility.isUnix()) {
        	System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        }
		return new ChromeDriver(ChromeOptionsFactory.setChromeOptions());
	}
	
	/**
	 * This method create a ChromeDriver and return the driver object
	 */
	@Override
	public WebDriver createWebDriver() throws IOException {
		DriverConfig cfg = this.getDriverConfig();
		driver = createNativeDriver();
		setImplicitWaitTimeout(cfg.getImplicitWaitTimeout());
		if (cfg.getPageLoadTimeout() >= 0) {
			setPageLoadTimeout(cfg.getPageLoadTimeout());
		}
		this.setWebDriver(driver);
		return driver;
	}
	/**
	 * This method used to set the page load timeout
	 * @param timeout
	 */
	protected void setPageLoadTimeout(final long timeout) {
		try {
			driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		} catch (UnsupportedCommandException e) {
		}
	}
}
