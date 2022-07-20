package com.mobile.reports;



import static com.aventstack.extentreports.MediaEntityBuilder.*;
import static com.mobile.utils.ScreenShotUtils.*;

public final class ExtentLogger {

    private ExtentLogger() {

    }

    public static void pass(String passMessage) {
        ExtentManager.getExtentTest().pass(passMessage);
    }

    public static void fail(String failMessage) {
        ExtentManager.getExtentTest().fail(failMessage,
                createScreenCaptureFromBase64String(getBase64Images())
                        .build());
    }

    public static void skip(String skipMessage) {
        ExtentManager.getExtentTest().skip(skipMessage);
    }

    public static void info(String infoMessage) {
        ExtentManager.getExtentTest().info(infoMessage);
    }

}
