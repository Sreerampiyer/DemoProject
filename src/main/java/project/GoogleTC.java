package project;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class GoogleTC {
	{
		
	}
	RemoteWebDriver driver;
@Test
public void openBroser() {
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("https://www.google.com/");
try {
	Thread.sleep(7000);
	System.out.println("Browser Opened");
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

driver.quit();
System.out.println("Browser Closed");
	
}


}


