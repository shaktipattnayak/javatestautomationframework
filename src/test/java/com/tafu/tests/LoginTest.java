package com.tafu.tests;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tafu.baseSetup.AppLogger;
import com.tafu.baseSetup.BaseTest;
import com.tafu.pageObjects.LoginPage;
import com.tafu.utilities.DataUtil;


public class LoginTest extends BaseTest{
	public static Logger log = AppLogger.getLogger(LoginTest.class);
	
	
	@DataProvider
	public Object[][] LoginTestData()
	{
		return DataUtil.getData("Login","LoginData", xls);
	}
	
	@Test(enabled=true,dataProvider="LoginTestData")
	public void Login(Hashtable<String,String>data) throws Exception
	{
		LoginPage lp = new LoginPage();
		lp.login(data.get("Username"),data.get("Password"));
		log.info("Performed login to application");
	}
}
