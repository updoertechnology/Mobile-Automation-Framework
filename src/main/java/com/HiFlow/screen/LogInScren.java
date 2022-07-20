package com.HiFlow.screen;

import java.time.Duration; 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mobile.Action.ActionPage;
import com.mobile.driver.DriverManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LogInScren extends ActionPage {
WebDriver driver = DriverManager.getWebDriver();	
	
	@AndroidFindBy(xpath  = "//[@text='Log in to access your assignments']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement logInPage;
	
	@AndroidFindBy(xpath  = "//[@text='E-mail']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement emailTextField;
	
	@AndroidFindBy(xpath  = "//[@text='Password']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement passwordTextField;
	
	@AndroidFindBy(xpath  = "//[@text='Log in']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement loginButton;
	
	public LogInScren() {
    	Duration d = Duration.ofSeconds(10L);
    	PageFactory.initElements(new AppiumFieldDecorator(
                DriverManager.getWebDriver(),d), this);
	}
	
	public void verifyLoginScreen() {
		isElementPresent(logInPage);
	}

	public void enterEmailId(String email) {
		if(isElementPresent(emailTextField)) {
			sendKeys(emailTextField, "Email Id", email);
		}
	}
	
	public void enterPassword(String password) {
		if(isElementPresent(emailTextField)) {
			sendKeys(emailTextField, "Email Id", password);
		}
	}
	
	public void clickOnLogInButton() {
		clickAndroidDeviceBackBttn();
		if(isElementPresent(loginButton)) {
			click(loginButton, "LogIn");
		}
	}
}
