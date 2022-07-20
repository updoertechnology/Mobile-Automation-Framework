package com.mobile.driver;

import com.mobile.enums.Modes;
import com.mobile.utils.PropertyUtils;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static com.mobile.driver.DriverManager.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public final class Driver {
    private Driver() {
    }

    public static void initDriver() throws MalformedURLException {
        if (isNull(getWebDriver())) {
            WebDriver driver = DriverFactory.getDriver(Modes.valueOf(PropertyUtils
                                    .getPropertyValue("mode")
                                    .toUpperCase()));
            setWebDriver(driver);
        }
    }

    public static void quitDriver() {
        if (nonNull(getWebDriver())) {
            getWebDriver().quit();
            unLoadWebDriver();
        }
    }
}
