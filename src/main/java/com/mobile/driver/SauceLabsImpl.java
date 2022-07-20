package com.mobile.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabsImpl implements IDriver {

    @SneakyThrows
    @Override
    public WebDriver getDriver() throws MalformedURLException  {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir"+"src/test/resources/apkFiles/air.au.com.ontherun.apk"));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,"8200");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"");
        return new AndroidDriver<AndroidElement>
                (new URL("http:/127.0.0.0.1:4273/wd/hub"),desiredCapabilities);
    }
}
