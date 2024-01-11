package com.onenetwork.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {


	static WebDriver driver;
	private static Logger Log = Logger.getLogger(BrowserFactory.class.getName());

	/**
	 * Set the Driver as per Browser Name passed
	 * @param browsername
	 */

	public synchronized static WebDriver setDriver(String browsername)
	{


		try {


			if(browsername.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");

			}
			else if(browsername.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(OptionsManager.getChromeOptions());
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");

			}
			else if(browsername.equalsIgnoreCase("ie"))
			{

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver(OptionsManager.getIEOptions());
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
			}
			else if(browsername.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver(OptionsManager.getEdgeOptions());
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");

			}else
			{
				Log.info("-----------Wrong browser Name.-------------");
			}
		} catch (Exception e) {

			Log.error("----------Exception occured when "+browsername+" Browser is initialized.---------", e);
		}
		return driver;



	}




}
