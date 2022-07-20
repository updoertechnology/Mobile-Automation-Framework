package com.mobile.Action;

import com.mobile.driver.DriverManager; 
import com.mobile.enums.LogType;
import com.mobile.reports.FrameWorkLogger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.geolocation.SupportsExtendedGeolocationCommands;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ActionPage {
	
	  private static Logger log = LogManager.getLogger();
	    private String parentHandle;
	    private Alert alert;
	    private WebDriverWait wait;
	    private AppiumDriver<?> driver;
	    
	protected void clickAndroidDeviceBackBttn() {
		driver.navigate().back();
	}
	protected void chooseItemAndClick(String name, List<MobileElement> elements) {
        elements.parallelStream()
                .filter(mobileElement -> mobileElement.getText().contains(name))
                .findFirst()
                .ifPresent(WebElement::click);
    }
	
	protected void clickOnEnter(MobileElement mobileElement, String elementName){
		mobileElement.sendKeys(Keys.ENTER);
    	FrameWorkLogger.log(LogType.INFO, elementName + "havaing locator as "+ mobileElement +" is clicked successfully");
        log.info(elementName + "is clicked successfully");
	}

    protected void click(MobileElement mobileElement, String elementName) {
    	mobileElement.click();
    	FrameWorkLogger.log(LogType.INFO, elementName + "havaing locator as "+ mobileElement +" is clicked successfully");
        log.info(elementName + "is clicked successfully");
    	
    }
    
    protected void clearTextField(MobileElement mobileElement,String elementName) {
    	mobileElement.clear();
    	FrameWorkLogger.log(LogType.INFO, "Clearing pre filled value from "+elementName+ " textfield");
    	log.info(elementName + "field is cleared successfully");	
    }
    
    protected void sendKeys(MobileElement mobileElement,  String textFieldName, String value) {
    	mobileElement.sendKeys(value);
    	FrameWorkLogger.log(LogType.INFO, "Entering "+value+ " in "+ textFieldName+ " textfield");
    }

    protected void click(By by, String elementName) {
        click((MobileElement) DriverManager.getWebDriver().findElement(by), elementName);
        System.out.println(elementName + "is clicked successfully");
    }

    protected void click(String locatorType, String value, String elementName) {
        if (locatorType.equalsIgnoreCase("xpath")) {
            click(By.xpath(value), elementName);
        }
        System.out.println(elementName + "is clicked successfully");
    }

    protected void scrollToSpecificElementAndClick(By by ) {
        while (DriverManager.getWebDriver().findElements(by).isEmpty()) {
            Dimension dimension = DriverManager.getWebDriver().manage().window().getSize();
            double screenHeightStart = dimension.getHeight() * 0.5;
            int scrollStart = (int) screenHeightStart;
            double screenHeightEnd = dimension.getHeight() * 0.2;
            int scrollEnd = (int) screenHeightEnd;
            int center = (int) (dimension.width * 0.5);
            new AndroidTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                    .press(PointOption.point(center, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(center, scrollEnd)).release().perform();
            if (!DriverManager.getWebDriver().findElements(by).isEmpty()) {
                DriverManager.getWebDriver().findElement(by).click();
            }
        }
    }

    protected void dragAndDrop(AndroidElement source, AndroidElement target) {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(ElementOption.element(target))
                .release()
                .perform();
    }

    protected void swipe(AndroidElement source, AndroidElement target){
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)))
                .moveTo(ElementOption.element(target))
                .release()
                .perform();
    }

    protected void longPress(AndroidElement androidElement){
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(androidElement)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
                .perform();
    }

    protected void tap(MobileElement androidElement){
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getWebDriver())
                .tap(TapOptions.tapOptions().withElement(ElementOption.element(androidElement)))
                .perform();
    }

    protected List<String> getDesiredValue(List<WebElement> elements, Function<WebElement,String> function){
        return elements.stream()
                .map(function)
                .collect(Collectors.toList());
    }
    
    public Boolean isElementPresent(MobileElement element) {
		// waitForVisibility(element);
		// return element.isDisplayed();
		Boolean status = false;
		try {
			if (element.isDisplayed()) {

				status = true;
			}
		} catch (Exception e) {

			status = false;
		}

		return status;
	}
    
    public void swipeToDownsideTillElementPresent(WebDriver driver, MobileElement el, int maxswipe) throws InterruptedException {
		for(int i=1; i<=maxswipe;i++) {
			swipeToSeeDownsideElementsOfScreen(driver, 1);
			Thread.sleep(500);
			if(isElementPresent(el)) {
				break;
			}
		}
		System.out.println("Element Not Present After maximum number of swiping");
	}
	
	public void swipeToUpsideTillElementPresent(WebDriver driver, MobileElement el, int maxswipe) throws InterruptedException {
		for(int i=1; i<=maxswipe;i++) {
			swipeToSeeUpsideElementsOfScreen(driver, 1);
			Thread.sleep(500);
			if(isElementPresent(el)) {
				break;
			}
		}
		System.out.println("Element Not Present After maximum number of swiping");
	}
	
	public void swipeToSeeUpsideElementsOfScreen(WebDriver driver, int howManySwipes) {
		Dimension size = driver.manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startVerticalY = (int) (size.height * 0.8);
		int endVerticalY = (int) (size.height * 0.21);
		int startVerticalX = (int) (size.width / 2.1);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction((PerformsTouchActions) driver).press(point(startVerticalX, endVerticalY))
						.waitAction(waitOptions(java.time.Duration.ofSeconds(2)))
						.moveTo(point(startVerticalX, startVerticalY)).release().perform();
				FrameWorkLogger.log(LogType.INFO,"Swiped successfully  to see upside element of the screen");
			}
		} catch (Exception e) {
			// print error or something
		}
	}
	
	public void swipeToSeeDownsideElementsOfScreen(WebDriver driver, int howManySwipes) {
		Dimension size = driver.manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startVerticalY = (int) (size.height * 0.8);
		int endVerticalY = (int) (size.height * 0.21);
		int startVerticalX = (int) (size.width / 2.1);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction((PerformsTouchActions) driver).press(point(startVerticalX, startVerticalY))
						.waitAction(waitOptions(java.time.Duration.ofSeconds(2)))
						.moveTo(point(startVerticalX, endVerticalY)).release().perform();
				FrameWorkLogger.log(LogType.INFO,"Swiped successfully  to see downside element of the screen");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public WebElement wait_until_MobileElementIs_Visible(WebDriver driver, MobileElement locator) {
        FrameWorkLogger.log(LogType.INFO,"15 secs - Waiting for element using -" + locator);
       return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(locator));
    }
    
 public boolean wait_until_MobileElementsIs_Visible(WebDriver driver,List<WebElement> elements){
    	
    	boolean elementvisibile = true;
    	for (int i=0; i<elements.size(); i++) {
    		elementvisibile= new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.visibilityOf(elements.get(i))) != null;
    		if (!elementvisibile) {
    			return false;
    		}

    	}
        return elementvisibile;
      }

    public List<WebElement> wait_until_MobileElementIs_Visible(List<WebElement> elements){
      return new WebDriverWait(DriverManager.getWebDriver(),15)
               .until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    
    // ******* EXPLICIT WAITS ON SINGLE ELEMENT ******************//
    // ************************************************************

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS CLICKABLE - DISPLAYED AND ENABLED
    public WebElement wait_until_MobileElementIs_Clickable(WebDriver driver, By locator) {
        log.info("15 secs - Waiting for element using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public WebElement wait_until_MobileElementIs_Visible(WebDriver driver, By locator) {
        log.info("15 secs - Waiting for element using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public WebElement wait_until_MobileElementIs_Present(WebDriver driver, By locator) {
        log.info("15 secs - Waiting for element using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ************* EXPLICIT WAITS ON MULTIPLE ELEMENTS ***************//
    // ************************************************************

    // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS PRESENT
    public List<WebElement> wait_until_MobileElementsAre_Present(WebDriver driver, By locator) {
        log.info("5 secs - Waiting for elements using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS VISIBLE
    public List<WebElement> wait_until_MobileElementsAre_Visible(WebDriver driver, By locator) {
        log.info("5 secs - Waiting for elements using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> wait_until_MobileElementsAre_Visible(
            WebDriver driver, By locator, int timeInSeconds) {
        log.info("5 secs - Waiting for elements using -" + locator);
        wait = new WebDriverWait(driver, timeInSeconds);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // ******** EXPLICIT WAITS ON PAGE TITLE,URL AND ELEMENT_NOT_PRESENT ************//

    public boolean is_MobileElement_NotPresent(WebDriver driver, By locator) {
        log.info("5 secs - checking for element presence using -" + locator);
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // **** RETURNING MOBILE ELEMENT ANDROID *****//
    // ************************************************************

    public WebElement android_returnMobileElementPresentUsingText(WebDriver driver, String text) {
        log.info("Trying to find element with text - " + text);
        return wait_until_MobileElementIs_Visible(
                driver, MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    public WebElement android_returnMobileElementPresentUsingID(WebDriver driver, String id) {
        log.info("Trying to find element with ID - " + id);
        return wait_until_MobileElementIs_Visible(
                driver, MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")"));
    }

    public WebElement android_returnMobileElementPresentUsingContentDesc(WebDriver driver,String contentDesc) {
        log.info("Trying to locate element using content-desc - " + contentDesc);
        return wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator("new UiSelector().description(\"" + contentDesc + "\")"));
    }

    public WebElement android_returnMobileElementPresentUsingXPath(WebDriver driver,String xpath) {
        log.info("Trying to locate element using x-path - " + xpath);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.xpath(xpath));
    }

    public WebElement android_returnMobileElementPresentUsingClassName(WebDriver driver, String className) {
        log.info("Trying to locate elements using className - " + className);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.className(className));
    }

    // **** RETURNING MOBILE ELEMENTS ANDROID *****//
    // ************************************************************

    public List<WebElement> android_returnMobileElementsPresentUsingXPath(WebDriver driver, String xpath) {
        log.info("Trying to locate elements using x-path - " + xpath);
        return wait_until_MobileElementsAre_Visible(driver, MobileBy.xpath(xpath));
    }

    public List<WebElement> android_returnMobileElementsPresentUsingClassName(WebDriver driver, String className) {
        log.info("Trying to locate elements using className - " + className);
        return wait_until_MobileElementsAre_Visible(driver, MobileBy.className(className));
    }

    // open notifications Android
    // ************************************************************

    public void android_openNotifications(WebDriver driver) {
        log.info("Opening notifications page");
        ((AndroidDriver) driver).openNotifications();
    }

    // rotate screen
    // ************************************************************

    public void rotateScreenPotrait(WebDriver driver) {
        log.info("rotating screen -  PORTRAIT mode");
        ((AppiumDriver) driver).rotate(ScreenOrientation.PORTRAIT);
    }

    public void rotateScreenlandscape(WebDriver driver) {
        log.info("rotating screen -  LANDSCAPE mode");
        ((AppiumDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
    }

    // clear notifications
    // ************************************************************

    public void android_clearNotifications(WebDriver driver) {

        // google pixel related
        if (android_isMobileElementPresentUsingText(driver, "CLEAR ALL")) {
            wait_until_MobileElementIs_Clickable(
                    driver, MobileBy.AndroidUIAutomator("new UiSelector().text(\"CLEAR ALL\")"))
                    .click();
            log.info("Existing notifications Cleared");

        } else {
            log.info("Could not find clear existing notifications option in the notifications page");
        }

        // TODO update clear notifications present
    }

    // Set location Android
    // ************************************************************

    public void android_SetLocation(WebDriver driver, double latitude, double longitude) {
        log.info("Trying to mock location --> Lat:" + latitude + " Long: " + longitude);
        Location loc = new Location(latitude, longitude, 10); // latitude, longitude, altitude
        ((AppiumDriver) driver).setLocation(loc);
        log.info("Location set to --> Latitude: " + latitude + " Longitude: " + longitude);
    }

    // **** CONTEXT SWITCHING *****//
    // ************************************************************

    public Set<String> switchToWebViewAndReturnAllContextHandles(WebDriver driver) {
        log.info("Trying to switch to webview");
        Set<String> availableContexts = ((AppiumDriver) driver).getContextHandles();
        log.info("No of Contexts Found  = " + availableContexts.size());
        for (String context : availableContexts) {
            log.info("Context - " + context);
            if (context.matches(".*?WEBVIEW.*?")) {
                log.info("Switching to context " + context);
                ((AppiumDriver) driver).context(context);
                log.info("Context switched to -" + ((LogManager) driver).getContext());
                break;
            }
        }
        return availableContexts;
    }

    public void switchToNativeContext(WebDriver driver, Set<String> availableContexts) {
        for (String context : availableContexts) {
            if (context.contains("NATIVE")) {
                log.info("Trying to switch to native context");
                ((AppiumDriver) driver).context(context);
                log.info("Switched to Context" + context);
            }
        }
    }

    // **** SCROLL FUNCTIONS (SCROLL'S ON ENTIRE PAGE) *****//
    // ************************************************************

    public MobileElement android_ScrollToText(WebDriver driver, String text) {
        log.info("Trying to scroll to element with text  - " + text);

        return (MobileElement)
                wait_until_MobileElementIs_Visible(
                        driver,
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public MobileElement android_ScrollToContentDesc(WebDriver driver, String contentDesc) {
        log.info("Trying to scroll to element with content desc - " + contentDesc);
        return (MobileElement)
                wait_until_MobileElementIs_Visible(
                        driver,
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()).scrollIntoView(description(\""
                                        + contentDesc
                                        + "\"));"));
    }

    public MobileElement android_scrollToID(WebDriver driver, String id) {
        log.info("Trying to scroll to android element with ID - " + id);

        return (MobileElement)
                wait_until_MobileElementIs_Visible(
                        driver,
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\""
                                        + id
                                        + "\"));"));
    }

    // **** SCROLL FUNCTIONS (SCROLL'S INSIDE PARTICULAR ELEMENT) *****//
    // ************************************************************
    // Other Supported Functions available here -
    // https://developer.android.com/reference/android/support/test/uiautomator/UiSelector

    public MobileElement android_scrollToTextInsideElementWithResourceID(WebDriver driver,
            String resourceID, String text) {
        log.info("Trying to scroll to android element with text - " + text);
        // make sure u give the resouce ID of the complete list of elements here as parameter

        return (MobileElement)
                wait_until_MobileElementIs_Visible(
                        driver,
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()"
                                        + ".resourceId(\""
                                        + resourceID
                                        + "\")).scrollIntoView("
                                        + "new UiSelector().text(\""
                                        + text
                                        + "\"));"));
    }

    public MobileElement android_scrollToTextInsideElementWithContentDesc(WebDriver driver,
            String contentDesc, String text) {
        log.info("Trying to scroll to android element with contentDesc - " + contentDesc);
        // make sure u give the resouce ID of the complete list of elements here as parameter

        return (MobileElement)
                wait_until_MobileElementIs_Visible(
                        driver,
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()"
                                        + ".description(\""
                                        + contentDesc
                                        + "\")).scrollIntoView("
                                        + "new UiSelector().text(\""
                                        + text
                                        + "\"));"));
    }

    public MobileElement android_scrollToTextInsideElementWithID(WebDriver driver, String id, String text) {
        log.info("Trying to scroll to android element with ID - " + id);
        // make sure u give the resouce ID of the complete list of elements here as parameter

        return (MobileElement)
                wait_until_MobileElementIs_Visible(
                        driver,
                        MobileBy.AndroidUIAutomator(
                                "new UiScrollable(new UiSelector()"
                                        + ".resourceId(\""
                                        + id
                                        + "\")).scrollIntoView("
                                        + "new UiSelector().text(\""
                                        + text
                                        + "\"));"));
    }

    // **** ANDROID KEY EVENT FUNCTIONS *****//
    // ************************************************************

    public void android_HomeKeyEvent(WebDriver driver) {
        log.info("android Home event");
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.HOME));
        sleep(2);
    }

    public void android_BackKeyEvent(WebDriver driver) {
        log.info("android back event");
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.BACK));
        sleep(2);
    }

    public void android_Enter(WebDriver driver) {
        log.info("android Enter event");
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.ENTER));
        sleep(2);
    }

    public void android_Tab(WebDriver driver) {
        log.info("android Tab event");
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.TAB));
        sleep(2);
    }

    public void android_MultiTaskingKeyEvent(WebDriver driver) {
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.APP_SWITCH));
        sleep(2);
    }

    // ********** CHECK FOR PRESENCE OF MOBILE ELEMENT ANDROID **************//
    // ************************************************************

    public boolean android_isMobileElementPresentUsingText(WebDriver driver, String text) {

        try {
            log.info("Trying to find element with text - " + text);
            if (wait_until_MobileElementsAre_Visible(
                    driver,
                    (MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")")),
                    5)
                    .size()
                    >= 1) {
                log.info("element found");
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return true;
    }

    public boolean android_isMobileElementPresentUsingID(WebDriver driver,String id) {
        log.info("Trying to find element with ID - " + id);

        try {
            if (wait_until_MobileElementsAre_Visible(
                    driver,
                    (MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")")),
                    5)
                    .size()
                    >= 1) {
                log.info("Found element having id -" + id);
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return true;
    }

    public boolean android_isMobileElementPresentUsingContentDesc(WebDriver driver, String contentDesc) {
        try {
            log.info("Trying to locate element using content-desc - " + contentDesc);
            if (wait_until_MobileElementsAre_Visible(
                    driver,
                    (MobileBy.AndroidUIAutomator(
                            "new UiSelector().description(\"" + contentDesc + "\")")),
                    5)
                    .size()
                    >= 1) {
                log.info("Found element having content desc - " + contentDesc);
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return true;
    }

    public boolean android_isMobileElementPresentUsingXpath(WebDriver driver, String xPath) {
        try {
            log.info("Trying to locate element using content-desc - " + xPath);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), 5).size() >= 1) {
                log.info("Found element having content desc - " + xPath);
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    // ********** SCROLL AND CHECK FOR PRESENCE OF MOBILE ELEMENT ANDROID **************//
    // ************************************************************

    public boolean android_isMobileElementPresentUsingText_Scroll(WebDriver driver, String text) {
        log.info("Trying to find element with text - " + text);
        if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));")))
                .size()
                >= 1) {
            log.info("element found");
            return true;
        } else {
            log.info("element not found");
            return false;
        }
    }

    public boolean android_isMobileElementPresentUsingID_Scroll(WebDriver driver, String id) {
        log.info("Trying to find element with ID - " + id);
        return wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\""
                                + id
                                + "\"));")))
                .size()
                >= 1;
    }

    public boolean android_isMobileElementPresentUsingContentDesc_Scroll(WebDriver driver, String contentDesc) {
        log.info("Trying to locate element using content-desc - " + contentDesc);
        if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(description(\""
                                + contentDesc
                                + "\"));")))
                .size()
                >= 1) {
            log.info("Found element having content desc -" + contentDesc);
            return true;
        } else return false;
    }

    // ********** TOUCH ACTIONS **************//
    // ************************************************************

    public void pressAndHold(WebDriver driver, WebElement element1, int timeInSeconds) {
        Rectangle rect1 = element1.getRect();

        TouchAction touch = new TouchAction((PerformsTouchActions) driver);
        touch
                .longPress(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
                .waitAction(waitOptions(Duration.ofSeconds(timeInSeconds)))
                .release()
                .perform();
        log.info("Press and Hold Action performed");
    }

    public void pressElement(WebDriver driver, MobileElement element, long seconds) {
        new TouchAction((PerformsTouchActions) driver)
                .press(element(element))
                .waitAction(waitOptions(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    public void tap(WebDriver driver, WebElement element) {
        new TouchAction((PerformsTouchActions) driver).tap(TapOptions.tapOptions().withElement(element(element))).perform();
    }

    public void tap(WebDriver driver, WebElement element, int milliseconds) {
        new TouchAction((PerformsTouchActions) driver)
                .tap(TapOptions.tapOptions().withElement(element(element)))
                .waitAction(waitOptions(Duration.ofMillis(milliseconds)))
                .perform();
    }

    public void tapByCoordinates(WebDriver driver, int x, int y) {
        new TouchAction((PerformsTouchActions) driver)
                .tap(PointOption.point(x, y))
                .waitAction(waitOptions(Duration.ofMillis(250)))
                .perform();
    }

    public void basicSwipe(WebDriver driver, WebElement element1, WebElement element2) {
        Rectangle rect1 = element1.getRect();
        Rectangle rect2 = element2.getRect();

        TouchAction touch = new TouchAction((PerformsTouchActions) driver);
        touch
                .press(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight()))
                .moveTo(
                        PointOption.point(
                                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight()))
                .release()
                .perform();
        log.info("Swipe Action performed");
    }

    public void dragDrop(WebDriver driver, WebElement element1, WebElement element2) {
        Rectangle rect1 = element1.getRect();
        Rectangle rect2 = element2.getRect();

        TouchAction touch = new TouchAction((PerformsTouchActions) driver);
        touch
                .longPress(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
                .moveTo(
                        PointOption.point(
                                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight() / 2))
                .release()
                .perform();
        log.info("Drag and drop Action performed");
    }

    // ******************************     IOS FUNCTIONS      ******************************* //
    // ************************************************************

    // ***** RETURN MOBILE ELEMENT ****** //

    public WebElement ios_returnMobileElementUsingAccessibilityId(WebDriver driver, String accessibilityId) {
        log.info("Trying to find element with id - " + accessibilityId);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.AccessibilityId(accessibilityId));
    }

    public WebElement ios_returnMobileElementUsingName(WebDriver driver, String name) {
        log.info("Trying to find element with name - " + name);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.name(name));
    }

    public WebElement ios_returnMobileElementUsingClassName(WebDriver driver, String className) {
        log.info("Trying to find element with class name - " + className);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.className(className));
    }

    public WebElement ios_returnMobileElementUsingXpath(WebDriver driver, String xPath) {
        log.info("Trying to find element with xPath - " + xPath);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.xpath(xPath));
    }

    public WebElement ios_returnMobileElementUsingPredicateString(WebDriver driver, String predicateString) {
        log.info("Trying to find element with predicateString - " + predicateString);
        return wait_until_MobileElementIs_Visible(
                driver, MobileBy.iOSNsPredicateString(predicateString));
    }

    public WebElement ios_returnMobileElementUsingClassChain(WebDriver driver, String classChain) {
        log.info("Trying to find element with classChain - " + classChain);
        return wait_until_MobileElementIs_Visible(driver, MobileBy.iOSClassChain(classChain));
    }

    // ***** PRESENCE OF MOBILE ELEMENT ****** //

    public boolean ios_isElementPresentUsingAccessibilityId(WebDriver driver, String acessibilityId) {

        try {
            log.info("Trying to find element with AcessibilityId - " + acessibilityId);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.AccessibilityId(acessibilityId)), 5)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingName(WebDriver driver, String name) {

        try {
            log.info("Trying to find element with name - " + name);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.name(name)), 5).size() >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingXpath(WebDriver driver, String xPath) {

        try {
            log.info("Trying to find element with xPath - " + xPath);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), 5).size() >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingPredicateString(WebDriver driver, String predicateString) {

        try {
            log.info("Trying to find element with predicateString - " + predicateString);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.iOSNsPredicateString(predicateString)), 5)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingClassChain(WebDriver driver, String classChain) {

        try {
            log.info("Trying to find element with classChain - " + classChain);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.iOSClassChain(classChain)), 5)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    // ***** PRESENCE OF MOBILE ELEMENT - WAIT UNTIL TIME SPECIFIED ****** //

    public boolean ios_isElementPresentUsingAccessibilityId(WebDriver driver,
            String acessibilityId, int timeinSeconds) {

        try {
            log.info("Trying to find element with AcessibilityId - " + acessibilityId);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.AccessibilityId(acessibilityId)), timeinSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingName(WebDriver driver, String name, int timeinSeconds) {

        try {
            log.info("Trying to find element with name - " + name);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.name(name)), timeinSeconds).size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingXpath(WebDriver driver, String xPath, int timeinSeconds) {

        try {
            log.info("Trying to find element with xPath - " + xPath);
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), timeinSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingPredicateString(WebDriver driver,
            String predicateString, int timeinSeconds) {

        try {
            log.info("Trying to find element with predicateString - " + predicateString);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.iOSNsPredicateString(predicateString)), timeinSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    public boolean ios_isElementPresentUsingClassChain(WebDriver driver, String classChain, int timeinSeconds) {

        try {
            log.info("Trying to find element with classChain - " + classChain);
            if (wait_until_MobileElementsAre_Visible(
                    driver, (MobileBy.iOSClassChain(classChain)), timeinSeconds)
                    .size()
                    >= 1) {
                log.info("element found");
                return true;
            }
        } catch (Exception e) {
            log.info("element not found");
            return false;
        }
        return false;
    }

    // ***** IOS SCROLL FUNCTIONS ****** //

    public void ios_scroll(WebDriver driver, int starty, int endy) {
        log.info("Trying to scroll down on screen from " + starty + " to " + endy);
        Dimension size = driver.manage().window().getSize();
        int x = size.getWidth() / 2;
        int start_y = (int) (size.getHeight() * starty / 100);
        int end_y = (int) (size.getHeight() * endy / 100);
        new TouchAction((PerformsTouchActions) driver)
                .longPress(PointOption.point(x, start_y))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
        log.info("scroll done");
    }

    public void ios_scrollUntilLabelIsFound(WebDriver driver, String label, int starty, int endy, int maxScrollTimes) {
        int retry = 0;
        while (!ios_isElementPresentUsingPredicateString(driver, "label =='" + label + "'", 1)) {
            if (retry > maxScrollTimes) {
                break;
            }
            ios_scroll(driver, starty, endy);
            retry++;
        }
    }

    public void ios_scrollDown_UsingParentElementToLabel(WebDriver driver, RemoteWebElement parent, String label) {

        log.info("Trying to scroll down in a container to element with label -" + label);
        String parentID = parent.getId();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, String> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", parentID);
        params.put("predicateString", "label == " + label + "");
        js.executeScript("mobile: scroll", params);
        log.info("scrolled to element with label -" + label);
    }

    public void ios_scrollUp_UsingParentElementToLabel(WebDriver driver, RemoteWebElement parent, String label) {
        log.info("Trying to scroll down to element with label -" + label);
        String parentID = parent.getId();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap params = new HashMap();
        params.put("direction", "up");
        params.put("element", parentID);
        params.put("predicateString", "label == '" + label + "'");
        js.executeScript("mobile: scroll", params);
        log.info("scrolled to element with label -" + label);
    }

    public boolean ios_swipeToDirection(WebDriver driver,MobileElement el, String direction) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> swipeObject = new HashMap<String, String>();
            if (direction.equals("down")) {
                swipeObject.put("direction", "down");
            } else if (direction.equals("up")) {
                swipeObject.put("direction", "up");
            } else if (direction.equals("left")) {
                swipeObject.put("direction", "left");
            } else if (direction.equals("right")) {
                swipeObject.put("direction", "right");
            }
            swipeObject.put("element", el.getId());
            js.executeScript("mobile:swipe", swipeObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Swiping element

    public void ios_swipeElementToTheLeft(WebDriver driver, WebElement el) {

        log.info("Trying to swipe element to the left");
        int x = el.getLocation().getX();
        int y = el.getLocation().getY();
        // elements x location is the beginning point of the element , multiplying with 4 to get to the
        // middle part of the element
        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(x * 4, y))
                .waitAction(waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x, y))
                .release()
                .perform();
        log.info("Element swipe to the left performed");
    }

    public void ios_swipeElementToTheRight(WebDriver driver,WebElement el) {

        log.info("Trying to swipe element to the left");
        int x = el.getLocation().getX();
        int y = el.getLocation().getY();
        // elements x location is the beginning point of the element , multiplying with 4 to get to the
        // middle part of the element
        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(x, y))
                .waitAction(waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x * 4, y))
                .release()
                .perform();
        log.info("Element flicked to the left");
    }

    // ********* MULTI TOUCH ACTIONS ***************************//

    public void zoom_Using_MultiTouchActions(WebDriver driver) {

        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        int halfWidth = width / 2;
        int halfHeight = height / 2;

        MultiTouchAction multiTouch = new MultiTouchAction((PerformsTouchActions) driver);
        TouchAction touch1 = new TouchAction((PerformsTouchActions) driver);
        TouchAction touch2 = new TouchAction((PerformsTouchActions) driver);

        touch1
                .press(PointOption.point(halfHeight, halfHeight))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, 60))
                .release();
        touch2
                .press(PointOption.point(halfHeight, halfHeight + 40))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, 80))
                .release();

        multiTouch.add(touch1).add(touch2);
        multiTouch.perform();
    }

    // ****************************  SELENIUM FUNCTIONS *****************************//

    // ******* EXPLICIT WAITS ON SINGLE ELEMENT ******************//

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS CLICKABLE - DISPLAYED AND ENABLED
    public WebElement wait_until_ElementIs_Clickable(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public WebElement wait_until_ElementIs_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public WebElement wait_until_ElementIs_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL SELENIUM FINDS 2 WINDOWS
    public void wait_until_Two_Windows_Are_Available(WebDriver driver) {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    // ************* EXPLICIT WAITS ON MULTIPLE ELEMENTS ***************//

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public List<WebElement> wait_until_ElementsAre_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public List<WebElement> wait_until_ElementsAre_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // ******** EXPLICIT WAITS ON PAGE TITLE,URL AND ELEMENT_NOT_PRESENT ************//

    public boolean wait_until_TitleContains(WebDriver driver, String keyword) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.titleContains(keyword));
    }

    public boolean wait_until_URL_Matches(WebDriver driver, String regex) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlMatches(regex));
    }

    public boolean IS_Element_NotPresent(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ************** NORMAL CLICK *******************//

    public void click(WebDriver driver, By locator) {
        wait_until_ElementIs_Present(driver, locator).click();
        waitForPageToLoad(driver);
    }

    // ******************** ACTIONS ***********************//

    // Hover over an element
    public void hover(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(wait_until_ElementIs_Visible(driver, locator)).build().perform();
        log.info("Hover action done on element -" + locator);
        waitForPageToLoad(driver);
    }

    // Hover over an element and click
    public void hoverAndClick(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(wait_until_ElementIs_Visible(driver, locator)).click().build().perform();
        log.info("click action on -" + locator + " performed");
        waitForPageToLoad(driver);
    }

    public void hoverAndClear(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        action.moveToElement(el).click().build().perform();
        el.clear();
        log.info("click and clear actions on -" + el + " performed");
        waitForPageToLoad(driver);
    }

    // Hover over an element, click and press enter
    public void hoverClickAndPressEnter(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Hover over an element click and send data
    public void hoverClickAndSendData(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(data)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Hover over an element click, send data and press enter
    public void hoverClickSendDataAndPressEnter(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(data)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // sendkeys
    public void hoverAndSendData(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .sendKeys(data)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Double click
    public void doubleClick(WebDriver driver, By locator) {
        Actions doubleClick = new Actions(driver);
        doubleClick.doubleClick(wait_until_ElementIs_Visible(driver, locator)).build().perform();
        waitForPageToLoad(driver);
    }

    // Drag and Drop by offset
    public void dragAndDropOffset(WebDriver driver, By locator, int offsetX, int offsetY) {
        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(el, offsetX, offsetY).build().perform();
        waitForPageToLoad(driver);
    }

    // Drag and drop Elements
    public void dragAndDropToElementContainner(
            WebDriver driver, WebElement source, WebElement target) {
        Actions builder = new Actions(driver);
        builder.dragAndDrop(source, target).build().perform();
        waitForPageToLoad(driver);
    }

    // *********** JAVA SCRIPT CLICK **********************************//

    public void jsClick(WebDriver driver, By locator) {
        String code =
                "var fireOnThis = arguments[0];"
                        + "var evObj = document.createEvent('MouseEvents');"
                        + "evObj.initEvent( 'click', true, true );"
                        + "fireOnThis.dispatchEvent(evObj);";

        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        ((JavascriptExecutor) driver).executeScript(code, el);
        waitForPageToLoad(driver);
    }

    public void jsFocusAndClick(WebDriver driver, By locator) {
        WebElement element = wait_until_ElementIs_Present(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForPageToLoad(driver);
    }

    public void jsFocusAndClick(WebDriver driver, WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        waitForPageToLoad(driver);
    }

    // ************ PAGE LOAD STATE ****************************//

    // Get Page State
    public String getPageState(WebDriver driver) {
        WebElement el = driver.findElement(By.cssSelector("body"));
        String code = "return document.readyState";
        String result = (String) ((JavascriptExecutor) driver).executeScript(code, el);
        log.info("PageState-" + result);
        return result;
    }

    // Wait For Page to Load Completely
    public void waitForPageToLoad(WebDriver driver) {
        while (!getPageState(driver).equals("complete")) {
            sleep(1);
        }
    }

    // Wait for Page title to change
    public void waitForPageTitleToChange(WebDriver driver, String title) {
        while (driver.getTitle().equalsIgnoreCase(title)) {
            sleep(1);
        }
        while (driver.getTitle().matches(".*?" + title + ".*?")) {
            sleep(1);
        }
    }

    // ******************** WINDOW HANDLES ******************************//

    public int getWindowHandlesSize(WebDriver driver) {
        log.info("Window Handles -" + driver.getWindowHandles().size());
        return driver.getWindowHandles().size();
    }

    public void switchToNewWindowHandle_After_ClickingOnGivenElement(WebDriver driver, By locator) {

        parentHandle = driver.getWindowHandle();
        log.info("Parent handle -" + parentHandle);
        wait_until_ElementIs_Clickable(driver, locator).click();
        waitForPageToLoad(driver);

        if (driver.getWindowHandles().size()
                >= 2) { // switch to a new window handle if there more than 1 window handles.
            // Switch to new window opened
            for (String winHandle : driver.getWindowHandles()) {
                log.info("Window Handle -" + winHandle);
                if (!winHandle.equals(parentHandle)) {
                    driver.switchTo().window(winHandle);
                    log.info("Switching to Child Window handle");
                    break;
                }
                driver.manage().window().maximize();
            }
        }
    }

    public void switchToNewWindowHandle(WebDriver driver, String parentHandle) {

        int windowHandles = driver.getWindowHandles().size();

        if (windowHandles >= 2) { // switch to a new window handle if there more than 1 window handles.
            for (String winHandle : driver.getWindowHandles()) {
                log.info("Window Handle -" + winHandle);
                if (!winHandle.equals(parentHandle)) {
                    driver.switchTo().window(winHandle);
                    log.info("Switching to Child Window handle -" + winHandle);
                    break;
                }
                driver.manage().window().maximize();
            }
        } else {
            log.info("Window handles found - " + windowHandles);
        }
    }

    public void switchToParentWindowHandle(WebDriver driver) {
        driver.switchTo().window(parentHandle);
        driver.manage().window().maximize();
    }

    // ******************** FRAMES *************//

    public int getNumberOfFrames(WebDriver driver) {
        return driver.findElements(By.tagName("iframe")).size();
    }

    public void SwitchToFrame_ByNumber(WebDriver driver, int n) {
        driver.switchTo().frame(n);
    }

    public void SwitchToFrame_ByNAME_OR_ByID(WebDriver driver,String nameorID) {
        driver.switchTo().frame(nameorID);
    }

    // Switch To default Content - Works to get back from a frame
    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    // ************* ALERTS *********************//

    public boolean isAlertPresent_SwitchToAlert(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public String getAlertText(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void sendTextToAlert(WebDriver driver, String text) {
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    public void closeAlert(WebDriver driver, boolean acceptAlert) {
        alert = driver.switchTo().alert();
        if (acceptAlert) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    // *************** EXTRAS ********************//

    // REFRESH PAGE
    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // Sleep
    public void sleep(int s) {
        try {
            Thread.sleep(s * 1000);
        } catch (InterruptedException ex) {
        } catch (IllegalArgumentException ex) {
        }
    }

    public boolean verify_Element_NotPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() == 0;
    }

    public void wait_until_Element_is_Not_Present(WebDriver driver, By locator) {
        if (driver.findElements(locator).size() > 0) {
            sleep(2);
        }
    }

    public String randomString() {
        Date date = new Date();
        Format dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateformat.format(date);
    }

    public void clickText(WebDriver driver,String text) { // used to get focus out of a text box
        hoverAndClick(driver, By.xpath("//*[contains(text(),'" + text + "')]"));
        sleep(2);
    }

    // ************ PERFORMANCE/NETWORK **************************//

    public String getNetworkData(WebDriver driver) {
        String scriptToExecute =
                "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor) driver).executeScript(scriptToExecute).toString();
        return netData;
    }

    // ************ ROBOT **************************//

    public void setClipboardData(String string) {
        // StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void uploadFile(String fileLocation) {

        if (Platform.getCurrent().toString().matches(".*?(win|WIN).*?")) {
            try {
                setClipboardData(fileLocation);
                Robot robot = new Robot();

                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }

        if (Platform.getCurrent().toString().matches(".*?(mac|MAC).*?")) {
            try {
                setClipboardData(fileLocation);
                Robot robot = new Robot();

                // Cmd + Tab is needed since it launches a Java app and the browser looses focus

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.delay(500);

                // Open Goto window

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_G);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_G);

                // Paste the clipboard value

                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_META);
                robot.keyRelease(KeyEvent.VK_V);

                // Press Enter key to close the Goto window and Upload window

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }
    }
    public String getRandomMobileNumber() {
    	return "753180"+String.valueOf((int)(Math.random()*9000)+1000);
    }



}
