package com.onenetwork.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.onenetwork.listeners.TestListener;

public class Reporter {
	
	static ExtentTest test;
	private static Logger Log = Logger.getLogger(Reporter.class.getName());
	
	static SoftAssert softAssert = new SoftAssert();
	
	/**
	 * This method will used for Info reporting.
	 * @param ExpectValue
	 * @param aValue
	 * @param driver
	 * @param attachScreenshot TODO
	 */
	
	public static void info(String ExpectValue,String aValue, WebDriver driver, boolean attachScreenshot)
	{
		
		 try {
			 
			 	if(attachScreenshot)
			 	{
			 		String screenshotPath = ScreenshotCapture.getScreenshot(driver, "screeshot");
					
					TestListener.getTest().info(ExpectValue+"::"+aValue, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					
			 	}else
			 	{
			 		TestListener.getTest().info(ExpectValue+"::"+aValue);
			 	}
				
			} catch (Exception e) {
				
				Log.warn("-------Exception Occured while Info reporting-------");
			}
	}
	
	
	/**
	 * This method will used for pass reporting.
	 * @param ExpectValue
	 * @param aValue
	 * @param driver
	 * @param attachScreenshot TODO
	 */
	
	public static void pass(String ExpectValue,String aValue, WebDriver driver, boolean attachScreenshot)
	{

		try {

			Assert.assertTrue(true);

			if(attachScreenshot)
			{
				String screenshotPath = ScreenshotCapture.getScreenshot(driver, "screeshot");

				TestListener.getTest().pass(ExpectValue+"::"+aValue, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

			}else
			{
				TestListener.getTest().pass(ExpectValue+"::"+aValue);
			}

		} catch (Exception e) {

			Log.warn("-------Exception Occured while Pass reporting-------");
		}
	}
	
	
	/**
	 * This method will used fail reporting.
	 * @param ExpectValue
	 * @param aValue
	 * @param driver
	 * @param isHardConstraint TODO
	 */
	
	public static void fail(String ExpectValue,String aValue, WebDriver driver, boolean isHardConstraint)
	{
		
		 try {
				String screenshotPath = ScreenshotCapture.getScreenshot(driver, "screeshot");
				
				TestListener.getTest().fail(ExpectValue+"::"+aValue, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				
				if(isHardConstraint)
				{
					Assert.assertTrue(false);
					
				}else
				{
					softAssert.assertTrue(false);
				}
				
			} catch (Exception e) {
				
				Log.warn("-------Exception Occured while Fail reporting-------");
			}
	}
	
	

}
