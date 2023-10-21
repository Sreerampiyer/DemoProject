package project;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class ProjSpecific extends wrapper.KeywordWrapper {

	RemoteWebDriver driver;
	Properties prop;
	static Logger logger = Logger.getLogger("KeywordWrapper");
	
public ProjSpecific () throws FileNotFoundException, IOException {
	
	PropertyConfigurator.configure(".\\Properties\\log4j.properties");
	
	prop = loadObjectRepository(".\\properties\\Bulk.properties");

}
	
 

	public void AdrLogin(String bulk, String data1, String data2) {
		try {
			
			logger.info("AdrLogin selected");
	//		String Id = prop.getProperty("Bulk.Fieldname.Locator1");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))));
			driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))).clear();
			driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))).sendKeys(data1);
			String value = driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))).getAttribute("value");
			ATUReports.add("Text entered", data1,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			
			String Id = prop.getProperty("Bulk.Fieldname.Locator2");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			driver.findElement(By.id(Id)).clear();
			driver.findElement(By.id(Id)).sendKeys(data2);
			value = driver.findElement(By.id(Id)).getAttribute("value");
			ATUReports.add("Text entered", data2,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			
	//		Id = prop.getProperty("Bulk.Fieldname.Locator3");
	//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
	//		driver.findElement(By.id(Id)).clear();
	//		driver.findElement(By.id(Id)).sendKeys(data3);
	//		value = driver.findElement(By.id(Id)).getAttribute("value");
	//		ATUReports.add("Text entered", data3,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			
			Id = prop.getProperty("Bulk.Fieldname.Locator4");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			driver.findElement(By.id(Id)).click();
			ATUReports.add("Button clicked",Id,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", "", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	public Properties loadObjectRepository(String FileName) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Properties p = new Properties();
		// Step 2: Load the Property file
		p.load(new FileInputStream(new File(FileName)));
		return p;
	}
	
	
	
	
}
