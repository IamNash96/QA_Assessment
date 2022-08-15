package data;

//import java.io.FileReader;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;

public class Data {
	
	
	public static String sColumn;
	public static int iRow;
	public static XSSFSheet sheet;
	public String getcellData(String sColumn, int iRow, XSSFSheet sheet) throws IOException {
		  this.sColumn = sColumn;
		  this.iRow = iRow;
		  this.sheet = sheet;
		  
	  	String sValue = "";
	  		
			XSSFRow row = sheet.getRow(0);
			
			//int count = columnCount(sheet);
			for (int i = 0 ; i<columnCount(sheet) ; i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(sColumn))
				{
				
				XSSFRow raw = sheet.getRow(iRow);
				XSSFCell cell = raw.getCell(i);
				DataFormatter formatter = new DataFormatter();
				sValue = formatter.formatCellValue(cell);
				break;
				}
			}
	  return sValue ;
}
	
	public static XSSFSheet sheet1;
	public int columnCount(XSSFSheet sheet1) {
		
		this.sheet1 = sheet1;
		  return sheet1.getRow(0).getLastCellNum(); 
		  
	}
	  
	

}
