package vn.tinypulse.page_object;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	public static LoginPage getLoginPage(WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static DashboardPage getDashboardPage(WebDriver driver) {
		return new DashboardPage(driver);
	}
	
	public static InvitePage getInvitePage(WebDriver driver) {
		return new InvitePage(driver);
	}
	
}
