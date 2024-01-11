package com.onenetwork.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class BootStrapLoginWindowPage extends BaseTest{
	
	//*********Page Variables*********

	private WebDriver driver;

	private static Logger Log = Logger.getLogger(BootStrapLoginWindowPage.class.getName());


	//*********Constructor*********

	public BootStrapLoginWindowPage(WebDriver driver)
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

	@FindBy(how=How.XPATH,using="//button[text()='Click here to Login']")
	@CacheLookup
	public WebElement loginBtn;
	
	@FindBy(how=How.XPATH,using="//input[@type='text']")
	@CacheLookup
	public WebElement userName;
	
	@FindBy(how=How.XPATH,using="//input[@type='password']")
	@CacheLookup
	public WebElement pwd;
	
	@FindBy(how=How.XPATH,using="//button[text()='Close']")
	@CacheLookup
	public WebElement closeBtn;


	//*********Page Methods*********


	/**
	 * This method is used to Login to Bootstrap Login Window
	 * @param uname TODO
	 * @param password TODO
	 */

	public void verifyBootstrapLoginWindow(String uname, String password)
	{
		try {
			driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("bootstraploginurl"));

			Log.info("-----------Navigating to URL-------------");

			Utilities.maximizeWindow();
			WaitUtilities.waitForPageToBeLoad(driver);

			WaitUtilities.waitForPageTitleIs(driver, "Selenium Practise: Handle BootStrap Model Dialog in Selenium Webdriver", explicitlyWait_Type4);
			
			loginBtn.click();
			
			Log.info("-----------Clicked on Login Window button-------------");
			
			WaitUtilities.waitForElementVisible(driver, bybootstratLoginUserName, 20);
			
			userName.sendKeys(uname);
			
			pwd.sendKeys(password);
			
			String aUserName = userName.getAttribute("value");
			
			uname = Utilities.fetchDataFromDatabase("select name from emp where id=1;");
			
			if(aUserName.equals(uname))
			{
				
				Reporter.pass("UserName should be "+uname+".", "Username is "+aUserName+".", driver, true);
				Log.info("-----------Username is matched-------------");
				
			}else
			{
				Reporter.fail("UserName should be "+uname+".", "Username is "+aUserName+".", driver, false);
				Log.info("-----------Username is not matched-------------");
			}
			
			closeBtn.click();
			
			Log.info("-----------Clicked on Close Window button-------------");
		} catch (Exception e) {
		
			Log.warn("-----------Exception Occured while working on Bootstrap Login Window.------------");
		}
		
	}
	
}
