package com.onenetwork.pages;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.onenetwork.testcases.BaseTest;
import com.onenetwork.util.PropertyManager;
import com.onenetwork.util.Reporter;
import com.onenetwork.util.Utilities;
import com.onenetwork.util.WaitUtilities;

public class WindowTestingPage extends BaseTest{
	
			//*********Page Variables*********
	
			private WebDriver driver;
		    private static Logger Log = Logger.getLogger(WindowTestingPage.class.getName());
			HashMap<String, String> testData = new HashMap<String,String>();
			String testDataHolder;
			
			//*********Constructor*********
			
			public WindowTestingPage(WebDriver driver)
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
			
			//*********URL = https://chercher.tech/python/windows-selenium-python
			
			//*********Web Elements*********
			
			@FindBy(how=How.XPATH,using="//a[text()='Click Here']")
			@CacheLookup
			public WebElement newWindowBtn;
			
			//*********Page Methods*********
			
			/**
			 * This method will return true if it is Instance of Chrome Driver
			 * @return
			 */
			
			public boolean isChrome()
			{
				return driver instanceof ChromeDriver;
			}
			
			/**
			 * This method will return true if it is Instance of IE Driver
			 * @return
			 */
			
			public boolean isIE()
			{
				return driver instanceof InternetExplorerDriver;
			}
			
			/**
			 * This method will return true if it is Instance of Firefox Driver
			 * @return
			 */
			
			public boolean isFirefox()
			{
				return driver instanceof FirefoxDriver;
			}
			
			/**
			 * This method will return true if it is Instance of Edge Driver
			 * @return
			 */
			
			public boolean isEdge()
			{
				return driver instanceof EdgeDriver;
			}
			
			/**
			 * This method is used to switch window using Title.
			 * @param titleName
			 */
			
			public void switchToWindowUsingTitle(String titleName)
			{
				Set<String> windowhandle = driver.getWindowHandles();
				
				for(String handle:windowhandle)
				{
					WebDriver windowDriver = driver.switchTo().window(handle);
					String aTitle = windowDriver.getTitle();
					
					if(aTitle.equals(titleName))
					{
						
						Log.info("--------------Switched to Window using Title Successfull.---------------------");
						
						List<WebElement> allLinks = new ArrayList<WebElement>();
						
						allLinks = windowDriver.findElements(By.tagName("a"));
						
						int workLinks=0,brokenLinks=0;
						
						Log.info("Total no of links = "+allLinks.size()+"");
						
						for(WebElement links:allLinks)
						{
							if((links.getAttribute("href")!=null))
							{
								workLinks ++;
							}else
							{
								brokenLinks++;
							}
							
							try {
								Log.info("URL: " + links.getAttribute("href")+" returned " + isLinkBroken(new URL(links.getAttribute("href"))));
							} catch (Exception e) {
								Log.info("At " + links.getAttribute("innerHTML") + " Exception occured -&gt; " + e.getMessage());
							}
						}
						
						Log.info("No of Working links = "+workLinks+"");
						Log.info("No of Broken links = "+brokenLinks+"");
						
						Reporter.info("New opened Window Title should be "+titleName+".", "New opened Window Title is "+aTitle+".", windowDriver, true);
						
						windowDriver.close();
						
					}
				}
			}
			
			/**
			 * This method is used to switch to Tab using title.
			 * @param titleName
			 */
			
			public void switchToTabUsingTitle(String titleName)
			{
				
				Set<String> windowhandle = driver.getWindowHandles();
				
				for(String handle:windowhandle)
				{
					WebDriver windowDriver = driver.switchTo().window(handle);
					String aTitle = windowDriver.getTitle();
					
					if(aTitle.equals(titleName))
					{
						
						Log.info("--------------Switched to Tab using Title Successfull.---------------------");
						
						List<WebElement> allLinks = new ArrayList<WebElement>();
						
						allLinks = windowDriver.findElements(By.tagName("a"));
						
						int workLinks=0,brokenLinks=0;
						
						Log.info("Total no of links = "+allLinks.size()+"");
						
						for(WebElement links:allLinks)
						{
							if((links.getAttribute("href")!=null))
							{
								workLinks ++;
							}else
							{
								brokenLinks++;
							}
							
							try {
								Log.info("URL: " + links.getAttribute("href")+" returned " + isLinkBroken(new URL(links.getAttribute("href"))));
							} catch (Exception e) {
								Log.info("At " + links.getAttribute("innerHTML") + " Exception occured -&gt; " + e.getMessage());
							}
						}
						
						Log.info("No of Working links = "+workLinks+"");
						Log.info("No of Broken links = "+brokenLinks+"");
						
						Reporter.info("New opened Tab Title should be "+titleName+".", "New opened tab Title is "+aTitle+".", windowDriver, true);
						
						windowDriver.close();
					}
				}
			}
			
			
			/**
			 * This method will used to find Broken link.
			 * @param url
			 * @return
			 * @throws Exception
			 */
			 
			
			public static String isLinkBroken(URL url) throws Exception
			{

				String response = "";

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				try

				{

					connection.connect();

					response = connection.getResponseMessage();         

					connection.disconnect();

					return response;

				}

				catch(Exception exp)

				{

					return exp.getMessage();

				}   

			}
			
			
			
			/**
			 * This method is used to Window Switch testing
			 * @param switchTab
			 */
			
			public void windowSwitchTest(boolean switchTab)
			{
				try {
					
					driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("windowurl"));
					Log.info("-----------Navigating to URL-------------");
					Utilities.maximizeWindow();
					WaitUtilities.waitForPageToBeLoad(driver);
					
					
					
					WaitUtilities.waitForPageTitleIs(driver, "The Internet", 30);
					
					
					String mainWindow = driver.getWindowHandle();
					
					if(isChrome())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToTabUsingTitle("New Window");
							
						}else
						{
							newWindowBtn.click();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToWindowUsingTitle("New Window");
						}
						
					}else if(isIE())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToTabUsingTitle("New Window");
							
						}else
						{
							newWindowBtn.click();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToWindowUsingTitle("New Window");
						}
						
					}else if(isFirefox())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToTabUsingTitle("New Window");
							
						}else
						{
							newWindowBtn.click();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToWindowUsingTitle("New Window");
						}
					}else if(isEdge())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToTabUsingTitle("New Window");
							
						}else
						{
							Actions act = new Actions(driver);
							act.keyDown(Keys.SHIFT).click(newWindowBtn).build().perform();
							WaitUtilities.waitForNoWindowsOpened(driver, 2);
							switchToWindowUsingTitle("New Window");
						}

					}
					
					driver.switchTo().window(mainWindow);
					Log.info("-----------Switched to Main Window.----------------");
					Reporter.info("should be Switch to Main window.", "Successfully Switch to main window.", driver, true);
					
				} catch (Exception e) {
					Log.error("----------Exception Occured while Switching to Window/Tab---------", e);
				}
					
			}
			
}
