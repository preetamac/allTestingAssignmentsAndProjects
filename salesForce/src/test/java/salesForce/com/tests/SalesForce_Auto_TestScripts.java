package salesForce.com.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.ReadDataFromXLSheet;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForce.com.base.BaseTest;
import salesForce.com.pages.forgotPassword.CheckYourEmailPage;
import salesForce.com.pages.forgotPassword.ForgotPasswordPage;
import salesForce.com.pages.home.AccountsEditPage;
import salesForce.com.pages.home.AccountsPage;
import salesForce.com.pages.home.CreateNewViewPage;
import salesForce.com.pages.home.DeveloperConsolePage;
import salesForce.com.pages.home.HomePage;
import salesForce.com.pages.home.LogOutPage;
import salesForce.com.pages.home.LoginHistoryPage;
import salesForce.com.pages.home.MyProfilePage;
import salesForce.com.pages.home.MySettingsPage;
import salesForce.com.pages.home.OpportunityPage;
import salesForce.com.pages.home.Accounts.AccActivGreatethan30LinkPage;
import salesForce.com.pages.home.Accounts.AccountsEditViewPage;
import salesForce.com.pages.home.Accounts.AccountsSalesForceDeveloperEditionPage;
import salesForce.com.pages.home.Accounts.MergeAccntLinkPage;
import salesForce.com.pages.home.Accounts.UnsavedReportPage;
import salesForce.com.pages.login.LoginPage;
import salesForce.com.utility.PropertiesUtility;
//import org.openqa.selenium.interactions.HasInputDevices;
//import org.openqa.selenium.interactions.Keyboard;
import Utility.Constants;

public class SalesForce_Auto_TestScripts extends BaseTest {

	// public static File file = new File(Constants.XL_DATA_PATH);
	// public static String userData =
	// ReadDataFromXLSheet.readCellDataFromXLfile(file, "Credentials", 1, 0);
	// public static String passData =
	// ReadDataFromXLSheet.readCellDataFromXLfile(file, "Credentials", 1, 1);

	// TestCase-1 Sending empty password
	@Test()

