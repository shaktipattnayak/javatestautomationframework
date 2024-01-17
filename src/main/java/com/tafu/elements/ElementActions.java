package com.tafu.elements;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tafu.baseSetup.AppLogger;
import com.tafu.baseSetup.BaseTest;
import com.tafu.baseSetup.SetupHelper;
import com.tafu.driverHelper.WebDriverFactory;

public class ElementActions extends BaseTest{
	public static Logger log = AppLogger.getLogger(ElementActions.class);

	public static WebDriverWait wait = new WebDriverWait(driver, 15);

	public static void waitUntilVisible(WebElement element) {
		log.info("Wait until visibility of element: " + element);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilVisible(String element_Xpath) {
		log.info("Wait until visibility of element: " + element_Xpath);
		wait.until(ExpectedConditions.visibilityOf(getWebEelement(element_Xpath)));
	}

	public static void waitUntilClickable(WebElement element) {
		log.info("Wait until clickability of element: " + element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitUntilClickable(String element_Xpath) {
		log.info("Wait until clickability of element: " + element_Xpath);
		wait.until(ExpectedConditions.elementToBeClickable(getWebEelement(element_Xpath)));
	}

	public static void waitForAlertPresent() {
		log.info("Wait for alert present");
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilAttribute(WebElement element, String attribute, String value) {
		log.info("Wait until element:" + element + " with attribute: " + attribute + " and value: " + value + " is present.");
		wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
	}

	public static void waitForElementDisplayed_Custom(WebElement element) throws Exception {
		log.info("Wait until display of element: " + element);
		for (int i = 1; i <= 20; i++) {
			if (driver.findElements(getByType(element)).size() > 0) {
				break;
			} else {
				SetupHelper.delayBy(1);
			}
		}
	}

	public static void waitForElementDisplayed_Custom(String element_Xpath) throws Exception {
		log.info("Wait until display of element: " + element_Xpath);
		for (int i = 1; i <= 15; i++) {
			if (driver.findElements(By.xpath(element_Xpath)).size() > 0) {
				break;
			} else {
				SetupHelper.delayBy(1);
			}
		}
	}

	public static void waitForElementClickable_Custom(String element_Xpath) throws Exception {
		log.info("Wait until clickability of element: " + element_Xpath);
		for (int j = 1; j <= 20; j++) {
			try {
				getWebEelement(element_Xpath).isEnabled();
				break;
			} catch (Exception CustomClickable) {
				SetupHelper.delayBy(1);
			}
		}
	}

	public static void waitForElementClickable_Custom(WebElement element) throws Exception {
		log.info("Wait until clickability of element: " + element);
		for (int j = 1; j <= 20; j++) {
			try {
				element.isEnabled();
				break;
			} catch (Exception CustomClickable) {
				SetupHelper.delayBy(1);
			}
		}
	}

	public static void waitForElement(WebElement element) throws Exception {
		waitUntilVisible(element);
		waitForElementDisplayed_Custom(element);
	}

	public static void waitForElement(String element_Xpath) throws Exception {
		waitUntilVisible(element_Xpath);
		waitForElementDisplayed_Custom(element_Xpath);
	}

	public static By getByType(WebElement element) {
		String flag = element.toString().split(" -> ")[1];
		String mainFlag = "";
		for (int i = 0; i < flag.length() - 1; i++) {
			mainFlag = mainFlag + flag.charAt(i);
		}
		String[] data = mainFlag.split(": ");

		String locator = data[0];
		String term = data[1];

		switch (locator) {
		case "xpath":
			return By.xpath(term);
		case "css selector":
			return By.cssSelector(term);
		case "id":
			return By.id(term);
		case "tag name":
			return By.tagName(term);
		case "name":
			return By.name(term);
		case "link text":
			return By.linkText(term);
		case "class name":
			return By.className(term);
		}
		return (By) element;
	}

	// Is this element displayed or not? This method avoids the problem of having to
	// parse an element's "style" attribute.
	public static boolean isElementDisplayed(WebElement element) {
		try {
			log.info("Element is displayed: " + element);
			return element.isDisplayed();
		} catch (NoSuchElementException ex) {
			log.info("Element is not displayed: " + element);
			return false;
		}
	}

	public static boolean isElementDisplayed(String element_Xpath) {
		try {
			log.info("Element is displayed: " + element_Xpath);
			return getWebEelement(element_Xpath).isDisplayed();
		} catch (NoSuchElementException ex) {
			log.info("Element is not displayed: " + element_Xpath);
			return false;
		}
	}

	public static void pageUp() {
		log.info("Performing page up");
		new Actions(driver).sendKeys(Keys.PAGE_UP).perform();
	}

	public static void pageDown() {
		log.info("Performing page down");
		new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
	}

	public static void moveToElement(WebElement element) {
		log.info("Move to element: " + element);
		new Actions(driver).moveToElement(element).build().perform();
	}

	public static void moveToElement(String element_Xpath) {
		log.info("Move to element: " + element_Xpath);
		new Actions(driver).moveToElement(getWebEelement(element_Xpath)).build().perform();
	}

	public static void elementMouseClick(WebElement element) {
		log.info("Mouse click performed on element: " + element);
		new Actions(driver).click(element);
	}

	public static void elementMouseClick(String element_Xpath) {
		log.info("Mouse click performed on element: " + element_Xpath);
		new Actions(driver).click(getWebEelement(element_Xpath));
	}

	public static WebElement getWebEelement(String element_Xpath) {
		return driver.findElement(By.xpath(element_Xpath));
	}

	public static void pageRefresh() {
		log.info("Refreshing page");
		driver.navigate().refresh();
	}

	public static void clickElement(WebElement element) throws Exception {
		waitForElement(element);
		waitUntilClickable(element);
		waitForElementClickable_Custom(element);
		log.info("Mouse click performed on element: " + element);
		element.click();
	}

	public static boolean waitForJQueryProcessing() {
		boolean jQcondition = false;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
																			// implicitlyWait()
			new WebDriverWait(driver, 30) {
			}.until(new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driverObject) {
					return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");

				}
			});
			jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // reset
																				// implicitlyWait
			return jQcondition;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jQcondition;
	}

	public static void clickElement(String element_Xpath) throws Exception {
		waitForElement(element_Xpath);
		waitUntilClickable(element_Xpath);
		waitForElementClickable_Custom(element_Xpath);
		log.info("Mouse click performed on element: " + element_Xpath);
		getWebEelement(element_Xpath).click();
	}

	public static String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public static void EnterText(WebElement element, String text) throws Exception {
		waitForElement(element);
		element.clear();
		log.info("Text: " + text + " entered for element: " + element);
		element.sendKeys(text);
	}

	public static void EnterText(String element_Xpath, String text) throws Exception {
		waitForElement(element_Xpath);
		getWebEelement(element_Xpath).clear();
		log.info("Text: " + text + " entered for element: " + element_Xpath);
		getWebEelement(element_Xpath).sendKeys(text);
	}

	public static void selectByText(WebElement element, String text) throws Exception {
		waitForElement(element);
		new Select(element).selectByVisibleText(text);
	}

	public static void selectByText(String element_Xpath, String text) throws Exception {
		waitForElement(element_Xpath);
		log.info("Selecting: " + text + " from dropdown element: " + element_Xpath);
		new Select(getWebEelement(element_Xpath)).selectByVisibleText(text);
	}

	public static void selectByIndex(WebElement element, int index) throws Exception {
		waitForElement(element);
		log.info("Selecting: " + index + " from dropdown element: " + element);
		new Select(element).selectByIndex(index);
	}

	public static void selectByIndex(String element_Xpath, int index) throws Exception {
		waitForElement(element_Xpath);
		log.info("Selecting: " + index + " from dropdown element: " + element_Xpath);
		new Select(getWebEelement(element_Xpath)).selectByIndex(index);
	}

	public static void selectByValue(WebElement element, String value) throws Exception {
		waitForElement(element);
		log.info("Selecting: " + value + " from dropdown element: " + element);
		new Select(element).selectByValue(value);
	}

	public static void selectByValue(String element_Xpath, String value) throws Exception {
		waitForElement(element_Xpath);
		log.info("Selecting: " + value + " from dropdown element: " + element_Xpath);
		new Select(getWebEelement(element_Xpath)).selectByVisibleText(value);
	}
	
	public static String getAttribute(WebElement element, String attributeName) {
		log.info("Getting attribute: " + attributeName + " from element: " + element);
		return element.getAttribute(attributeName);
	}

	public static void acceptAlert() {
		waitForAlertPresent();
		log.info("Accept alert");
		driver.switchTo().alert().accept();
	}

	public static int getElementsSize(WebElement element) {
		log.info("Get size of element: " + element);
		return driver.findElements(getByType(element)).size();
	}

	public static int getElementsSize(String element_Xpath) {
		log.info("Get size of element: " + element_Xpath);
		return driver.findElements(By.xpath(element_Xpath)).size();
	}

	public static void scrollToElement(WebElement element) {
		log.info("Scrolling to element: " + element);
		Point p = element.getLocation();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("Window.ScrollBy(" + p.x + "," + p.y + ")", "");
	}

}
