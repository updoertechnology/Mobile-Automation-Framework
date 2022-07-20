package com.mobile.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.JsonArray;
import com.mobile.constants.FrameworkConstants;

import java.net.URL;



public class LocalDriverImpl implements IDriver{
    @SneakyThrows
    @Override
    public WebDriver getDriver() {
    	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        
        // String appPath1 =System.getProperty("user.dir") + "/src/test/resources/apkFiles/doctor-app-release.apk";
       //  String appPath2 =System.getProperty("user.dir") + "/src/test/resources/apkFiles/PatientApp.apk";

         
//         JsonArray jsonArray = new JsonArray();
//         jsonArray.add(FrameworkConstants.getInstance().getAPPPATH1());
//         System.out.println(jsonArray.toString());
        
         
//         desiredCapabilities.setCapability("otherApps", jsonArray.toString());
//         desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,"8200");
         desiredCapabilities.setCapability(MobileCapabilityType.APP, FrameworkConstants.getInstance().getAPPPATH());
          
         desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.expedicar.conveyor.app");
         desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.expedicar.conveyor.app.MainActivity");
//         desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        // desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
         desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
         desiredCapabilities.setCapability("platformVersion", "11.0");
         desiredCapabilities.setCapability("udid", "I7PZOV59WGNNBIKN");
         desiredCapabilities.setCapability("deviceName", "Redmi Note 10S");
         //params.setUDID(System.getProperty("udid", "emulator-5554"));
         //  params.setDeviceName(System.getProperty("deviceName", "Nexus 6"));
//         desiredCapabilities.setCapability("deviceName", "Android Emulator");
//         desiredCapabilities.setCapability("skipDeviceInitialization","true");
//         desiredCapabilities.setCapability("skipServerInstallation","true");
//         desiredCapabilities.setCapability("autoGrantPermissions", "true");
       //  desiredCapabilities.setCapability("autoAcceptAlerts", "true");
       //  URL url = new URL("http://0.0.0.0:4723/wd/hub");
     return new AndroidDriver<AndroidElement>(new ServerManager().getServer().getUrl(), desiredCapabilities);
     //	return new AndroidDriver<AndroidElement>(url, desiredCapabilities);
    }
}
