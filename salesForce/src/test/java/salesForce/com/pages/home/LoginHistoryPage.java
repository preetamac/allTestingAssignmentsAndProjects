package salesForce.com.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class LoginHistoryPage extends BasePage{
	
	@FindBy(xpath="//div[@class='pShowMore']")
	WebElement loginHisLink;
	
	public LoginHistoryPage(WebDriver driver)
	{
		super(driver);
	}

	public void clickOnLoginHistoryDownLink()
	{
		loginHisLink.click();
	}
	
	public String getTitleLoginHistoryPage() {
		return pageTitle();
	}
	
	
}
