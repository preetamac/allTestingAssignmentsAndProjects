package salesForce.com.pages.home.Accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class MergeAccntLinkPage extends BasePage {
	
	
	@FindBy(xpath="(//input[@title='Find Accounts'])[1]")
	WebElement findAccountsText;
	
	

	@FindBy(xpath="//input[@name='srchbutton']")
	WebElement findAccountsBtn;
	
	@FindBy(xpath="(//input[@name='goNext'])[1]")
	WebElement nextButton;
	
	@FindBy(xpath="(//input[@title='Merge'])[2]")
	WebElement mergeButton;
	
	public MergeAccntLinkPage(WebDriver driver)
	{
		super(driver);
	}
	public String getMergeAccntLinkPageTitle()
	{
		return pageTitle();
	}
	
	public void enterAcctNameinText()
	{
		findAccountsText.sendKeys("New");
	}
	
	public void clickOnfindAccountsBtn()
	{
		findAccountsBtn.click();
		
	}
	public WebDriver clickOnnextButton()
	{
		nextButton.click();
		return driver;
	}
	
	public void clickOnmergeButton()
	{
		mergeButton.click();
		
	}
}
