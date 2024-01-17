package com.tafu.browserFactory;

import org.openqa.selenium.chrome.ChromeOptions;

import com.tafu.helper.OSUtility;

public class ChromeOptionsFactory{
	
    /***
     * Set Chrome Options
     * @return options
     */
    public static ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("disable-popup-blocking");
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        options.addArguments("--ignore-certificate-errors");
        if (OSUtility.isUnix()) {
        	options.addArguments("headless");
        }
        return options;
    }
}