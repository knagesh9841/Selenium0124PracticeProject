package com.onenetwork.util;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

public class OptionsManager {
	
	 //Get Chrome Options
	
    public static ChromeOptions getChromeOptions() {
    	
    	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.default_content_settings.popups", 0);
	    chromePrefs.put("download.default_directory",
				System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
	    chromePrefs.put("profile.default_content_setting_values.notifications", 2);
	    chromePrefs.put("credentials_enable_service", false);
	    chromePrefs.put("profile.password_manager_enabled", false);

	       
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--test-type");
	    options.addArguments("--disable-extensions"); //to disable browser extension popup
	    options.addArguments("--disable-web-security");
	    options.addArguments("--no-proxy-server");
	    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	    options.setExperimentalOption("prefs", chromePrefs);
	    
        //options.addArguments("--incognito");
        
        
        return options;
    }

    //Get Firefox Options
    
    public static FirefoxOptions getFirefoxOptions () {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        
        //Accept Untrusted Certificates
        
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.openFile",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
        		"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        profile.setPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		profile.setPreference( "pdfjs.disabled", true );
        
        //Use No Proxy Settings
        
        profile.setPreference("network.proxy.type", 0);
        
        //SSL Certificates
        
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);

        
        //Set Firefox profile to capabilities
        
        options.setProfile(profile);
        
        return options;
    }
    
    //Get IE Options
    
    public static InternetExplorerOptions getIEOptions()
    {
    	InternetExplorerOptions options = new InternetExplorerOptions();

    	options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    	options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); 
    	options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);         
    	options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR,UnexpectedAlertBehaviour.DISMISS); //Accept unexpected alerts 
    	options.setCapability("requireWindowFocus", true); 
    	options.setCapability("enablePersistentHover", false); 
    	//options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
    	return options;
    }
    
    
//Get Edge Options
    
    public static EdgeOptions getEdgeOptions()
    {
    	EdgeOptions options = new EdgeOptions();

    	//options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    	//options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); 
    	//options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);         
    	//options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR,UnexpectedAlertBehaviour.DISMISS); //Accept unexpected alerts 
    	//options.setCapability("requireWindowFocus", true); 
    	//options.setCapability("enablePersistentHover", false); 
    	//options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
    	return options;
    }

}
