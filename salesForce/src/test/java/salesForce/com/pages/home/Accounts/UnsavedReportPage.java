package salesForce.com.pages.home.Accounts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class UnsavedReportPage extends BasePage {
	
	
	@FindBy(xpath="//input[@name='dateColumn']")
	WebElement dateField;
	
	@FindBy(xpath="//div[text()='Created Date']")
	WebElement createdField;
	
	
	@FindBy(xpath="//img[@id='ext-gen152']")
	WebElement fromdate;
	
	@FindBy(xpath="(//button[text()='Today'])[1]")
	WebElement fromtodayDate;
	
	
	@FindBy(xpath="//img[@id='ext-gen154']")
	WebElement todate;
	
	@FindBy(xpath="(//button[text()='Today'])[2]")
	WebElement toTodayDate;
	
	
	@FindBy(xpath="//button[@id='ext-gen49']")
	WebElement saveBtton;
	
	
	
	
	
	public UnsavedReportPage(WebDriver driver)
	{
		super(driver);
	}
	public String getUnsavedReportPageTitle() {
		return pageTitle();
	}
	
	public void clickOndateField()
	{
		dateField.click();
		createdField.click();
		
		
	}
	public void clickOnfromdate() 
	{
		fromdate.click();
		
		fromtodayDate.click();
		
	}
	
	public void clickOnTodate() 
	{
		todate.click();
		
		toTodayDate.click();
		
	}
	
	public void clickonSaveBtn()
	{
		saveBtton.click();
	}

}
