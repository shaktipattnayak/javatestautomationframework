package com.tafu.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tafu.baseSetup.AppLogger;
import com.tafu.baseSetup.BaseTest;
import com.tafu.elements.ElementActions;

public class LoginPage extends BaseTest{
	public static Logger log = AppLogger.getLogger(LoginPage.class);
	
		public LoginPage() {
			PageFactory.initElements(driver, this);
			log.info("LoginPage object initialized");
		}
		
		@FindBy(xpath=" //a[@class='login']")
		private WebElement LoginLink;
		
		@FindBy(id="email")
		private WebElement Username;
		
		@FindBy(id="passwd")
		private WebElement Password;
		
		@FindBy(xpath="//p[@class='submit']//span[1]")
		private WebElement SigninButton;

		
		public void login(String user_name, String password) throws Exception {
			log.info("Performing login action");
			ElementActions.clickElement(LoginLink);
			ElementActions.EnterText(Username, user_name);
			ElementActions.EnterText(Password, password);
			ElementActions.clickElement(SigninButton);
		}
}