package vn.tiki.page_object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import vn.tiki.common.AbstractPage;
import vn.tiki.locators.ProductDetailPageLocators;

public class ProductDetailPage extends AbstractPage {

	WebDriver driver;

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitle() {
		waitToElementVisible(driver, ProductDetailPageLocators.SELECTED_PRODUCT_TITLE);
		return getElementText(driver, ProductDetailPageLocators.SELECTED_PRODUCT_TITLE);
	}

	public String getPrice() {
		if (isElementUndisplayed(driver, ProductDetailPageLocators.SELECTED_PRODUCT_PRICE, 5)) {
			return getElementText(driver, ProductDetailPageLocators.SELECTED_PRODUCT_FLASH_SALE_PRICE);
		}
		return getElementText(driver, ProductDetailPageLocators.SELECTED_PRODUCT_PRICE);
	}

	public void addToCart(int quantity) {
		waitToElementVisible(driver, ProductDetailPageLocators.QUANTITY_INPUT);
		sendkeyToElement(driver, ProductDetailPageLocators.QUANTITY_INPUT, String.valueOf(quantity));
		sleepInSecond(2);
	}

	public void clickToAddToCartBtn() {
		waitToElementClickable(driver, ProductDetailPageLocators.ADD_TO_CART_BUTTON);
		clickToElement(driver, ProductDetailPageLocators.ADD_TO_CART_BUTTON);
	}

	public void inputTelephomeNumber(String telephoneNumber) {
		waitToElementVisible(driver, ProductDetailPageLocators.TELEPHONE_INPUT);
		sendkeyToElement(driver, ProductDetailPageLocators.TELEPHONE_INPUT, telephoneNumber);
	}

	public void clickToContinueBtn() {
		waitToElementClickable(driver, ProductDetailPageLocators.CONTINUE_BUTTON);
		clickToElement(driver, ProductDetailPageLocators.CONTINUE_BUTTON);
	}

	public void inputPassword(String password) {
		waitToElementVisible(driver, ProductDetailPageLocators.PASSWORD_INPUT);
		sendkeyToElement(driver, ProductDetailPageLocators.PASSWORD_INPUT, password);
	}

	public void clickToLoginBtn() {
		waitToElementClickable(driver, ProductDetailPageLocators.LOGIN_BUTTON);
		clickToElement(driver, ProductDetailPageLocators.LOGIN_BUTTON);
	}

	public boolean isMaxiumQuantityAllowedOrderErrorMsgUndisplayed() {
		//waitToElementInvisible(driver, ProductDetailPageLocators.MAXIUM_QUANTITY_ALLOWED_ORDER_ERROR_MESSAGE);
		return isElementUndisplayed(driver, ProductDetailPageLocators.MAXIUM_QUANTITY_ALLOWED_ORDER_ERROR_MESSAGE, 5);
	}

	public int getMaxiumQuantityAllowedOrdered() {
		waitToElementVisible(driver, ProductDetailPageLocators.MAXIUM_QUANTITY_ALLOWED_ORDER_ERROR_MESSAGE);
		String[] maximumQuanityAllowedOrderErrorMsg = getElementText(driver, ProductDetailPageLocators.MAXIUM_QUANTITY_ALLOWED_ORDER_ERROR_MESSAGE).split("\s");
		return Integer.parseInt(maximumQuanityAllowedOrderErrorMsg[maximumQuanityAllowedOrderErrorMsg.length - 1]);
	}

	public boolean isOverleftQuantityInstockErrorMsgUndisplayed() {
		waitToElementInvisible(driver, ProductDetailPageLocators.OVER_LEFT_QUANTITY_INSTOCK_ERROR_MESSAGE);
		return isElementUndisplayed(driver, ProductDetailPageLocators.OVER_LEFT_QUANTITY_INSTOCK_ERROR_MESSAGE, 5);
	}

	public int getLeftQuantityInstockAllowedOrdered() {
		waitToElementVisible(driver, ProductDetailPageLocators.OVER_LEFT_QUANTITY_INSTOCK_ERROR_MESSAGE);
		String[] overLeftQuanityAllowedOrderErrorMsg = getElementText(driver, ProductDetailPageLocators.OVER_LEFT_QUANTITY_INSTOCK_ERROR_MESSAGE).split("-");
		Pattern p = Pattern.compile("^([a-zA-Z]+)([0-9]+)(.*)");
		Matcher m = p.matcher(overLeftQuanityAllowedOrderErrorMsg[overLeftQuanityAllowedOrderErrorMsg.length-1]);
		if (m.find()) {
			String leftQuantity = m.group(1);
			System.out.println(leftQuantity);
			return Integer.parseInt(leftQuantity);
		}
		 return 0;
	}

	public boolean isLoginPageDisplayed() {
		waitToElementVisible(driver, ProductDetailPageLocators.LOGIN_POPUP, 15);
		return isElementDisplay(driver, ProductDetailPageLocators.LOGIN_POPUP);
	}

	public void clickToShoppingCart() {
		sleepInSecond(5);
		waitToElementClickable(driver, ProductDetailPageLocators.SHOPPING_CART);
		clickToElement(driver, ProductDetailPageLocators.SHOPPING_CART);
	}

	/*
	 * public boolean isMaxiumQuantityAllowedOrderErrorMsgUnDisplayed() { waitToElementInvisible(driver,
	 * ProductDetailPageLocators.MAXIUM_QUANTITY_ALLOWED_ORDER_ERROR_MESSAGE); return isElementUndisplayed(driver,
	 * ProductDetailPageLocators.MAXIUM_QUANTITY_ALLOWED_ORDER_ERROR_MESSAGE); }
	 */

	public boolean isAddToCartSuccessUndisplayed() {
		waitToElementInvisible(driver, ProductDetailPageLocators.ADD_TO_CART_SUCCESS);
		return isElementUndisplayed(driver, ProductDetailPageLocators.ADD_TO_CART_SUCCESS, 5);
	}

}
