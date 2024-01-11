package com.onenetwork.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static String reportFileName = "ExtentReports-Version3-Test-Automaton-Report.html";
	private static String windowsPath = System.getProperty("user.dir")+ "\\TestReport";
	private static String windowsUniquePath = System.getProperty("user.dir")+ "\\UniqueTestReport";

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	//Create an extent report instance
	public static ExtentReports createInstance() {
		createReportPath(windowsPath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(windowsPath+"\\"+reportFileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(reportFileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}

	private static void createReportPath (String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!" );
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}
	
	
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
		} finally {
			is.close();
			os.close();
		}
	}
	
	public static void copyLatestExtentReport() throws IOException {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_");
		File source = new File(System.getProperty("user.dir") + "\\TestReport\\ExtentReports-Version3-Test-Automaton-Report.html");
		
		createReportPath(windowsUniquePath);
		
		File dest = new File(System.getProperty("user.dir") + "\\UniqueTestReport\\" + date.toString() + ".html");
		
		copyFileUsingStream(source, dest);
	}

}
