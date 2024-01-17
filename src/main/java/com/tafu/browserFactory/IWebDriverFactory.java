package com.tafu.browserFactory;

import org.openqa.selenium.WebDriver;

import com.tafu.driverHelper.DriverConfig;

public interface IWebDriverFactory {
	
	void cleanUp();
	
	WebDriver createWebDriver() throws Exception;
	
//	WebDriver getWebDriver();
	
	DriverConfig getDriverConfig();
}
