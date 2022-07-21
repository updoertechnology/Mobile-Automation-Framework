package com.screen;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mobile.Action.ActionPage;
import com.mobile.driver.DriverManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SearchResultScreen extends ActionPage{
WebDriver driver = DriverManager.getWebDriver();	
	
	@AndroidFindBy(xpath  = "//android.widget.TextView[@index=2]")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement rideOverview;
	
	public SearchResultScreen() {
    	Duration d = Duration.ofSeconds(10L);
    	PageFactory.initElements(new AppiumFieldDecorator(
                DriverManager.getWebDriver(),d), this);
	}
	
	public void clickOnSearchedRide() {
		if(isElementPresent(rideOverview)) {
			click(rideOverview, "Searched Result");
		}
		else {
			System.out.println("There is no such ride");
		}
	}

}
