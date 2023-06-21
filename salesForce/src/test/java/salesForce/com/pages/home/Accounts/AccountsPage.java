package salesForce.com.pages.home.Accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class AccountsPage extends BasePage{
	
	@FindBy(xpath="//input[@name='new']")
	WebElement newBtn;
	
	
	public AccountsPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	public WebDriver clickOnNewButton()
	{
		newBtn.click();
		return driver;
	}
	
	public String getTitleOfAccountsPage() {
		return pageTitle();
	}
}
