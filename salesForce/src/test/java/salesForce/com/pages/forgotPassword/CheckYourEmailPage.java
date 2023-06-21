package salesForce.com.pages.forgotPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import salesForce.com.pages.base.BasePage;

public class CheckYourEmailPage extends BasePage
{
	@FindBy(xpath="//a[text()='Return to Login']")
	WebElement returnToLoginBtn;
	

	
	
	public CheckYourEmailPage(WebDriver driver)
	{
		super(driver);
	}
	
	public WebDriver clickReturnToLogInBtn()
	{
		returnToLoginBtn.click();
		return driver;
	}
}
