package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
	
	
	private static Logger Log = Logger.getLogger(LoginTest.class.getName());
		
	 
	  
	  @Test(description="Enter Address,Verify Address,Delete Address,Verify Deleted Address.")
	  public void addPersonalDetails()
	  {
		  Log.info("-----------Execution started for Method addPersonalDetails.-------------");
		  
		  addrsPage_Object().fillAndVerifyPersonalDetails();
		 
		  Log.info("-----------Execution Completed for Method addPersonalDetails.-------------");
		   
	  }
	  
	 
}
