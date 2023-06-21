package salesForce.com.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class MySettingsPage extends BasePage{
	
	@FindBy(xpath="(//a[@class='header setupFolder'])[1]")
	WebElement personalTab;
	
	@FindBy(xpath="//span[@id='LoginHistory_font']")
	WebElement loginHistory;
	
	@FindBy(xpath="(//a[@class='header setupFolder'])[2]")
	WebElement displayAndLayoutTab;
	
	@FindBy(xpath="//a[@id='CustomizeTabs_font']")
	WebElement customiseMyTab;
	
	
	public MySettingsPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getTitleOfMySettingsPage() {
		return pageTitle();
	}
	
	public void clickOnPersonalTab()
	{
		personalTab.click();
	}

	public WebDriver clickOnLoginHistory()
	{
		loginHistory.click();
		return driver;
	}

	public void clickOnDisplayLauoutTab()
	{
		displayAndLayoutTab.click();
	}
	
	public WebDriver clickOnCustomizeMyTabs()
	{
		customiseMyTab.click();
		return driver;
	}
}
