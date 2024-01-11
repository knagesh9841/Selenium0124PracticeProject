package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class WindowTest extends BaseTest{
 
	private static Logger Log = Logger.getLogger(WindowTest.class.getName());
	
	  @Test(description="Find out Element in Window.")
	  public void windowTest() throws InterruptedException {
		
		  Log.info("-----------Execution started for Method windowTest.-------------");
		  
		  windowpage_Object().windowSwitchTest(false);
		  
		  Log.info("-----------Execution Completed for Method windowTest.-------------");
	  }
	  
}
