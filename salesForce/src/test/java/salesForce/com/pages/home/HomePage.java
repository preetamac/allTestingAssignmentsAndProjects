package salesForce.com.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import salesForce.com.pages.base.BasePage;

public class HomePage extends BasePage{
	
	
	
	@FindBy(xpath="(//span[@class='menuButtonLabel'])[1]")
	WebElement userProfile;

	@FindBy(xpath="//a[@title='Logout']")
	WebElement logout;
	
	@FindBy(xpath="//div[@id='userNavMenu']")
	WebElement userMenuDropDownList;
	
	@FindBy(xpath="//a[@title='My Profile']")
	WebElement myProfile;
	
	@FindBy(xpath="//a[@class='debugLogLink menuButtonMenuLink']")
	WebElement devloperConsoleItem;
	
	@FindBy(xpath="//a[text()='My Settings']")
	WebElement mySettingsItem;
	
	@FindBy(xpath="(//a[text()='Accounts'])[1]")
	WebElement accountsTab;
	

	@FindBy(xpath="//a[@title='Opportunities Tab']")
	WebElement opportunityTab;
	
	
	
	//Constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	public void clickUserProfile()
	{
		userProfile.click();
	}
	
	public WebDriver clickLogout()
	{
		logout.click();
		return driver;
	}
	public String getTitleOfHomePage() {
		return pageTitle();
	}

	
	public void clickOnUserMenuDrpDwnList()
	{
		userMenuDropDownList.click();
	}
	
	public WebDriver clickOnMyProfileItem()
	{
		myProfile.click();
		return driver;
	}
	
	
	public WebDriver clickOnDevloperConsoleItem()
	{
		devloperConsoleItem.click();
		return driver;
	}
	
	public WebDriver clickOnMySettingsItem()
	{
		mySettingsItem.click();
		return driver;
	}
	
	public WebDriver clickOnAccountsTab()
	{
		accountsTab.click();
		return driver;
	}
	
	public WebDriver clickOnOpportunityTab()
	{
		opportunityTab.click();
		return driver;
	}
}
       