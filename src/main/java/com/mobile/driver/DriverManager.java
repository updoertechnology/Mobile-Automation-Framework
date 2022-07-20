package com.mobile.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private DriverManager(){}
   private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        return webDriverThreadLocal.get();
    }

    public static  void setWebDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    public static void unLoadWebDriver(){
        webDriverThreadLocal.remove();
    }
}
