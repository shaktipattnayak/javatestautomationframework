package com.tafu.driverHelper;

public class DriverConfig {
	public static final String BASE_URL = "";
	public static final String USER_DIRECTORY = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	public static final String IE = "ie";
	public static final String GECKO_DRIVER_KEY = "webdriver.gecko.driver";
	public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	public static final String IE_DRIVER_KEY = "webdriver.ie.driver";
	public static final String GECKO_DRIVER_VALUE = "geckodriver";
	public static final String CHROME_DRIVER_VALUE = "chromedriver";
	public static final String IE_DRIVER_VALUE = "IEDriverServer";
	public static final String DEFAULT_USERNAME = "test@email.com";
	public static final String DEFAULT_PASSWORD = "abcabc";
	public static String DRIVERS_DIRECTORY = "";
	private static final int DEFAULT_IMPLICIT_WAIT_TIMEOUT = 5;
    private double implicitWaitTimeout = DEFAULT_IMPLICIT_WAIT_TIMEOUT;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 60;
    private int pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
    private static final int DEFAULT_EXPLICIT_WAIT_TIME_OUT = 15;
    private int explicitWaitTimeout = DEFAULT_EXPLICIT_WAIT_TIME_OUT;

    public double getImplicitWaitTimeout() {
        return implicitWaitTimeout;
    }
    
    public void setImplicitWaitTimeout(final double implicitWaitTimeout) {
        this.implicitWaitTimeout = implicitWaitTimeout;
    }
    
    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }
    
    public void setPageLoadTimeout(final int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }
    
    public int getExplicitWaitTimeout() {
        if (explicitWaitTimeout < getImplicitWaitTimeout()) {
            return (int) getImplicitWaitTimeout();
        } else {
            return explicitWaitTimeout;
        }
    }
    
    public void setExplicitWaitTimeout(final int explicitWaitTimeout) {
        this.explicitWaitTimeout = explicitWaitTimeout;
    }
}
