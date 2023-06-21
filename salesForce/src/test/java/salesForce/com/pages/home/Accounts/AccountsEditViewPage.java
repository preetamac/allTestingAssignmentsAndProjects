package salesForce.com.pages.home.Accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import salesForce.com.pages.base.BasePage;

public class AccountsEditViewPage extends BasePage{
	
	@FindBy(xpath="//input[@id='fname']")
	WebElement newViewName;
	
	@FindBy(xpath="(//select[@class='column'])[1]")
	WebElement fieldFilter;
	
	@FindBy(xpath="(//select[@class='operator'])[1]")
	WebElement operatorFilter;
	
	@FindBy(xpath="//input[@id='fval1']")
	WebElement valueFilter;
	
	
	@FindBy(xpath="(//input[@name='save'])[1]")
	WebElement saveBtn;
	
	
	public AccountsEditViewPage(WebDriver driver)
	{
		super(driver);
	}

	
	public void enterNewViewName() throws InterruptedException
	{
		newViewName.clear();
		Thread.sleep(2000);
		newViewName.sendKeys("xyz");
	}
	public void clickOnOperatorFilter()
	{
		operatorFilter.click();
		
		Select selOperator = new Select(operatorFilter);
		selOperator.selectByVisibleText("contains");;
		
		log.info("Operator selected");
	}
	public void clickOnFieldFilter()
	{
		fieldFilter.click();
		Select selField = new Select(fieldFilter);
		
		selField.selectByVisibleText("Account Name");
		
		log.info("Field Selected");
	}
	
	public void enterValueFilter() throws InterruptedException
	{
		valueFilter.clear();
		Thread.sleep(2000);
		valueFilter.sendKeys("a");
		log.info("enter 'a' in value filter");
	}
	
	public void clickOnSaveButton()
	{
		saveBtn.click();
		log.info("Saved button clicked");
	}
}
