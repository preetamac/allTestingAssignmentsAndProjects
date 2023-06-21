package salesForce.com.pages.forgotPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class ForgotPasswordPage extends BasePage{
	
	
	
	@FindBy(xpath="//input[@name='un']")
	WebElement userName;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement clickContBtn;
	
	
	public void enterUserName(String usernamedata) {
		enterText(userName, usernamedata, "username Field");
	}
	
	public WebDriver clickContBtton()
	{
		clickContBtn.click();
		return driver;
	}
	
	public String getForgotPassPageTitle()
	{
		return pageTitle();
	}
	
	
	
	public ForgotPasswordPage(WebDriver driver)
	{
		super(driver);
	}
	

}
