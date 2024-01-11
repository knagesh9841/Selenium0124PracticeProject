package com.onenetwork.pages;


import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.onenetwork.testcases.BaseTest;
import com.onenetwork.util.PropertyManager;
import com.onenetwork.util.Reporter;
import com.onenetwork.util.Utilities;
import com.onenetwork.util.WaitUtilities;

import static com.onenetwork.objectrepository.ObjectRepository.*;

public class FrameTestingPage extends BaseTest{
	
		//*********Page Variables*********
	
		private WebDriver driver;
	    private static Logger Log = Logger.getLogger(FrameTestingPage.class.getName());
		HashMap<String, String> testData = new HashMap<String,String>();
		String testDataHolder;
		
		//*********Constructor*********
		
		public FrameTestingPage(WebDriver driver)
		{
			this.driver = driver;
		}
		
		// Load Explicitly Wait

		private static int explicitlyWait_Type1;
		private static int explicitlyWait_Type2;
		private static int explicitlyWait_Type3;
		private static int explicitlyWait_Type4;

		static
		{
			loadProperties();
		}


		private static void loadProperties()
		{
			Log.info("-----------Loading GlobalWait.Properties File-------------");
			explicitlyWait_Type1 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type1"));
			explicitlyWait_Type2 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type2"));
			explicitlyWait_Type3 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type3"));
			explicitlyWait_Type4 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type4"));
			

		}
				
		//*********URL = https://chercher.tech/practice/frames-example-selenium-webdriver
		
		//*********Page Methods*********
		
		/**
		 * This method will use to find out element in IFrame and Perform operation.
		 * @param topicaName TODO
		 * @param select TODO
		 * @param animalName TODO
		 * @param headingName TODO
		 */
		
		public void verifyElementInIFrame(String topicaName, boolean select, String animalName, String headingName)
		{
			WebDriver TopFrameDriver,BottomFrameDriver;
			
			try {
				
				
				driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("frameurl"));
				
				Log.info("-----------Navigating to URL-------------");
				
				Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);
				
				WaitUtilities.waitForPageTitleIs(driver, "Frames Example in Selenium webdriver", explicitlyWait_Type4);
				
				TopFrameDriver = driver.switchTo().frame(driver.findElement(byFrame1));
				
				BottomFrameDriver = TopFrameDriver.switchTo().frame(TopFrameDriver.findElements(byFrameElement).get(0));
				
				WebElement checkBox = BottomFrameDriver.findElement(byFramePageCheckBox);
				
				if(select)
				{
					if(!checkBox.isSelected())
					{
						checkBox.click();
						
						Reporter.info("Checkbox should be checked.", "Checkbox is checked.", BottomFrameDriver, false);
						
					}else
					{
						Reporter.info("Checkbox should be checked.", "Checkbox is allready checked.", BottomFrameDriver, false);
					}
				}else
				{
					if(checkBox.isSelected())
					{
						checkBox.click();

						Reporter.info("Checkbox should be unchecked.", "Checkbox is unchecked.", BottomFrameDriver, false);
					}else
					{
						Reporter.info("Checkbox should be unchecked.", "Checkbox is allready unchecked.", BottomFrameDriver, false);
					}
				}
				
				
				TopFrameDriver = BottomFrameDriver.switchTo().parentFrame();
				
				WebElement topicNameElement = TopFrameDriver.findElement(byFramePageTopicname);
				
				topicNameElement.sendKeys(topicaName);
				
				Reporter.info("Topic Name should be Set to "+topicaName+".", "Topic Name should is Set to "+topicaName+".", TopFrameDriver, false);
				
				TopFrameDriver = TopFrameDriver.switchTo().parentFrame();
				
				String actualheadingName = TopFrameDriver.findElement(byFrameTopicHeading).getText();
				
				
				if(actualheadingName.equals(headingName))
				{
					
					Reporter.info("Heading Name should be "+headingName+".", "Heading Name is "+actualheadingName+".", TopFrameDriver, false);
					
					Log.info("-----------Heading Name is "+headingName+".-------------");
				}else
				{
					Reporter.fail("Heading Name should be "+headingName+".", "Heading Name is "+actualheadingName+".", TopFrameDriver, false);
					
					
					Log.info("-----------heading Name is Not Matched.-------------");
				}
				
				
				
				TopFrameDriver = driver.switchTo().frame(driver.findElement(byFrame2));
				
				Select selectElement = new Select(TopFrameDriver.findElement(byFramePageAnimals));
				
				selectElement.selectByVisibleText(animalName);
				
				Reporter.info("Animal Name should be Set to "+animalName+".", "Animal Name should is Set to "+animalName+".", TopFrameDriver, true);
				
				
			} catch (Exception e) {
				
				Log.error("----------Exception Occured while Finding Element in IFrame---------", e);
				
			}
			
			
		}
		

}
