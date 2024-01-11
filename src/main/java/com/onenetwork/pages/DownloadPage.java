package com.onenetwork.pages;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.onenetwork.testcases.BaseTest;
import com.onenetwork.util.PropertyManager;
import com.onenetwork.util.Reporter;
import com.onenetwork.util.Utilities;
import com.onenetwork.util.WaitUtilities;

public class DownloadPage extends BaseTest{
	
	//*********Page Variables*********
	
		private WebDriver driver;
	    private static Logger Log = Logger.getLogger(DownloadPage.class.getName());
		HashMap<String, String> testData = new HashMap<String,String>();
		String testDataHolder;
		String downloadDir = System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles";
		String ieEdgeDownloadDir = System.getProperty("user.home")+ File.separator + "Downloads";
		
		//*********Constructor*********
		
		public DownloadPage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		// Load Explicitly Wait

		private static int explicitlyWait_Type1;
		private static int explicitlyWait_Type2;
		private static int explicitlyWait_Type3;
		private static int explicitlyWait_Type4;

		static
		{
			loadProperties();
		}


		private static void loadProperties()
		{
			Log.info("-----------Loading GlobalWait.Properties File-------------");
			explicitlyWait_Type1 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type1"));
			explicitlyWait_Type2 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type2"));
			explicitlyWait_Type3 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type3"));
			explicitlyWait_Type4 = Integer.parseInt(PropertyManager.getInstance("GlobalWait").getConfigTimeData("explicitlyWait_Type4"));

		}

		// url = https://www.toolsqa.com/automation-practice-form/
		
		//*********Web Elements*********
		
		@FindBy(how=How.XPATH,using="//a[text()='Download']")
		@CacheLookup
		public WebElement downloadBtn;
		
		@FindBy(how=How.XPATH,using="//input[@id='uploadFile']")
		@CacheLookup
		public WebElement uploadBtn;
		
		@FindBy(how=How.XPATH,using="//p[@id='uploadedFilePath']")
		@CacheLookup
		public WebElement uploadedfile;
		
		
		// It Wont throw error even if Element is not present in DOM
		@FindBy(how=How.XPATH,using="//nagesh[@id='uploade']")
		public WebElement test;
		
		//*********Page Methods*********
		
		/**
		 * This Method will download the file.
		 */

