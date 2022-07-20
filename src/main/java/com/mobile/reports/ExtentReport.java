package com.mobile.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mobile.constants.FrameworkConstants;

import java.util.Objects;

public class ExtentReport {
   private static ExtentReports extentReports;


    public static void initReports() {
        if (Objects.isNull(ExtentManager.getExtentTest())){
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("OTR_Mobile_Automation");
        sparkReporter.config().setDocumentTitle("OTR");
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Devices_Name","vivo 1907");
        extentReports.setSystemInfo("Application_Version","3.0.10");
        extentReports.setSystemInfo("OTR Project Manager", "Vinay Bhavsar");
        extentReports.setSystemInfo("Automation Tester", "Samkit Jain");
        extentReports.setSystemInfo("Organization", "CDN Solution");
        }
    }

    public static void tearDownReport() {
        if (Objects.nonNull(ExtentManager.getExtentTest())){
        extentReports.flush();
        ExtentManager.unLoad();
        }
    }

    public static void createTest(String testCaseName) {
        ExtentManager.setExtentTest(extentReports.createTest(testCaseName));
    }
}
