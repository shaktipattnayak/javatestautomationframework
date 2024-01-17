package com.tafu.browserFactory;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.tafu.driverHelper.DriverConfig;

public interface ICapabilitiesFactory {
	
	DesiredCapabilities createCapabilities(DriverConfig cfg);

}
