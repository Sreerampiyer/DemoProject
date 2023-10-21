package wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class KeywordWrapper {
	static String a = "";
	static String b = "";
	static String TransactionID = "";
	static String PaymentID="";
	static String ReferenceID="";
	static String TrackID="";
	static String getUrl= "";
	static String MultiSetID = "";
	RemoteWebDriver driver;
	Properties prop;
	
	static Logger logger = Logger.getLogger("KeywordWrapper");
	
	
	public KeywordWrapper() throws FileNotFoundException, IOException  {

	//	PropertyConfigurator.configure(".\\Properties\\log4j.properties");
		PropertyConfigurator.configure("./Properties/log4j.properties");
		
		prop = loadObjectRepository("Bulk.properties");	
		
	}
	
	/* Index
	 1. Launch Browser 		- 		launchBrowser(String url)
	 
	 2. Button click Using ID, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyClickById(String Id , String data)
	 b) By Name 			- 		verifyClickByName(String Name , String data)
	 c) By ClassName 		- 		verifyClickByClassName(String ClassName , String data)
	 d) By Xpath 			- 		verifyClickByXpath(String Xpath , String data)
	 
	 3.Button click by Attribute value Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		clickAttrById(String Id , String data)
	 b) By Name 			- 		clickAttrByName(String Name , String data)
	 c) By ClassName 		- 		clickAttrByClassName(String ClassName , String data)
	 d) By Xpath 			- 		clickAttrByXpath(String Xpath , String data)
	 
	 4. Button click without verifying the data Using ID, Name, ClassName, Xpath, LinkText, PartialLinkText
	 
	 a) By Id 				- 		clickById(String Id)
	 b) By Name 			- 		clickByName(String Name)
	 c) By ClassName 		- 		clickByClassName(String ClassName)
	 d) By Xpath 			- 		clickByXpath(String Xpath)
	 e) By LinkText 		- 		clickByLinkText(String linkText)
	 f) PartialLinkText 	- 		clickByPartialLinkText(String linkText)
	 
	 5. Enter Text Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		enterTextById(String Id, String data)
	 b) By Name 			- 		enterTextByName(String Name, String data)
	 c) By ClassName 		- 		enterTextByClassName(String ClassName, String data)
	 d) By Xpath 			- 		enterTextByXpath(String Xpath, String data)
	 
	 6. Select List Box Options by Visible Text Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		selectVisibleTextById(String Id, String data)
	 b) By Name 			- 		selectVisibleTextByName(String Name, String data)
	 c) By ClassName 		- 		selectVisibleTextByClassName(String ClassName, String data)
	 d) By Xpath 			- 		selectVisibleTextByXpath(String Xpath, String data)
	 
	 7. Select List Box Options by Value Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		selectValueById(String Id, String data)
	 b) By Name 			- 		selectValueByName(String Name, String data)
	 c) By ClassName 		- 		selectValueByClassName(String ClassName, String data)
	 d) By Xpath 			- 		selectValueByXpath(String Xpath, String data)
	 
	 8. Select List Box Options by Index Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		selectIndexById(String Id, String data)
	 b) By Name 			- 		selectIndexByName(String Name, String data)
	 c) By ClassName 		- 		selectIndexByClassName(String ClassName, String data)
	 d) By Xpath 			- 		selectIndexByXpath(String Xpath, String data)
	 
	 9. Verify List Box Default Selected Text Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyListBoxDefaultTextById(String Id ,String data)
	 b) By Name 			- 		verifyListBoxDefaultTextByName(String Name ,String data)
	 c) By ClassName 		- 		verifyListBoxDefaultTextByClassName(String ClassName ,String data)
	 d) By Xpath 			- 		verifyListBoxDefaultTextByXpath(String Xpath ,String data)
	 
	 10. Verify List Box Options Count Using Id, Name, ClassName, Xpath
	 
 	 a) By Id 				- 		verifyListBoxOptionsCountById(String Id, String Count)
	 b) By Name 			- 		verifyListBoxOptionsCountByName(String Name, String Count)
	 c) By ClassName 		- 		verifyListBoxOptionsCountByClassName(String ClassName, String Count)
	 d) By Xpath 			- 		verifyListBoxOptionsCountByXpath(String Xpath, String Count)

	 11. Verify Attribute Text Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyAttrTextById(String Id, String data)
	 b) By Name 			- 		verifyAttrTextByName(String Name, String data)
	 c) By ClassName 		- 		verifyAttrTextByClassName(String ClassName, String data)
	 d) By Xpath 			- 		verifyAttrTextByXpath(String Xpath, String data)
	 
     12. Verify Attribute Text Length Using Id, Name, ClassName, Xpath
	  
  	 a) By Id 				- 		verifyAttrTextLengthById(String Id, String Length)
	 b) By Name 			- 		verifyAttrTextLengthByName(String Name, String Length)
	 c) By ClassName 		- 		verifyAttrTextLengthByClassName(String ClassName, String Length)
	 d) By Xpath 			- 		verifyAttrTextLengthByXpath(String Xpath, String Length) 
	 
	 13. Verify Text Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyTextById(String Id, String data)
	 b) By Name 			- 		verifyTextByName(String Name, String data)
	 c) By ClassName 		- 		verifyTextByClassName(String ClassName, String data)
	 d) By Xpath 			- 		verifyTextByXpath(String Xpath, String data)
	 
	 14. Alert Functions
	 
	 a) acceptAlert()
	 b) dismissAlert()
	 c) verifyAlertText(String data)
	 d) verifyAlertTextWithAccept(String data)
	 e) verifyAlertTextWithReject(String data)
	 
	 15. Verify Radio Button Default Selection Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyRadioBtnDefaultSelectionById(String Id)
	 b) By Name 			- 		verifyRadioBtnDefaultSelectionByName(String Name)
	 c) By ClassName 		- 		verifyRadioBtnDefaultSelectionByClassName(String ClassName)
	 d) By Xpath 			- 		verifyRadioBtnDefaultSelectionByXpath(String Xpath)
	 
	 16. Click Radio Button Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		clickRadioBtnById(String Id,String Id1,String data)
	 b) By Name 			- 		clickRadioBtnByName(String Name,String Name1,String data)
	 c) By ClassName 		- 		clickRadioBtnByClassName(String ClassName,String ClassName1,String data)
	 d) By Xpath 			- 		clickRadioBtnByXpath(String Xpath,String Xpath1,String data)
	 
	 17. Select Check Box Using Id, Name, ClassName, Xpath
	 
	 a) By id 				- 		selectCheckBoxById(String Id,String Id1,String data)
	 b) By Name 			- 		selectCheckBoxByName(String Name,String Name1,String data)
	 c) By ClassName 		- 		selectCheckBoxByClassName(String ClassName,String ClassName1,String data)
	 d) By Xpath 			- 		selectCheckBoxByXpath(String Xpath,String Xpath1,String data)
	 
	 18. Verify Maximum Length of the field with alphabets Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMaxAlphaById(String Id, String data, String value1)
	 b) By Name 			- 		verifyMaxAlphaByName(String Name, String data, String value1)
	 c) By ClassName 		- 		verifyMaxAlphaByClassName(String ClassName, String data, String value1)
	 d) By Xpath 			- 		verifyMaxAlphaByXpath(String Xpath, String data, String value1)
	 
	 19. Verify Minimum Length of the field with alphabets Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMinAlphaById(String Id, String data, String value1)
	 b) By Name 			- 		verifyMinAlphaByName(String Name, String data, String value1)
	 c) By ClassName 		- 		verifyMinAlphaByClassName(String ClassName, String data, String value1)
	 d) By Xpath 			- 		verifyMinAlphaByXpath(String Xpath, String data, String value1)
	 
	 20. Verify Maximum Length of the field with numbers Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMaxNumById(String Id, String data, String value1)
	 b) By Name 			- 		verifyMaxNumByName(String Name, String data, String value1)
	 c) By ClassName 		- 		verifyMaxNumByClassName(String ClassName, String data, String value1)
	 d) By Xpath 			- 		verifyMaxNumByXpath(String Xpath, String data, String value1)
	 
	 21. Verify Minimum Length of the field with numbers Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMinNumById(String Id, String data, String value1)
	 b) By Name 			- 		verifyMinNumByName(String Name, String data, String value1)
	 c) By ClassName 		- 		verifyMinNumByClassName(String ClassName, String data, String value1)
	 d) By Xpath 			- 		verifyMinNumByXpath(String Xpath, String data, String value1)
	 
	 22. Verify Maximum Length of the field with alpha numerics Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMaxAlphaNumById(String Id, String data, String value1, String value2)
	 b) By Name 			- 		verifyMaxAlphaNumByName(String Name, String data, String value1, String value2)
	 c) By ClassName 		- 		verifyMaxAlphaNumByClassName(String ClassName, String data, String value1, String value2)
	 d) By Xpath 			- 		verifyMaxAlphaNumByXpath(String Xpath, String data, String value1, String value2)
	 
	 23. Verify Minimum Length of the field with alpha numerics Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMinAlphaNumById(String Id, String data, String value1, String value2)
	 b) By Name 			- 		verifyMinAlphaNumByName(String Name, String data, String value1, String value2)
	 c) By ClassName 		- 		verifyMinAlphaNumByClassName(String ClassName, String data, String value1, String value2)
	 d) By Xpath 			- 		verifyMinAlphaNumByXpath(String Xpath, String data, String value1, String value2)
	 
	 24. Verify Maximum Length of the field Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMaxLengthById(String Id, String data, String value1)
	 b) By Name 			- 		verifyMaxLengthByName(String Name, String data, String value1)
	 c) By ClassName 		- 		verifyMaxLengthByClassName(String ClassName, String data, String value1)
	 d) By Xpath 			- 		verifyMaxLengthByXpath(String Xpath, String data, String value1)
	 
	 25. Verify Minimum Length of the field Using Id, Name, ClassName, Xpath
	 
	 a) By Id 				- 		verifyMinLengthById(String Id, String data, String value1)
	 b) By Name 			- 		verifyMinLengthByName(String Name, String data, String value1)
	 c) By ClassName 		- 		verifyMinLengthByClassName(String ClassName, String data, String value1)
	 d) By Xpath 			- 		verifyMinLengthByXpath(String Xpath, String data, String value1)
	 
	 26. Quit Browser 		- 		quitBrowser()
	 
	 */

	
	
	//Launch Browser
	public void launchBrowser(String url, String browser) throws IOException {
		//	String browser = prop.getProperty("Browser.Name");
		//	String path = "D:\\files\\driver";
			String path = "./driver";
				//	+ ""
				//	+ "driver";
		//	String url = prop.getProperty("LoginPage.Url");
			try {

				if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.firefox.driver", path + "\\geckodriver.exe");
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver");
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("ie")) {
					System.setProperty("webdriver.ie.driver", path + "\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				} else if (browser.equals("Phantomjs")) {
					 Capabilities caps = new DesiredCapabilities();
					((DesiredCapabilities) caps).setJavascriptEnabled(true);
					((DesiredCapabilities) caps).setCapability("takesScreenshot", false);
					((DesiredCapabilities) caps).setCapability("locationContextEnabled", true);
					((DesiredCapabilities) caps).setCapability("acceptSslCerts", true);
					File file = new File(path + "\\phantomjs.exe");
					System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
					driver = new PhantomJSDriver(caps);
							}

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(url);

				ATUReports.setWebDriver(driver);
			//	ATUReports.indexPageDescription = "Login Credentials Verification";
				ATUReports.setAuthorInfo("Varadaajan CA", Utils.getCurrentTime(), "1.0");
				ATUReports.setTestCaseReqCoverage("Selenium Automation");
			} catch (Exception e) {
				ATUReports.add("Browser opening failed", browser, LogAs.FAILED, null);
			}
		}	


	public void launchBrowser1(String url) throws IOException {
			String browser = prop.getProperty("Browser.Name");
			String path = "D:\\files\\driver";
		//	String url = prop.getProperty("LoginPage.Url");
			try {

				if (browser.equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", path + "\\chromedriver.exe");
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("ie")) {
					System.setProperty("webdriver.ie.driver", path + "\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				} else if (browser.equals("Phantomjs")) {
					 Capabilities caps = new DesiredCapabilities();
					((DesiredCapabilities) caps).setJavascriptEnabled(true);
					((DesiredCapabilities) caps).setCapability("takesScreenshot", false);
					((DesiredCapabilities) caps).setCapability("locationContextEnabled", true);
					((DesiredCapabilities) caps).setCapability("acceptSslCerts", true);
					File file = new File(path + "\\phantomjs.exe");
					System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
					driver = new PhantomJSDriver(caps);
							}

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(url);

				ATUReports.setWebDriver(driver);
			//	ATUReports.indexPageDescription = "Login Credentials Verification";
				ATUReports.setAuthorInfo("Testing Services", Utils.getCurrentTime(), "1.0");
				ATUReports.setTestCaseReqCoverage("Payment Gateway Automation");
			} catch (Exception e) {
				ATUReports.add("Browser opening failed", browser, LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			}
		}	
	
	//Button click Using ID, Name, ClassName, Xpath, LinkText, PartialLinkText
	
	//Click By Id
	public void verifyClickById(String Id , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String value=driver.findElement(By.id(Id)).getText();
			if(value.equals(data)){
			driver.findElement(By.id(Id)).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	//Click By Name
	public void verifyClickByName(String Name , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String value=driver.findElement(By.name(Name)).getText();
			if(value.equals(data)){
			driver.findElement(By.name(Name)).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Click By ClassName
	public void verifyClickByClassName(String ClassName , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String value=driver.findElement(By.className(ClassName)).getText();
			if(value.equalsIgnoreCase(data)){
			driver.findElement(By.className(ClassName)).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Click By Xpath
	public void verifyClickByXpath(String Xpath , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String value=driver.findElement(By.xpath(Xpath)).getText().trim();
//			//System.out.println("Get " + value);
//			//System.out.println(data);
			if(value.equals(data)){
			driver.findElement(By.xpath(Xpath)).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyMouseOverClickByXpath(String Xpath , String data) {
		try {
			String value=driver.findElement(By.xpath(Xpath)).getText().trim();
		//	//System.out.println(value);
			if(value.equalsIgnoreCase(data)){
			Actions actions = new Actions(driver);
			WebElement Personal = driver.findElement(By.xpath(Xpath));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			actions.moveToElement(Personal).build().perform();
			driver.findElement(By.xpath(Xpath)).click();
			
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	
	//Button click Using ID, Name, ClassName, Xpath, LinkText, PartialLinkText
	
	//Click By Id
	public void clickAttrById(String Id , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String value=driver.findElement(By.id(Id)).getAttribute("value");
			if(value.equals(data)){
				ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			driver.findElement(By.id(Id)).click();
		//	ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	//Click By Name
	public void clickAttrByName(String Name , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String value=driver.findElement(By.name(Name)).getAttribute("value");
			if(value.equals(data)){
			driver.findElement(By.name(Name)).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Click By ClassName
	public void clickAttrByClassName(String ClassName , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String value=driver.findElement(By.className(ClassName)).getAttribute("value");
			if(value.equals(data)){
			driver.findElement(By.className(ClassName)).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Click By Xpath
	public void clickAttrByXpath(String Xpath , String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String value=driver.findElement(By.xpath(Xpath)).getAttribute("value");
			if(value.equals(data)){
			driver.findElement(By.xpath(Xpath)).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	
	//Click Without verifying data Using id, Name, ClassName, Xpath
	
	//Click By Id
	public void clickById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			driver.findElement(By.id(Id)).click();
			ATUReports.add("Button clicked",Id,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Button not clicked",Id,LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	//Click By Name
	public void clickByName(String Name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
		
			driver.findElement(By.name(Name)).click();
			ATUReports.add("Button clicked",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
		} catch (NoSuchElementException e) {
			ATUReports.add("Button not clicked",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Click By ClassName
	public void clickByClassName(String ClassName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			driver.findElement(By.className(ClassName)).click();
			ATUReports.add("Button clicked",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Button not clicked",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Click By Xpath
	public void clickByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			driver.findElement(By.xpath(Xpath)).click();
			ATUReports.add("Button clicked",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (NoSuchElementException e) {
			ATUReports.add("Button not clicked",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Click By LinkText
	public void clickByLinkText(String linkText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
			ATUReports.add("Link clicked",linkText, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			driver.findElement(By.linkText(linkText)).click();
//			ATUReports.add("Link clicked",linkText, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Link not clicked",linkText, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void clickByPartialLinkText(String linkText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
			driver.findElement(By.partialLinkText(linkText)).click();
			ATUReports.add("Link clicked",linkText, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Link not clicked",linkText, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	//Enter Text Using Id, Name, ClassName, Xpath
	
	//Enter Text by Id
	public void enterTextById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			driver.findElement(By.id(Id)).clear();
			driver.findElement(By.id(Id)).sendKeys(data);
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	//Enter Text by Name
	public void enterTextByName(String Name, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(data);
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add(Name+" - Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	public void clearTextByName(String Name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			//ATUReports.add(Name+" - Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add(Name+" - clear failed ", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	//Enter Text by ClassName
	public void enterTextByClassName(String ClassName, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			driver.findElement(By.className(ClassName)).clear();
			driver.findElement(By.className(ClassName)).sendKeys(data);
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			ATUReports.add("Text entered", data ,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data , LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	//Enter Text by Xpath
	public void enterTextByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			driver.findElement(By.xpath(Xpath)).clear();
			driver.findElement(By.xpath(Xpath)).sendKeys(data);
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			ATUReports.add("Text entered", data ,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data , LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}
	
	//Select List Box Options by Visible Text Using Id, Name, ClassName, Xpath

	//Select Using Visible Text By Id
	public void selectVisibleTextById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			Select i = new Select(driver.findElement(By.id(Id)));
			i.selectByVisibleText(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Using Visible Text By Name
	public void selectVisibleTextByName(String Name, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			Select i = new Select(driver.findElement(By.name(Name)));
			i.selectByVisibleText(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Using Visible Text By ClassName
	public void selectVisibleTextByClassName(String ClassName, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			Select i = new Select(driver.findElement(By.className(ClassName)));
			i.selectByVisibleText(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select Using Visible Text By Xpath
	public void selectVisibleTextByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			Select i = new Select(driver.findElement(By.xpath(Xpath)));
			i.selectByVisibleText(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select List Box Options by Value Using Id, Name, ClassName, Xpath

	//Select Using Value By Id
	public void selectValueById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			Select i = new Select(driver.findElement(By.id(Id)));
			i.selectByValue(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Using Value By Name
	public void selectValueByName(String Name, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			Select i = new Select(driver.findElement(By.name(Name)));
			i.selectByValue(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Using Value By ClassName
	public void selectValueByClassName(String ClassName, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			Select i = new Select(driver.findElement(By.className(ClassName)));
			i.selectByValue(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select Using Value By Xpath
	public void selectValueByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			Select i = new Select(driver.findElement(By.xpath(Xpath)));
			i.selectByValue(data);
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select Option by Index Using Id, Name, ClassName, Xpath

	//Select Using Index By Id
	public void selectIndexById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			Select i = new Select(driver.findElement(By.id(Id)));
			i.selectByIndex(Integer.parseInt(data));
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Using Index By Name
	public void selectIndexByName(String Name, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			Select i = new Select(driver.findElement(By.name(Name)));
			i.selectByIndex(Integer.parseInt(data));
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Using Index By ClassName
	public void selectIndexByClassName(String ClassName, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			Select i = new Select(driver.findElement(By.className(ClassName)));
			i.selectByIndex(Integer.parseInt(data));
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select Using Index By Xpath
	public void selectIndexByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			Select i = new Select(driver.findElement(By.xpath(Xpath)));
			i.selectByIndex(Integer.parseInt(data));
			ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Option not selected",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify List Box Default Selected Text Using Id, Name, ClassName, Xpath
	
	//Verify List Box Default Text By Id
	public void verifyListBoxDefaultTextById(String Id ,String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			Select i = new Select(driver.findElement(By.id(Id)));
			String Text = i.getFirstSelectedOption().getText();
			if(Text.equalsIgnoreCase(data)){
				ATUReports.add("Default Selection matched","", data, Text, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
			ATUReports.add("Default Selection not matched","", data, Text, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify List Box Default Text By Name
	public void verifyListBoxDefaultTextByName(String Name ,String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			Select i = new Select(driver.findElement(By.name(Name)));
			String Text = i.getFirstSelectedOption().getText();
			if(Text.equalsIgnoreCase(data)){
				ATUReports.add("Default Selection matched","", data, Text,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Default Selection not matched","", data, Text,LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify List Box Default Text By ClassName
	public void verifyListBoxDefaultTextByClassName(String ClassName ,String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			Select i = new Select(driver.findElement(By.className(ClassName)));
			String Text = i.getFirstSelectedOption().getText();
			if(Text.equalsIgnoreCase(data)){
				ATUReports.add("Default Selection matched","", data, Text,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Default Selection not matched","", data, Text,LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify List Box Default Text By Xpath
	public void verifyListBoxDefaultTextByXpath(String Xpath ,String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			Select i = new Select(driver.findElement(By.xpath(Xpath)));
			String Text = i.getFirstSelectedOption().getText();
			if(Text.equalsIgnoreCase(data)){
				ATUReports.add("Default Selection matched","", data, Text,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Default Selection not matched","", data, Text,LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify List Box Options Count Using Id, Name, ClassName, Xpath
	
	//Verify List Box Options Count By Id
	public void verifyListBoxOptionsCountById(String Id, String Count) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			Select i = new Select(driver.findElement(By.id(Id)));
			int size = i.getOptions().size();
			if (size == Integer.parseInt(Count)){
				ATUReports.add("Options Count matched","",Count, size + "" , LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Options Count not matched","", Count ,size + "" , LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}  catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify List Box Options Count By Name
	public void verifyListBoxOptionsCountByName(String Name, String Count) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			Select i = new Select(driver.findElement(By.name(Name)));
			int size = i.getOptions().size();
			if (size == Integer.parseInt(Count)){
				ATUReports.add("Options Count matched","",Count ,size + "", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Options Count not matched","", Count,size + "" , LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}  catch (NoSuchElementException e) {
			ATUReports.add("No such element present",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify List Box Options Count By ClassName
	public void verifyListBoxOptionsCountByClassName(String ClassName, String Count) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			Select i = new Select(driver.findElement(By.className(ClassName)));
			int size = i.getOptions().size();
			if (size == Integer.parseInt(Count)){
				ATUReports.add("Options Count matched","",Count,size + "" ,  LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Options Count not matched","", Count,size + "" , LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}  catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify List Box Options Count By Xpath
	public void verifyListBoxOptionsCountByXpath(String Xpath, String Count) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			Select i = new Select(driver.findElement(By.xpath(Xpath)));
			int size = i.getOptions().size();
			if (size == Integer.parseInt(Count)){
				ATUReports.add("Options Count matched","",Count + "",size + "" ,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Options Count not matched","", Count + "",size + "" , LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}  catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Attribute Text Using Id, Name, ClassName, Xpath
	
	//Verify Attr Text By Id
	public void verifyAttrTextById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getAttribute("value");
			if (Text.equalsIgnoreCase(data))
			{
				ATUReports.add("Text matched","",data,Text ,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","", data, Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Attr Text By Name
	public void verifyAttrTextByName(String Name, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String Text = driver.findElement(By.name(Name)).getAttribute("value");
			if (Text.equalsIgnoreCase(data))
			{
				ATUReports.add("Text matched","",data,Text ,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched", "",data,Text , LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Attr Text By ClassName
	public void verifyAttrTextByClassName(String ClassName, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String Text = driver.findElement(By.className(ClassName)).getAttribute("value");
			if (Text.equalsIgnoreCase(data))
			{
				ATUReports.add("Text matched","",data,Text ,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched", "",data,Text , LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Attr Text By Xpath
	public void verifyAttrTextByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			if (Text.equalsIgnoreCase(data))
			{
				ATUReports.add("Text matched","",data,Text ,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","",data,Text ,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Attribute Text Length Using Id, Name, ClassName, Xpath
	
	//Verify Attr Text Length By Id
	public void verifyAttrTextLengthById(String Id, String Length) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getAttribute("value");
			int length = Text.length();
			if (length == Integer.parseInt(Length)) {
				ATUReports.add("Text Length matched","", Length,length + "", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text Length not matched","", Length, length + "", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Attr Text Length By Name
	public void verifyAttrTextLengthByName(String Name, String Length) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String Text = driver.findElement(By.name(Name)).getAttribute("value");
			int length = Text.length();
			if (length == Integer.parseInt(Length)) {
				ATUReports.add("Text Length matched", "",Length, length + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text Length not matched","", Length, length + "", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Attr Text Length By ClassName
	public void verifyAttrTextLengthByClassName(String ClassName, String Length) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String Text = driver.findElement(By.className(ClassName)).getAttribute("value");
			int length = Text.length();
			if (length == Integer.parseInt(Length)) {
				ATUReports.add("Text Length matched", "",Length, length + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text Length not matched", "",Length, length + "", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Attr Text Length By Xpath
	public void verifyAttrTextLengthByXpath(String Xpath, String Length) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int length = Text.length();
			if (length == Integer.parseInt(Length)) {
				ATUReports.add("Text Length matched","", Length, length + "", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text Length not matched", "",Length, length + "", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present" ,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Text Using Id, Name, ClassName, Xpath
	
	//Verify Text By Id
	public void verifyTextById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getText().trim();
			if (Text.equalsIgnoreCase(data)) {
				ATUReports.add("Text matched","",data,Text,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			/*	String path="F:/Auto/EnhKeyWord12/ATU_Reports/23-10-2017/Logs/";
				String file= "<a href="+ path + "Log_179919" + ">Sample Reports</a>";
				ATUReports.add(file,data,Text,LogAs.PASSED,null);
				ATUReports.add(file,data,Text,LogAs.FAILED,null);*/
			}
			else{
				ATUReports.add("Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Text By Name
	public void verifyTextByName(String Name, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String Text = driver.findElement(By.name(Name)).getText().trim();
			if (Text.equalsIgnoreCase(data)) {
				ATUReports.add("Text matched","", data, Text,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Text By ClassName
	public void verifyTextByClassName(String ClassName, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String Text = driver.findElement(By.className(ClassName)).getText().trim();
			if (Text.equalsIgnoreCase(data)) {
				ATUReports.add("Text matched", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Text By Xpath
	public void verifyTextByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			if (Text.equalsIgnoreCase(data)) {
				ATUReports.add("Text matched", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Alert Functions
	
	//Accept Alert
	public void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			ATUReports.add("Alert accepted", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Alert handling failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Dismiss Alert
	public void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
			ATUReports.add("Alert dismissed", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Alert handling failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Alert Text
	public void verifyAlertText(String data) {
		try {
			String Text = driver.switchTo().alert().getText();
			if (Text.equalsIgnoreCase(data))
				ATUReports.add("Alert Text matched", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			else
				ATUReports.add("Alert Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Alert Text with accept function
	public void verifyAlertTextWithAccept(String data) {
		try {
			String Text = driver.switchTo().alert().getText();
			if (Text.equalsIgnoreCase(data))
				ATUReports.add("Alert Text matched", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			else
				ATUReports.add("Alert Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		driver.switchTo().alert().accept();
	}

	//Verify Alert Text with reject function
	public void verifyAlertTextWithReject(String data) {
		try {
			String Text = driver.switchTo().alert().getText();
			if (Text.equalsIgnoreCase(data))
				ATUReports.add("Alert Text matched","", data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			else
				ATUReports.add("Alert Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		driver.switchTo().alert().dismiss();
	}

	//Verify Radio Button Default Selection Using Id, Name, ClassName, Xpath
	
	//Verify Radio Button Default Selection By Id
	public void verifyRadioBtnDefaultSelectionById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			if(driver.findElement(By.id(Id)).isSelected()){
				ATUReports.add("Default Selection verified", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Default Selection not verified", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Radio Button Default Selection By Name
	public void verifyRadioBtnDefaultSelectionByName(String Name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			if(driver.findElement(By.name(Name)).isSelected()){
				ATUReports.add("Default Selection verified", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Default Selection not verified", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Radio Button Default Selection By ClassName
	public void verifyRadioBtnDefaultSelectionByClassName(String ClassName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			if(driver.findElement(By.className(ClassName)).isSelected()){
				ATUReports.add("Default Selection verified", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Default Selection not verified", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Radio Button Default Selection By Xpath
	public void verifyRadioBtnDefaultSelectionByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			if(driver.findElement(By.xpath(Xpath)).isSelected()){
				ATUReports.add("Default Selection verified", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Default Selection not verified", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Click Radio Button Using Id, Name, ClassName, Xpath
	
	//Click Radio Button By Id
	public void clickRadioBtnById(String Id,String Id1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String value=driver.findElement(By.id(Id1)).getText();
			if(value.equalsIgnoreCase(data)){
				driver.findElement(By.id(Id)).click();
				ATUReports.add("Button Clicked", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Button not Clicked",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Click Radio Button By Name
	public void clickRadioBtnByName(String Name,String Name1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String value=driver.findElement(By.name(Name1)).getText();
			if(value.equalsIgnoreCase(data)){
				driver.findElement(By.name(Name)).click();
				ATUReports.add("Button Clicked", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Button not Clicked",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Click Radio Button By ClassName
	public void clickRadioBtnByClassName(String ClassName,String ClassName1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String value=driver.findElement(By.className(ClassName1)).getText();
			if(value.equalsIgnoreCase(data)){
				driver.findElement(By.className(ClassName)).click();
				ATUReports.add("Button Clicked", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Button not Clicked",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Click Radio Button By Xpath
	public void clickRadioBtnByXpath(String Xpath,String Xpath1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String value=driver.findElement(By.xpath(Xpath1)).getText();
			if(value.equalsIgnoreCase(data)){
				driver.findElement(By.xpath(Xpath)).click();
				ATUReports.add("Button Clicked", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Button not Clicked",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select Check Box Using Id, Name, ClassName, Xpath
	
	//Select Check Box By Id
	public void selectCheckBoxValById(String Id,String Id1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String val=driver.findElement(By.id(Id1)).getText();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.id(Id)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else if(value.equalsIgnoreCase("unchecked") || value.equalsIgnoreCase("")){
				driver.findElement(By.id(Id)).click();
				ATUReports.add("Check Box selected",data, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else
				ATUReports.add("Check Box not selected",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Check Box By Name
	public void selectCheckBoxValByName(String Name,String Name1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String val=driver.findElement(By.name(Name1)).getText();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.name(Name)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else if(value.equalsIgnoreCase("unchecked") || value.equalsIgnoreCase("")){
				driver.findElement(By.name(Name)).click();
				ATUReports.add("Check Box selected",data, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else
				ATUReports.add("Check Box not selected",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Check Box By ClassName
	public void selectCheckBoxValByClassName(String ClassName,String ClassName1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String val=driver.findElement(By.className(ClassName1)).getText();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.className(ClassName)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else if(value.equalsIgnoreCase("unchecked") || value.equalsIgnoreCase("")){
				driver.findElement(By.className(ClassName)).click();
				ATUReports.add("Check Box selected",data, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else
				ATUReports.add("Check Box not selected",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select Check Box By Xpath
	public void selectCheckBoxValByXpath(String Xpath,String Xpath1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String val=driver.findElement(By.xpath(Xpath1)).getText();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.xpath(Xpath)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else if(value.equalsIgnoreCase("unchecked") || value.equalsIgnoreCase("")){
				driver.findElement(By.xpath(Xpath)).click();
				ATUReports.add("Check Box selected",data, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else
				ATUReports.add("Check Box not selected",data, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//select the check box by id
	public void selectCheckBoxById(String Id,String Id1){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String value=driver.findElement(By.id(Id)).getAttribute("checked");
			String data=driver.findElement(By.id(Id1)).getText().trim();
		//	//System.out.println(data);
		//	//System.out.println(value);
			if(value.equalsIgnoreCase("true")){
				ATUReports.add("Check Box Selected",data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else {
				driver.findElement(By.id(Id)).click();
				ATUReports.add("Check Box selected",data, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Check box not found", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void deselectCheckBoxById(String Id,String Id1){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String value=driver.findElement(By.id(Id)).getAttribute("checked");
			String data=driver.findElement(By.id(Id1)).getText().trim();
		//	//System.out.println(data);
		//	//System.out.println(value);
			if(value.equalsIgnoreCase("true")){
				driver.findElement(By.id(Id)).click();
				ATUReports.add("Check Box de-Selected",data,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else {
				ATUReports.add("Check Box de-Selected",data, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Check box not found", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	//de- select the check box by id
	//select the check box by name
	public void selectCheckBoxByName(String Name){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String value=driver.findElement(By.name(Name)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				driver.findElement(By.name(Name)).click();
				ATUReports.add("Check Box selected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//de-select the check box by name
	public void deselectCheckBoxByName(String Name){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String value=driver.findElement(By.name(Name)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				driver.findElement(By.name(Name)).click();
				ATUReports.add("Check Box deselected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Check Box deselected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//select the check box by classname
	public void selectCheckBoxByClassName(String ClassName){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String value=driver.findElement(By.className(ClassName)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else {
				driver.findElement(By.className(ClassName)).click();
				ATUReports.add("Check Box selected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//de-select the check box by classname
	public void deselectCheckBoxByClassName(String ClassName){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String value=driver.findElement(By.className(ClassName)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				driver.findElement(By.className(ClassName)).click();
				ATUReports.add("Check Box de-selected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else {
				ATUReports.add("Check Box de-selected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//select the Check Box by Xpath
	public void selectCheckBoxByXpath(String Xpath){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String value=driver.findElement(By.xpath(Xpath)).getAttribute("class");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				driver.findElement(By.xpath(Xpath)).click();
				ATUReports.add("Check Box selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}			
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//de-select the Check Box by Xpath
	public void deselectCheckBoxByXpath(String Xpath){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String value=driver.findElement(By.xpath(Xpath)).getAttribute("value");
			if(value.equalsIgnoreCase("checked")){
				driver.findElement(By.xpath(Xpath)).click();
				ATUReports.add("Check Box de-selected",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Check Box de-selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	
	//Verify Maximum Length of the field Using Id, Name, ClassName, Xpath
	
	//Verify Maximum length of the field By Id
	public void verifyMaxLengthById(String Id, String data,String value1) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.id(Id)).sendKeys(value1);
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum length of the field By Name
	public void verifyMaxLengthByName(String Name, String data,String value1) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.name(Name)).sendKeys(value1);
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum length of the field By ClassName
	public void verifyMaxLengthByClassName(String ClassName, String data,String value1) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.className(ClassName)).sendKeys(value1);
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum length of the field By Xpath
	public void verifyMaxLengthByXpath(String Xpath, String data,String value1) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.xpath(Xpath)).sendKeys(value1);
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum Length of the field Using Id, Name, ClassName, Xpath
	
	//Verify Minimum length of the field By Id
	public void verifyMinLengthById(String Id, String data,String value1) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.id(Id)).sendKeys(value1);
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field By Name
	public void verifyMinLengthByName(String Name, String data,String value1) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.name(Name)).sendKeys(value1);
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field By ClassName
	public void verifyMinLengthByClassName(String ClassName, String data,String value1) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.className(ClassName)).sendKeys(value1);
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field By Xpath
	public void verifyMinLengthByXpath(String Xpath, String data,String value1) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.xpath(Xpath)).sendKeys(value1);
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Spl Char " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum Length of the field with alphabets Using Id, Name, ClassName, Xpath
	
	//Verify Maximum length of the field with alphabets By Id
	public void verifyMaxAlphaById(String Id, String data,String value1) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.id(Id)).sendKeys(value1);
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Maximum length of the field with alphabets By Name
	public void verifyMaxAlphaByName(String Name, String data,String value1) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.name(Name)).sendKeys(value1);
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Maximum length of the field with alphabets By ClassName
	public void verifyMaxAlphaByClassName(String ClassName, String data,String value1) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.className(ClassName)).sendKeys(value1);
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Maximum length of the field with alphabets By Xpath
	public void verifyMaxAlphaByXpath(String Xpath, String data, String value1) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.xpath(Xpath)).sendKeys(value1);
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum Length of the field with alphabets Using Id, Name, ClassName, Xpath
	
	//Verify Minimum length of the field with alphabets By Id
	public void verifyMinAlphaById(String Id, String data, String value1) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.id(Id)).sendKeys(value1);
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field with alphabets By Name
	public void verifyMinAlphaByName(String Name, String data,String value1) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.name(Name)).sendKeys(value1);
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field with alphabets By ClassName
	public void verifyMinAlphaByClassName(String ClassName, String data, String value1) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.className(ClassName)).sendKeys(value1);
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field with alphabets By Xpath
	public void verifyMinAlphaByXpath(String Xpath, String data,String value1) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.xpath(Xpath)).sendKeys(value1);
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum Length of the field with numbers Using Id, Name, ClassName, Xpath
	
	//Verify Maximum length of the field with numbers By Id
	public void verifyMaxNumById(String Id, String data, String value1) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.id(Id)).sendKeys(value1);
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Maximum length of the field with numbers By Name
	public void verifyMaxNumByName(String Name, String data, String value1) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.name(Name)).sendKeys(value1);
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Num: " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Num: " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Maximum length of the field with numbers By ClassName
	public void verifyMaxNumByClassName(String ClassName, String data, String value1) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.className(ClassName)).sendKeys(value1);
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum length of the field with numbers By Xpath
	public void verifyMaxNumByXpath(String Xpath, String data, String value1) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.xpath(Xpath)).sendKeys(value1);
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Minimum Length of the field with numbers Using Id, Name, ClassName, Xpath
	
	//Verify Minimum length of the field with numbers By Id
	public void verifyMinNumById(String Id, String data, String value1) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.id(Id)).sendKeys(value1);
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Minimum length of the field with numbers By Name
	public void verifyMinNumByName(String Name, String data, String value1) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.name(Name)).sendKeys(value1);
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Minimum length of the field with numbers By ClassName
	public void verifyMinNumByClassName(String ClassName, String data, String value1) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.className(ClassName)).sendKeys(value1);
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field with numbers By Xpath
	public void verifyMinNumByXpath(String Xpath, String data, String value1) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			int k = value1.length();
			for (int i = 1; i <= j; i+=k) {
				driver.findElement(By.xpath(Xpath)).sendKeys(value1);
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum Length of the field with alpha numerics Using Id, Name, ClassName, Xpath
	
	//Verify Maximum length of the field with alpha numerics By Id
	public void verifyMaxAlphaNumById(String Id, String data,String value1,String value2) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.id(Id)).sendKeys(value1);
				}
				else{
					driver.findElement(By.id(Id)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha Num " + value1 + " Num: " + value2 ,data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Maximum length of the field with alpha numerics By Name
	public void verifyMaxAlphaNumByName(String Name, String data,String value1,String value2) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.name(Name)).sendKeys(value1);
				}
				else{
					driver.findElement(By.name(Name)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha Num " + " \" " + value1 + value2 + " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum length of the field with alpha numerics By ClassName
	public void verifyMaxAlphaNumByClassName(String ClassName, String data,String value1,String value2) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.className(ClassName)).sendKeys(value1);
				}
				else{
					driver.findElement(By.className(ClassName)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha Num " + " \" " + value1 + value2 + " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Maximum length of the field with alpha numerics By Xpath
	public void verifyMaxAlphaNumByXpath(String Xpath, String data,String value1,String value2) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.xpath(Xpath)).sendKeys(value1);
				}
				else{
					driver.findElement(By.xpath(Xpath)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Max Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Max Length not Matched","Alpha Num " + " \" " + value1 + value2 + " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum Length of the field with alpha numerics Using Id, Name, ClassName, Xpath
	
	//Verify Minimum length of the field with alpha numerics By Id
	public void verifyMinAlphaNumById(String Id, String data,String value1,String value2) {

		try {
			driver.findElement(By.id(Id)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.id(Id)).sendKeys(value1);
				}
				else{
					driver.findElement(By.id(Id)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.id(Id)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha Num " + " \" " + value1 + value2 + " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Verify Minimum length of the field with alpha numerics By Name
	public void verifyMinAlphaNumByName(String Name, String data,String value1,String value2) {

		try {
			driver.findElement(By.name(Name)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.name(Name)).sendKeys(value1);
				}
				else{
					driver.findElement(By.name(Name)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha Num " + " \" " + value1 + value2 + " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field with alpha numerics By ClassName
	public void verifyMinAlphaNumByClassName(String ClassName, String data,String value1,String value2) {

		try {
			driver.findElement(By.className(ClassName)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.className(ClassName)).sendKeys(value1);
				}
				else{
					driver.findElement(By.className(ClassName)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.className(ClassName)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha Num " + " \" " + value1 + value2 + " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Verify Minimum length of the field with alpha numerics By Xpath
	public void verifyMinAlphaNumByXpath(String Xpath, String data,String value1,String value2) {

		try {
			driver.findElement(By.xpath(Xpath)).clear();
			int j = Integer.valueOf(data);
			for (int i = 1; i <= j; i++) {
				if(i % 2 != 0){
					driver.findElement(By.xpath(Xpath)).sendKeys(value1);
				}
				else{
					driver.findElement(By.xpath(Xpath)).sendKeys(value2);
				}
			}
			String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
			int num = value.length();
			if (num == j) {
				ATUReports.add("Min Length Matched","Alpha Num " + " \" " + value1 + value2 + " \" ", data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Min Length not Matched","Alpha Num " + " \" " + value1 + value2 + " \" ",data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select list box option in loop by Xpath
	public void selectListInLoopByXpath(String Xpath,String Xpath1,String data) {
		try {
		List<WebElement> element = driver.findElements(By.xpath(Xpath));
		int size = element.size();
			driver.findElement(By.xpath(Xpath1)).click();
			for (int i = 1; i <= size; i++) {
				String value = driver.findElement(By.xpath(Xpath + "[" + i + "]")).getText().trim();
				////System.out.println(value);
				if (value.equalsIgnoreCase(data)) {
					driver.findElement(By.xpath(Xpath + "[" + i + "]")).click();
					ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}
				else{
					driver.findElement(By.className("jScrollArrowDown")).click();
				}
			}
		} catch (Exception e) {
			ATUReports.add("Option selection failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			e.printStackTrace();
		}
	
	}
	
	//Verify and click a value in loop by Xpath
	public void verifyClickInLoopByXpath(String Xpath , String data) {
		try {
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
			for(int i =1; i <= size; i++){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath + "[" + i + "]/td[4]/a")));
			String value=driver.findElement(By.xpath(Xpath + "[" + i + "]/td[4]/a")).getText().trim();
			if(value.equals(data)){
			driver.findElement(By.xpath(Xpath + "[" + i + "]/td[4]/a")).click();
			ATUReports.add(data + " clicked",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			}
		} catch (NoSuchElementException e) {
			ATUReports.add(data + " not clicked",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Switch to last window
	public void switchToLastWindow(){
		try {
			Set<String> window = driver.getWindowHandles();
			for (String string : window) {
				driver.switchTo().window(string);
			}
		} catch (Exception e) {
		}
	}
	
	//Switch to first window
	public void switchToFirstWindow(){
		try {
			Set<String> window = driver.getWindowHandles();
			for (String string : window) {
				driver.switchTo().window(string);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Select check box using Attribute value class by Id
	public void selectCheckBoxClassById(String Id,String Id1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String val=driver.findElement(By.id(Id1)).getText().trim();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.id(Id)).getAttribute("class");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				driver.findElement(By.xpath(Id)).click();
				ATUReports.add("Check Box selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}			
		} 
		}catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Select check box using Attribute value class by Name
	public void selectCheckBoxClassByName(String Name,String Name1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			String val=driver.findElement(By.name(Name1)).getText().trim();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.name(Name)).getAttribute("class");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				driver.findElement(By.name(Name)).click();
				ATUReports.add("Check Box selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}			
		} 
		}catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select check box using Attribute value class by ClassName
	public void selectCheckBoxClassByClassName(String ClassName,String ClassName1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			String val=driver.findElement(By.className(ClassName1)).getText().trim();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.className(ClassName)).getAttribute("class");
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				driver.findElement(By.className(ClassName)).click();
				ATUReports.add("Check Box selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}			
		} 
		}catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	//Select check box using Attribute value class by Xpath
	public void selectCheckBoxClassByXpath(String Xpath,String Xpath1,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String val=driver.findElement(By.xpath(Xpath1)).getText().trim();
			if(val.equalsIgnoreCase(data)){
			String value=driver.findElement(By.xpath(Xpath)).getAttribute("class");
		//	//System.out.println(" came " + value);
			if(value.equalsIgnoreCase("checked")){
				ATUReports.add("Check Box Selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else if(value.equals(" ")){
				driver.findElement(By.xpath(Xpath)).click();
				ATUReports.add("Check Box selected", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}			
		} 
		}catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	//Quit Browser
	public void quitBrowser() {
		driver.quit();
	//	ATUReports.add("Browser closed", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
	}
	
	public void verifyTextSplitByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			if (Text.equalsIgnoreCase(data)) {
				ATUReports.add("Text matched", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyTranStatusByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			if (Text.equals(data.trim())) {
				ATUReports.add("Transaction Status", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Transaction Status","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction status is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void verifyTranStatusDescByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			if (Text.equalsIgnoreCase(data.trim())) {
				ATUReports.add("Status Description", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Status Description","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction Status Desc is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	
	public void verifyTranStatusDescById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getText().trim();
			////System.out.println("'"+Text+"'");
			////System.out.println("'"+data+"'");
			if (Text.equalsIgnoreCase(data.trim())) {
				ATUReports.add("Status description", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Status description","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction Status descripiton is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

		public void getTransactionIDByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			WebElement element = driver.findElement(By.xpath(Xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			TransactionID = driver.findElement(By.xpath(Xpath)).getText().trim();
			try {
				Properties pr = new Properties();
				pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
				
				String Query = "select card_name_serach_tx from tranlog where tran_id='" + TransactionID + "'";
				String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
				FileInputStream in = new FileInputStream("tran_log.properties");
				Properties props = new Properties();
				props.load(in);
				in.close();
				FileOutputStream out = new FileOutputStream("tran_log.properties");
				//System.out.println("Entered");
				props.setProperty(Data + "_TransactionID", TransactionID);
				props.store(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//String[] str1=Text.split(":");
				ATUReports.add("Transaction ID",TransactionID, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction ID not present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
/*	public void getPaymentIDByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			 PaymentID = driver.findElement(By.xpath(Xpath)).getText().trim();
			//String[] str1=Text.split(":");
				ATUReports.add("Payment ID",PaymentID, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Payment ID not present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}*/
	
	public void IsDisplayedById(String Id,String Name) {
		try {
//			//System.out.println("Entered");
//			WebDriverWait wait = new WebDriverWait(driver, 10);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
		if(driver.findElement(By.id(Id)).isDisplayed())
			ATUReports.add("Element present",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("Element not present", Name,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Element not present", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void IsNotDisplayedById(String Id,String Name) {
		try {
		if(!driver.findElement(By.id(Id)).isDisplayed())
			ATUReports.add("Element not present",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("Element present",Name, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (Exception e) {
			ATUReports.add("Element present verification failed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public static boolean sleep(String Msec) {
		try {
			//System.out.println("Iam halting for "+Msec+" Msec");
			Thread.sleep(Integer.parseInt(Msec));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void verifyTextBoxById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getAttribute("value");
			if (Text.equals(""))
			{
				ATUReports.add("Card not present in FC table",Text,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text Present",Text,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyEmptyTextBoxById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getAttribute("value");
			if (Text.equals(""))
			{
				ATUReports.add("Card list empty",Text,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Card list not empty",Text,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyCardListById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getText();
			if (!Text.equals(""))
			{
				ATUReports.add("Card list not empty",Text,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Card list empty",Text,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}


	public void IsPresentById(String Id) {
		try {
//			//System.out.println("Entered");
//			WebDriverWait wait = new WebDriverWait(driver, 10);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
		if(driver.findElement(By.id(Id)).isDisplayed())
			ATUReports.add("Faster CheckOut button present", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			
			ATUReports.add("Faster CheckOut button not present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void verifyCountGreaterThanValueByTagName(String TagName,String value) {
		try {
			String names = "";
			List<WebElement> tags = driver.findElements(By.tagName(TagName));
			for (WebElement tag : tags) {
				names = names+","+tag.getText();
			}
		if(tags.size()>Integer.parseInt(value))
			ATUReports.add("Available Options", names.substring(1),LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("More than "+value+" option not Available",names.substring(1), LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (Exception e) {
			ATUReports.add("Actual count is not greater than the expected", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void verifyTagCountByTagName(String TagName,String value) {
		try {
			String names = "";
			List<WebElement> tags = driver.findElements(By.tagName(TagName));
			for (WebElement tag : tags) {
				names = names+","+tag.getText();
			}
		if(driver.findElements(By.tagName(TagName)).size()==Integer.parseInt(value))
			ATUReports.add("Only option Available",names.substring(1), LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("More than "+value+" option Available",names.substring(1), LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (Exception e) {
			ATUReports.add("Actual count is not greater than the expected", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void switchToFrameById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			driver.switchTo().frame(driver.findElement(By.id(Id)));
			ATUReports.add("Switched to iFrame", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Frame Focus failed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void clearTextById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			driver.findElement(By.id(Id)).clear();
		//	driver.findElement(By.id(Id)).sendKeys(data);
		//	String value = driver.findElement(By.id(Id)).getAttribute("value");
		//	ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not cleared", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to clear Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	
	public void enterTransactionIDByName(String Name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(TransactionID);
		//	String value = driver.findElement(By.name(Name)).getAttribute("value");
			ATUReports.add("Transaction ID entered", TransactionID, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Transaction ID not entered", TransactionID, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}

	}

	public void IsAllElementsDisplayed(String Id,String Id1,String Id2,String Id3) {
		try {
		if(driver.findElement(By.id(Id)).isDisplayed()
				&& driver.findElement(By.id(Id1)).isDisplayed()
				&& driver.findElement(By.id(Id2)).isDisplayed()
				&& driver.findElement(By.id(Id3)).isDisplayed())
			ATUReports.add("All elements in iFrame is displayed", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("Some elements in iFrame is not displayed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Element not present", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void IsAllElementsEnabled(String Id1,String Id2,String Id3) {
		try {
		if(driver.findElement(By.id(Id1)).isEnabled()
				&& driver.findElement(By.id(Id2)).isEnabled()
				&& driver.findElement(By.id(Id3)).isEnabled())
			ATUReports.add("All elements in iFrame is enabled", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("Some elements in iFrame is not enabled", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Element not present", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	public void enterCaptchaByName(String Name, String data) {
		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(data);
			String value = driver.findElement(By.name(Name)).getText();
			ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add(Name+" - Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Captcha field is not available", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyCaptchaTextByXpath(String Xpath, String data) {
		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			if (Text.equalsIgnoreCase(data)) {
				ATUReports.add("Text matched","",data,Text,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("Captcha not present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Captcha field is not available", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyPINTextByXpath(String Xpath, String data) {
		////System.out.println("Entered");
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
		//	//System.out.println(Text);
			if (Text.equalsIgnoreCase(data)) {
				ATUReports.add("Text matched","",data,Text,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Text not matched","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("PIN not present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("PIN field is not available", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

		public void verifyiFrameErrorText(String Id, String data) {
		try {
			String val = driver.findElement(By.id(Id)).getText();
			if (val.equals(data)) {
				ATUReports.add("iFrame URL not allowed to open in another window", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("iFrame Url is opened in another window", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (Exception e) {
			ATUReports.add("iFrame URL not allowed to open in another window", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}


		public void selectCardMonthById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			Select i = new Select(driver.findElement(By.id(Id)));
			i.selectByVisibleText(data);
			ATUReports.add("Card Month selected", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Card Month not selected",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("Card Month not available",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}


	public void verifyCardNumberMaskingById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getText().trim();
			if (Text.contains("*")) {
				ATUReports.add("Card Number masked",Text,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Card Number not masked",Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("Card Number Masking failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Card Number Masking is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyCardNumberNotMaskedById(String Id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			String Text = driver.findElement(By.id(Id)).getText().trim();
			if (!Text.contains("*")) {
				ATUReports.add("Card Number not masked",Text,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Card Number masked",Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("Card Number Masking failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Card Number Masking is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
		public void selectCardYearById(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			Select i = new Select(driver.findElement(By.id(Id)));
			i.selectByVisibleText(data);
			ATUReports.add("Card Year selected", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Card Year not selected",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch (WebDriverException e1) {
			ATUReports.add("Card Year not available",data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

		public String executeGivenQueryInDB(String ServerIp, int portNo, String userName, String password,String SSID,String Query) {
			
			try {
				Connection con = null;
				ResultSet rs = null;
				Statement stmt = null;
				String Val = "";
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@" + ServerIp + ":" + portNo + ":"+SSID, "" + userName + "",
							"" + password + "");
					stmt = con.createStatement();
					try {
						rs=stmt.executeQuery(Query);
					} catch (Exception e) {
						
					}
					while (rs.next()) {
						Val = rs.getString(1);
					}
					stmt.close();
					stmt = null;
					rs.close();

					// Close the local connection
					if ((con != null)) {
						con.close();
						con = null;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				/*if (Val.equals(Text)) {
					a = true;
				}*/
				return Val;
			} catch (Exception e) {
			return "";
			}
			
		}
		
		
		public void verifyCustIDPresentInDB() throws FileNotFoundException, IOException{
			//System.out.println("Entered");
			try {
				//System.out.println("Entered");
				Properties pr = new Properties();
				// Step 2: Load the Property file
				pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
				String Query = "select card_num_hash_tx from faster_chck_reg where cust_id = '2345678'";
				String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
			//System.out.println("Data " + Data);
					ATUReports.add("Hashed Card Value",Data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			} catch (NumberFormatException e) {
				ATUReports.add("DB verification failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

			}
		}
		
		public void deleteCustIDPresentInDB() throws FileNotFoundException, IOException{
			//System.out.println("Entered");
			try {
				//System.out.println("Entered");
				Properties pr = new Properties();
				// Step 2: Load the Property file
				pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
				String Query = "delete from faster_chck_reg";
				String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
			//System.out.println("Data " + Data);
					ATUReports.add("Hashed Card Value",Data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
			} catch (NumberFormatException e) {
				ATUReports.add("DB verification failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

			}
		}

public void enterPaymentIDByName(String PayId) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(PayId)));
				driver.findElement(By.name(PayId)).clear();
				driver.findElement(By.name(PayId)).sendKeys(PaymentID);
			//	String value = driver.findElement(By.name(Name)).getAttribute("value");
				ATUReports.add("Payment ID entered", PaymentID, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (NoSuchElementException e) {
				ATUReports.add("Payment ID not entered", PaymentID, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (WebDriverException e1) {
				ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}

		}
public void verifyOverNumById(String Id, String data, String value1) {

			try {
				driver.findElement(By.id(Id)).clear();
				int j = Integer.valueOf(data);
				int k = value1.length();
				for (int i = 1; i <= j; i+=k) {
					driver.findElement(By.id(Id)).sendKeys(value1);
				}
				String value = driver.findElement(By.id(Id)).getAttribute("value");
				int num = value.length();
				if (num == j) {
					ATUReports.add("Max Length Matched","Num " + " \" " + value1 +  " \" ", data,num + "",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				} else {
					ATUReports.add("Max Length not Matched","Num " + " \" " + value1 +  " \" ",data,num + "",LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (NoSuchElementException e) {
				ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (WebDriverException e1) {
				ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}

		
		public void verifyRecordExistINTable() throws FileNotFoundException, IOException{
			try {
				Properties pr = new Properties();
				pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
				String Query = "select payment_id from preauth_log where payment_id='"+PaymentID+"'";
				String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
				String Query1 = "select xid from preauth_log where payment_id='"+PaymentID+"'";
				String XID = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query1);
				String Query2 = "select cavv from preauth_log where payment_id='"+PaymentID+"'";
				String CAVV = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query2);
				if(Data.equals(PaymentID))
					ATUReports.add("Record Exists in DB","Payment ID : "+PaymentID,"XID : "+XID,"CAVV : "+CAVV, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				else
					ATUReports.add("Record Doesnot Exists in DB", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NumberFormatException e) {
				ATUReports.add("DB verification failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

			}
		}
		
		public void getiFrameUrl(){
			try {
				getUrl=driver.getCurrentUrl();
				ATUReports.add("iFrame Url",getUrl, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (Exception e) {
				ATUReports.add("iFrame Url fetching failed",getUrl, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				e.printStackTrace();
			}
		}
		
		//Launch Browser
		public void launchiFrameUrl(String browser) throws IOException {
				String path = "D:\\files\\driver";
				try {
					if (browser.equalsIgnoreCase("firefox")) {
						driver = new FirefoxDriver();
					} else if (browser.equalsIgnoreCase("chrome")) {
						System.setProperty("webdriver.chrome.driver", path + "\\chromedriver.exe");
						driver = new ChromeDriver();
					} else if (browser.equalsIgnoreCase("ie")) {
						System.setProperty("webdriver.ie.driver", path + "\\IEDriverServer.exe");
						driver = new InternetExplorerDriver();
					} else if (browser.equals("Phantomjs")) {
						 Capabilities caps = new DesiredCapabilities();
						((DesiredCapabilities) caps).setJavascriptEnabled(true);
						((DesiredCapabilities) caps).setCapability("takesScreenshot", false);
						((DesiredCapabilities) caps).setCapability("locationContextEnabled", true);
						((DesiredCapabilities) caps).setCapability("acceptSslCerts", true);
						File file = new File(path + "\\phantomjs.exe");
						System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
						driver = new PhantomJSDriver(caps);
								}
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.get(getUrl);
					ATUReports.setWebDriver(driver);
					ATUReports.setAuthorInfo("Testing Services", Utils.getCurrentTime(), "1.0");
					ATUReports.setTestCaseReqCoverage("Payment Gateway Automation");
				} catch (Exception e) {
					ATUReports.add("Browser opening failed", browser, LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				}
			}	
	public void IsCaptchaEnabledAndDisplayed(String Name) {
			try {
			if(driver.findElement(By.name(Name)).isDisplayed()
					&& driver.findElement(By.name(Name)).isEnabled())
				ATUReports.add("Captcha field is displayed and enabled", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			else
				ATUReports.add("Captcha field is not displayed and enabled",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (Exception e) {
				ATUReports.add("Element not present", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	
	public void verifyRecordExistInTranLogTable() throws FileNotFoundException, IOException{
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
			String Query = "select payment_id from tranlog where payment_id='"+PaymentID+"'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
			String Query1 = "select response_code from tranlog where payment_id='"+PaymentID+"'";
			String responseCode = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query1);
			
			if(Data.equals(PaymentID))
				ATUReports.add("Record Exists in DB","Payment ID : "+PaymentID,"Resp Code : "+responseCode, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			else
				ATUReports.add("Record Doesnot Exists in DB", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		} catch (NumberFormatException e) {
			ATUReports.add("DB verification failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}

	public void verifyTranStatusContainsByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			String[] str1 = Text.split(":");
			if (Text.contains(data)) {
				ATUReports.add("Transaction Status", "", data, str1[0], LogAs.PASSED, new CaptureScreen(
						ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Transaction Status", "", data, str1[0], LogAs.FAILED, new CaptureScreen(
						ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction status is unavailable", LogAs.FAILED, new CaptureScreen(
					ScreenshotOf.BROWSER_PAGE));
		}
	}

	// select response_rcvd_time from tranlog where tran_id='201700312316271';

	public void enterHHMMSSfromOldTxnResp(String HHName, String MMName, String SSName, String Name)
			throws FileNotFoundException, IOException {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));

			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("tran_log.properties")));

			String Query = "select response_rcvd_time from tranlog where tran_id='"
					+ prop.getProperty(Name + "_TransactionID") + "'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")),
					pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
			String[] txnresp = Data.split(" ");
			//System.out.println(Data);
			//System.out.println(txnresp.toString());
			String[] time = txnresp[1].split(":");
			//System.out.println(time.toString());
			
			//System.out.println(time[0]);
			//System.out.println(time[1]);
			//System.out.println(time[2].substring(0, 2));

			
			if (SSName.equals("secDef")) {
				enterTextByName(HHName, String.valueOf((Integer.parseInt(time[0])-2)));
				enterTextByName(MMName, String.valueOf((Integer.parseInt(time[1]))));
				enterTextByName("second", "00");
			} else {
				enterTextByName(HHName, time[0]);
				enterTextByName(MMName, time[1]);
				enterTextByName(SSName, time[2].substring(0, 2));
			}
//			enterTextByName(SSName, "00");
		} catch (NumberFormatException e) {
			ATUReports.add("DB verification failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}

	public void selectTranIDByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			try {
				if (driver.findElement(By.name("last")).isDisplayed()) {
					driver.findElement(By.name("last")).click();
				}
			} catch (Exception e) {
			}
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
			for (int i = 1; i <= size; i++) {
				String val = driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[4]/a")).getText().trim();
				if (val.equals(TransactionID)) {
					String value = driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[1]/div/a")).getAttribute(
							"class");
					if (value.equals("")) {
						driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[1]/div/a")).click();
						ATUReports.add("Record Selected", TransactionID, LogAs.PASSED, new CaptureScreen(
								ScreenshotOf.BROWSER_PAGE));
					} else if (value.equalsIgnoreCase("checked")) {
						ATUReports.add("Record Selected", TransactionID, LogAs.PASSED, new CaptureScreen(
								ScreenshotOf.BROWSER_PAGE));
					}
					break;
				}
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void selectActionByXpath(String Xpath, String ActionValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
			for (int i = 1; i <= size; i++) {
				String TranID = driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[4]/a")).getText().trim();
				if (TranID.equals(TransactionID)) {
					driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[2]/div/div[1]")).click();
					List<WebElement> element1 = driver.findElements(By.xpath(Xpath + "[" + i + "]" + "/td[2]/div/div[3]//span"));
					int size1 = element1.size();
					for (int j = 1; j <= size1; j++) {
						String Action = driver .findElement( By.xpath(Xpath + "[" + i + "]" + "/td[2]/div/div[3]//span" + "[" + j + "]")).getText().trim();
						if (Action.equals(ActionValue)) {
							driver.findElement( By.xpath(Xpath + "[" + i + "]" + "/td[2]/div/div[3]//span" + "[" + j + "]")).click();
							ATUReports.add("Action Value Selected",ActionValue, LogAs.PASSED, new CaptureScreen(
									ScreenshotOf.BROWSER_PAGE));
							break;
						}
					}
				}
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void enterAmountByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
			for (int i = 1; i <= size; i++) {
				String TranID = driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[4]/a")).getText().trim();
				if (TranID.equals(TransactionID)) {
					driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[3]/input[1]")).clear();
					driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[3]/input[1]")).sendKeys(data);
					String value = driver.findElement(By.xpath(Xpath)).getAttribute("value");
					ATUReports.add("Text entered", data, "", value, LogAs.PASSED, new CaptureScreen(
							ScreenshotOf.BROWSER_PAGE));
				}
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void getMultiSetIDByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Str1 = null;
			Str1 = driver.findElement(By.xpath(Xpath)).getText().trim();
			if(Str1.length()<=0)
				ATUReports.add("Multi Set ID not found",  LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			String[] Split = Str1.split("-");
			MultiSetID = Split[1].trim();
			ATUReports.add("Multi Set ID", MultiSetID, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Payment ID not present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void queryTranID(String Result)
 throws FileNotFoundException, IOException {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));

			String Query = "select result_code from tranlog where tran_set_id='" + MultiSetID + "'";
		//	//System.out.println(Query);
			String Data = "";
			try {
				Thread.sleep(2000);
				Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")),
						pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);

			} catch (Exception e) {

			}
//			//System.out.println("Len" + Data.length());
//			//System.out.println("df:" + Data);
//			//System.out.println("Res:" + Result);
			if (!Data.equals("")) {
				if (Data.equals(Result)) {
//					//System.out.println("Am in ");
					ATUReports.add("Transaction Status", "", Result, Data, LogAs.PASSED, null);
				} else {
					ATUReports.add("Transaction Status", "", Result, Data, LogAs.FAILED, null);
				}
			} else if (Data.equals("")) {

//				//System.out.println("Am sec in");
				String Query1 = "select tran_status from multi_transaction where tran_set_id = '" + MultiSetID + "'";
//				//System.out.println(Query1);
				String Data1 = executeGivenQueryInDB(pr.getProperty("ServerIP"),
						Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"),
						pr.getProperty("Password"), pr.getProperty("SID"), Query1);
//				//System.out.println(Data1);
				if (Data1.equals("N") || Data1.equals("S")) {
					ATUReports.add("Transaction Yet to be Processed", MultiSetID, LogAs.FAILED, null);
				} else if (Data1.equals("Y")) {
					String Query2 = "select error_description from multi_transaction where tran_set_id = '"
							+ MultiSetID + "'";
//					//System.out.println(Query2);
					String Data2 = executeGivenQueryInDB(pr.getProperty("ServerIP"),
							Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"),
							pr.getProperty("Password"), pr.getProperty("SID"), Query2);
					if (Data2.equals(Result)) {
//						//System.out.println(Data2);
						ATUReports.add("Transaction Status", "", Result, Data2, LogAs.PASSED,null);
					} else {
						ATUReports.add("Transaction Status", "", Result, Data2, LogAs.FAILED, null);
					}
				}

			} else {
				ATUReports.add("Transaction Not Processed", MultiSetID, LogAs.FAILED, null);
			}

		} catch (NumberFormatException e) {
			ATUReports.add("DB verification failed", LogAs.FAILED, null);

		}
	}
	
	//created by durga on 04-01-2017
	
	public void enterInstIdById() throws FileNotFoundException, IOException, InterruptedException {
		String data="";
		try {	
			Thread.sleep(1000);
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("LoginPage.properties")));
			data = prop.getProperty("InstitutionLogin.InstId.value");
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.InstId.Id"))).clear();
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.InstId.Id"))).sendKeys(data);
			String value = driver.findElement(By.id(prop.getProperty("InstitutionLogin.InstId.Id"))).getAttribute("value");
			ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void enterUserIdById() throws FileNotFoundException, IOException {
		String data="";
		try {	
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("LoginPage.properties")));
			data = prop.getProperty("InstitutionLogin.UserId.value");
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.UserId.Id"))).clear();
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.UserId.Id"))).sendKeys(data);
			String value = driver.findElement(By.id(prop.getProperty("InstitutionLogin.UserId.Id"))).getAttribute("value");
			ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void enterPasswordById() throws FileNotFoundException, IOException {
		String data="";
		try {	
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("LoginPage.properties")));
			data = prop.getProperty("InstitutionLogin.Password.value");
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.Password.Id"))).clear();
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.Password.Id"))).sendKeys(data);
			String value = driver.findElement(By.id(prop.getProperty("InstitutionLogin.Password.Id"))).getAttribute("value");
			ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void enterCaptchaById() throws FileNotFoundException, IOException {
		String data="";
		try {	
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("LoginPage.properties")));
			data = prop.getProperty("InstitutionLogin.Captcha.value");
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.Captcha.Id"))).clear();
			driver.findElement(By.id(prop.getProperty("InstitutionLogin.Captcha.Id"))).sendKeys(data);
			String value = driver.findElement(By.id(prop.getProperty("InstitutionLogin.Captcha.Id"))).getAttribute("value");
			ATUReports.add("Text entered", data,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void selectListByXpath(String Xpath,String Xpath1,String data) {
		try {
		List<WebElement> element = driver.findElements(By.xpath(Xpath));
		int size = element.size();
			driver.findElement(By.xpath(Xpath1)).click();
			for (int i = 1; i <= size; i++) {
				String value = driver.findElement(By.xpath(Xpath + "[" + i + "]")).getText().trim();
				////System.out.println(value);
				if (value.equalsIgnoreCase(data)) {
					driver.findElement(By.xpath(Xpath + "[" + i + "]")).click();
					ATUReports.add("Option selected", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}
				
			}
		} catch (Exception e) {
			ATUReports.add("Option selection failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			e.printStackTrace();
		}
	}
	
	public void verifyResultStatusByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			WebElement element = driver.findElement(By.xpath(Xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			if (Text.equals(data)) {
				ATUReports.add("Transaction Status", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Transaction Status","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction status is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	public void enterTransactionIDFromProp(String Name, String CardHolderData) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("tran_log.properties")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(prop.getProperty(CardHolderData + "_TransactionID"));
		//	System.out.println("Entered2");
			ATUReports.add("Transaction ID entered", prop.getProperty(CardHolderData + "_TransactionID"), LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Transaction ID not entered", prop.getProperty(CardHolderData + "_TransactionID"),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}
	
		public void clickMenusByXpath(String Xpath,String MenusOption) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
	
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
			for (int i = 1; i <= size; i++) {
				String Value=driver.findElement(By.xpath(Xpath + "[" + i + "]")).getText().trim();
				if(Value.equalsIgnoreCase(MenusOption)){
			driver.findElement(By.xpath(Xpath + "[" + i + "]")).click();
			ATUReports.add("Button clicked",MenusOption,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			break;
				}
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("Button not clicked",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
		public void verifyClickMenusByXpath(String Xpath,String MenusOption) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
		
				List<WebElement> element = driver.findElements(By.xpath(Xpath));
				int size = element.size();
		//		//System.out.println(size);
				for (int i = 1; i <= size; i++) {
					String Value=driver.findElement(By.xpath(Xpath + "[" + i + "]")).getText().trim();
			//		//System.out.println("Actual :" +Value);
			//		//System.out.println(MenusOption);
					if(Value.equalsIgnoreCase(MenusOption)){
				driver.findElement(By.xpath(Xpath + "[" + i + "]")).click();
				ATUReports.add("Button clicked",MenusOption,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				break;
					}
				}
			} catch (NoSuchElementException e) {
				ATUReports.add("Button not clicked",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (WebDriverException e1) {
				ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	
		
	public void selectPaymentIDByXpath(String Xpath) {
		try {
			// //System.out.println("Entered");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
			// //System.out.println("Size : " + size);
			for (int i = 1; i <= size; i++) {
				String val = driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[1]/a")).getText().trim();
				// //System.out.println("Transac ID : " + val);
				// //System.out.println("Tran ID : " + TransactionID);
				if (val.equals(PaymentID)) {
						driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[1]/a")).click();
						ATUReports.add("Record Selected",PaymentID, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					break;
				}
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void selectOrderListPayIDByXpath(String Xpath,String data) {
		try {
			// //System.out.println("Entered");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
			// //System.out.println("Size : " + size);
			for (int i = 1; i <= size; i++) {
				String ResCode=driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[10]")).getText().trim();
				if(ResCode.equals(data)){
				String val = driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[2]")).getText().trim();
				// //System.out.println("Transac ID : " + val);
				// //System.out.println("Tran ID : " + TransactionID);
				if (val.equals(PaymentID)) {
					String value = driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[1]/div/a")).getAttribute("class");
					// //System.out.println("Attr :"+ value);
					if (value.equals("")) {
						driver.findElement(By.xpath(Xpath + "[" + i + "]" + "/td[1]/div/a")).click();
						ATUReports.add("Record Selected",PaymentID, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					} else if (value.equalsIgnoreCase("checked")) {
						ATUReports.add("Record Selected", PaymentID,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
					break;
				}
			}
				else{
					ATUReports.add("Result Code does not exist",data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	
	public void verifyErrorStatusByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			WebElement element = driver.findElement(By.xpath(Xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			String Res=Text.substring(8);
			if (Res.equals(data)) {
				ATUReports.add("Transaction Status", "",data,Res, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Transaction Status","", data,Res, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction status is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void getTranIDByXpath(String Xpath) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
		WebElement element = driver.findElement(By.xpath(Xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		String TranID = driver.findElement(By.xpath(Xpath)).getText().trim();
		//String[] str1=Text.split(":");
			ATUReports.add("Transaction ID",TranID, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	} catch (NoSuchElementException e) {
		ATUReports.add("No such element present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	} catch (WebDriverException e1) {
		ATUReports.add("Transaction ID not present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
}
	
	public void IsFieldEnabled(String Name,String Name1) {
		try {
//			//System.out.println("Entered");
//			WebDriverWait wait = new WebDriverWait(driver, 10);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
		if(driver.findElement(By.name(Name)).isEnabled()
				&& driver.findElement(By.name(Name1)).isEnabled())
			ATUReports.add("Text Box Enabled", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("Not allowing multiple captures", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Element not present", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
public void getCaptureTransactionID(){
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
			String Query = "select card_name_serach_tx from tranlog where tran_id='" + TransactionID + "'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"),
					Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"),
					pr.getProperty("Password"), pr.getProperty("SID"), Query);
			Thread.sleep(2000);
		//	//System.out.println("Multi is :"+MultiSetID);
			String Query1 = "select tran_id from tranlog where tran_set_id='" + MultiSetID + "'";
			String Data1 = executeGivenQueryInDB(pr.getProperty("ServerIP"),
					Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"),
					pr.getProperty("Password"), pr.getProperty("SID"), Query1);
			FileInputStream in = new FileInputStream("tran_log.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
			FileOutputStream out = new FileOutputStream("tran_log.properties");
			////System.out.println("Data1 is "+Data1);
			props.setProperty(Data + "_Capture_TransactionID", Data1);
			props.store(out, null);
			out.close();
			
			TransactionID=Data1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//Load Property File
public Properties loadObjectRepository(String FileName) throws FileNotFoundException, IOException {
	// Step 1: Instantiate Properties (java.util)
	Properties p = new Properties();
	// Step 2: Load the Property file
	p.load(new FileInputStream(new File(FileName)));
	return p;
}

public void propertyfile(String file) {
	try {
		prop = loadObjectRepository(file);
	} catch (FileNotFoundException e) {
	} catch (IOException e) {
	}
}

public void proplaunchBrowser(String URL, String browser) throws IOException {
	//	String browser = prop.getProperty("Browser.Name");
		String path = "D:\\files\\driver";
	//	String URL1 = prop.getProperty(url);
		try {

			if (prop.getProperty(browser).equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (prop.getProperty(browser).equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", path + "\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (prop.getProperty(browser).equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", path + "\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else if (prop.getProperty(browser).equals("Phantomjs")) {
				 Capabilities caps = new DesiredCapabilities();
				((DesiredCapabilities) caps).setJavascriptEnabled(true);
				((DesiredCapabilities) caps).setCapability("takesScreenshot", false);
				((DesiredCapabilities) caps).setCapability("locationContextEnabled", true);
				((DesiredCapabilities) caps).setCapability("acceptSslCerts", true);
				File file = new File(path + "\\phantomjs.exe");
				System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
				driver = new PhantomJSDriver(caps);
						}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty(URL));
			ATUReports.setWebDriver(driver);
			ATUReports.indexPageDescription = "Login Credentials Verification";
			ATUReports.setAuthorInfo("Testing Services", Utils.getCurrentTime(), "1.0");
			ATUReports.setTestCaseReqCoverage("Login Credentials Verification");
		} catch (Exception e) {
			ATUReports.add("Browser opening failed", browser, LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}
	}	
public void propEnterTextById(String Id,String data) throws FileNotFoundException, IOException, InterruptedException {
	try {	
		Thread.sleep(1000);
		driver.findElement(By.id(Id)).clear();
		driver.findElement(By.id(Id)).sendKeys(prop.getProperty(data));
		String value = driver.findElement(By.id(Id)).getAttribute("value");
		ATUReports.add("Text entered", prop.getProperty(data),"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	} catch (NoSuchElementException e) {
		ATUReports.add("Text not entered", prop.getProperty(data), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	} catch (WebDriverException e1) {
		ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
}

public void propSelectVisibleTextById(String Id, String data) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
		Select i = new Select(driver.findElement(By.id(Id)));
		i.selectByVisibleText(prop.getProperty(data));
		ATUReports.add("Option selected", prop.getProperty(data), LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	} catch (NoSuchElementException e) {
		ATUReports.add("Option not selected",prop.getProperty(data), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
	catch (WebDriverException e1) {
		ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
}

public void getRefundTransactionID() {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
			String Query = "select card_name_serach_tx from tranlog where tran_id='" + TransactionID + "'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")),
					pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
			Thread.sleep(2000);
			// System.out.println("Multi is :"+MultiSetID);
			String Query1 = "select tran_id from tranlog where tran_set_id='" + MultiSetID + "'";
			String Data1 = executeGivenQueryInDB(pr.getProperty("ServerIP"),
					Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"),
					pr.getProperty("SID"), Query1);
			FileInputStream in = new FileInputStream("tran_log.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
			FileOutputStream out = new FileOutputStream("tran_log.properties");
			// System.out.println("Data1 is "+Data1);
			props.setProperty(Data + "_Refund_TransactionID", Data1);
			props.store(out, null);
			out.close();

			TransactionID = Data1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void getCaptureTranID() {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
			String Query = "select card_name_serach_tx from tranlog where tran_id='" + TransactionID + "'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")),
					pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);

			FileInputStream in = new FileInputStream("tran_log.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
			FileOutputStream out = new FileOutputStream("tran_log.properties");
			// System.out.println("Data1 is "+Data1);
			props.setProperty(Data + "_Capture_TransactionID", TransactionID);
			props.store(out, null);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void propEnterTextByName(String Name,String data) throws FileNotFoundException, IOException, InterruptedException {
		try {	
			Thread.sleep(1000);
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(prop.getProperty(data));
			String value = driver.findElement(By.name(Name)).getAttribute("value");
			ATUReports.add("Text entered", prop.getProperty(data),"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", prop.getProperty(data), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void enterPaymentIDFromProp(String Name, String CardHolderData) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("tran_log.properties")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(prop.getProperty(CardHolderData + "_PaymentID"));
		//	System.out.println("Entered2");
			ATUReports.add("Payment ID entered", prop.getProperty(CardHolderData + "_PaymentID"), LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Payment ID not entered", prop.getProperty(CardHolderData + "_PaymentID"),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}

	public void getPaymentIDByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			WebElement element = driver.findElement(By.xpath(Xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			PaymentID = driver.findElement(By.xpath(Xpath)).getText().trim();
			try {
				Properties pr = new Properties();
				pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
				
				String Query = "select card_name_serach_tx from tranlog where payment_id='" + PaymentID + "'";
				String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
				FileInputStream in = new FileInputStream("tran_log.properties");
				Properties props = new Properties();
				props.load(in);
				in.close();
				FileOutputStream out = new FileOutputStream("tran_log.properties");
				//System.out.println("Entered");
				props.setProperty(Data + "_PaymentID", PaymentID);
				props.store(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//String[] str1=Text.split(":");
				ATUReports.add("Payment ID",PaymentID, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Payment ID not present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void getReferenceIDByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			WebElement element = driver.findElement(By.xpath(Xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			ReferenceID = driver.findElement(By.xpath(Xpath)).getText().trim();
			try {
				Properties pr = new Properties();
				pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
				
				String Query = "select card_name_serach_tx from tranlog where tran_rfrn_tx='" + ReferenceID + "'";
				String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
				FileInputStream in = new FileInputStream("tran_log.properties");
				Properties props = new Properties();
				props.load(in);
				in.close();
				FileOutputStream out = new FileOutputStream("tran_log.properties");
				//System.out.println("Entered");
				props.setProperty(Data + "_ReferenceID", ReferenceID);
				props.store(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//String[] str1=Text.split(":");
				ATUReports.add("Reference ID",ReferenceID, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Reference ID not present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void enterReferenceIDFromProp(String Name, String CardHolderData) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("tran_log.properties")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(prop.getProperty(CardHolderData + "_ReferenceID"));
		//	System.out.println("Entered2");
			ATUReports.add("Reference ID entered", prop.getProperty(CardHolderData + "_ReferenceID"), LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Reference ID not entered", prop.getProperty(CardHolderData + "_ReferenceID"),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}
	
	public void getTrackIDByXpath(String Xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			WebElement element = driver.findElement(By.xpath(Xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			TrackID = driver.findElement(By.xpath(Xpath)).getText().trim();
			try {
				Properties pr = new Properties();
				pr.load(new FileInputStream(new File("PG_Auto_Database.properties")));
				
				String Query = "select card_name_serach_tx from tranlog where mrch_track_id='" + TrackID + "'";
				String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
				FileInputStream in = new FileInputStream("tran_log.properties");
				Properties props = new Properties();
				props.load(in);
				in.close();
				FileOutputStream out = new FileOutputStream("tran_log.properties");
				//System.out.println("Entered");
				props.setProperty(Data + "_TrackID", TrackID);
				props.store(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//String[] str1=Text.split(":");
				ATUReports.add("Track ID",TrackID, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Track ID not present", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void enterTrackIDFromProp(String Name, String CardHolderData) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("tran_log.properties")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
			driver.findElement(By.name(Name)).clear();
			driver.findElement(By.name(Name)).sendKeys(prop.getProperty(CardHolderData + "_TrackID"));
		//	System.out.println("Entered2");
			ATUReports.add("Track ID entered", prop.getProperty(CardHolderData + "_TrackID"), LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Track ID not entered", prop.getProperty(CardHolderData + "_TrackID"),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}
	
	// wait for the element to visible using id
	public boolean waitTillVisibleByID(String ID) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ID)));
		return true;
	}

	// wait for the element to visible using Xpath
	public boolean waitTillVisibleByXpath(String Xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
		return true;
	}

	// wait for the element to visible using LinkText
	public boolean waitTillVisibleByLinkText(String LinkText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(LinkText)));
		return true;
	}	

	public void switchtoFrameByName(String Name){
		try{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
		driver.switchTo().frame(driver.findElement(By.name(Name)));
		ATUReports.add("Switched to iFrame", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	} catch (Exception e) {
		ATUReports.add("Frame Focus failed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
	}
	
	public void switchtoFrameByClassName(String ClassName)
	{
		try{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
			driver.switchTo().frame(driver.findElement(By.className(ClassName)));
			ATUReports.add("Switched to iFrame", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Frame Focus failed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	public void switchtoFrameByXpath(String Xpath)
	{
		try{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			driver.switchTo().frame(driver.findElement(By.xpath(Xpath)));
			ATUReports.add("Switched to iFrame", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Frame Focus failed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	public void switchtoDefault(){
		driver.switchTo().defaultContent();
	}

	
	public void scrollingToElementByXpath(String xpath) {

		WebElement element = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
	}

	public void scrollingToElementById(String id) {

		WebElement element = driver.findElement(By.id(id));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
	}

	public void scrollingToElementByName(String name) {

		WebElement element = driver.findElement(By.name(name));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
	}

	public void scrollingToElementByClassName(String classname) {

		WebElement element = driver.findElement(By.className(classname));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
	}
	public void scrollingToBottom() {
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	// Get the Text by ID and store in property file
	public void propgetTextById() throws InterruptedException, FileNotFoundException, IOException {
		try {

			Properties pr = new Properties();
			String value = driver.findElement(By.id("txnid")).getText();
			String value1 = driver.findElement(By.id("cpin")).getText();
			FileOutputStream out = new FileOutputStream("Output.properties");
			pr.setProperty("gstn.result.txnid", value);
			pr.setProperty("gstn.result.cpin", value1);
			pr.store(out, null);
			out.close();
		} catch (NoSuchElementException e) {
			ATUReports.add("Text entry in property file failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		Thread.sleep(1000);

	}

	public void getPurchaseStatus(String PurStat) {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("Output.properties")));
			String data=pr.getProperty("gstn.result.txnid");
			
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("Readdata.properties")));
			
			String Query = "select PURCHASE_STATUS from GSTN_TRAN_LOG where GSTNTXNID='" + data + "'";
//			System.out.println(Query);
			String Data = executeGivenQueryInDB(prop.getProperty("ServerIP"), Integer.parseInt(prop.getProperty("PortNo")),
					prop.getProperty("UserName"), prop.getProperty("Password"), prop.getProperty("SID"), Query);

			if (Data.equals(PurStat)) {
				ATUReports.add("Purchase Status Verified in DB", "", PurStat, Data, LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Purchase Status not Verified in DB", "", PurStat, Data, LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (FileNotFoundException e) {
				ATUReports.add("Purchase Status verification failed", LogAs.FAILED, new CaptureScreen(
						ScreenshotOf.BROWSER_PAGE));
		} catch (IOException e) {
		}
		}

	public void getResultCode(String ResultCode) {
		
		try {

			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("Output.properties")));
			String txnid=pr.getProperty("gstn.result.txnid");
			
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("Readdata.properties")));
			
			String Query ="select TRANID from GSTN_TRAN_LOG where GSTNTXNID='" + txnid + "'";
			String Data = executeGivenQueryInDB(prop.getProperty("ServerIP"), Integer.parseInt(prop.getProperty("PortNo")),
					prop.getProperty("UserName"), prop.getProperty("Password"), prop.getProperty("SID"), Query);
			
			String Query1 ="select RESULT_CODE from TRANLOG where TRAN_ID='" + Data + "'";
			String Data1 = executeGivenQueryInDB(prop.getProperty("ServerIP"), Integer.parseInt(prop.getProperty("PortNo")),
					prop.getProperty("UserName"), prop.getProperty("Password"), prop.getProperty("SID"), Query1);
			
			if (Data1.equals(ResultCode)) {
				ATUReports.add("Result Code Verified in DB", "", ResultCode, Data1, LogAs.PASSED, new CaptureScreen(
						ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Result Code not Verified in DB", "", ResultCode, Data1, LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (Exception e) {
			ATUReports.add("Result Code verification failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} 
	}


	public void getTranDescStatus(String trandes) {
		
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("Output.properties")));
			String txnid=pr.getProperty("gstn.txnid");
			
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File("Readdata.properties")));
			
			String Query ="select payment_id from GSTN_TRAN_LOG where GSTNTXNID='" + txnid + "'";
			String Data = executeGivenQueryInDB(prop.getProperty("ServerIP"), Integer.parseInt(prop.getProperty("PortNo")),
					prop.getProperty("UserName"), prop.getProperty("Password"), prop.getProperty("SID"), Query);

			String Query1 ="select TRAN_DESC from TRANLOG where payment_id='" + Data + "'";
			String Data1 = executeGivenQueryInDB(prop.getProperty("ServerIP"), Integer.parseInt(prop.getProperty("PortNo")),
					prop.getProperty("UserName"), prop.getProperty("Password"), prop.getProperty("SID"), Query1);
			if (Data1.equals(trandes)) {
				ATUReports.add("Transaction Desc Verified in DB", "", trandes, Data1, LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} else {
				ATUReports.add("Transaction Desc not Verified in DB", "", trandes, Data1, LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (Exception e) {
			ATUReports.add("Transaction Desc verification failed", LogAs.FAILED, new CaptureScreen(
					ScreenshotOf.BROWSER_PAGE));
		} 
	}


	public void verifyMobileNumPresentInDB(String mobilenum) {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("Readdata.properties")));
			String Query = "select reg_time from oneclk_chck_reg where mob_num='" + mobilenum + "'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
		//	String[] txnresp = Data.split(" ");
			//System.out.println("Checked:"+ txnresp[0]);
			if(!Data.equals("")){
				ATUReports.add("Record exist", mobilenum, Data,LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Record not exist", mobilenum, Data,LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (Exception e) {
			ATUReports.add("Record exist failed ", LogAs.FAILED, new CaptureScreen(
					ScreenshotOf.BROWSER_PAGE));
		} 
	}
	
	public void selectOneClickById(String Id,String Id1){
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
//			String value=driver.findElement(By.id(Id)).getAttribute("checked");
			String data=driver.findElement(By.id(Id1)).getText().trim();
			if(!driver.findElement(By.id("fCCustMob")).isDisplayed()){
			driver.findElement(By.id(Id)).click();
			}
			else {
				//driver.findElement(By.id(Id)).click();
				ATUReports.add("Check Box selected",data, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Check box not found", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyMobileNumNotPresentInDB(String mobilenum) {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("Readdata.properties")));
			String Query = "select reg_time from oneclk_chck_reg where mob_num='" + mobilenum + "'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
		//	String[] txnresp = Data.split(" ");
			//System.out.println("Checked:"+ txnresp[0]);
			if(!Data.equals("")){
				ATUReports.add("Record exist", mobilenum, Data,LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Record not exist", mobilenum, Data,LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (Exception e) {
			ATUReports.add("Record exist failed ", LogAs.FAILED, new CaptureScreen(
					ScreenshotOf.BROWSER_PAGE));
		} 
	}
	
	public void IsOneClickNotDisplayedById(String Id,String Name) {
		try {
		if(!driver.findElement(By.id(Id)).isDisplayed())
			ATUReports.add("Element not present",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add("Element present",Name, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (Exception e) {
			ATUReports.add("Element not present", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void deleteMobileNumPresentInDB(String mobilenum) {
		try {
			Properties pr = new Properties();
			pr.load(new FileInputStream(new File("Readdata.properties")));
			String Query = "delete from oneclk_chck_reg where mob_num='" + mobilenum + "'";
			String Data = executeGivenQueryInDB(pr.getProperty("ServerIP"), Integer.parseInt(pr.getProperty("PortNo")), pr.getProperty("UserName"), pr.getProperty("Password"), pr.getProperty("SID"), Query);
		//	String[] txnresp = Data.split(" ");
			//System.out.println("Checked:"+ txnresp[0]);
			if(!Data.equals("")){
				ATUReports.add("Record deleted", mobilenum,LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Record not deleted", mobilenum, Data,LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (Exception e) {
			ATUReports.add("Record deletion failed ", LogAs.FAILED, new CaptureScreen(
					ScreenshotOf.BROWSER_PAGE));
		} 
	}
	
	public void verifySavedCardPresent(String Id) {
		
			try {
				if (driver.findElement(By.id(Id)).isDisplayed())
				{
					ATUReports.add("Card list present",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				ATUReports.add("Card list not present",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
	}
	
		public void browserBack() {
		try {
			driver.navigate().back();
			ATUReports.add("Browser previous page opened", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Browser previous page opening failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void browserRefresh() {
		try {
			driver.navigate().refresh();
			ATUReports.add("Browser page refreshed", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Browser page refresh failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
		public void propGetTxnid() throws InterruptedException, FileNotFoundException, IOException {
		try {

			Properties pr = new Properties();
			String value = driver.findElement(By.id("jsondata")).getText();
			String[] data = value.split("txnid", 16);
			String value1= data[1].substring(3,18);
			System.out.println("Sec" + data[1]);
			System.out.println("first" + value1);
			FileOutputStream out = new FileOutputStream("Output.properties");
			pr.setProperty("gstn.txnid", value1);
			pr.store(out, null);
			out.close();
		} catch (NoSuchElementException e) {
			ATUReports.add("Text entry in property file failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		Thread.sleep(1000);

	}
		public void IsDisplayedByClass(String Class,String Name) {
		try {
//			//System.out.println("Entered");
//			WebDriverWait wait = new WebDriverWait(driver, 10);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
		if(driver.findElement(By.className(Class)).isDisplayed())
			ATUReports.add(Name+" Element displayed",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add(Name+" Element not displayed", Name,LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add(Name+" Element display verification failed", LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void IsElementEnabledById(String Id,String Name) {
		try {
		if(driver.findElement(By.id(Id)).isEnabled())
			ATUReports.add(Name + " Element enabled",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add(Name+" Element not enabled",Name, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add(Name+" Element present verification failed", Name,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void IsElementEnabledByClass(String Class,String Name) {
		try {
		if(driver.findElement(By.className(Class)).isEnabled())
			ATUReports.add(Name+" Element enabled",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add(Name +" Element not enabled",Name, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add(Name+" Element present verification failed", Name,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void IsElementEnabledByXpath(String Xpath,String Name) {
		try {
		if(driver.findElement(By.xpath(Xpath)).isEnabled())
			ATUReports.add(Name+" Element enabled",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add(Name +" Element not enabled",Name, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add(Name+" Element present verification failed", Name,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void verifyTextEntry(String Id, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
			driver.findElement(By.id(Id)).clear();
			driver.findElement(By.id(Id)).sendKeys(data);
			ATUReports.add("Text not entered", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (NoSuchElementException e) {
			ATUReports.add("Text not entered", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void IsElementNotEnabledById(String Id,String Name) {
		try {
		if(!driver.findElement(By.id(Id)).isEnabled())
			ATUReports.add("Element not enabled",Name, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		else
			ATUReports.add(" Element enabled",Name, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (Exception e) {
			ATUReports.add("Element present verification failed", Name,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
		public void verifyCheckDigitByXpath(String Xpath, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
			if (!Text.equals(data.trim())) {
				ATUReports.add("Transaction Status", "",data,Text, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			else{
				ATUReports.add("Transaction Status","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		} catch (NoSuchElementException e) {
			ATUReports.add("No such element present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} catch (WebDriverException e1) {
			ATUReports.add("Transaction status is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
		
		public void VerifyMerchantInActive(String Xpath,String Xpath1,String data) {
			boolean success = false;
			try {
			List<WebElement> element = driver.findElements(By.xpath(Xpath));
			int size = element.size();
				driver.findElement(By.xpath(Xpath1)).click();
				for (int i = 1; i <= size; i++) {
					String value = driver.findElement(By.xpath(Xpath + "[" + i + "]")).getText().trim();
					WebElement element1 = driver.findElement(By.xpath(Xpath + "[" + i + "]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element1);
					if (value.equals(data)) {
						driver.findElement(By.xpath(Xpath + "[" + i + "]")).click();
						ATUReports.add("In-Active Merchant Listed", data, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						success = true;
						break;
					}
				}
				if (success == false) {
					ATUReports.add("In Active Merchant Not Listed", data, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (Exception e) {
				ATUReports.add("Option selection failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				e.printStackTrace();
			}
		}
		
		public void VerifyMerchantLinkInActive(String Xpath, String data) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
				String Text = driver.findElement(By.xpath(Xpath)).getText().trim();
				if (Text.equalsIgnoreCase(data)) {
					ATUReports.add("In-Active Merchant Not Listed","",data,Text,LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
				else{
					ATUReports.add("In-Active Merchant Listed","", data,Text, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			} catch (NoSuchElementException e) {
				ATUReports.add("No such element present",LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (WebDriverException e1) {
				ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
		
		public void mouseOverByPositionByLinkText(String LinkText) {
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			try {
				 WebElement Image = driver.findElement(By.linkText(LinkText));
				 Point point = Image.getLocation();
			     int xOffset = point.getX();
			       // System.out.println("Element's Position from left side Is "+xOffset +" pixels.");
			      int yOffset = point.getY();
			     //   System.out.println("Element's Position from top side Is "+yOffset +" pixels.");
			        Actions actions = new Actions(driver);
					//WebElement Personal = driver.findElement(By.xpath(Xpath));
					//waitTillVisibleByXpath(Xpath);
					actions.moveByOffset(xOffset, yOffset).build().perform();
			} catch (Exception e) {
				
			}
		}
		
		
		public void mouseOverByPositionByName(String Name) {
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			try {
				 WebElement Image = driver.findElement(By.name(Name));
				 Point point = Image.getLocation();
			     int xOffset = point.getX();
			       // System.out.println("Element's Position from left side Is "+xOffset +" pixels.");
			      int yOffset = point.getY();
			     //   System.out.println("Element's Position from top side Is "+yOffset +" pixels.");
			        Actions actions = new Actions(driver);
					//WebElement Personal = driver.findElement(By.xpath(Xpath));
					//waitTillVisibleByXpath(Xpath);
					actions.moveByOffset(xOffset, yOffset).build().perform();
			} catch (Exception e) {
				
			}
		}
		
		
		public void mouseOverByPositionById(String ID) {
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			try {
				 WebElement Image = driver.findElement(By.id(ID));
				 Point point = Image.getLocation();
			     int xOffset = point.getX();
			       // System.out.println("Element's Position from left side Is "+xOffset +" pixels.");
			      int yOffset = point.getY();
			     //   System.out.println("Element's Position from top side Is "+yOffset +" pixels.");
			        Actions actions = new Actions(driver);
					//WebElement Personal = driver.findElement(By.xpath(Xpath));
					//waitTillVisibleByXpath(Xpath);
					actions.moveByOffset(xOffset, yOffset).build().perform();
			} catch (Exception e) {
				
			}
		}
		
		
		public void mouseOverByPositionByXpath(String Xpath) {
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			try {
				 WebElement Image = driver.findElement(By.xpath(Xpath));
				 Point point = Image.getLocation();
			     int xOffset = point.getX();
			       // System.out.println("Element's Position from left side Is "+xOffset +" pixels.");
			      int yOffset = point.getY();
			     //   System.out.println("Element's Position from top side Is "+yOffset +" pixels.");
			        Actions actions = new Actions(driver);
					//WebElement Personal = driver.findElement(By.xpath(Xpath));
					//waitTillVisibleByXpath(Xpath);
					actions.moveByOffset(xOffset, yOffset).build().perform();
			} catch (Exception e) {
				
			}
		}
		
		
		public void mouseOverByPositionByClassName(String ClassName) {
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
			try {
				 WebElement Image = driver.findElement(By.className(ClassName));
				 Point point = Image.getLocation();
			     int xOffset = point.getX();
			       // System.out.println("Element's Position from left side Is "+xOffset +" pixels.");
			      int yOffset = point.getY();
			     //   System.out.println("Element's Position from top side Is "+yOffset +" pixels.");
			        Actions actions = new Actions(driver);
					//WebElement Personal = driver.findElement(By.xpath(Xpath));
					//waitTillVisibleByXpath(Xpath);
					actions.moveByOffset(xOffset, yOffset).build().perform();
			} catch (Exception e) {
				
			}
		}
		
		/* --------------------------------------------------------------------------------------------------- */
		/* --------------------------------------------Project specific wrappers : Phase start --------------- */
		/* --------------------------------------------------------------------------------------------------- */
		public void AdrLogin(String data1, String data2) {
			try {
				
				logger.info("AdrLogin selected");
		//		String Id = prop.getProperty("Bulk.Fieldname.Locator1");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))));
				driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))).clear();
				driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))).sendKeys(data1);
				String value = driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator1"))).getAttribute("value");
				ATUReports.add("Text entered", data1,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
		//		String Id = prop.getProperty("Bulk.Fieldname.Locator2");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("Bulk.Fieldname.Locator2"))));
				driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator2"))).clear();
				driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator2"))).sendKeys(data2);
				value = driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator2"))).getAttribute("value");
				ATUReports.add("Text entered", data2,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
		//		Id = prop.getProperty("Bulk.Fieldname.Locator3");
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
		//		driver.findElement(By.id(Id)).clear();
		//		driver.findElement(By.id(Id)).sendKeys(data3);
		//		value = driver.findElement(By.id(Id)).getAttribute("value");
		//		ATUReports.add("Text entered", data3,"",value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				
				String Id = prop.getProperty("Bulk.Fieldname.Locator4");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("Bulk.Fieldname.Locator4"))));
				driver.findElement(By.id(prop.getProperty("Bulk.Fieldname.Locator4"))).click();
				ATUReports.add("Button clicked",prop.getProperty("Bulk.Fieldname.Locator4"),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
							
			} catch (NoSuchElementException e) {
				ATUReports.add("Text not entered", "", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (WebDriverException e1) {
				ATUReports.add("Unable to enter Text", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}

		} 

		/* --------------------------------------------------------------------------------------------------- */
		/* --------------------------------------------Project specific wrappers : Phase end   --------------- */
		/* --------------------------------------------------------------------------------------------------- */		
		

		//Click By Name
		public void DoubleClickByXpath(String xpath) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(xpath)));
				Actions actions = new Actions(driver);
				actions.moveToElement(driver.findElement(By.xpath(xpath))).doubleClick().build().perform();			
				ATUReports.add(""+ " clicked","", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			 catch (NoSuchElementException e) {
				ATUReports.add(""+ " not clicked","", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (WebDriverException e1) {
				ATUReports.add("The browser is unavailable", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}		
		
		
}
