package com.mobile.Listeners;


import com.mobile.reports.ExtentReport;
import com.mobile.reports.FrameWorkLogger;
import com.mobile.enums.LogType;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersClass implements ITestListener, ISuiteListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        FrameWorkLogger.log(LogType.PASS, result.getName() + " is Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        FrameWorkLogger.log(LogType.FAIL, result.getName() + " is Failed");
        FrameWorkLogger.log(LogType.SCREEN_CAPTURE, result.getName());
        // ExtentLogger.fail();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        FrameWorkLogger.log(LogType.SKIP, result.getName() + " is skipped");
    }

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}


}
