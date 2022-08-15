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

	@Before
	   public void beforeMethod() throws IOException {
		utility.initialize();
	   }

  @Test
  public void Test() throws IOException {
	    app.captureContactDetails();
	    utility.validate();
  }
  
  
  
  @After
  public void afterMethod() throws IOException {
	  
	  	utility.close();
     
  } 
  
  	
  
}
