package com.onenetwork.testcases;

import org.testng.annotations.Parameters;
import com.onenetwork.pages.AddressPage;
import com.onenetwork.pages.BootStrapDropdownPage;
import com.onenetwork.pages.BootStrapLoginWindowPage;
import com.onenetwork.pages.CalendarPage;
import com.onenetwork.pages.DownloadPage;
import com.onenetwork.pages.FrameTestingPage;
import com.onenetwork.pages.MenuSelectionPage;
import com.onenetwork.pages.TooltipPage;
import com.onenetwork.pages.WindowTestingPage;
import com.onenetwork.util.BrowserFactory;
import com.onenetwork.util.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
 
	WebDriver driver;
	
	private static List<String> listOfThreads = Collections.synchronizedList(new ArrayList<String>());
	
	private static int i = 1;
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<AddressPage> addrsPage_Object_TL = new ThreadLocal<AddressPage>();
	private static ThreadLocal<FrameTestingPage> framepage_Object_TL = new ThreadLocal<FrameTestingPage>();
	private static ThreadLocal<WindowTestingPage> windowpage_Object_TL = new ThreadLocal<WindowTestingPage>();
	private static ThreadLocal<MenuSelectionPage> menuSelectPage_Object_TL = new ThreadLocal<MenuSelectionPage>();
	private static ThreadLocal<DownloadPage> downloadPage_Object_TL = new ThreadLocal<DownloadPage>();
	private static ThreadLocal<BootStrapLoginWindowPage> bootStrapLoginWindowPage_Object_TL = new ThreadLocal<BootStrapLoginWindowPage>();
	private static ThreadLocal<BootStrapDropdownPage> bootStrapDropdownPage_Object_TL = new ThreadLocal<BootStrapDropdownPage>();
	private static ThreadLocal<TooltipPage> tooltipPage_Object_TL = new ThreadLocal<TooltipPage>();
	private static ThreadLocal<CalendarPage> calendarPage_Object_TL = new ThreadLocal<CalendarPage>();
	private static ThreadLocal<Utilities> Utilities_Object_TL = new ThreadLocal<Utilities>();
	
	
	private static Logger Log = Logger.getLogger(BaseTest.class.getName());
	
  @BeforeClass
  @Parameters({ "browser" })
  public synchronized void setUp(String browser) {
	  
	  
	  synchronized(BaseTest.class)
	  {
		  if(!listOfThreads.contains(Thread.currentThread().getName()))
		  {
			  System.out.println(Thread.currentThread().getName() + " is getting renamed to Thread - "+i);
			  Thread.currentThread().setName("Thread-"+i);
			  listOfThreads.add("Thread-"+ i++);
		  }
	  }
	  
	  
	  setDriver(browser);
	  
	  setAddressPage(new AddressPage(getDriver()));
	  setFrameTestingPage(new FrameTestingPage(getDriver()));
	  setWindowTestingPage(new WindowTestingPage(getDriver()));
	  setMenuSelectionPage(new MenuSelectionPage(getDriver()));
	  setDownloadPage(new DownloadPage(getDriver()));
	  setBootStrapLoginWindowPage(new BootStrapLoginWindowPage(getDriver()));
	  setBootStrapDropdownPage(new BootStrapDropdownPage(getDriver()));
	  setTooltipPage(new TooltipPage(getDriver()));
	  setCalendarPage(new CalendarPage(getDriver()));
	  
	  Log.info("-----------Page object is initialized.-------------");
	  
	  
  }
  
  /**
	  * Set the Driver as per Browser Name passed
	  * @param browsername
	  */
	
	public static void setDriver(String browsername)
	{
		tlDriver.set(BrowserFactory.setDriver(browsername));
		
	}
  
  /**
   * Set Address Page Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setAddressPage(AddressPage obj_AddressPage)
  {
	  BaseTest.addrsPage_Object_TL.set(obj_AddressPage);
  }
  
  /**
   * Set Address Page Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setFrameTestingPage(FrameTestingPage obj_FrameTestingPage)
  {
	  BaseTest.framepage_Object_TL.set(obj_FrameTestingPage);
  }
  
  /**
   * Set WindowTestingPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  
  public static void setWindowTestingPage(WindowTestingPage obj_WindowTestingPage)
  {
	  BaseTest.windowpage_Object_TL.set(obj_WindowTestingPage);
  }
  
  /**
   * Set MenuSelectionPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  
  public static void setMenuSelectionPage(MenuSelectionPage obj_MenuSelectionPage)
  {
	  BaseTest.menuSelectPage_Object_TL.set(obj_MenuSelectionPage);
  }
  
  
  /**
   * Set DownloadPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setDownloadPage(DownloadPage obj_DownloadPage)
  {
	  BaseTest.downloadPage_Object_TL.set(obj_DownloadPage);
  }
  
  /**
   * Set BootStrapLoginWindowPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setBootStrapLoginWindowPage(BootStrapLoginWindowPage obj_BootStrapLoginWindowPage)
  {
	  BaseTest.bootStrapLoginWindowPage_Object_TL.set(obj_BootStrapLoginWindowPage);
  }
  
  /**
   * Set BootStrapDropdownPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setBootStrapDropdownPage(BootStrapDropdownPage obj_BootStrapDropdownPage)
  {
	  BaseTest.bootStrapDropdownPage_Object_TL.set(obj_BootStrapDropdownPage);
  }
  
  
  /**
   * Set TooltipPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setTooltipPage(TooltipPage obj_TooltipPage)
  {
	  BaseTest.tooltipPage_Object_TL.set(obj_TooltipPage);
  }
  
  
  /**
   * Set Utilities Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setUtilities(Utilities obj_Utilities)
  {
	  BaseTest.Utilities_Object_TL.set(obj_Utilities);
  }
  
  /**
   * Set CalendarPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static void setCalendarPage(CalendarPage obj_CalendarPage)
  {
	  BaseTest.calendarPage_Object_TL.set(obj_CalendarPage);
  }
  
  /**
	 * Get driver from tlDriver
	 * @return
	 */
	
  public static WebDriver getDriver () {
      return tlDriver.get();
  }
 
  /**
   * get Address Page Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static AddressPage addrsPage_Object()
  {
	  return addrsPage_Object_TL.get();
  }
  
  /**
   * get Address Page Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static FrameTestingPage framepage_Object()
  {
	  return framepage_Object_TL.get();
  }
  
  /**
   * get WindowTestingPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  
  public static WindowTestingPage windowpage_Object()
  {
	  return windowpage_Object_TL.get();
  }
  
  /**
   * get MenuSelectionPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  
  public static MenuSelectionPage menuSelectPage_Object()
  {
	  return menuSelectPage_Object_TL.get();
  }
  
  
  /**
   * get DownloadPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static DownloadPage downloadPage_Object()
  {
	  return downloadPage_Object_TL.get();
  }
  
  /**
   * get BootStrapLoginWindowPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static BootStrapLoginWindowPage bootStrapLoginWindowPage_Object()
  {
	  return bootStrapLoginWindowPage_Object_TL.get();
  }
  
  /**
   * Set BootStrapDropdownPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static BootStrapDropdownPage bootStrapDropdownPage_Object()
  {
	  return bootStrapDropdownPage_Object_TL.get();
  }
  
  
  /**
   * Get TooltipPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static TooltipPage tooltipPage_Object()
  {
	  return tooltipPage_Object_TL.get();
  }
  
  
  /**
   * get CalendarPage Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static CalendarPage calendarPage_Object()
  {
	  return calendarPage_Object_TL.get();
  }
  
  /**
   * get Utilities Object to ThreadLocal
   * @param obj_AddressPage
   */
  
  public static Utilities getUtilities()
  {
	  return Utilities_Object_TL.get();
  }
  
  
  

  @AfterClass
  public void tearDown() {
	  getDriver().quit();
	  Log.info("-----------Browser is Closed.-------------");
  }

}
