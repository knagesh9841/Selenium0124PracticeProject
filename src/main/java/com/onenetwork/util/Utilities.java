package com.onenetwork.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.onenetwork.testcases.BaseTest;



public class Utilities extends BaseTest{
	
	static Logger Log = Logger.getLogger(Utilities.class.getName());
	
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
		
	/**
	 * This method will upload file using Robot class.
	 * @param filePath
	 * @param uploadBtn
	 * @param driver
	 */
	
	public static void uploadFileWithRobot (String filePath, WebElement uploadBtn, WebDriver driver) {

		try {



			if(driver instanceof ChromeDriver)
			{
				uploadBtn.click();

				StringSelection stringSelection = new StringSelection(filePath);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);

				Robot robot = new Robot();

				robot.delay(250);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.delay(150);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}else
			{
				uploadBtn.sendKeys(filePath);
			}

			Log.info("-----------File is Uploaded Successfully.-------------");

		} catch (Exception e) {

			Log.error("----------File is Not Uploaded Successfully.---------");

		}
	}
	
	/**
	 * This Method will Upload file using Autoit.
	 * @param filePath
	 * @param uploadBtn
	 * @param driver
	 */
	
	public static void uploadFileWithAutoIt (String filePath, WebElement uploadBtn, WebDriver driver) {

		try {



			if(driver instanceof ChromeDriver)
			{
				uploadBtn.click();

				Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\uploadFile.exe"+" "+filePath);
				
				Log.info("-----------File is Uploaded Successfully.-------------");
				
			}else
			{
				uploadBtn.sendKeys(filePath);
			}

			Log.info("-----------File is Uploaded Successfully.-------------");

		} catch (Exception e) {

			Log.error("----------File is Not Uploaded Successfully.---------");

		}
	}
	
	/**
	 * This Method will Upload file using Sikuli.
	 * @param filePath
	 * @param uploadBtn
	 * @param driver
	 * @param inputBox TODO
	 * @param openBtn TODO
	 */
	
	public static void uploadFileWithSikuli (String filePath, WebElement uploadBtn, WebDriver driver, String inputBox, String openBtn) {

		try {



			if(driver instanceof ChromeDriver)
			{
				Screen screen = new Screen();
		        Pattern fileInputTextBox = new Pattern(inputBox);
		        Pattern openButton = new Pattern(openBtn);
		        
				uploadBtn.click();

				screen.wait(fileInputTextBox, explicitlyWait_Type3);
				screen.type(fileInputTextBox, filePath);
				screen.click(openButton);
				
				Log.info("-----------File is Uploaded Successfully.-------------");
				
			}else
			{
				uploadBtn.sendKeys(filePath);
				Log.info("-----------File is Uploaded Successfully.-------------");
			}

			

		} catch (Exception e) {

			Log.error("----------File is Not Uploaded Successfully.---------");

		}
	}
	
	
	/**
	 * This Method will Download file using Sikuli.
	 * @param downloadBtn
	 * @param driver
	 * @param saveBtn TODO
	 * @param closeBtn TODO
	 */
	
	public static void downloadFileWithSikuli (WebElement downloadBtn, WebDriver driver, String saveBtn, String closeBtn) {

		try {


				Screen screen = new Screen();
		        Pattern saveButton = new Pattern(saveBtn);
		        Pattern closeButton = new Pattern(closeBtn);
		        
				//downloadBtn.click();
		        Utilities.clickByJavaScriptExecutor(driver, downloadBtn);
		        WaitUtilities.waitForSleep(5000L);
		        
				screen.wait(saveButton, explicitlyWait_Type3);
				screen.click(saveButton);
				WaitUtilities.waitForSleep(10000L);
				screen.wait(closeButton, explicitlyWait_Type3);
				screen.click(closeButton);
				
				Log.info("-----------File is Downloaded Successfully.-------------");
				
			
			

		} catch (Exception e) {

			Log.error("----------File is Not Downloaded Successfully.---------");

		}
	}
	
	/**
	 * This method will scroll Page using Robot class.
	 * @param isUpScroll TODO
	 */
	
	public static void scrollPageUsingRobotClass(boolean isUpScroll)
	{
		try {
			Robot robot = new Robot();

			if(isUpScroll)
			{
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
			}else
			{
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			}
			
			Log.info("-----------Page is Scrolled Successfully.-------------");
			
		} catch (Exception e) {
			Log.error("----------Page is Not Scrolled Successfully..---------");
		}
	}
	
	/**
	 * This method will scroll Page using Robot class.
	 * @param driver TODO
	 * @param value TODO
	 * @param noOfLines TODO
	 */
	
	public static void scrollPageUsingJavaScript(WebDriver driver, int value, boolean noOfLines)
	{
		try {
			

			JavascriptExecutor js=(JavascriptExecutor) driver;
			
			if(noOfLines)
			{
				js.executeScript("window.scrollByLines("+value+")", "");
			}else
			{
				js.executeScript("window.scrollBy(0,"+value+")", "");
			}
			
			
			Log.info("-----------Page is Scrolled Successfully.-------------");
			
		} catch (Exception e) {
			Log.error("----------Page is Not Scrolled Successfully..---------");
		}
	}
	
	
	/**
	 * This method is used to click by JavaScriptExecutor.
	 * @param driver TODO
	 * @param elementToClick TODO
	 */
	
	public static void clickByJavaScriptExecutor(WebDriver driver, WebElement elementToClick)
	{
		try {
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", elementToClick);
			
		} catch (Exception e) {
			Log.error("---------- Exception Occured while Click by JavaScriptExecutor.---------");
		}
	}
	
	
	
	/**
	 * This method is used to ScrollInto View By JavaScriptExecutor.
	 * @param driver TODO
	 * @param elementToScroll TODO
	 */
	
	public static void scrollIntoViewByJavaScriptExecutor(WebDriver driver, WebElement elementToScroll)
	{
		try {
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
			
		} catch (Exception e) {
			Log.error("---------- Exception Occured while ScrollInto View By JavaScriptExecutor.---------");
		}
	}
	
	/**
	 * This method will return Date based on days Specified and Date Format.
	 * @param noOfdays
	 * @param dateFormat
	 * @return
	 */
	
	public static String getRequiredDateBasedonDateFormat(int noOfdays, String dateFormat)
	{
		
		String aDate=null;
		
		try {
			
			LocalDate currentDate = LocalDate.now();
			LocalDate eDate = currentDate.plusDays(noOfdays);
			DateTimeFormatter format = DateTimeFormatter.ofPattern(""+dateFormat+""); 
			aDate = eDate.format(format);
			
		} catch (Exception e) {
			Log.error("---------- Exception Occured while returning required Date.---------");
		} 
		
		return aDate;
		
	}
	
	
	/**
	 * This method will return DateTime based on days Specified and DateTime Format.
	 * @param noOfdays
	 * @param dateFormat
	 * @return
	 */
	
	public static String getRequiredDateTimeBasedonDateTimeFormat(int noOfdays, String dateFormat)
	{
		
		String aDateTime=null;
		
		try {
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			LocalDateTime eDateTime = currentDateTime.plusDays(noOfdays);
			DateTimeFormatter format = DateTimeFormatter.ofPattern(""+dateFormat+""); 
			aDateTime = eDateTime.format(format);
			
		} catch (Exception e) {
			Log.error("---------- Exception Occured while returning required DateTime.---------");
		} 
		
		return aDateTime;
		
	}
	
	/** 
	 * This Method will execute Query on Oracle Database and return result.
	 * @param query
	 * @return
	 */
	
	public static String fetchDataFromDatabase(String query)
	{
		String resultOfQuery=null;
		Connection con=null;
		Statement stmt=null;
		
		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Log.info("------My Sql Driver is registered successfully------");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Nagesh","root","root");
			Log.info("------Connection With Database is Established successfully------");
			
			stmt=con.createStatement();
			Log.info("------Statement is created successfully------");
			
			ResultSet rs = null;
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				resultOfQuery = rs.getString(1);
				Log.info("------Record Found.------");
			}
			
			con.close();
			Log.info("-----Connection closed successfully------");
			
			
		} catch (SQLException e) {
			Log.error("---------- Exception Occured while Fetching data from Database.---------");
		}
		
				
		return resultOfQuery;
		
	}
	
	
	
	
	/**
	 * This will maximize the window
	 */

	@SuppressWarnings("deprecation")
	public static void maximizeWindow()
	{
		try {
			getDriver().manage().window().maximize();
			Dimension dim = new Dimension(Integer.parseInt(PropertyManager.getInstance("configuration").getConfigTimeData("window-width")),Integer.parseInt(PropertyManager.getInstance("configuration").getConfigTimeData("window-height")));
			Point pnt = new Point(0, 0);
			getDriver().manage().window().setSize(dim);
			getDriver().manage().window().setPosition(pnt);
			Log.info("-----------Window is Maximized.-------------");
			getDriver().manage().timeouts().implicitlyWait(Long.valueOf(PropertyManager.getInstance("configuration").getConfigTimeData("implicitlywait")), TimeUnit.SECONDS);
			Log.info("-----------implicitly Wait is set to "+PropertyManager.getInstance("configuration").getConfigTimeData("implicitlywait")+" Seconds.-------------");
			getDriver().manage().timeouts().pageLoadTimeout(Long.valueOf(PropertyManager.getInstance("configuration").getConfigTimeData("pageLoadTimeout")), TimeUnit.SECONDS);
			Log.info("-----------Page Load Timeout is set to "+PropertyManager.getInstance("configuration").getConfigTimeData("pageLoadTimeout")+" Seconds.-------------");
			getDriver().manage().timeouts().setScriptTimeout(Long.valueOf(PropertyManager.getInstance("configuration").getConfigTimeData("scriptTimeOut")), TimeUnit.SECONDS);
			Log.info("-----------Script Timeout is set to "+PropertyManager.getInstance("configuration").getConfigTimeData("scriptTimeOut")+" Seconds.-------------");
			getDriver().manage().deleteAllCookies();
			Log.info("-----------Cookies Deleted.-------------");
		} catch (Exception e) {
			Log.error("---------- Exception Occured while Maximizing window.---------");
		}
	}
	
	
	
	
	/**
	 * This method will kill Browser error window.
	 */
	protected static void killBrowserErrorWindow()
	{
		try {
			
			Process processErrWin = Runtime.getRuntime().exec("taskkill /F /T /IM WerFault.exe");
			processErrWin.waitFor();
			
			processErrWin = Runtime.getRuntime().exec("taskkill /F /T /IM WerFault.exe *32");
			processErrWin.waitFor();
			
		} catch (Exception e) {
			
			Log.warn("------Failed to kill Browser error window.------");
		}
	}
	
	
	/**
	 * This method will kill Browser and Driver Process.
	 * @param browserName 
	 */
	
	public static void killBrowserAndDriver(String browserName)
	{
		String browserProcess = null,driverProcess = null;
		
		switch(browserName)
		{
		    case "ie":
		    	browserProcess = "iexplore";
		    	driverProcess = "IEDriverServer.exe";
		    	break;
		    case "chrome":
		    	browserProcess = "chrome";
		    	driverProcess = "chromedriver.exe";
		    	break;
		    case "edge":
		    	browserProcess = "MicrosoftEdge";
		    	driverProcess = "MicrosoftWebDriver.exe";
		    	break;
		    case "firefox":
		    	browserProcess = "firefox";
		    	driverProcess = "GeckoDriver.exe";
		    	break;
		    default:
		    	Log.warn("------Entered Wrong Browser Name.------");
		    	break;
		}
		
		try {
			
			killBrowserErrorWindow();
			
			Process processDriver = Runtime.getRuntime().exec("taskkill /F /T /IM "+ driverProcess);
			Process processBrowser = Runtime.getRuntime().exec("taskkill /F /T /IM "+ browserProcess + ".exe");
			
			processDriver.waitFor();
			processBrowser.waitFor();
			
			Log.info("Driver process "+driverProcess+" and Browser process "+browserProcess+" is killed successfully.");
			
		} catch (Exception e) {
			
			Log.warn("------Failed to kill Browser or Driver.------");
		}
		
	}
	
	
	public static boolean genericClick(
		    WebElement elementToClick,
		    By elementToVerify,
		    String elementType,
		    String isEnableOrDisableCheckBox) {
		    try {
		      
		    	WebDriver driver = getDriver();
		       Actions actions = new Actions(driver);

		      if (elementType.equalsIgnoreCase("Button")) {
		        elementToClick.click();
		        Log.info("Clicked By--> Selenium Click");
		        
		        boolean clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          clickByJavaScriptExecutor(driver,elementToClick);
		          Log.info("Clicked By--> Java Script executor");
		          
		        }
		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          actions.moveToElement(elementToClick).click(elementToClick).build().perform();
		          Log.info("Clicked By--> Action Click");
		          
		        }
		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.sendKeys(Keys.ENTER);
		          Log.info("Clicked By--> Send Keys Enter");
		          
		        }

		      }
		      else if (elementType.equalsIgnoreCase("Link")) {
		        clickByJavaScriptExecutor(driver,elementToClick);
		        Log.info("Clicked By--> Java Script executor");
		        
		        boolean clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          actions.moveToElement(elementToClick).click(elementToClick).build().perform();
		          Log.info("Clicked By--> Actions Click");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.click();
		          Log.info("Clicked By--> Selenium Click");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          actions.moveToElement(elementToClick).moveByOffset(0, 3).click().perform();
		          
		          Log.info("Clicked By--> OffSet Click");
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.sendKeys(Keys.ENTER);
		          Log.info("Clicked By--> Send Keys Enter");
		          
		        }

		      }

		      else if (elementType.equalsIgnoreCase("NoteLink")) {
		        actions.moveToElement(elementToClick).moveByOffset(0, 3).click().perform();
		        
		        Log.info("Clicked By--> OffSet Click");
		        boolean clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);

		        if (!clickStatus) {
		          clickByJavaScriptExecutor(driver,elementToClick);
		          Log.info("Clicked By--> Java Script executor");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.click();
		          Log.info("Clicked By--> Selenium Click");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          actions.moveToElement(elementToClick).click(elementToClick).build().perform();
		          Log.info("Clicked By--> Actions Click");
		          
		        }

		      }

		      else if (elementType.equalsIgnoreCase("CrossSymbol")) {
		        actions.moveToElement(elementToClick).moveByOffset(0, 3).click().perform();
		        
		        Log.info("Clicked By--> OffSet Click");
		        boolean clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.sendKeys(Keys.ENTER);
		          
		          Log.info("Clicked By--> Send Keys Enter");
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          clickByJavaScriptExecutor(driver,elementToClick);
		          Log.info("Clicked By--> Java Script executor");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.click();
		          Log.info("Clicked By--> Selenium Click");
		          
		        }

		      }
		      else if (elementType.equalsIgnoreCase("CheckBox")) {
		        clickByJavaScriptExecutor(driver,elementToClick);
		        
		        Log.info("Clicked By--> Java Script executor");
		        if (isEnableOrDisableCheckBox.equalsIgnoreCase("enabled") && (!elementToClick.isSelected())) {
		          actions.moveToElement(elementToClick).moveByOffset(0, 3).click().perform();
		          Log.info("Clicked By--> Offset Click");
		        }
		        else if (isEnableOrDisableCheckBox.equalsIgnoreCase("disabled") && (elementToClick.isSelected())) {
		          actions.moveToElement(elementToClick).moveByOffset(0, 3).click().perform();
		          Log.info("Clicked By--> Offset Click");
		        }

		        if (isEnableOrDisableCheckBox.equalsIgnoreCase("enabled") && (!elementToClick.isSelected())) {
		          actions.moveToElement(elementToClick).click(elementToClick).build().perform();
		          Log.info("Clicked By--> Action Click");
		        }
		        else if (isEnableOrDisableCheckBox.equalsIgnoreCase("disabled") && (elementToClick.isSelected())) {
		          actions.moveToElement(elementToClick).click(elementToClick).build().perform();
		          Log.info("Clicked By--> Action Click");
		        }
		      }

		      else if (elementType.equalsIgnoreCase("contextMenu")) {
		        clickByJavaScriptExecutor(driver,elementToClick);
		        Log.info("Clicked By--> Java Script executor");
		        
		        boolean clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, 20);
		        if (!clickStatus) {
		          actions.moveToElement(elementToClick).click(elementToClick).build().perform();
		          Log.info("Clicked By--> Actions Click");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.click();
		          Log.info("Clicked By--> Selenium Click");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.sendKeys(Keys.ENTER);
		          Log.info("Clicked By--> Send Keys Enter");
		          
		        }

		      }

		      else if (elementType.equalsIgnoreCase("priorityDownUpArrow")) {
		        actions.moveToElement(elementToClick).moveByOffset(0, 3).click().perform();
		        
		        Log.info("Clicked By--> OffSet Click");
		        boolean clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          actions.moveToElement(elementToClick).click(elementToClick).build().perform();
		          Log.info("Clicked By--> Actions Click");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          clickByJavaScriptExecutor(driver,elementToClick);
		          Log.info("Clicked By--> Java Script executor");
		          
		        }
		        
		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          JavascriptExecutor executor = (JavascriptExecutor) driver;
		          executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", elementToClick);
		          Log.info("Clicked By--> Java Script executor");
		          
		        }

		        clickStatus = WaitUtilities.waitForElementVisible(driver,elementToVerify, explicitlyWait_Type3);
		        if (!clickStatus) {
		          elementToClick.click();
		          Log.info("Clicked By--> Selenium Click");
		          
		        }

		      }

		      return true;

		    }
		    catch (Exception e) {
		      throw e;
		    }
		  }

}
