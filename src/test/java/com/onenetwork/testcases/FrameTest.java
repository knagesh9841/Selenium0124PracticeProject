package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class FrameTest extends BaseTest {
	
	private static Logger Log = Logger.getLogger(FrameTest.class.getName());
	
	  @Test(description="Find out Element in IFrame.")
	  
	  public void frameTest() throws InterruptedException {
		
		  Log.info("-----------Execution started for Method frameTest.-------------");
		  
		  framepage_Object().verifyElementInIFrame("FrameTest", true, "Baby Cat", "Not a Friendly Topic");
		  
		  Log.info("-----------Execution Completed for Method frameTest.-------------");
	  }
}
