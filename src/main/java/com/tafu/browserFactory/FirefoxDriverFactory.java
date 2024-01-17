package com.tafu.browserFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tafu.driverHelper.DriverConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverFactory extends AWebDriverFactory implements IWebDriverFactory {

	private long timeout = 60; 
	
	public static WebDriver createNativeDriver() {
//		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver(FirefoxOptionsFactory.setFirefoxOptions());
	}
	
	/**
	 * This method create a FirefoxDriver and return the driver object
	 */
	@Override
	public WebDriver createWebDriver() throws IOException {
		DriverConfig cfg = this.getDriverConfig();
		driver = createWebDriverWithTimeout();
		setImplicitWaitTimeout(cfg.getImplicitWaitTimeout());
		if (cfg.getPageLoadTimeout() >= 0) {
			setPageLoadTimeout(cfg.getPageLoadTimeout());
		}
		this.setWebDriver(driver);
		return driver;
	}
	
	protected WebDriver createWebDriverWithTimeout() {
        long time = 0;
        while (time < getTimeout()) {
            try {
                driver = createNativeDriver();
                return driver;
            } catch (WebDriverException ex) {
                if (ex.getMessage().contains("SocketException")
                        || ex.getMessage().contains("Failed to connect to binary FirefoxBinary")
                        || ex.getMessage().contains("Unable to bind to locking port 7054 within 45000 ms")) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) { }

                    time++;
                } else {
                    throw new RuntimeException(ex);
                }
            }
        }

        throw new RuntimeException("Got customexception when creating webDriver with socket timeout 1 minute");
    }
	
	protected long getTimeout() {
		return timeout;
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