	public void TestCase1_InvalidLogin() throws InterruptedException {

		log.info("Inside the Inside Invalid Login Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.invalid.userid");
		String password = appProp.getProperty("login.blank.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		String strActual = loginpage.getTextOfErrMsgText();
		String strExpected = "Please enter your password.";

		Assert.assertEquals(strActual, strExpected, "Error Message did not  print--fail");
		log.info("Correct Error Message printed--Please enter your password");

		log.info("First test case completed");

		/*Old Code
		 * Using excel sheet launchBrowser("chrome"); WebElement username =
		 * driver.findElement(By.xpath("//input[@name='username']"));
		 * enterText(username, userData);
		 * 
		 * WebElement pass = driver.findElement(By.xpath("//input[@name='pw']"));
		 * enterText(pass, "");
		 * 
		 * WebElement btn = driver.findElement(By.xpath("//input[@name='Login']"));
		 * btn.click(); WebElement errMsg =
		 * driver.findElement(By.xpath("//div[text()='Please enter your password.']"));
		 * String strExpected = errMsg.getText(); String strActual =
		 * "Please enter your password."; if (strExpected.equals(strActual)) {
		 * System.out.println("Error msg Printed Please enter your password.--Pass"); }
		 * else { System.out.println("Msg dint print--Fail"); }
		 * 
		 * driver.close();
		 */
	}

	// Test Case 2-Login with valid username and password
	@Test()
	public void TestCase2_LoginSuccess() throws InterruptedException {

		log.info("inside the Login Success Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		HomePage homepage = new HomePage(driver);

		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		log.info("second test case completed");

	}

	// Test Case-3 -Test the remember username check box
	@Test()
	public void Test_case3_ChecBoxkRememberUsername() throws InterruptedException {
		log.info("inside the ChecBoxkRememberUsername TestCase-3 Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		loginpage.clickRemCheckBox();
		driver = loginpage.clickLogInButton();

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");

		homepage.clickUserProfile();
		log.info("User profile icon is clicked");
		driver = homepage.clickLogout();

		log.info("menu is displayed and logout is clicked");

		String actualStr = loginpage.getTextOfUserTextArea();

		String expStr = "preetamchillal@abcd.com";
		Assert.assertEquals(actualStr, expStr, "Username is not displayed in username text field");
		log.info("UserId is already displayed in username text field");
		log.info("Third test case completed");

	}

	// Test-Case4-Test Forgot password link
	@Test()
	public void Test_Case4A_TestForgotPassword() {
		log.info("inside the TestForgotPassword link TestCase-4A Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");

		LoginPage loginpage = new LoginPage(driver);
		driver = loginpage.clickOnForgotPassLink();

		ForgotPasswordPage forgotpasswordpage = new ForgotPasswordPage(driver);
		String actualTitle = forgotpasswordpage.getForgotPassPageTitle();

		String expectPageTitle = "Forgot Your Password | Salesforce";

		Assert.assertEquals(actualTitle, expectPageTitle, "Wrong page Not a Forgot Password page");
		log.info("Forgot password link is working landed on Forgot password page");

		forgotpasswordpage.enterUserName(userId);
		driver = forgotpasswordpage.clickContBtton();
		CheckYourEmailPage checkyouremail = new CheckYourEmailPage(driver);

		driver = checkyouremail.clickReturnToLogInBtn();

		LoginPage backtoLoginPage = new LoginPage(driver);

		String actTitle = backtoLoginPage.getTitleOfPage();
		String expTitle = "Login | Salesforce";
		Assert.assertEquals(actTitle, expTitle, "Wrong page/Not landed on login page");
		log.info("Landed on login page");
		log.info("Fourth test case completed");

	}

	// Test Case to check error message for entering the in wrong username=123 and
	// password=22131
	@Test()
	public void Test_case4B_ErrorMsgForWrongPass() throws InterruptedException {

		log.info("Inside Test_case4B_ErrorMsgForWrongPas Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.invalid.userid");
		String password = appProp.getProperty("login.invalid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		String strActual = loginpage.getTextOfInvalidLogErrMsg();
		String strExpected = "Please check your username and password. If you still"
				+ " can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(strActual, strExpected, "Error Message did not  print--fail");
		log.info("Correct Error Message printed--Please check your username and password");

	}

	// Click on User menu drop down which drop down list MyProfile.....Logout etc
	@Test()
	public void Test_Case5_CheckUserDropDown() throws InterruptedException {

		log.info("inside Test_Case5_CheckUserDropDown Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = loginpage.getTitleOfPage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");

		// String windowHandle = driver.getWindowHandle();
		// driver.switchTo().window(windowHandle);
		homepage.clickUserProfile();
		log.info("clicked on userProfile");

		WebElement userMenu = driver.findElement(By.xpath("//div[@id='userNavMenu']"));
		if (userMenu.isDisplayed()) {
			log.info("User menuProfile list is displayed ,Pass");
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));

		log.info(list.size() + " Items in the list");

		String expValues[] = { "My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
				"Logout" };
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(expValues[i])) {
				log.info(list.get(i).getText() + " is present");
			} else {
				log.info(list.get(i).getText() + " is not present");
			}

		}

	}

	// Select My profile option from the userMenu Drop down list
	@Test()
	public void Test_Case6_SelectMyProfile() throws InterruptedException, AWTException {

		log.info("inside Test_Case6_SelectMyProfile Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		Thread.sleep(3000);

		homepage.clickUserProfile();
		log.info("clicked on userProfile");
		Thread.sleep(3000);
		WebElement userMenu = driver.findElement(By.xpath("//div[@id='userNavMenu']"));
		if (userMenu.isDisplayed()) {
			log.info("User menuProfile list is displayed ,Pass");
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));

		log.info(list.size() + " Items in the list");

		// String expStr = "My Profile";
		// for (int i = 0; i < list.size(); i++) {
		// if (list.get(i).getText().contains(expStr)) {
		// list.get(i).click();
		// log.info("Clicked on My profile tab from usermenu");
		// break;
		// }
		// }

		driver = homepage.clickOnMyProfileItem();
		log.info("Clicked on My Profile item ");

		MyProfilePage myprofilepage = new MyProfilePage(driver);

		String actTitle = myprofilepage.getTitleOfMyProfilePage();

		String expTitle = "User: Preetam xyz ~ Salesforce - Developer Edition";
		Assert.assertEquals(actTitle, expTitle, "Wrong page/not landed on my profile page");
		log.info("Landed on My profile page");

//		myprofilepage.clickOnEditProfile();
//		log.info("Clicked On Edit profile element");
//		Thread.sleep(3000);
//
//		WebElement editProfileFrame = driver.findElement(By.xpath("//iframe[@id='contactInfoContentId']"));
//		driver.switchTo().frame(editProfileFrame);
//		Thread.sleep(3000);
//		log.info("Switched to Edit Profile frame");
//		WebElement aboutTab = driver.findElement(By.xpath("//li[@id='aboutTab']//a"));
//		aboutTab.click();
//		Thread.sleep(2000);
//		log.info("clicked on about tab");
//
//		WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
//		lastName.clear();
//		lastName.sendKeys("xyz");
//		log.info("entered last name");
//
//		WebElement saveAllBtn = driver.findElement(By.xpath("//input[@value='Save All']"));
//		saveAllBtn.click();
//		log.info("clicked on save button");
//
//		driver.switchTo().defaultContent();
//		Thread.sleep(3000);
//		// WebElement changeduserProfilename=driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
//		// String changedUserProname=changeduserProfilename.getText();
//
//		// Assert.assertEquals(changedUserProname, "Preetam xyz", "profile last name not changed");
//
//		myprofilepage.clickOnPostLink();
//		log.info("clicked on post link");
//		WebElement textAreaFrame = driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
//		driver.switchTo().frame(textAreaFrame);
//		WebElement textArea = driver.findElement(By.xpath("//body[@contenteditable='true']"));
//		textArea.sendKeys("Hi Preetam,Good Morniing! ");
//		log.info("Text Entered");
//		driver.switchTo().defaultContent();
//		Thread.sleep(3000);
//		WebElement shareBtn = driver.findElement(By.xpath("//input[@title='Share']"));
//		shareBtn.click();
//		log.info("Clicked on share button msg displayed");
//		// message Verifivation not done,manually checked time not printing correct msg
//				// displaying
		myprofilepage.clickOnFileTab();
		log.info("Clicked on file tab");
		Thread.sleep(3000);
		WebElement uploadFromComp = driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		uploadFromComp.click();
		log.info("Clicked on upload from computer button");
		Thread.sleep(2000);
		WebElement chooseFile = driver.findElement(By.xpath("(//div[@class='requiredInput'])[1]"));
		chooseFile.click();
		log.info("Clicked on choose file");

		// half done need to complete

		Robot robot = new Robot();
		for (int i = 0; i < 8; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(1000);
		}
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_TAB);
		for (int i = 0; i < 19; i++) {
			robot.keyPress(KeyEvent.VK_PAGE_UP);
			// Thread.sleep(1000);
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		WebElement shareButton = driver.findElement(By.xpath("//input[@title='Share']"));
		shareButton.click();

		Actions actions = new Actions(driver);

		WebElement myprofilephoto = driver.findElement(By.xpath("//span[@class='moderatorBadge']"));
		actions.moveToElement(myprofilephoto).click().perform();

		WebElement selectFile = driver.findElement(By.xpath("//input[@type='file']"));
		selectFile.click();

	}

	// Select MySetting option from user menu drop down

	@Test()
	public void TestCase7_SelectMySettingOption() throws InterruptedException {
		log.info("inside TestCase7_SelectMySettingOption Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		Thread.sleep(2000);

		homepage.clickUserProfile();
		log.info("clicked on userProfile");
		WebElement userMenu = driver.findElement(By.xpath("//div[@id='userNavMenu']"));
		if (userMenu.isDisplayed()) {
			log.info("User menuProfile list is displayed ,Pass");
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));

		log.info(list.size() + " Items in the list");

		driver = homepage.clickOnMySettingsItem();
		log.info("Clicked on My Settings item ");

		MySettingsPage mysettingspage = new MySettingsPage(driver);
		String actMySettingPageTitle = mysettingspage.getTitleOfMySettingsPage();
		String expMySettingdPageTilte = "Hello, Preetam xyz! ~ Salesforce - Developer Edition";
		Assert.assertEquals(actMySettingPageTitle, expMySettingdPageTilte, "Wrong Page not landed on Mysettings Page");
		log.info("Landed On Mysettings Page");

		mysettingspage.clickOnPersonalTab();
		log.info("Clicked on personal tab");
		driver = mysettingspage.clickOnLoginHistory();
		log.info("Clicked on loginHistory option");

		LoginHistoryPage loginhistorypage = new LoginHistoryPage(driver);

		String expectedPage = "Login History ~ Salesforce - Developer Edition";
		String actualPage = loginhistorypage.getTitleLoginHistoryPage();
		Assert.assertEquals(actualPage, expectedPage, "Landed on wrong page/Not a Login History page");
		// loginhistorypage.clickOnLoginHistoryDownLink();
		log.info("Clicked on loginHistory download link");

		mysettingspage.clickOnDisplayLauoutTab();

		log.info("Clicked on Display & Layout option");
		Thread.sleep(2000);
		driver = mysettingspage.clickOnCustomizeMyTabs();
		log.info("Clicked on Customise My Tabs");
		Thread.sleep(2000);

		String expPage = "Customize My Tabs ~ Salesforce - Developer Edition";
		String actPage = driver.getTitle();
		Assert.assertEquals(actPage, expPage, "Landed on wrong page");
		// or
		WebElement heading = driver.findElement(By.xpath("//h1[text()='Customize My Tabs']"));
		String actText = heading.getText();
		String expText = "Customize My Tabs";

		Assert.assertEquals(actText, expText, "Heading doesnt match");

		Thread.sleep(2000);
		WebElement customAppDropDwn = driver.findElement(By.xpath("//select[@id='p4']"));
		customAppDropDwn.click();
		log.info("Clicked on Customapp dropdown");

		// WebElement
		// cancelBtn=driver.findElement(By.xpath("//input[@title='Cancel']"));
		// ((JavascriptExecutor)driver).executeScript("argument[1].scrollIntoView(true);",cancelBtn);

		Thread.sleep(2000);
		Select item = new Select(customAppDropDwn);
		item.selectByVisibleText("Salesforce Chatter");
		log.info("selected Salesforce chatter");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		WebElement availTab = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		Thread.sleep(2000);

		Select availTtems = new Select(availTab);
		availTtems.selectByVisibleText("Reports");
		log.info("selected Reports from available list");
		WebElement clickAddBtn = driver.findElement(By.xpath("//img[@alt='Add']"));
		clickAddBtn.click();
		log.info("Clicked on Add btn");
		Thread.sleep(2000);
		WebElement saveBtn = driver.findElement(By.xpath("//input[@value=' Save ']"));
		saveBtn.click();
		log.info("Clicked on save btn");
		// verification code should be added to check reports is added
		WebElement email = driver.findElement(By.xpath("//span[@id='EmailSetup_font']"));
		email.click();
		log.info("Email entered");

		Thread.sleep(2000);
		WebElement myEmailSettng = driver.findElement(By.xpath("//a[@id='EmailSettings_font']"));
		myEmailSettng.click();
		log.info("Clicked my email setting");
		Thread.sleep(2000);
		WebElement sender_name = driver.findElement(By.xpath("//input[@id='sender_name']"));
		sender_name.clear();
		Thread.sleep(2000);
		sender_name.sendKeys("Preetam Abcd");
		log.info("Entered emailName");
		WebElement sende_Email = driver.findElement(By.xpath("//input[@id='sender_email']"));
		sende_Email.clear();
		Thread.sleep(2000);
		sende_Email.sendKeys("preetam.chillal@gmail.com");
		log.info("entered email in text box");
		WebElement radioYes = driver.findElement(By.xpath("(//input[@type='radio'])[1]"));
		radioYes.click();
		log.info("Clicked on radio button");
		Thread.sleep(2000);
		WebElement saveButton = driver.findElement(By.xpath("//input[@title='Save']"));
		saveButton.click();
		log.info("Clicked on save btn");
		Thread.sleep(2000);
		WebElement confirmMesg = driver.findElement(By.xpath("//div[@class='message confirmM4']"));
		String actualtext = confirmMesg.getText();
		String ExpText = "Your settings have been successfully saved.";
		Assert.assertEquals(actualtext, ExpText, "Fail Confirm Message did not match");

		WebElement calendrAndRemindr = driver.findElement(By.xpath("//span[@id='CalendarAndReminders_font']"));
		calendrAndRemindr.click();
		log.info("Clicked on Calender and reminder");

		WebElement activtReminder = driver.findElement(By.xpath("//a[@id='Reminders_font']"));
		activtReminder.click();
		log.info("Clicked on active reminder");
		String actualPageTitle = driver.getTitle();
		String expPageTitle = "Activity Reminders ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualPageTitle, expPageTitle, "Page Title doesnt match");
		WebElement openTestReminBtn = driver.findElement(By.xpath("//input[@id='testbtn']"));
		openTestReminBtn.click();
		log.info("Clicked on Add btn");
		WebElement openTestReminderBtn = driver.findElement(By.xpath("//input[@title='Open a Test Reminder']"));
		openTestReminderBtn.click();
		log.info("Clicked on Open a test reminder btn");

		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> it = allWindowHandles.iterator();
		while (it.hasNext()) {
			String ChildWindow = it.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				log.info("Switched to child window");
				String expReminderPage = "2 Reminders";
				String actReminPage = driver.getTitle();
				Assert.assertEquals(actReminPage, expReminderPage, "This is not Reminder Page/Wrong page");
				driver.close();
			}
			driver.switchTo().window(mainWindowHandle);
			log.info("Switched to main window");

		}
		WebElement saveButtn = driver.findElement(By.xpath("//input[@name='save']"));
		saveButtn.click();

	}

