package com.android.HiFlow.testcase;

import org.openqa.selenium.remote.RemoteWebDriver; 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.HiFlow.screen.HomeScreen;
import com.HiFlow.screen.InstructionScreen;
import com.HiFlow.screen.LogInScren;
import com.HiFlow.screen.RideDeatilScreen;
import com.HiFlow.screen.SearchResultScreen;
import com.mobile.FrameWorkAssertion.FrameWorkSoftAssertions;
import com.mobile.Listeners.AnnotationTransformers;
import com.mobile.Listeners.ListenersClass;
import com.mobile.driver.DriverManager;
import com.mobile.utils.PropertyUtils;

@Listeners({ ListenersClass.class, AnnotationTransformers.class })
public class LoginTests extends BaseTest{
	FrameWorkSoftAssertions frameWorkSoftAssertions = new FrameWorkSoftAssertions();
	
	InstructionScreen instructionScreen = null;
	LogInScren loginScreen = null;
	HomeScreen homeScreen = null;
	SearchResultScreen searchResultScreen = null;
	RideDeatilScreen rideDeatilScreen = null;

	@BeforeMethod
	public void initialiseMethod() {
//		instructionScreen = new InstructionScreen();
//		loginScreen = new LogInScren();
//		homeScreen = new HomeScreen();
		if (PropertyUtils.getPropertyValue("mode").toUpperCase().equalsIgnoreCase("bs")) {
			RemoteWebDriver driver = (RemoteWebDriver) DriverManager.getWebDriver();
			System.out.println(
					"BrowserStack URL :: https://automate.browserstack.com/searchv2?q=" + driver.getSessionId());
		}
	}

	@Test(description = "Verify User Is Abel To Logged In Sucessfully")
	public void LogIn(){
		instructionScreen = new InstructionScreen();
		loginScreen = new LogInScren();
		homeScreen = new HomeScreen();
		searchResultScreen = new SearchResultScreen();
		rideDeatilScreen = new RideDeatilScreen();
		instructionScreen.verifyInstructionPage();
		instructionScreen.clickOnCloseButton();
		loginScreen.verifyLoginScreen();
		loginScreen.enterEmailId("demo@test.com");
		loginScreen.enterPassword("0987654321");
		loginScreen.clickOnLogInButton();
		homeScreen.verifyForHomeScreen();
		homeScreen.searchForAnyTransferNumber("398552");
		homeScreen.clickEnterToSearch();
		searchResultScreen.clickOnSearchedRide();
		rideDeatilScreen.verifyRideDetailScreen();
		
	}
	
}
