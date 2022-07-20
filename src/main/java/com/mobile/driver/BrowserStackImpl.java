package com.mobile.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackImpl implements IDriver{

    @SneakyThrows
    @Override
    public WebDriver getDriver() throws MalformedURLException  {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("app", "bs://906d1a616d3349959418c5ce1db28890960be658");
        desiredCapabilities.setCapability("browserstack.user", "samkitjain2");
        desiredCapabilities.setCapability("browserstack.key", "yekMp3zM2DpAPAsqscgY");
        desiredCapabilities.setCapability("os_version", "9.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Google Pixel 3");
        desiredCapabilities.setCapability("project", "Doctor_App");
        desiredCapabilities.setCapability("build", "Java Android");
        //desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        return new AndroidDriver<AndroidElement>
                (new URL("http:/hub.browserstack.com/wd/hub"),desiredCapabilities);

    }
}
