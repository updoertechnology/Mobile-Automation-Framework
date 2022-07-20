package com.mobile.factory;


import com.mobile.constants.FrameworkConstants;
import com.mobile.driver.DriverManager;
import com.mobile.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class ExplicitWaitFactory {
    private ExplicitWaitFactory() {
    }

    public static WebElement performExplicitWaitWait(WaitStrategy waitStrategy, WebElement element) {
        switch (waitStrategy) {
            case CLICKABLE:
                return new WebDriverWait(DriverManager.getWebDriver(), FrameworkConstants.getEXPLICITLYWAIT())
                        .until(ExpectedConditions.elementToBeClickable(element));
            case VISIBLE:
                return new WebDriverWait(DriverManager.getWebDriver(), FrameworkConstants.getEXPLICITLYWAIT())
                        .until(ExpectedConditions.visibilityOf(element));
            case PRESENCE:
                return new WebDriverWait(DriverManager.getWebDriver(), FrameworkConstants.getEXPLICITLYWAIT())
                        .until(ExpectedConditions.presenceOfElementLocated((By) element));
            case NONE:
                return element;
        }
        return null;
    }



    public static boolean isElementInVisible(WebElement element){
        return  new WebDriverWait(DriverManager.getWebDriver(), FrameworkConstants.getEXPLICITLYWAIT())
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean isElementNotVisible(WebElement element){
        return  new WebDriverWait(DriverManager.getWebDriver(), FrameworkConstants.getEXPLICITLYWAIT())
                .until(ExpectedConditions.invisibilityOfElementLocated((By) element));
    }






}
