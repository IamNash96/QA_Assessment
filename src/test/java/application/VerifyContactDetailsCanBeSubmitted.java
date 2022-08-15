package application;

import com.aventstack.extentreports.ExtentReports;
import data.Data;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reporting.Reporting;
import utilities.Utility;

import java.io.IOException;
public class VerifyContactDetailsCanBeSubmitted {
		
	Utility utility = new Utility();
	Application app = new Application();
	Data data = new Data();
	Reporting report = new Reporting();
	XSSFSheet sheet1 = null;
	String sColumn = null;
	int iRow = 0;
	//XSSFSheet sheet = null;
	String Report = null;
	ExtentReports extent;
	//WebDriver dr = null;
	
  
	@Before
	   public void beforeMethod() throws IOException {
		utility.initialize();
	   }

  @Test
  public void Test() throws IOException {
	    app.captureContactDetails();
	    utility.validate();
		data.columnCount(data.sheet1);
		utility.Reports(utility.Report);
		utility.Test(utility.extent);
		data.getcellData(data.sColumn, data.iRow, data.sheet);
		report.Screen(utility.driver);
		
				
  }
  
  
  
  @After
  public void afterMethod() throws IOException {
	  
	  	utility.close();
     
  } 
  
  	
  
}
