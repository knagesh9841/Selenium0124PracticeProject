package com.onenetwork.pages;


import java.util.HashMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import static com.onenetwork.objectrepository.ObjectRepository.*;
import com.onenetwork.exceltestdata.ExcelUtils;
import com.onenetwork.testcases.BaseTest;
import com.onenetwork.util.PropertyManager;
import com.onenetwork.util.Reporter;
import com.onenetwork.util.Utilities;
import com.onenetwork.util.WaitUtilities;

public class AddressPage extends BaseTest{
	
	//*********Page Variables*********
	
	private WebDriver driver;
    private static Logger Log = Logger.getLogger(AddressPage.class.getName());
	HashMap<String, String> testData = new HashMap<String,String>();
	String testDataHolder,actualValue;
	
	
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
	
	//*********Constructor*********
	
	public AddressPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// url = http://a.testaddressbook.com/
	
	//*********Web Elements*********
	
	@FindBy(how=How.XPATH,using="//span[text()='Text Box']")
	@CacheLookup
	public WebElement textBoxBtn;
	
	
	@FindBy(how=How.ID,using="userName")
	@CacheLookup
	public WebElement userName;
	
	@FindBy(how=How.ID,using="userEmail")
	@CacheLookup
	public WebElement userEmail;
	
	@FindBy(how=How.ID,using="currentAddress")
	@CacheLookup
	public WebElement currentAddress;
	
	@FindBy(how=How.ID,using="permanentAddress")
	@CacheLookup
	public WebElement permanentAddress;
	
	@FindBy(how=How.XPATH,using="//button[@id='submit']")
	@CacheLookup
	public WebElement submitBtn;
	
	@FindBy(how=How.XPATH,using="//div[@id='output']")
	@CacheLookup
	public WebElement output;
	
	@FindBy(how=How.XPATH,using="//p[@id='name']")
	@CacheLookup
	public WebElement outputName;
	
	@FindBy(how=How.XPATH,using="//p[@id='email']")
	@CacheLookup
	public WebElement outputemail;
	
	
	@FindBy(how=How.XPATH,using="//p[@id='currentAddress']")
	@CacheLookup
	public WebElement outputcurrentAddress;
	
	
	@FindBy(how=How.XPATH,using="//p[@id='permanentAddress']")
	@CacheLookup
	public WebElement outputpermanentAddress;
	

	
	
	//*********Page Methods*********
	
	/**
	 * This method will fill address details and verify success message.
	 */
	
	public void fillAndVerifyPersonalDetails()
	{
		
		
		try {
			
			driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("basicurl"));

			Log.info("-----------Navigating to URL-------------");
			
			Utilities.maximizeWindow();
			WaitUtilities.waitForPageToBeLoad(driver);
			
			WaitUtilities.waitForPageTitleIs(driver, "DEMOQA", 30);
			
			WaitUtilities.waitForElementVisible(driver, byAddressPage_textbox, explicitlyWait_Type4);
			
			Utilities.clickByJavaScriptExecutor(driver, textBoxBtn);
			
			Log.info("-----------Clicked on Text Box button from Home Page-------------");
			
			testData = ExcelUtils.loadData("ExcelTestDataFile.xlsx", "Personal Details", 1);
			
			WaitUtilities.waitForElementVisible(driver, byAddressPage_userName, explicitlyWait_Type4);
	
			
			testDataHolder = ExcelUtils.getData(testData, "Full Name");
			
			userName.sendKeys(testDataHolder);
			
			Log.info("-----------Full Name Entered is "+testDataHolder+".-------------");
			
			
			testDataHolder = ExcelUtils.getData(testData, "Email");
			
			userEmail.sendKeys(testDataHolder);
			
			Log.info("-----------Email Entered is "+testDataHolder+".-------------");
			
			
			testDataHolder = ExcelUtils.getData(testData, "Current Address");
			
			currentAddress.sendKeys(testDataHolder);
			
			Log.info("-----------Current Address Entered is "+testDataHolder+".-------------");
			
			
			testDataHolder = ExcelUtils.getData(testData, "Permenant Address");
			
			permanentAddress.sendKeys(testDataHolder);
			
			Log.info("-----------Permenant Address Entered is "+testDataHolder+".-------------");
			
			
			WaitUtilities.waitForElementVisible(driver, byAddressPage_textbox, explicitlyWait_Type4);
			
			Utilities.scrollIntoViewByJavaScriptExecutor(driver, submitBtn);
			
			submitBtn.click();
			
			Log.info("-----------Clicked on Submit Button.-------------");
			
			WaitUtilities.waitForElementVisible(driver, byAddressPage_output, explicitlyWait_Type4);
			

			actualValue = outputName.getText().trim();
			testDataHolder = ExcelUtils.getData(testData, "Full Name");
			
			
			if(actualValue.contains(testDataHolder))
			{
				
				Reporter.pass("Full Name should be "+testDataHolder+".", "Full Name is "+actualValue+".", driver, true);
				
				Log.info("-----------Full Name is successfully Verified-------------");
				
			}else
			{
				Reporter.fail("Full Name should be "+testDataHolder+".", "Full Name is "+actualValue+".", driver, false);
				
				Log.info("-----------Full Name is not successfully Verified.-------------");
			}
			
			actualValue = outputemail.getText().trim();
			testDataHolder = ExcelUtils.getData(testData, "Email");
			
			
			if(actualValue.contains(testDataHolder))
			{
				
				Reporter.pass("Email should be "+testDataHolder+".", "Email is "+actualValue+".", driver, true);
				
				Log.info("-----------Email is successfully Verified-------------");
				
			}else
			{
				Reporter.fail("Email should be "+testDataHolder+".", "Email is "+actualValue+".", driver, false);
				
				Log.info("-----------Email is not successfully Verified.-------------");
			}
			
			
			actualValue = outputcurrentAddress.getText().trim();
			testDataHolder = ExcelUtils.getData(testData, "Current Address");
			
			
			if(actualValue.contains(testDataHolder))
			{
				
				Reporter.pass("Current Address should be "+testDataHolder+".", "Current Address is "+actualValue+".", driver, true);
				
				Log.info("-----------Current Address is successfully Verified-------------");
				
			}else
			{
				Reporter.fail("Current Address should be "+testDataHolder+".", "Current Address is "+actualValue+".", driver, false);
				
				Log.info("-----------Current Address is not successfully Verified.-------------");
			}
			
			
			actualValue = outputpermanentAddress.getText().trim();
			testDataHolder = ExcelUtils.getData(testData, "Permenant Address");
			
			
			if(actualValue.contains(testDataHolder))
			{
				
				Reporter.pass("Permenant Address should be "+testDataHolder+".", "Permenant Address is "+actualValue+".", driver, true);
				
				Log.info("-----------Permenant Address is successfully Verified-------------");
				
			}else
			{
				Reporter.fail("Permenant Address should be "+testDataHolder+".", "Permenant Address is "+actualValue+".", driver, false);
				
				Log.info("-----------Email is not successfully Verified.-------------");
			}
			
			
			
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while Updating Details---------", e);
		}
	}
	
	

	

}
