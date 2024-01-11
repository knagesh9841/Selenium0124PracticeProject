package com.onenetwork.pages;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.onenetwork.testcases.BaseTest;
import com.onenetwork.util.PropertyManager;
import com.onenetwork.util.Reporter;
import com.onenetwork.util.Utilities;
import com.onenetwork.util.WaitUtilities;

public class CalendarPage extends BaseTest{
	
			//*********Page Variables*********

			private WebDriver driver;

			private static Logger Log = Logger.getLogger(CalendarPage.class.getName());


			//*********Constructor*********

			public CalendarPage(WebDriver driver)
			{
				this.driver = driver;
				PageFactory.initElements(driver, this);
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


			//*********Web Elements*********

			@FindBy(how=How.XPATH,using="//input[@id='datepicker']")
			@CacheLookup
			public WebElement datePicker;
			
			@FindBy(how=How.XPATH,using="//table[@class='ui-datepicker-calendar']//td")
			@CacheLookup
			public List<WebElement> dateTable;
			
		

			//*********Page Methods*********
			
			public void verifyCalendarDate(String date)
			{

				try {
					driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("calendarurl"));

					Log.info("-----------Navigating to URL-------------");

					Utilities.maximizeWindow();
					WaitUtilities.waitForPageToBeLoad(driver);
					

					WaitUtilities.waitForPageTitleIs(driver, "Selenium Practise: How to handle calendar in Selenium Webdriver", explicitlyWait_Type4);
					
					datePicker.click();
					
					Log.info("-----------Clicked on Datepicker button-------------");
					
					WaitUtilities.waitForElementVisible(driver, By.xpath("//table[@class='ui-datepicker-calendar']//td/a[text()='"+date+"']"), explicitlyWait_Type3);
					
					boolean sFlag=false;
					
					for(WebElement dateElement:dateTable)
					{
						if(dateElement.getText().equals(date))
						{
							sFlag = true;
							dateElement.click();
							Reporter.info("Date "+date+" should be selected successfully.", "Date "+date+" is selected successfully.", driver, true);
							Log.info("-----------Date "+date+" is selected successfully.-------------");
							break;
						}
					}
					
					if(!sFlag)
					{
						Reporter.fail("Date "+date+" should be selected successfully.", "Date "+date+" is selected successfully.", driver, false);
					}
					
					
				} catch (Exception e) {
				
					Log.warn("-----------Exception Occured while Entering Date.------------");
				}
			}

}
