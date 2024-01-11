package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class MenuSelectionTest extends BaseTest {
	
	private static Logger Log = Logger.getLogger(MenuSelectionTest.class.getName());
	
	  @Test(description="Select Menu.")
	  public void menuSelectionTest() throws InterruptedException {
		
		  Log.info("-----------Execution started for Method menuSelectionTest.-------------");
		  
		  menuSelectPage_Object().verifyMenuSelection("Cypress Tutorial for Beginners: Getting started with End to End Testing");
		  
		  Log.info("-----------Execution Completed for Method menuSelectionTest.-------------");
	  }

}
