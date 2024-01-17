package com.tafu.baseSetup;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.tafu.helper.FileUtility;
import com.tafu.helper.OSUtility;

public class SetupHelper extends BaseTest {
	
	public static Logger log = AppLogger.getLogger(SetupHelper.class);
	
	public static String getUserDirectory() {
		return System.getProperty("user.dir");
	}

	public static String getExtentReportPath() {
		String extent_report_path = null;
		if (OSUtility.isMac())
			extent_report_path = getOutputFolderPath() + "/report.html";
		else if (OSUtility.isWindows())
			extent_report_path = getOutputFolderPath() + "\\report.html";
		else
			extent_report_path = getOutputFolderPath() + "/report.html";
		log.debug("Extent Report Path: " + extent_report_path);
		return extent_report_path;
	}

	public static String getOutputFolderPath() {
		String out_folder_path = null;
		if (OSUtility.isMac())
			out_folder_path = getUserDirectory() + "/OutputFiles";
		else if (OSUtility.isWindows())
			out_folder_path = getUserDirectory() + "\\OutputFiles";
		else
			out_folder_path = getUserDirectory() + "/OutputFiles";
		log.debug("Output folder path: " + out_folder_path);
		return out_folder_path;
	}
	
	public static String getScreenshotFolderPath() {
		String screenshot_folder_path = null;
		if (OSUtility.isMac())
			screenshot_folder_path = getOutputFolderPath() + "/Screenshots";
		else if (OSUtility.isWindows())
			screenshot_folder_path = getOutputFolderPath() + "\\Screenshots";
		else
			screenshot_folder_path = getOutputFolderPath() + "/Screenshots";
		log.info("Screenshot folder Path: " + screenshot_folder_path);
		return screenshot_folder_path;
	}
	
	public static String getSeleniumLogPath() {
		String selenium_log_path = null;
		if (OSUtility.isMac())
			selenium_log_path = getOutputFolderPath() + "/selenium.log";
		else if (OSUtility.isWindows())
			selenium_log_path = getOutputFolderPath() + "\\selenium.log";
		else
			selenium_log_path = getOutputFolderPath() + "/selenium.log";
		log.debug("Selenium Log Path: " + selenium_log_path);
		return selenium_log_path;
	}

	public static void clearFolder(String folderPath) {
		for (File file : new File(folderPath).listFiles())
			if (!file.isDirectory()) {
				file.delete();
				log.info("Failed Screenshots are deleted");
			}
	}
	
	public static String getResourcesFolderPath() {
		String test_resources_folder_path = null;
		if (OSUtility.isMac()) {
			test_resources_folder_path = getUserDirectory()+"/Resources";
    	}
    	else if(OSUtility.isWindows()) {
    		test_resources_folder_path = getUserDirectory()+"\\Resources";
    	}
    	else
    		test_resources_folder_path = getUserDirectory()+"/Resources";
		log.debug("Resources Folder Path: " + test_resources_folder_path);
    	return test_resources_folder_path;
    }
	
	public static String getTestDataFolderPath() {
		String test_data_folder_path = null;
		if (OSUtility.isMac())
			test_data_folder_path = getResourcesFolderPath() + "/TestData";
		else if (OSUtility.isWindows())
			test_data_folder_path = getResourcesFolderPath() + "\\TestData";
		else
			test_data_folder_path = getResourcesFolderPath() + "/TestData";
		log.debug("Test Data Folder Path: " + test_data_folder_path);
		return test_data_folder_path;
	}

	public static String getExcelFilePath() {
		String excel_file_path = null;
		if(OSUtility.isMac())
			excel_file_path = getTestDataFolderPath() + "/Test-Cases.xlsx";
		else if (OSUtility.isWindows())
			excel_file_path = getTestDataFolderPath() + "\\Test-Cases.xlsx";
		else
			excel_file_path = getTestDataFolderPath() + "/Test-Cases.xlsx";
		log.debug("Excel File Path: " + excel_file_path);
		return excel_file_path;
	}

	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		String datetime = dateFormat.format(date);
		return datetime;
	}
	
	public static void delayBy(int timeInSec) throws Exception {
		Thread.sleep(timeInSec * 1000);
	}

	public static String captureScreenShot(String ScreenShotName) {
		try {
			ScreenShotName = ScreenShotName + "_" + getCurrentDateTime();
			TakesScreenshot ts = (TakesScreenshot) driver;
			File Source = ts.getScreenshotAs(OutputType.FILE);
			log.info("Screenshot file source path:" + Source);
			String dest = getScreenshotFolderPath()+ ScreenShotName + ".png";
			log.info("Screenshot file destination path:" + dest);
			File destination = new File(dest);
			FileUtils.copyFile(Source, destination);
			log.info("Screenshot captured: " + ScreenShotName + ".png");
			return dest;
		} catch (Exception e) {
			System.out.println("Exception while capturing screen shot" + e);
			return e.getMessage();
		}
	}
	public static void main(String args[]) {
		System.out.println(getOutputFolderPath());
	}
	
	public void executeInitialSetup() throws IOException{
		FileUtility.createMultiLevelFolders(getOutputFolderPath());
		FileUtility.createMultiLevelFolders(getTestDataFolderPath());
		FileUtility.createMultiLevelFolders(getScreenshotFolderPath());
		FileUtility.createFile(getSeleniumLogPath());
		FileUtility.createFile(getExtentReportPath());
	}
}
