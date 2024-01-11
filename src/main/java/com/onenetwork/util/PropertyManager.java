package com.onenetwork.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	 private static PropertyManager instance;
	    private static final Object lock = new Object();
	    Properties prop;
	    
	    //Create a Singleton instance. We need only one instance of Property Manager.
	    public static PropertyManager getInstance (String fileName) {
	        
	            synchronized (lock) {
	                instance = new PropertyManager();
	                instance.loadData(fileName);
	            }
	        return instance;
	    }
	 
	    //Get all configuration data and assign to related fields.
	    private void loadData(String fileName) {
	        //Declare a properties object
	        prop = new Properties();
	 
	        //Read configuration.properties file
	        try {
	        	String propertyFilePath = System.getProperty("user.dir")+
	    	            "\\src\\main\\java\\com\\onenetwork\\resources\\config\\"+fileName+".properties";
	            prop.load(new FileInputStream(propertyFilePath));
	            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
	        } catch (IOException e) {
	            System.out.println("Configuration properties file cannot be found");
	        }
	 
	       
	    }
	    
	    public String getConfigTimeData(String key)
	    {
	    	return prop.getProperty(key);
	    }
	 
	   

}
