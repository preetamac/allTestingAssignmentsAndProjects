package salesForce.com.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class CreateNewViewPage extends BasePage {
	
	@FindBy(xpath="//input[@id='fname']")
	WebElement newViewName;
	
	@FindBy(xpath="//input[@id='devname']")
	WebElement newUniqname;
	
	@FindBy(xpath="(//input[@title='Save'])[1]")
	WebElement SaveBtn;
	
	
	
	
	public CreateNewViewPage(WebDriver driver)
	{
		super(driver);
		
	}
	public String getTitleOfCreateNewViewPage() {
		return pageTitle();
	}
	public void enterViewName()
	{
		newViewName.clear();
		newViewName.sendKeys("PAC1");
	}
	
	public void enterViewUniqName()
	{
		newUniqname.clear();
		newUniqname.sendKeys("NewUniquePAC1");
	}
	public WebDriver clickOnSaveBtn()
	{
		SaveBtn.click();
		return driver;
		
	}

}
