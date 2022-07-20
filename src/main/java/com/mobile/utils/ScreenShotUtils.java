package com.mobile.utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.mobile.driver.DriverManager;

public final class ScreenShotUtils {
 private ScreenShotUtils(){

 }

 public static String getBase64Images(){
    return  ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BASE64);
 }

}
