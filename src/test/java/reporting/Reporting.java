package reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;

import java.io.File;
import java.io.IOException;

public class Reporting {
	
	public void report()
	{
		XSSFWorkbookType fs = null;
		XSSFWorkbook wk = new XSSFWorkbook(fs);
	}
	
	public static WebDriver dr;
	public String Screen(WebDriver dr) throws IOException{
		
		  this.dr = dr;
		  String path = null;
		  TakesScreenshot ts = (TakesScreenshot) dr;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(source, new File("./Screenshot/Screen.png"));
		  path = "Screenshot\\Screen.png";
		  dr.quit();
		  return path;
	  }



}