	// Select developer console from user menu
	@Test

	public void TestCase8_SelectDeveloperConsole() throws InterruptedException {

		log.info("inside Test_Case6_SelectMyProfile Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		Thread.sleep(2000);

		homepage.clickUserProfile();
		log.info("clicked on userProfile");

		WebElement userMenu = driver.findElement(By.xpath("//div[@id='userNavMenu']"));
		if (userMenu.isDisplayed()) {
			log.info("User menuProfile list is displayed ,Pass");
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));

		log.info(list.size() + " Items in the list");

		Thread.sleep(2000);
		driver = homepage.clickOnDevloperConsoleItem();
		log.info("Clicked on Developer Console page");
		Thread.sleep(2000);

		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> it = allWindowHandles.iterator();
		while (it.hasNext()) {
			String ChildWindow = it.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				log.info("Switched to child window that is Devloper console page");
				Assert.assertEquals(driver.getTitle(), "Developer Console", "Wrong Page Not a Developer Console page");
				Thread.sleep(2000);
				driver.close();
			}
		}

		driver.switchTo().window(mainWindowHandle);
		log.info("Switched to main window that is Home page");

	}

	// Select logout option from user menu
	@Test
	public void TestCase9_SelectLogOut() throws InterruptedException {

		log.info("inside Test_Case6_SelectMyProfile Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		Thread.sleep(2000);

		homepage.clickUserProfile();
		log.info("clicked on userProfile");

		WebElement userMenu = driver.findElement(By.xpath("//div[@id='userNavMenu']"));
		if (userMenu.isDisplayed()) {
			log.info("User menuProfile list is displayed ,Pass");
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));

		log.info(list.size() + " Items in the list");

		Thread.sleep(2000);
		driver = homepage.clickLogout();
		log.info("Clicked on Logout item");
		Thread.sleep(2000);

		LogOutPage logoutpage = new LogOutPage(driver);
		String actLogoutPage = logoutpage.getTitleOfLogOutPage();
		Assert.assertEquals(actLogoutPage, "Login | Salesforce", "Not the login page/wrong page");
	}

	// Create An Account
	@Test
	public void TestCase10_CreateAccount() throws InterruptedException {
		log.info("inside TestCase10_CreateAccount Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();
		log.info("Clicked Login Button");

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");

		driver = homepage.clickOnAccountsTab();
		log.info("clicked on Accounts Tab");

		AccountsPage accountspage = new AccountsPage(driver);
		String expAccntpageTitle = "Accounts: Home ~ Salesforce - Developer Edition";

		String actualAcntPageTitle = accountspage.getTitleOfAccountsPage();
		Assert.assertEquals(actualAcntPageTitle, expAccntpageTitle, "Landed On wrong page/Not Accounts page");
		log.info("Landed on Accounts page");
		Thread.sleep(3000);

		// pop up
		driver.switchTo().activeElement();
		WebElement popUp = driver.findElement(By.xpath("//a[@class='dialogClose']"));
		popUp.click();

		driver = accountspage.clickOnNewButton();
		log.info("Clicked on new Button");
		Thread.sleep(3000);
		AccountsEditPage accountseditpage = new AccountsEditPage(driver);
		Assert.assertEquals(accountseditpage.getTitleOfAccountsEditPage(),
				"Account Edit: New Account ~ Salesforce - Developer Edition",
				"Wrong page/Not Landed on Accounts edit page");

		Thread.sleep(3000);

		accountseditpage.enterAccntName();
		log.info("Account name Entered");
		accountseditpage.selectTechPartnerfromlist();
		log.info("Selected tech partner from list");
		accountseditpage.selectCustomerPriority();
		log.info("selected customer priority");

		

		accountseditpage.clickOnSaveButton();
		log.info("Clicked on save button");
		log.info("New Account created");

		Assert.assertEquals(driver.getTitle(), "Account: NewAutoAccnt2 ~ Salesforce - Developer Edition",
				"New account page is not displayed");

	}

	@Test
	public void TestCase11_CreateNewView() throws InterruptedException {
		log.info("inside TestCase10_CreateAccount Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();
		log.info("Clicked Login Button");

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");

		driver = homepage.clickOnAccountsTab();
		log.info("clicked on Accounts Tab");

		AccountsPage accountspage = new AccountsPage(driver);
		String expAccntpageTitle = "Accounts: Home ~ Salesforce - Developer Edition";

		String actualAcntPageTitle = accountspage.getTitleOfAccountsPage();
		Assert.assertEquals(actualAcntPageTitle, expAccntpageTitle, "Landed On wrong page/Not Accounts page");
		log.info("Landed on Accounts page");
		Thread.sleep(3000);

		// pop up
		driver.switchTo().activeElement();
		WebElement popUp = driver.findElement(By.xpath("//a[@class='dialogClose']"));
		popUp.click();

		driver = accountspage.clickOnCreateNewView();
		CreateNewViewPage createnewviewpage = new CreateNewViewPage(driver);
		Assert.assertEquals(createnewviewpage.getTitleOfCreateNewViewPage(), 
				"Accounts: Create New View ~ Salesforce - Developer Edition","Wrong Page not a create new view page");
		// Enter View nname
		
		createnewviewpage.enterViewName();
		Thread.sleep(3000);
		// Enter Unique name
		createnewviewpage.enterViewUniqName();
		
		// Click on save button
		driver=createnewviewpage.clickOnSaveBtn();
		log.info("Clicked on save Button and NewlyAdded Account saved");
		
		
		AccountsSalesForceDeveloperEditionPage accountssalesforcedeveloperedition=new AccountsSalesForceDeveloperEditionPage(driver);
		
		WebElement selectList = driver.findElement(By.xpath("//select[@class='title']"));
		Select sel = new Select(selectList);
		String actResult = sel.getFirstSelectedOption().getText();
		String expResult = "PAC1";
		Assert.assertEquals(actResult, expResult, "Wrong View name /View name not matching");
		System.out.println("Newly Added View name is displaying in the list first");
	}

//test case12 edit view
	@Test
	public void TestCase12_EditView() throws InterruptedException {
		log.info("inside TestCase12_EditView Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();
		log.info("Clicked Login Button");

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");

		driver = homepage.clickOnAccountsTab();
		log.info("clicked on Accounts Tab");

		AccountsPage accountspage = new AccountsPage(driver);
		String expAccntpageTitle = "Accounts: Home ~ Salesforce - Developer Edition";

		String actualAcntPageTitle = accountspage.getTitleOfAccountsPage();
		Assert.assertEquals(actualAcntPageTitle, expAccntpageTitle, "Landed On wrong page/Not Accounts page");
		log.info("Landed on Accounts page");
		Thread.sleep(3000);
		// to close pop up
				driver.switchTo().activeElement();
				WebElement popUp = driver.findElement(By.xpath("//a[@class='dialogClose']"));
				popUp.click();


		// to verify the correct username (viewname) is displaying in the list /dropdown
		WebElement selectList = driver.findElement(By.xpath("//select[@name='fcf']"));
		//selectList.click();
		Select sel = new Select(selectList);
		String actResult = sel.getFirstSelectedOption().getText();
		String expResult = "Abcd";
		Assert.assertEquals(actResult, expResult, "Wrong View name /View name not matching");
		System.out.println("Newly Added View name is displaying in the list first");

		driver=accountspage.clickOn_editLink();
		AccountsEditViewPage accountseditviewpage=new AccountsEditViewPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Accounts: Edit View ~ Salesforce - Developer Edition",
				"not an Edit View Page/Wrong page");
		
		accountseditviewpage.enterNewViewName();
		Thread.sleep(2000);
		accountseditviewpage.clickOnFieldFilter();
		Thread.sleep(2000);
		accountseditviewpage.clickOnOperatorFilter();
		Thread.sleep(2000);
		accountseditviewpage.enterValueFilter();
		Thread.sleep(2000);
		accountseditviewpage.clickOnSaveButton();
		
		
		
		

	}

	@Test
	public void TestCase13_MergeAccounts() throws InterruptedException {
		log.info("inside TestCase12_EditView Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();
		log.info("Clicked Login Button");

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		

		driver = homepage.clickOnAccountsTab();
		log.info("clicked on Accounts Tab");

		AccountsPage accountspage = new AccountsPage(driver);
		String expAccntpageTitle = "Accounts: Home ~ Salesforce - Developer Edition";

		String actualAcntPageTitle = accountspage.getTitleOfAccountsPage();
		Assert.assertEquals(actualAcntPageTitle, expAccntpageTitle, "Landed On wrong page/Not Accounts page");
		log.info("Landed on Accounts page");
		
		Thread.sleep(3000);
		// to close pop up
				driver.switchTo().activeElement();
				WebElement popUp = driver.findElement(By.xpath("//a[@class='dialogClose']"));
				popUp.click();
		
		driver=accountspage.clickOnMergeAccntLink();
		
		MergeAccntLinkPage mergeaccntlinkpage=new MergeAccntLinkPage(driver);
		Assert.assertEquals(mergeaccntlinkpage.getMergeAccntLinkPageTitle(), 
			"Merge My Accounts ~ Salesforce - Developer Edition","Wrong page/Not on Merge my Account page");
		log.info("Landed on Merge My Accounts page");
		mergeaccntlinkpage.enterAcctNameinText();
		log.info("entered act name in text");
		mergeaccntlinkpage.clickOnfindAccountsBtn();
		log.info("Clicked on find acct button");
		
		Thread.sleep(3000);
		
		//Should select two accounts to merge
		List<WebElement> chkBoxList=driver.findElements(By.xpath("//table[@class='list']//th[@scope='row']"));
		chkBoxList.get(0).click();
		chkBoxList.get(1).click();
		log.info("Check box selected(2)");
		
		
		
		
		//two accounts selected(cheked) already
		mergeaccntlinkpage.clickOnnextButton();
		log.info("Clicked next button");
		WebElement headerText=driver.findElement(By.xpath("//div[@class='pbWizardTitle tertiaryPalette brandTertiaryBgr']//h2"));
		
		Assert.assertEquals(headerText.getText(),"Step 2. Select the values to retain","Text dint match");
		mergeaccntlinkpage.clickOnmergeButton();
		Alert alt=driver.switchTo().alert();
		alt.accept();
		
		Assert.assertEquals(driver.getTitle(), "Accounts: Home ~ Salesforce - Developer Edition","Not landed on Account home page");
		log.info("landed on account home page");
		
		//to verify merged account name is displayed in recent view table
	
//		WebElement trData=driver.findElement(By.xpath("//tr[@class='dataRow even first']"));
//		String rowdata=trData.getText();
//		Assert.assertEquals(rowdata,"New","account not matching");
//		log.info("Recentview Table Accnt matching with merged account");
//		
		
		
		
		
		
		

		// need to complete
	}

	@Test
	public void TestCase14_CreateAccReport() throws InterruptedException {
		log.info("inside TestCase12_EditView Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();
		log.info("Clicked Login Button");

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		

		driver = homepage.clickOnAccountsTab();
		log.info("clicked on Accounts Tab");

		AccountsPage accountspage = new AccountsPage(driver);
		String expAccntpageTitle = "Accounts: Home ~ Salesforce - Developer Edition";

		String actualAcntPageTitle = accountspage.getTitleOfAccountsPage();
		Assert.assertEquals(actualAcntPageTitle, expAccntpageTitle, "Landed On wrong page/Not Accounts page");
		log.info("Landed on Accounts page");
		Thread.sleep(3000);
		// to close pop up
				driver.switchTo().activeElement();
				WebElement exLightpopUp = driver.findElement(By.xpath("//a[@class='dialogClose']"));
				exLightpopUp.click();
		
		
		
		driver=accountspage.clickOnAccActivGreatethan30Link();
		AccActivGreatethan30LinkPage acactgreaterthan30linkpage=new AccActivGreatethan30LinkPage(driver);
		
		Assert.assertEquals(driver.getTitle(), "Unsaved Report ~ Salesforce - Developer Edition",
				"Wrong page/Not a Unsaved Report page");

		
		
		UnsavedReportPage unsavedpage=new UnsavedReportPage(driver);
		unsavedpage.clickOndateField();
		unsavedpage.clickOnfromdate();
		unsavedpage.clickOnTodate();
		unsavedpage.clickonSaveBtn();
		
		
		WebElement savereportwindow=driver.switchTo().activeElement();
		
			WebElement reportName=driver.findElement(By.xpath("//input[@name='reportName']"));
			
			reportName.sendKeys("report3");
			WebElement reportUniqName=driver.findElement(By.xpath("//input[@name='reportDevName']"));
			reportUniqName.click();
			Thread.sleep(3000);
			
			WebElement saveAndRunBtn=driver.findElement(By.xpath("//table[@id='dlgSaveAndRun']"));
			saveAndRunBtn.click();
			
			Assert.assertEquals(driver.getTitle(), "report3 ~ Salesforce - Developer Edition","Wrong page/not a report page");


		
		
		
		
	}

	@Test
	public void TestCase15_OpportunitiesDrpDwn() throws InterruptedException {
		log.info("inside TestCase12_EditView Method");
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(userId);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogInButton();
		log.info("Clicked Login Button");

		HomePage homepage = new HomePage(driver);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";

		String actualTitle = homepage.getTitleOfHomePage();
		Assert.assertEquals(actualTitle, expectedTitle, "Landed On wrong page/Not a sales force home page");
		log.info("Landed on home page");
		driver=homepage.clickOnOpportunityTab();
		OpportunityPage opportunitypage=new OpportunityPage(driver);
		Assert.assertEquals(driver.getTitle(), "Opportunities: Home ~ Salesforce - Developer Edition","wrong page/ not an opportunity page");
		log.info("Landed on opportunity  page");

		
		Thread.sleep(3000);
		// to close pop up
				driver.switchTo().activeElement();
				WebElement exLightpopUp = driver.findElement(By.xpath("//a[@class='dialogClose']"));
				exLightpopUp.click();
		

		WebElement opprDrpDwn = driver.findElement(By.xpath("//select[@name='fcf']"));
		boolean result = opprDrpDwn.isDisplayed();
		Assert.assertTrue(result, "DropDown is not present ");
		log.info("Drop down is present");


		List<WebElement> list = driver.findElements(By.xpath("//select[@name='fcf']//option"));
		// To Display the drop down List in console
		// for(int i=0;i<list.size();i++)
		// {
		// System.out.println(list.get(i).getText());
		// }

		String expValues[] = { "All Opportunities", "Closing Next Month", "Closing This Month", "My Opportunities",
				"New Last Week", "New This Week", "Opportunity Pipeline", "Private", "Recently Viewed Opportunities",
				"Won" };
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(expValues[i])) {
				System.out.println(list.get(i).getText() + " is present");
			} else {
				System.out.println(list.get(i).getText() + " is not present");
			}

		}
		
	}

	@Test
	public void TestCase16_CreateNewOportunity() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log.info("Browser is launched");
		driver.get("https://login.salesforce.com/");
		WebElement username=driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("preetamchillal@abcd.com");
		WebElement pass=driver.findElement(By.xpath("//input[@name='pw']"));
		pass.sendKeys("Testing@1234");
		WebElement loginBtn=driver.findElement(By.xpath("//input[@name='Login']"));
		//loginBtn.click();

	}

	@Test
	public void TestCase17_TestOpportunityPipelineReport() throws InterruptedException {

	}

	@Test
	public void TestCase18_TestStuckOpportunitiesReport() throws InterruptedException {

	}

	@Test
	public void TestCase19_TestQuarterlySummaryReport() throws InterruptedException {

	}

	@Test
	public void TestCase20_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase21_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase22_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase23_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase24_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase25_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase26_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase27_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase28_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase29_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase30_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase31_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase32_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase33_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase34_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase35_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase36_CreateNewOportunity() throws InterruptedException {

	}

	@Test
	public void TestCase37_CreateNewOportunity() throws InterruptedException {

	}

}