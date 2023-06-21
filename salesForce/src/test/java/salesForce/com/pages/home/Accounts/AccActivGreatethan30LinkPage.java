package salesForce.com.pages.home.Accounts;

import org.openqa.selenium.WebDriver;

import salesForce.com.pages.base.BasePage;

public class AccActivGreatethan30LinkPage extends BasePage{
	
	public AccActivGreatethan30LinkPage(WebDriver driver)
	{
		super(driver);
	}

	
	public String getAccActivGreatethan30LinkPageTitle() {
		return pageTitle();
	}
	
}
