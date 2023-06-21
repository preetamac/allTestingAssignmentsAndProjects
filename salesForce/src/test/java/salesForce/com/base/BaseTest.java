package salesForce.com.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import salesForce.com.base.BaseTest;
import salesForce.com.utility.ExtentReportsUtility;
import salesForce.com.utility.Log4JUtility;
import salesForce.com.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected Logger log;
	public static WebDriver driver;
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	
	@BeforeTest
	public void setUpForBeforeTest()
	{
		log=logObject.getLogger();
		
	}
	
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeTestMethod(String browserName)
	{
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp=pro.loadFile("applicationDataProperties");
		String url=appProp.getProperty("url");
		launchBrowser(browserName);
		goToUrl(url);
		
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod()
	{
		//driver.close();
		log.info("Driver closed");
	}
	
	
	
	public  void launchBrowser(String browserName)
	{
		switch(browserName)
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver() ;
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		System.out.println(browserName+" is opened");
		//log.info(browserName+"Browser is launched");
	}

	
	public void goToUrl(String url)
	{
		driver.get(url);
		//log.info(url+" is Entered");
	}
	public void closeBrowser()
	{
		driver.close();
		//log.info("Current browser closed");
	}
}
