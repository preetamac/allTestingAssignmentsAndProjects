package salesForce.com.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class AccountsPage extends BasePage{
	
	@FindBy(xpath="//input[@title='New']")
	WebElement newBtn;
	
	@FindBy(xpath="//a[text()='Create New View']")
	WebElement createNewview;
	
	@FindBy(xpath="//a[text()='Edit']")
	WebElement editLink;
	
	@FindBy(xpath="//a[text()='Merge Accounts']")
	WebElement mergeAccountLink;
	
	
	@FindBy(xpath="//a[text()='Accounts with last activity > 30 days']")
	WebElement acctActivityGreaterThan30Link;
	
	
	
	public AccountsPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	public WebDriver clickOnNewButton()
	{
		WaitUntilElementVisible(newBtn,"new Button");
		newBtn.click();
		return driver;
	}
	
	public String getTitleOfAccountsPage() {
		return pageTitle();
	}
	
	public WebDriver clickOnCreateNewView()
	{
		createNewview.click();
		return driver;
	}
	
	public WebDriver clickOn_editLink()
	{
		editLink.click();
		return driver;
	}
	
	public WebDriver clickOnMergeAccntLink()
	{
		mergeAccountLink.click();
		return driver;
		
	}
	
	public WebDriver clickOnAccActivGreatethan30Link()
	{
		acctActivityGreaterThan30Link.click();
		return driver;
		
	}
}