		public void verifyDownloadedFile()
		{
			try {
				driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("downloadurl"));

				Log.info("-----------Navigating to URL-------------");

				//Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);

				WaitUtilities.waitForPageTitleIs(driver, "DEMOQA", explicitlyWait_Type4);

				deleteDownloadFolderPath();
				String path = createFolderPath();

				
				if(driver instanceof FirefoxDriver || driver instanceof ChromeDriver)
				{
					downloadBtn.click();
					WaitUtilities.waitForSleep(5000L);
					
				}else if(driver instanceof EdgeDriver)
				{
					Utilities.downloadFileWithSikuli(downloadBtn, driver, System.getProperty("user.dir") + "\\downloadImages\\SaveBtn.PNG", System.getProperty("user.dir") + "\\downloadImages\\CloseBtn.PNG");
					WaitUtilities.waitForSleep(10000L);

				}else if(driver instanceof InternetExplorerDriver)
				{
					Utilities.scrollIntoViewByJavaScriptExecutor(driver, downloadBtn);
					Utilities.downloadFileWithSikuli(downloadBtn, driver,
							System.getProperty("user.dir") + "\\downloadImages\\SaveBtnIE.PNG",
							System.getProperty("user.dir") + "\\downloadImages\\CloseBtnIE.PNG");
					WaitUtilities.waitForSleep(10000L);
				}

				export(path);
				verifyFileisCopiedOrNot(path+ File.separator + "SampleFile.jpeg");
			} catch (Exception e) {
				
				Log.warn("-----------Exception Occured while verifying Downloaded file.-----------");
				
			}

		}
		
		
		/**
		 * This method will create Existing folder and Create New Folder.
		 * @return
		 */
		
		public String createFolderPath()
		{
			String projectPath = null;
			try {
				projectPath = System.getProperty("user.dir");
				projectPath = projectPath.split(":")[0];
				projectPath = projectPath+":\\Export";
				File file = new File(projectPath);

				FileUtils.deleteDirectory(file);
				Log.info("-----------Folder Deleted Successfully.-------------");

				file.mkdir();
				Log.info("-----------Folder Created Successfully.-------------");
				return projectPath;
			} catch (Exception e) {
				Log.warn("-----------Exception Occured while Creating/Deleting Folder.-----------");
				return projectPath;
			}
		}
		
		/**
		 * This method will delete Download Directory 
		 */
		
		public void deleteDownloadFolderPath()
		{
			try {
				File file = new File(downloadDir);
				FileUtils.deleteDirectory(file);
				Log.info("-----------Downloaded Folder is Deleted Successfully.-------------");
			} catch (IOException e) {
				Log.warn("-----------Downloaded Folder is Not Deleted Successfully.------------------");
			}
		}
		
		
		/**
		 * This method will copy file from Downloaded Directory to Specified Directory.
		 * @param filePath
		 */
		
		public void export(String filePath)
		{
			try {
				if(driver instanceof ChromeDriver || driver instanceof FirefoxDriver)
				{
					File path = new File(downloadDir);
					List<File> sortedfiles = Arrays.asList(path.listFiles()).stream().sorted((file1,file2) -> {return file1.lastModified() > file2.lastModified() ? -1 : 1;}).collect(Collectors.toList());
					
					if(sortedfiles.size() > 0)
					{
						File downloadedFile = sortedfiles.get(0);
						File destPath = new File(filePath);
						try {
							FileUtils.copyFileToDirectory(downloadedFile, destPath);
							
							Log.info("-----------File Copied Successfully.-------------");
						} catch (IOException e) {
							Log.warn("-----------Unable to copy File.");
						}
					}
				}else
				{
					File path = new File(ieEdgeDownloadDir);
					List<File> sortedfiles = Arrays.asList(path.listFiles()).stream().sorted((file1,file2) -> {return file1.lastModified() > file2.lastModified() ? -1 : 1;}).collect(Collectors.toList());
					
					if(sortedfiles.size() > 0)
					{
						File downloadedFile = sortedfiles.get(0);
						File destPath = new File(filePath);
						try {
							FileUtils.copyFileToDirectory(downloadedFile, destPath);
							
							Log.info("-----------File Copied Successfully.-------------");
						} catch (IOException e) {
							Log.warn("-----------Unable to copy File.");
						}
					}
				}
			} catch (Exception e) {
				Log.warn("-----------Exception Occured while copying File.");
			}
		}
		
		
		public void verifyFileisCopiedOrNot(String path)
		{
			try {
				File file = new File(path);
				if(file.exists())
				{
					
					Reporter.pass("File should be exists at Location "+path+".", "File is exists at Location "+path+".", driver, true);
					Log.info("----------File is exists at Location "+path+".--------------");
					
				}else
				{
					Reporter.fail("File should be exists at Location "+path+".", "File is not exists at Location "+path+".", driver, false);
					 
					Log.info("-----------File is not exists at Location "+path+".-------------");
				}
			} catch (Exception e) {
				
				Log.error("----------Exception Occured while Verifying Copied File.---------", e);
			}
		}
		
		
		/**
		 * This method will upload file
		 */
		
		public void uploadFile()
		{

			try {

				driver.get(PropertyManager.getInstance("configuration").getConfigTimeData("downloadurl"));

				Log.info("-----------Navigating to URL-------------");

				//Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);

				WaitUtilities.waitForPageTitleIs(driver, "DEMOQA", explicitlyWait_Type4);



				//String autoITFile = System.getProperty("user.dir")+"\\UploadFile.exe";
				String uploadFileName = System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles" + File.separator +"sampleFile.jpeg";

				Log.info("-----------Clicked on Upload Button.-------------");

				uploadBtn.sendKeys(uploadFileName);

				//Runtime.getRuntime().exec(autoITFile +" " + uploadFileName);


				String file = uploadedfile.getText();

				if(file.equals("C:\\fakepath\\sampleFile.jpeg")) {

					Reporter.pass("File should be exists at Uploaded.", "File is Uploaded.",
							driver, true); Log.info("----------File is Uploaded.--------------");

				}else { Reporter.fail("File should be exists at Uploaded.",
						"File is No tUploaded.", driver, false);

				Log.info("----------File is Not Uploaded.--------------"); }





			} catch (Exception e) {

				Log.error("----------Exception Occured while Uploading File.---------", e);
			}


		}

}
