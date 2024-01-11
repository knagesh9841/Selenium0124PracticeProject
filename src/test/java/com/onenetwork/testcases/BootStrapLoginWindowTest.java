package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class BootStrapLoginWindowTest extends BaseTest{
	
	private static Logger Log = Logger.getLogger(BootStrapLoginWindowTest.class.getName());
	
	  @Test(description="Bootstrap Login Window Functionality.")
	  
	  public void bootStrapLoginWindowTest() {
		
		  Log.info("-----------Execution started for Method bootStrapLoginWindowTest.-------------");
		  
		  bootStrapLoginWindowPage_Object().verifyBootstrapLoginWindow("Nagesh", "Nagesh");
		  
		  Log.info("-----------Execution Completed for Method bootStrapLoginWindowTest.-------------");
	  }

}
