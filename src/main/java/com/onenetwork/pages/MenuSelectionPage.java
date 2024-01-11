package com.onenetwork.pages;


import java.util.HashMap;

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

public class MenuSelectionPage extends BaseTest{
	
	//*********Page Variables*********
	
	private WebDriver driver;
    private static Logger Log = Logger.getLogger(MenuSelectionPage.class.getName());
	HashMap<String, String> testData = new HashMap<String,String>();
	String testDataHolder;
	
	//*********Constructor*********
	
	public MenuSelectionPage(WebDriver driver)
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
			
	//*********URL = https://www.toolsqa.com/
	
	//*********Web Elements*********
	
	@FindBy(how=How.XPATH,using="//nav[@class='navbar']//span[text()='Tutorials']")
	@CacheLookup
	public WebElement tutorialMenu;
	
	@FindBy(how=How.XPATH,using="//nav[@class='mega-menu']//span[text()='Front-End Testing Automation']")
	@CacheLookup
	public WebElement webAutoToolMenu;
	
	@FindBy(how=How.XPATH,using="//div[@class='second-generation']//a[text()='Cypress']")
	@CacheLookup
	public WebElement cucumberTutMenu;
	
	//*********Page Methods*********
	
	/**
	 * This method is used to Select Menu.
	 * @param titleName TODO
	 */
	
	public void verifyMenuSelection(String titleName)
	{
		try {
			
			driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("menuselectionurl"));
			
			Log.info("-----------Navigating to URL-------------");
			
			Utilities.maximizeWindow();
			WaitUtilities.waitForPageToBeLoad(driver);
			
			WaitUtilities.waitForPageTitleIs(driver, "Tools QA", explicitlyWait_Type4);
			
			Actions action = new Actions(driver);
			
			action.moveToElement(tutorialMenu).click().perform();
			
			WaitUtilities.waitForElementVisible(driver, bywebAutoToolMenu, explicitlyWait_Type4);
			
			action.moveToElement(webAutoToolMenu).perform();
			
			WaitUtilities.waitForElementVisible(driver, bycucumberTutMenu, explicitlyWait_Type4);
			
			action.moveToElement(cucumberTutMenu).click().perform();
			
			WaitUtilities.waitForPageTitleIs(driver, "Cypress Tutorial for Beginners: Getting started with End to End Testing", explicitlyWait_Type4);
			
			String aTitleName = driver.getTitle();
			
			
			if(aTitleName.equals(titleName))
			{
				
				Reporter.pass("Cypress Tutorial for Beginners page should be Displayed Successfully.", "Cypress Tutorial for Beginners page is Displayed Successfully.", driver, true);
				
				Log.info("-----------Cypress Tutorial for Beginners Page is Opened. -------------");
				
			}else
			{
				Reporter.fail("Cypress Tutorial for Beginners page should be Displayed Successfully.", "Cypress Tutorial for Beginners page is not Displayed Successfully.", driver, false);
				
				
				Log.info("-----------Cypress Tutorial for Beginners Page is not Opened.------------");
			}
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while Selecting Menu.---------", e);
			
		}
		
		
	}

}
