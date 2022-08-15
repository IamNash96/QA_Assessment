package utilities;


import application.Application;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import data.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import reporting.Reporting;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Utility {

	Reporting report = new Reporting();
	public static WebDriver driver = null;
	public ExtentReports extent;
	public ExtentTest test;
	
	public static String excel=null, url = null, txtName = null, txtEmployees = null, dropEmployees = null, txtService = null, dropService = null, checkboxAgree = null, btnSubmit = null, inputEmail = null, inputNumber = null, txtMessage = null, successfulSubmit = null;
	
	public void initialize() throws IOException
	{
		
		//Initializing the WebDriver
		System.setProperty("webdriver.chrome.driver", "driver2\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Fetching the configuration file data
		String propFile = "config.properties";
	    FileReader read = new FileReader(propFile);
					

		Properties props = new Properties();
		props.load(read);
		url = props.getProperty("url");
		excel = props.getProperty("excel");
		txtName = props.getProperty("name");
		inputEmail = props.getProperty("email");
		inputNumber = props.getProperty("number");
		txtEmployees = props.getProperty("employees_txt");
		dropEmployees = props.getProperty("employees");
		txtService  =     props.getProperty("txtService");
		dropService = props.getProperty("service");
		txtMessage = props.getProperty("message");
		checkboxAgree = props.getProperty("agree");
		btnSubmit = props.getProperty("submit");
		successfulSubmit = props.getProperty("successfulSubmission");
		
		driver.manage().window().maximize();
		driver.get(url); //Navigating to URL, you can also use navigate().to(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Using waits to set the max time to load
		
		
		
		
		extent = Reports("Report\\MyReport.html");
		test = Test(extent);
		
		
	
		
	}

	public String generateRandomValue(int length) {

		//int length = 10;
		boolean useLetters = false;
		boolean useNumbers = true;
		String generatedValue = RandomStringUtils.random(length, useLetters, useNumbers);
		System.out.println(generatedValue);
		return generatedValue;
	}

	public String generateRandomString(int length) {

		//int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		System.out.println(generatedString);
		return generatedString;
	}
	
	public void validate() throws IOException
	{
		if(driver.findElement(By.xpath(successfulSubmit)).isDisplayed()) {
			String path = report.Screen(driver);
			test.log(Status.PASS, "pass");
			test.pass("pass");
			test.pass("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath("."+path).build());
			test.addScreencastFromPath("/Screen.png");
			test.createNode("My node", "Checking it out.");
		}
		else {
			test.log(Status.FAIL, "fail");
			test.fail("fail");
		}
	}
	
	public static String Report;
	//public static ExtentReports extent;
	public ExtentReports Reports(String Report)
	  {
		  
		  this.Report = Report;
		  ExtentHtmlReporter htmlReporter = new  ExtentHtmlReporter(Report);
		  ExtentReports extent = new ExtentReports();
		  extent.attachReporter(htmlReporter);
		  htmlReporter.config().setTheme(Theme.DARK);
		  return extent;
		 
	  }
	
	 public ExtentTest Test(ExtentReports extent)
	  {
		  ExtentTest test = extent.createTest("Assessment");
		  return test;
	  }
	public String takeScreenShot(WebDriver driver, String FileName, String sDefaultPath) throws Exception {

		String encodedBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return encodedBase64;
	}

	public void ExtentLogPass(WebDriver driver, String sMessagePass, ExtentTest logger, Boolean Screenshot, String sDefaultPath) throws Exception {
		if (Screenshot) {

			String fileName = takeScreenShot(driver, sMessagePass, sDefaultPath);
			logger.pass(sMessagePass, MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());

			//Comment this line if want to take taskbars
            /*String fileNames = takeScreenShotTaskBar(driver, sMessagePass, sDefaultPath);
            logger.pass(sMessagePass, MediaEntityBuilder.createScreenCaptureFromBase64String(fileNames).build());*/

		} else {
			logger.pass(sMessagePass);
		}
	}

	 
	 public void close() throws IOException
	 {
		 
		 extent.flush();
		 driver.quit();
	 } 
	 
	 

}
