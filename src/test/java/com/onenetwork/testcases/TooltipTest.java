package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TooltipTest extends BaseTest{
	
	private static Logger Log = Logger.getLogger(TooltipTest.class.getName());
	
	  @Test(description="Tooltip text Functionality.")
	  
	  public void tooltipTest() {
		
		  Log.info("-----------Execution started for Method tooltipTest.-------------");
		  
		  tooltipPage_Object().verifyTooltipText("Tooltip text");
		  
		  Log.info("-----------Execution Completed for Method tooltipTest.-------------");
	  }

}
