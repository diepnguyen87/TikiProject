package vn.tinypulse.page_object;

import org.openqa.selenium.WebDriver;

import vn.tiki.common.AbstractPage;
import vn.tinypulse.InvitePageLocators;

public class InvitePage extends AbstractPage {
	
	WebDriver driver;

	public InvitePage(WebDriver driver) {
		//waitToElementInvisible(driver, InvitePageLocators.LOADING_ICON);
		this.driver = driver;
	}

	public void inputFirstName(String firstName, String row) {
		waitToElementVisible(driver, InvitePageLocators.FIRST_NAME_TXT, row);
		sendkeyToElement(driver, InvitePageLocators.FIRST_NAME_TXT, firstName, row);
	}

	public void inputLastName(String lastName, String row) {
		waitToElementVisible(driver, InvitePageLocators.LAST_NAME_TXT, row);
		sendkeyToElement(driver, InvitePageLocators.LAST_NAME_TXT, lastName, row);
	}

	public void inputEmailAddress(String emailAddress, String row) {
		waitToElementVisible(driver, InvitePageLocators.EMAIL_TXT, row);
		sendkeyToElement(driver, InvitePageLocators.EMAIL_TXT, emailAddress, row);
	}

	public void inputStartDate(String startDate, String row) {
		waitToElementVisible(driver, InvitePageLocators.START_DATE_TXT, row);
		sendkeyToElement(driver, InvitePageLocators.START_DATE_TXT, startDate, row);
		
		clickToElement(driver, InvitePageLocators.CURRENT_MONTH_CALENDAR, startDate);
	}
	
}
