package com.mobile.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
    private ExtentManager() {

    }

    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

     static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }

    public static void setExtentTest(ExtentTest extentTest){

     extentTestThreadLocal.set(extentTest);
    }

    public static void unLoad(){
     extentTestThreadLocal.remove();
    }

}
