package salesForce.com.pages.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import salesForce.com.utility.Constants;
import salesForce.com.utility.ExtentReportsUtility;
import salesForce.com.utility.Log4JUtility;

public class BasePage {
	
	
	protected  WebDriver driver;
	protected WebDriverWait wait;
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected Logger log;
	protected ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		log=logObject.getLogger();
	}
	
	public void clickElement(WebElement element,String objName)
	{
		if(element.isDisplayed()) {
			element.click();
			log.info("Pass: "+objName+" element clicked");
		}else {
			log.error("Fail: "+objName+" element not dispalyed");
		}
	}
	
	
	public  void getPageTitle( WebDriver driver,String expectedTitle)
	{
		String actualTitle=driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("Landed on "+expectedTitle+" correct home page");
		} else {
			System.out.println("Not Correct home page");
		}
	}
	
	public  void enterText(WebElement element,String data,String objName)
	{
		
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			log.info("Pass: "+objName+" is entered to the username field");
		//	report.logTestInfo("pass: field is entered to the username field");
		} else {
			log.error("Fail:"+ objName+" element is not displayed");
		}
		
	}
	
	
	public String pageTitle()
	{
		return driver.getTitle();
		
	}

	
	
	public void refreshPage()
	{
		driver.navigate().refresh();
	}
	
	public String getTextFromWebElement(WebElement element,String name)
	{
		if(element.isDisplayed())
		{
			return element.getText();
		}else {
			log.info(name+" Web element is not display");
			return null;
		}
	}
	
//---------------------reusable method for Handling alerts--------------- 		
	public Alert switchToAlert()
	{
		Alert alert=driver.switchTo().alert();
		log.info("Swirched to Alert Box");
		return alert;
	}
	
	public void AcceptAlert(Alert alert)
	{
		log.info("Alert clicked Accept/yes/ok button");
		alert.accept();
	}
	
	public String getAlertText(Alert alert)
	{
		log.info("Extracting text in the alert");
		return alert.getText();
	}
	
	public void dismisAlert()
	{
		Alert alert=switchToAlert();
		alert.dismiss();
		log.info("Alert clicked cancel/deny/no button ");
	}
//--------------ScreenShots------------------
	
	public File getScreenshotOfThePage(WebDriver driver)
	{
		String date=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot screenShot=(TakesScreenshot)driver;
		File imgFile=screenShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
		
		try {
			FileUtils.copyFile(imgFile, destFile);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("ScreenShot captured at="+destFile.getAbsolutePath());
		return destFile;
	}
//----------------------Explicit wait----------------

public void waitUntilPageLoad()
{
	log.info("Waiting until page loads wiht 30 sec max");
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
}

public void moveToElementAction(WebElement ele, String objName)
{
	Actions action=new Actions(driver);
	action.moveToElement(ele).build().perform();
	log.info("Mouse Move to "+objName+" WebElement");
	
}

public void WaitUntilElementVisible(WebElement ele, String objName)
{
	log.info("Waiting for an webElement "+objName+" for its Visibility");
	wait=new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOf(ele));
}




}
