package salesForce.com.pages.home;

import org.openqa.selenium.WebDriver;

import salesForce.com.pages.base.BasePage;

public class CutomizeMyTabsPage extends BasePage{
	
	
	public CutomizeMyTabsPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getCustMyTabPageTitle()
	{
		return pageTitle();
	}

}
