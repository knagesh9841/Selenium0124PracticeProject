package com.onenetwork.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import static com.onenetwork.objectrepository.ObjectRepository.*;

import com.onenetwork.testcases.BaseTest;
import com.onenetwork.util.PropertyManager;
import com.onenetwork.util.Reporter;
import com.onenetwork.util.Utilities;
import com.onenetwork.util.WaitUtilities;

public class TooltipPage extends BaseTest{
	
	//*********Page Variables*********

		private WebDriver driver;

		private static Logger Log = Logger.getLogger(TooltipPage.class.getName());


		//*********Constructor*********

		public TooltipPage(WebDriver driver)
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

		@FindBy(how=How.XPATH,using="//div[@class='tooltip']")
		@CacheLookup
		public WebElement mouseOverBtn;
		
		@FindBy(how=How.XPATH,using="//span[@class='tooltiptext']")
		@CacheLookup
		public WebElement tooltipMsg;
		
	

		//*********Page Methods*********
		
		
		public void verifyTooltipText(String eMessage)
		{

			try {
				driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("tooltipurl"));

				Log.info("-----------Navigating to URL-------------");

				Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);
				

				WaitUtilities.waitForPageTitleIs(driver, "Selenium Practise: How to automate tooltip in Selenium webdriver", explicitlyWait_Type4);
				
				Actions action = new Actions(driver);
				action.moveToElement(mouseOverBtn).clickAndHold().build().perform();
				
				Log.info("-----------Mouse over the button-------------");
				
				WaitUtilities.waitForElementVisible(driver, bytooltipText, 30);
				
				String aMessage = tooltipMsg.getText();
				
				if(aMessage.equals(eMessage))
				{
					
					Reporter.pass("Tooltip Message should be "+eMessage+".", "Tooltip Message is "+aMessage+".", driver, true);
					Log.info("-----------Tooltip Message is matched-------------");
					
				}else
				{
					Reporter.fail("Tooltip Message should be "+eMessage+".", "Tooltip Message is "+aMessage+".", driver, false);
					Log.info("-----------Tooltip Message is not matched-------------");
				}
				
				
			} catch (Exception e) {
			
				Log.warn("-----------Exception Occured while verifying tooltip text.------------");
			}
		}

}
