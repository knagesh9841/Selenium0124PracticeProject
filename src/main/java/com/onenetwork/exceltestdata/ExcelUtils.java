package com.onenetwork.exceltestdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelUtils {
	
	
	//*********Variables*********
	
	 private static Logger Log = Logger.getLogger(ExcelUtils.class.getName());
	 
	 private static FileInputStream excelFile;
	 private static XSSFSheet ExcelWSheet;
	 private static XSSFWorkbook ExcelWBook;
	 private static XSSFRow ExcelRow;
	 private static XSSFCell ExcelCell;
	 
	 
	 
	//*********Methods*********
	
	/**
	 * This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	 * @param fileName TODO
	 * @param sheetName TODO
	 */
	
	public static void setExcelFile(String fileName, String sheetName)
	{
		try {
			
			
			excelFile = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\onenetwork\\testdata\\"+fileName));
			ExcelWBook = new XSSFWorkbook(excelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
		} catch (Exception e) {
		
			Log.error("----------Exception Occured while Setting Path and Sheet for reading Excel file---------", e);
		}
		
	}
	
	/**
	 * This method will return the Value from excel file based on Column Name Specified.
	 * @param testData TODO
	 * @param colName TODO
	 * @return TODO
	 */
	
	
	public static String getData(HashMap<String, String> testData, String colName)
	{
		String data = null;
		
		try {
			
			data = testData.get(colName);
			
			return data;
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while reading data from Excel file---------", e);
			return data;
		}
		
		
			
	}
	
	/**
	 * This method is to read the test data from the Excel cell, in this we are passing parameters as FileName,SheetName ,Row num.
	 * @param fileName TODO
	 * @param sheetName TODO
	 * @param rowNo TODO
	 * @return TODO
	 */
	
	
	public static HashMap<String, String> loadData(String fileName, String sheetName, int rowNo)
	{
		HashMap<String, String> excelData = null;
		
		try {
			
			excelData = new HashMap<String,String>();
			setExcelFile(fileName, sheetName);
			ExcelRow = ExcelWSheet.getRow(0);
			int noOfCol =  ExcelRow.getLastCellNum();
			
			String header[] = new String[noOfCol];
			
			for(int cnt =0;cnt<header.length;cnt++)
			{
				header[cnt] = ExcelRow.getCell(cnt).getStringCellValue();
			}
			
			ExcelRow = ExcelWSheet.getRow(rowNo);
			
			for(int cnt =0;cnt<header.length;cnt++)
			{
				ExcelCell = ExcelRow.getCell(cnt);
				CellType type = ExcelCell.getCellType();
				String data = null;
				switch (type) {
				case STRING:
					data = ExcelCell.getStringCellValue();
					
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(ExcelCell)) {
		                
						data =  ExcelCell.getDateCellValue() + "";
		               
		            } else
		            {
		            	data = new BigDecimal(ExcelCell.getNumericCellValue()).toPlainString();
		            }
					
					
					break;

				default:
					break;
				}
				
				excelData.put(header[cnt], data);
			}
			
			Log.info("----------File "+fileName+" is loaded successfully.----------");
			
			return excelData;
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while reading data from Excel file---------", e);
			return excelData;
			
		}
	}
	
	/**
	 * This method will set data in Excel File.
	 * @param fileName TODO
	 * @param sheetName TODO
	 * @param rowNo TODO
	 * @param colHeader TODO
	 * @param colValue TODO
	 */
	
	public static void writeDataToExcelFile(String fileName, String sheetName, int rowNo, String[] colHeader, String[] colValue)
	{
		try {
			
			setExcelFile(fileName, sheetName);
			ExcelRow = ExcelWSheet.getRow(0);
			int noCols = ExcelRow.getLastCellNum();
			
			String [] headername = new String[noCols];
			
			for(int cnt = 0;cnt<headername.length;cnt++)
			{
				headername[cnt] = ExcelRow.getCell(cnt).getStringCellValue();
			}
			
			ExcelRow = ExcelWSheet.getRow(rowNo);
			
			for(int cnt = 0;cnt < colHeader.length;cnt++)
			{

				for(int inCnt = 0;inCnt < headername.length;inCnt++)
				{
					if(headername[inCnt].equals(colHeader[cnt]))
					{
						ExcelCell = ExcelRow.getCell(inCnt);

						if (ExcelCell == null) {

							ExcelCell = ExcelRow.createCell(inCnt);

							ExcelCell.setCellValue(colValue[cnt]);

						} else {

							ExcelCell.setCellValue(colValue[cnt]);

						}
						
						break;
					}
				}
			}
			
			
			FileOutputStream fileOut = new FileOutputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\onenetwork\\testdata\\"+fileName));
			ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while Writting data to Excel file---------", e);
		}
	}

}
