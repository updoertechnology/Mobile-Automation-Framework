package com.android.testcase;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.mobile.driver.Driver;
import com.mobile.driver.DriverManager;
import com.mobile.driver.ServerManager;
import com.mobile.reports.ExtentReport;
import com.mobile.utils.ExcelUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.StartsActivity;

public class BaseTest {

	protected String doctorAppPackageName = "com.deardoc.doctor";
	protected String doctorAppActivity = "com.deardoc.doctor.MainActivity";
	protected String patientAppPackageName = "com.deardoc.patient";
	protected String patientAppActivity = "com.remedoapp.patient.ui.activities.SplashActivity";

	 protected void launchAPP(String AppName, WebDriver driver, String appPackage, String appActivity) {
	    	((StartsActivity) driver).startActivity(new Activity(appPackage, appActivity));
	    	//FrameWorkLogger.log(LogType.INFO, "Launching "+AppName+ " now");
	    }
	 public void launchHiFlowApp() {
		  launchAPP("App", DriverManager.getWebDriver(), doctorAppPackageName, doctorAppActivity);
	   }
	   
	   public void launchPatientApp() {
			  launchAPP("Patient App", DriverManager.getWebDriver(), patientAppPackageName, patientAppActivity);
		   }
	   
	   public void closePatientApp() {
			((AppiumDriver) DriverManager.getWebDriver()).terminateApp(patientAppPackageName);
		   }
	   
	   public void resetPatientApp() {
			((AppiumDriver) DriverManager.getWebDriver()).removeApp(patientAppPackageName);
		   }
	   
	   public void resetDoctorApp() {
			((AppiumDriver) DriverManager.getWebDriver()).removeApp(doctorAppPackageName);
		   }
    @BeforeSuite
    public void setUpSuite(){
    	new ServerManager().startServer();
    	System.out.println("Initializing Extent report");
    	ExtentReport.initReports();
    }

    @AfterSuite
    public void tearDownSuite(){
        ExtentReport.tearDownReport();
    }
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        Driver.initDriver();
        
    }
    
    @BeforeClass
    public void resettingApp() throws MalformedURLException {
    	Driver.initDriver();
    	resetPatientApp();
    	resetDoctorApp();
    	Driver.quitDriver();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();

    }
    
    @DataProvider(name = "data")
    public Iterator<Object[]> getData(Method M) throws Exception{
    	List<Hashtable<String, String>> dataList = ExcelUtils.readFile(System.getProperty("user.dir")+"", "", M.getName());
    	List<Object[]> testArray = new ArrayList<Object[]>();
    	Iterator<Hashtable<String, String>> dataIterator = dataList.iterator(); 
    	while(dataIterator.hasNext()){
    		Hashtable<String, String> testdataset = dataIterator.next();
    		if(!(testdataset.get("RunTest").equalsIgnoreCase("TRUE"))){
    			dataIterator.remove();
    		}else{
    			Object[] dataObj = {testdataset};
    			testArray.add(dataObj);
    		}
    	}
    	return testArray.iterator();
    }
}
