package com.tafu.browserFactory;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.tafu.baseSetup.AppLogger;
import com.tafu.driverHelper.DriverConfig;

public abstract class AWebDriverFactory implements IWebDriverFactory {
	
	public static Logger log = AppLogger.getLogger(AWebDriverFactory.class);

	protected WebDriver driver;

	public void cleanUp() {
		try {
			if (driver != null) {
				try {
					log.info("Quiting WebDriver");
					driver.quit();
				} catch (WebDriverException ex) {
					log.info("Exception encountered when quiting driver:" + ex.getMessage());
				}
				driver = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver createWebDriver() throws Exception {
		return null;
	}

//	@Override
//	public WebDriver getWebDriver() {
//		return driver;
//	}

	public void setWebDriver(final WebDriver driver) {
		this.driver = driver;
	}

	public void setImplicitWaitTimeout(final double timeout) {
		if (timeout < 1) {
			driver.manage().timeouts().implicitlyWait((long) (timeout * 100), TimeUnit.MILLISECONDS);
		} else {
			try {
				driver.manage().timeouts().implicitlyWait(new Double(timeout).intValue(), TimeUnit.SECONDS);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public DriverConfig getDriverConfig() {
		return new DriverConfig();
	}
}
