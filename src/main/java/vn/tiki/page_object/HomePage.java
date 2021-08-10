package vn.tiki.page_object;

import org.openqa.selenium.WebDriver;

import vn.tiki.common.AbstractPage;
import vn.tiki.locators.HomePageLocators;

public class HomePage extends AbstractPage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void inputSearchField(String searchData) {
		waitToElementVisible(driver, HomePageLocators.SEARCH_INPUT);
		sendkeyToElement(driver, HomePageLocators.SEARCH_INPUT, searchData);
	}

	public void clickToSearchButton() {
		waitToElementClickable(driver, HomePageLocators.SEARCH_BUTTON);
		clickToElement(driver, HomePageLocators.SEARCH_BUTTON);
	}

	public void clickToTikiNowChkbox() {
		waitToElementVisible(driver, HomePageLocators.TIKI_NOW_CHKBOX);
		clickToElement(driver, HomePageLocators.TIKI_NOW_CHKBOX);
		waitToElementVisible(driver, HomePageLocators.SEARCH_RESULT_TITLE);
	}

	public String getProductName(String nthItem) {
		waitToElementVisible(driver, HomePageLocators.SELECTED_ITEM_NAME, nthItem);
		return getElementText(driver, HomePageLocators.SELECTED_ITEM_NAME, nthItem);
	}

	public String getProductPrice(String nthItemPrice) {
		waitToElementVisible(driver, HomePageLocators.SELECTED_ITEM_PRICE, nthItemPrice);
		return getElementText(driver, HomePageLocators.SELECTED_ITEM_PRICE, nthItemPrice);
	}

	public void clickToSelectedProduct(String productIndex) {
		waitToElementClickable(driver, HomePageLocators.SElECTED_PRODUCT, productIndex);
		clickToElementByJS(driver, HomePageLocators.SElECTED_PRODUCT, productIndex);
	}
	
}
