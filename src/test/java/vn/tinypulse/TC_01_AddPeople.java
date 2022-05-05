package vn.tinypulse;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import vn.tiki.common.AbstractTest;
import vn.tinypulse.page_object.DashboardPage;
import vn.tinypulse.page_object.InvitePage;
import vn.tinypulse.page_object.LoginPage;
import vn.tinypulse.page_object.PageGeneratorManager;

public class TC_01_AddPeople extends AbstractTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private InvitePage invitePage;
	
	private String emailAddress = "diep.test200@gmail.com";
	private String password = "!)aArdvark01";
	

	@Parameters({ "browserName", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		startEnvironment(environmentName);
		log.info("Open webpage: " + conf.getURL());
		driver.get(conf.getURL());
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_login() {
		loginPage.addNumbers();
		
		loginPage.inputEmailAddress(emailAddress);
		loginPage.clickToContinueBtn();
		
		loginPage.inputPassword(password);
		loginPage.clickToSignInBtn();
		dashboardPage = PageGeneratorManager.getDashboardPage(driver);
	}
	
	@Test
	public void TC_02_Add_People(){
		dashboardPage.clickToSettingMenu();
		dashboardPage.clickToAddPeopleMenuItem();
		invitePage = PageGeneratorManager.getInvitePage(driver);
		//sleepInMiliSecond(5000);
		//add row 1
		for(int row=1; row <= 2; ++row) {
			invitePage.inputFirstName("Diep" + row, String.valueOf(row));
			invitePage.inputLastName("Nguyen", String.valueOf(row));
			invitePage.inputEmailAddress("diep.nguyen@yahoo.com" + row, String.valueOf(row));
			invitePage.inputStartDate("2022-05-25", String.valueOf(row));
		}
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		//driver.quit();
	}

}
