package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class BootStrapDropdownTest extends BaseTest {
	
	private static Logger Log = Logger.getLogger(BootStrapDropdownTest.class.getName());
	
	  @Test(description="Bootstrap Drop Down Functionality.")
	  
	  public void bootStrapDropdownTest() {
		
		  Log.info("-----------Execution started for Method bootStrapDropdownTest.-------------");
		  
		  bootStrapDropdownPage_Object().verifyBootstrapDropdown("HTML");
		  
		  Log.info("-----------Execution Completed for Method bootStrapDropdownTest.-------------");
	  }


}
