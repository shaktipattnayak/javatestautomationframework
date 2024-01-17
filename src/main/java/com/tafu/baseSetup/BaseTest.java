package com.tafu.baseSetup;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.tafu.driverHelper.WebDriverFactory;
import com.tafu.genericLibrary.ExcelLibrary;
import com.tafu.helper.FileUtility;

public class BaseTest{
	public static Logger log = AppLogger.getLogger(BaseTest.class);
	
	public static ExcelLibrary xls;
	public static SetupHelper setuphelper = new SetupHelper();
	public static ExtentReports reports;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlreporter;
	public static WebDriver driver;

	@BeforeSuite
	public void beforeSuiteSetUp() throws IOException {
		setuphelper.executeInitialSetup();
		SetupHelper.clearFolder(SetupHelper.getScreenshotFolderPath());
		boolean isExcelFileExist = FileUtility.isFileExist(SetupHelper.getExcelFilePath());
		if (isExcelFileExist == true) {
			xls = new ExcelLibrary(SetupHelper.getExcelFilePath());
			log.info("Excel file object created");
		}else {
			log.info("Excel file object not created please place the excel file in" + 
								SetupHelper.getExcelFilePath() + " location");
		}
		htmlreporter = new ExtentHtmlReporter(new File(SetupHelper.getExtentReportPath()));
		reports = new ExtentReports();
		reports.setSystemInfo("Enironment", "STG");
		reports.attachReporter(htmlreporter);
	}

	@AfterSuite
	public void afterSuiteSetUp() throws Exception {
		SetupHelper.delayBy(2);
		reports.flush();
	}

	@BeforeTest
	@Parameters({"browser","URL"})
	public void TestSetup(String browser, String URL) {
		driver = WebDriverFactory.getInstance().getDriver(browser); 
		log.info("WebDriver initiated");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.navigate().to(URL);
		log.info("Application Launched");	
	}

	@AfterTest
	public void TestTeardown() {
		WebDriverFactory.quitDriver();
		log.info("Browser closed");
	}
	
	
	@BeforeMethod
	public void beforeMethodSetUp(Method method) {
		String testName = method.getName();
		test = reports.createTest(testName);
	}

	@AfterMethod
	public void afterMethodSetUp(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName() + " test is Passed.", ExtentColor.GREEN));
			log.info(result.getName() + " test is Passed.");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = SetupHelper.captureScreenShot(result.getName());
			test.fail(MarkupHelper.createLabel(result.getName() + " is failed.", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.addScreenCaptureFromPath(screenShotPath);
			log.info(result.getName() + " test is FAiled.");

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip((MarkupHelper.createLabel(result.getName() + " is Skipped.", ExtentColor.BLUE)));
			log.info(result.getName() + " is Skipped.");
		}
	}
}
