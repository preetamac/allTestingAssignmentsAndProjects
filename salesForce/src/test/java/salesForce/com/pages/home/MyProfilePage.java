package salesForce.com.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class MyProfilePage extends BasePage{
	
	@FindBy(xpath="(//img[@title='Edit Profile'])[1]")
	WebElement editProfile;
	
	@FindBy(xpath="//span[text()='Post']")
	WebElement postLink;
	
	@FindBy(xpath="//a[@title='File']")
	WebElement fileTab;
	
	public String getTitleOfMyProfilePage() {
		return pageTitle();
	}

	
	public MyProfilePage(WebDriver driver)
	{
		super(driver);
	}

	public void clickOnEditProfile()
	{
		editProfile.click();
	}
	
	public void clickOnPostLink()
	{
		postLink.click();
	}
	
	public void clickOnFileTab()
	{
		fileTab.click();
	}
}
