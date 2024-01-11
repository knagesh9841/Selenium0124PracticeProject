package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class DownloadTest extends BaseTest{
	
	private static Logger Log = Logger.getLogger(DownloadTest.class.getName());
	
	  @Test(description="Download Functionality.")
	  
	  public void downloadTest() {
		
		  Log.info("-----------Execution started for Method downloadTest.-------------");
		  
		  downloadPage_Object().verifyDownloadedFile();
		  downloadPage_Object().uploadFile();
		  
		  Log.info("-----------Execution Completed for Method downloadTest.-------------");
	  }

}
