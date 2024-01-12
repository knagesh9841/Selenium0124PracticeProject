package com.onenetwork.listeners;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.onenetwork.util.ExtentManager;
import com.onenetwork.util.Utilities;

public class TestListener implements ISuiteListener,ITestListener,IInvokedMethodListener{
	
	/*
	 * static { DOMConfigurator.configure(System.getProperty("user.dir") +
	 * "\\src\\main\\java\\com\\onenetwork\\resources\\config\\log4j.xml"); }
	 */
	
	private static ExtentReports extent = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static Logger Log = Logger.getLogger(TestListener.class.getName());
    String fromSuite,browsername;
    
    @Override
    synchronized public void onStart(ISuite suite) {
		Log.info("-----------About to begin executing Suite "+suite.getName()+".-----------");
		
		fromSuite = suite.getXmlSuite().getParameter("browsername");
	    browsername = System.getProperty("browsername", fromSuite);
	   
	    Utilities.killBrowserAndDriver(browsername);
	    
	}

	@Override
	synchronized public void onFinish(ISuite suite) {
		Log.info("-----------About to end executing Suite "+suite.getName()+".-----------");
		
		fromSuite = suite.getXmlSuite().getParameter("browsername");
	    browsername = System.getProperty("browsername", fromSuite);
	   
	    Utilities.killBrowserAndDriver(browsername);
	    
	    extent.flush();
		try {
			ExtentManager.copyLatestExtentReport();
		} catch (IOException e) {
			
		}
	    
	}

	@Override
	synchronized public void onTestStart(ITestResult result) {
		
		Log.info("-----------"+result.getInstanceName() + " started.-----------");
		Log.info("-----------"+result.getMethod().getMethodName() + " started.-----------");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
		
	}

	@Override
	synchronized public void onTestSuccess(ITestResult result) {
		
		Log.info("-----------"+result.getInstanceName() + " passed.-----------");
		Log.info("-----------"+result.getMethod().getMethodName() + " passed-----------");
		
		test.get().pass("Test case is passed");
	}

	@Override
	synchronized public void onTestFailure(ITestResult result) {
		
		Log.info("-----------"+result.getInstanceName() + " failed.-----------");
		Log.info("-----------"+result.getMethod().getMethodName() + " failed-----------");
		test.get().fail("Test Case is Failed.");
        test.get().fail(result.getThrowable());
		
	}

	@Override
	synchronized public void onTestSkipped(ITestResult result) {
		
		Log.info("-----------"+result.getInstanceName() + " skipped.-----------");
		Log.info("-----------"+result.getMethod().getMethodName() + " skipped-----------"); 
		test.get().info("Testcase is Skipped.");
		test.get().skip(result.getThrowable());
	}

	@Override
	synchronized public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		Log.info("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
		
	}

	@Override
	synchronized public void onStart(ITestContext context) {
		
		Log.info("----------- Test Suite "+context.getName()+" started-----------");
	}

	@Override
	synchronized public void onFinish(ITestContext context) {
		
		Log.info("-----------Test Suite "+context.getName()+" is ending-----------");
		
	}
	
	/**
	 * Get test 
	 * @return
	 */
	
    public synchronized static ExtentTest getTest () {
        return test.get();
    }
    
    
    @Override
    synchronized public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		Log.info("-----------About to begin executing following method :  "+method.getTestMethod().getMethodName()+".-----------");

	}

	@Override
	synchronized public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		Log.info("-----------Completed executing following method :  "+method.getTestMethod().getMethodName()+".-----------");

	}

	

}
