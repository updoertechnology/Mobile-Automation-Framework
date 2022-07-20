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

public class RideDeatilScreen extends ActionPage {
WebDriver driver = DriverManager.getWebDriver();	
	
	@AndroidFindBy(xpath  = "//android.view.ViewGroup[@index=3]")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement rideDetailScreen;
	
	public RideDeatilScreen() {
    	Duration d = Duration.ofSeconds(10L);
    	PageFactory.initElements(new AppiumFieldDecorator(
                DriverManager.getWebDriver(),d), this);
	}
	
	public void verifyRideDetailScreen() {
		isElementPresent(rideDetailScreen);
	}


}
