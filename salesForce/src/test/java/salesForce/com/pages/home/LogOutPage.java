package salesForce.com.pages.home;

import org.openqa.selenium.WebDriver;

import salesForce.com.pages.base.BasePage;

public class LogOutPage extends BasePage{
	
	
	public LogOutPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getTitleOfLogOutPage() {
		return pageTitle();
	}


}
