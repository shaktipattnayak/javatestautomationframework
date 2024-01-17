package com.tafu.driverHelper;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.tafu.baseSetup.AppLogger;
import com.tafu.browserFactory.ChromeDriverFactory;
import com.tafu.browserFactory.FirefoxDriverFactory;
import com.tafu.helper.OSUtility;

public class WebDriverFactory {
	
	public static Logger log = AppLogger.getLogger(WebDriverFactory.class);

    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();

    /***
     * Get driver instance based on the browser type
     * @param browser
     * @return
     */
    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        setDriver(browser);
        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase(DriverConfig.FIREFOX)) {
                    log.info("Initializing Firefox Driver");
                    driver = new FirefoxDriverFactory().createWebDriver();
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase(DriverConfig.CHROME)) {
                	log.info("Initializing Chrome Driver");
                	driver = new ChromeDriverFactory().createWebDriver();
                    threadedDriver.set(driver);
                }
//                if (browser.equalsIgnoreCase(DriverConfig.IE)) {
//                    InternetExplorerOptions ieOptions = setIEOptions();
//                    driver = new InternetExplorerDriver(ieOptions);
//                    threadedDriver.set(driver);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    /***
     * Quit driver instance
     */
    public static void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

    /***
     * Set system path for driver files
     * @param browser
     */
    private void setDriver(String browser) {
        String driverPath = "";
        String os = DriverConfig.OS_NAME.toLowerCase().substring(0, 3);
        log.info("OS Name from system property :: " + os);
        String directory = DriverConfig.USER_DIRECTORY + OSUtility.getDriversDirectory();
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase(DriverConfig.FIREFOX)) {
            driverKey = DriverConfig.GECKO_DRIVER_KEY;
            driverValue = DriverConfig.GECKO_DRIVER_VALUE;
        } else if (browser.equalsIgnoreCase(DriverConfig.CHROME)) {
            driverKey = DriverConfig.CHROME_DRIVER_KEY;
            driverValue = DriverConfig.CHROME_DRIVER_VALUE;
        } else if (browser.equalsIgnoreCase(DriverConfig.IE)) {
            driverKey = DriverConfig.IE_DRIVER_KEY;
            driverValue = DriverConfig.IE_DRIVER_VALUE;
        } else {
            log.info("Browser type not supported");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        log.info("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }

}
