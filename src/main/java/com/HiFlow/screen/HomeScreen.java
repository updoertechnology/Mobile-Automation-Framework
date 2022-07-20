package com.HiFlow.screen;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mobile.Action.ActionPage;
import com.mobile.driver.DriverManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomeScreen extends ActionPage{
WebDriver driver = DriverManager.getWebDriver();	
	
	@AndroidFindBy(xpath  = "//[@text='Available transfers']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement homeScreen;
	
	@AndroidFindBy(xpath  = "//android.view.ViewGroup[@index='4']/android.widget.EditText[@index='1']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement searchTextField;
	
	public HomeScreen() {
    	Duration d = Duration.ofSeconds(10L);
    	PageFactory.initElements(new AppiumFieldDecorator(
                DriverManager.getWebDriver(),d), this);
	}
	
	public void verifyForHomeScreen() {
		isElementPresent(homeScreen);
	}
	
	public void searchForAnyTransferNumber(String searchText) {
		if(isElementPresent(searchTextField)) {
			sendKeys(searchTextField, "Search Field", searchText);
		}
	}
	
	public void clickEnterToSearch() {
		clickOnEnter(searchTextField, "Search Field");
	}
	
	

}
