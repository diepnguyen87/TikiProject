package vn.tinypulse.page_object;

import org.openqa.selenium.WebDriver;

import vn.tiki.common.AbstractPage;
import vn.tinypulse.DashboardPageLocators;

public class DashboardPage extends AbstractPage {

	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSettingMenu() {
		waitToElementClickable(driver, DashboardPageLocators.SETTING_MENU);
		clickToElement(driver, DashboardPageLocators.SETTING_MENU);
	}

	public void clickToAddPeopleMenuItem() {
		waitToElementClickable(driver, DashboardPageLocators.ADD_PEOPLE_MENU_ITEM);
		clickToElement(driver, DashboardPageLocators.ADD_PEOPLE_MENU_ITEM);
	}

	
}
