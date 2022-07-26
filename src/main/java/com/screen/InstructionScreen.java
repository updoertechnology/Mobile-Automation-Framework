package com.screen;

import java.time.Duration; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mobile.Action.ActionPage;
import com.mobile.driver.DriverManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class InstructionScreen extends ActionPage{
WebDriver driver = DriverManager.getWebDriver();	
	
	@AndroidFindBy(xpath  = "//[@text='Présentation de l'app']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement constructionPage;

	@AndroidFindBy(xpath  = "//[@text='Fermer']")
	@iOSXCUITFindBy(accessibility = "")
	MobileElement closeButton;
	
	public InstructionScreen() {
    	Duration d = Duration.ofSeconds(10L);
    	PageFactory.initElements(new AppiumFieldDecorator(
                DriverManager.getWebDriver(),d), this);
	}
	
	public void verifyInstructionPage(){
		isElementPresent(constructionPage);
	}
	
	public void clickOnCloseButton() {
		if (isElementPresent(closeButton)) {
			click(closeButton, "Fermer");
		}
	}
}
