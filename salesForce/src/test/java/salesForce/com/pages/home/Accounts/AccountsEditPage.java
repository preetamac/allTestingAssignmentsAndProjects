package salesForce.com.pages.home.Accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import salesForce.com.pages.base.BasePage;

public class AccountsEditPage extends BasePage{
	
	
	@FindBy(xpath="//input[@id='acc2']")
	WebElement accntName;
	
	@FindBy(xpath="//select[@id='acc6']")
	WebElement selectType;
	
	@FindBy(xpath="//select[@id='00NHs00000Dk6TL']")
	WebElement custPriority;
	
	
	public AccountsEditPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getTitleOfAccountsEditPage() {
		return pageTitle();
	}

	
	public void enterAccntName()
	{
		accntName.sendKeys("New Account Name");
	}
	
	public void selectTechPartnerfromlist()
	{
		WaitUntilElementVisible(selectType, "DropDownList");
		selectType.click();
		Select techPartner=new Select(selectType);
		techPartner.selectByValue("Technology Partner");
	}
	
	public void selectCustomerPriority()
	{
		WaitUntilElementVisible(custPriority, "Cutomer priority DropDownList");
		custPriority.click();
		Select custPri=new Select(custPriority);
		custPri.selectByValue("High");
	}
}
