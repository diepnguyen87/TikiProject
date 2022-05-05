package vn.tinypulse.page_object;

import org.openqa.selenium.WebDriver;

import vn.tiki.common.AbstractPage;
import vn.tinypulse.LoginPageLocators;

public class LoginPage extends AbstractPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void inputEmailAddress(String emailAddress) {
		waitToElementVisible(driver, LoginPageLocators.EMAIL_TXT);
		sendkeyToElement(driver, LoginPageLocators.EMAIL_TXT, emailAddress);
	}

	public void clickToContinueBtn() {
		waitToElementClickable(driver, LoginPageLocators.CONTINUE_BTN);
		clickToElement(driver, LoginPageLocators.CONTINUE_BTN);
	}

	public void inputPassword(String password) {
		waitToElementVisible(driver, LoginPageLocators.PASSWORD_TXT);
		sendkeyToElement(driver, LoginPageLocators.PASSWORD_TXT, password);
	}

	public void clickToSignInBtn() {
		waitToElementClickable(driver, LoginPageLocators.SIGN_IN_BTN);
		clickToElement(driver, LoginPageLocators.SIGN_IN_BTN);
	}

	public void addNumbers() {
		// TODO Auto-generated method stub
		
	}
		

}
