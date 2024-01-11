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

public class BootStrapDropdownPage extends BaseTest{
	
	//*********Page Variables*********

		private WebDriver driver;

		private static Logger Log = Logger.getLogger(BootStrapDropdownPage.class.getName());


		//*********Constructor*********

		public BootStrapDropdownPage(WebDriver driver)
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

		@FindBy(how=How.XPATH,using="//button[@id='menu1']")
		@CacheLookup
		public WebElement menuBtn;
		
		@FindBy(how=How.XPATH,using="//ul[@class='dropdown-menu']//li/a")
		@CacheLookup
		public List<WebElement> menus;
		
		
	
		//*********Page Methods*********
		
		/**
		 * This method is used to select Bootstrap Dropdown
		 * @param menuOption
		 */
		
		public void verifyBootstrapDropdown(String menuOption)
		{
			try {
				driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("bootstrapdropdownurl"));

				Log.info("-----------Navigating to URL-------------");

				Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);
				
				WaitUtilities.waitForPageTitleIs(driver, "Selenium Practise: Bootstrap Dropdown Example for Selenium", explicitlyWait_Type4);
				
				menuBtn.click();
				
				WaitUtilities.waitForElementVisible(driver, By.xpath("//ul[@class='dropdown-menu']//li/a[text()='"+menuOption+"']"), explicitlyWait_Type3);
				
				for(int cnt=0;cnt<menus.size();cnt++)
				{
					if(menus.get(cnt).getText().equals(menuOption))
					{
						menus.get(cnt).click();
						Log.info("-----------Selected "+menuOption+" option.-------------");
						Reporter.info(""+menuOption+" Menu should be selected.", ""+menuOption+" Menu is selected.", driver, true);
					}
				}
			} catch (Exception e) {
				Log.warn("-----------Exception Occured while Selecting Bootstrap Dropdown menu.----------");
			}
			
			
		}


}
