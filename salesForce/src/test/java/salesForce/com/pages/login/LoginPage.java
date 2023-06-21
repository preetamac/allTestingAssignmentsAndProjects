package salesForce.com.pages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import salesForce.com.pages.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	// @FindBy(xpath="//a[text()=\"Today's Deals\"]")
	// private WebElement deals;

	@FindBy(xpath = "//input[@name='username']")
	WebElement username;

	@FindBy(xpath = "//input[@name='pw']")
	WebElement pass;

	@FindBy(xpath = "//input[@name='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//input[@name='rememberUn']")
	WebElement remCheckBox;

	@FindBy(xpath = "//span[@id='idcard-identity']")
	WebElement userTextArea;

	@FindBy(xpath = "//a[text()='Forgot Your Password?']")
	WebElement forgotPassLink;
	
	@FindBy(xpath="//div[text()='Please enter your password.']")
	WebElement errMsgText;
	
	@FindBy(xpath="(//div[@class='loginError'])[2]")
	WebElement invalidLogErrMsg;
	
	//Constructor

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUserName(String usernamedata) {
		enterText(username, usernamedata, "username Field");
	}

	public void enterPassword(String passdata) {
		enterText(pass, passdata, "Password Field");
	}

	public WebDriver clickLogInButton() {
		clickElement(loginBtn, "login button");
		return driver;
	}

	public String getTitleOfPage() {
		return pageTitle();
	}

	public void clickRemCheckBox() {
		remCheckBox.click();
	}

	public String getTextOfUserTextArea() {
		return userTextArea.getText();
	}

	public WebDriver clickOnForgotPassLink() {
		forgotPassLink.click();
		return driver;
	}
	
	public String getTextOfErrMsgText()
	{
		return errMsgText.getText();
	}
	
	public String getTextOfInvalidLogErrMsg()
	{
		return invalidLogErrMsg.getText();
	}
}
