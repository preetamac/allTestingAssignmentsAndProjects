package salesForce.com.pages.home;

import org.openqa.selenium.WebDriver;

import salesForce.com.pages.base.BasePage;

public class DeveloperConsolePage extends BasePage{
	
	
	public DeveloperConsolePage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getTitleOfDeveloperConsolePage() {
		return pageTitle();
	}

}
