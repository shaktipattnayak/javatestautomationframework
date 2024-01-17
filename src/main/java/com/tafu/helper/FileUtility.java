package com.tafu.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tafu.baseSetup.AppLogger;
import com.tafu.baseSetup.SetupHelper;

public class FileUtility {

	public static Logger log = AppLogger.getLogger(FileUtility.class);

	public static String getLog4jFilePath() {
		String file_path = null;
		if (OSUtility.isMac()) {
			file_path = SetupHelper.getUserDirectory() + "/src/main/resources/configs/log4j.properties";
		} else if (OSUtility.isMac()) {
			file_path = SetupHelper.getUserDirectory() + "\\src\\main\\resources\\configs\\log4j.properties";
		} else {
			file_path = SetupHelper.getUserDirectory() + "/src/main/resources/configs/log4j.properties";
		}
		return file_path;
	}

	public static void WritePropertiesFile(String key, String data) throws IOException {
		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		File file = null;
		Properties prop = new Properties();
		file = new File(getLog4jFilePath());
		fileIn = new FileInputStream(file);
		prop.load(fileIn);
		prop.setProperty(key, data);
		fileOut = new FileOutputStream(file);
		prop.store(fileOut, "log file path");
	}

	public static String getSeleniumLogFilePath() {
		String file_path = null;
		if (OSUtility.isMac()) {
			file_path = SetupHelper.getOutputFolderPath() + "/selenium.log";
		} else if (OSUtility.isMac()) {
			file_path = SetupHelper.getOutputFolderPath() + "\\selenium.log";
		} else {
			file_path = SetupHelper.getOutputFolderPath() + "/selenium.log";
		}
		return file_path;
	}

	public static void createSingleFolder(String folderPath) {
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdir();
			log.info("Folder created at" + folderPath);
		} else {
			log.info("Folder already exists in " + folderPath);
		}
	}

	public static void createMultiLevelFolders(String foldersPath) {
		File folder = new File(foldersPath);
		if (!folder.exists()) {
			folder.mkdirs();
			log.info("Folders are created in: " + foldersPath);
		} else {
			log.info("Folders are already exists in: " + foldersPath);
		}
	}

	public static void createFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
			log.info("File created in: " + filePath);
		}
		log.info("File already exists in:" + filePath);
	}

	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		if (file.exists())
			return true;
		else
			return false;
	}
}
