package application;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import data.Data;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Application {

	Data data = new Data();
	Utility utility = new Utility();
	ExtentReports extent;
	String report;
	//String excel = null, txtName = null, txtEmployees = null, txtService = null, checkboxAgree = null, btnSubmit = null, inputEmail = null, inputNumber = null, message = null, submit = null;

	public void captureContactDetails() throws IOException {
		try{
		//Excel file path	
		File file = new File(utility.excel);
		FileInputStream fileStream = new FileInputStream(file);

		// workbook 
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		XSSFSheet mySheet = workbook.getSheet("Sheet1");
		int rowCount = mySheet.getPhysicalNumberOfRows(); //Getting the number of rows
		for(int i = 1;i<rowCount;i++) //Fetching the data from excel file and looping through it
		{

			    //Switch to iFrame
			utility.driver.switchTo().frame(utility.driver.findElement(By.xpath("//*[@title='Form 0']")));


			  utility.driver.findElement(By.xpath(utility.txtName)).clear();
			  utility.driver.findElement(By.xpath(utility.txtName)).sendKeys(data.getcellData("name",i,mySheet));
			  utility.ExtentLogPass(utility.driver, "Name Captured", utility.test, true, "Report\\MyReport");

			//Clear field and enter email
			  utility.driver.findElement(By.xpath(utility.inputEmail)).clear();
			  utility.driver.findElement(By.xpath(utility.inputEmail)).sendKeys(data.getcellData("email",i,mySheet));
			  utility.ExtentLogPass(utility.driver, "Email Captured", utility.test, true, "Report\\MyReport");

			  //Clear field and enter phone number
			  utility.driver.findElement(By.xpath(utility.inputNumber)).clear();
			  utility.driver.findElement(By.xpath(utility.inputNumber)).sendKeys(data.getcellData("phone",i,mySheet));
			  utility.ExtentLogPass(utility.driver, "Phone Number Captured", utility.test, true, "Report\\MyReport");

			  //Select number of employees
			  utility.driver.findElement(By.xpath(utility.txtEmployees)).click();
			  Select numEmployees = new Select(utility.driver.findElement(By.xpath(utility.txtEmployees)));
			  numEmployees.selectByVisibleText(data.getcellData("employeeNum", i, mySheet));
			  utility.ExtentLogPass(utility.driver, "Employee Number Captured", utility.test, true, "Report\\MyReport");

			  //Select service
			  utility.driver.findElement(By.xpath(utility.txtService)).click();
			  Select service = new Select(utility.driver.findElement(By.xpath(utility.txtService)));
			  service.selectByVisibleText(data.getcellData("service", i, mySheet));
			  utility.ExtentLogPass(utility.driver, "Service Captured", utility.test, true, "Report\\MyReport");

			  //Clear field and leave a message
			  utility.driver.findElement(By.xpath(utility.txtMessage)).clear();
			  utility.driver.findElement(By.xpath(utility.txtMessage)).sendKeys(data.getcellData("message",i,mySheet));
			  utility.ExtentLogPass(utility.driver, "Message Captured", utility.test, true, "Report\\MyReport");

			  if(utility.driver.findElement(By.xpath(utility.checkboxAgree)).isEnabled()){
				  utility.driver.findElement(By.xpath(utility.checkboxAgree)).click();
				  utility.ExtentLogPass(utility.driver, "Agree checkbox clicked", utility.test, true, "Report\\MyReport");
			  }
			  //Click submit button
			  utility.driver.findElement(By.xpath(utility.btnSubmit)).click();
			  utility.driver.switchTo().parentFrame();
			  break;
		}

		workbook.close();

	} catch (Exception e) {
			e.printStackTrace();
		}
		}

}
