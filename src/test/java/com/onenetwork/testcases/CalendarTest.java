package com.onenetwork.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {
	
	private static Logger Log = Logger.getLogger(CalendarTest.class.getName());
	
	  @Test(description="Calendar Date Selection Functionality.")
	  
	  public void calendarTest() {
		
		  Log.info("-----------Execution started for Method calendarTest.-------------");
		  
		  calendarPage_Object().verifyCalendarDate("25");
		  
		  Log.info("-----------Execution Completed for Method calendarTest.-------------");
	  }

}
