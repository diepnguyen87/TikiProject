package vn.tiki.page_object;

import org.openqa.selenium.WebDriver;

import vn.tiki.common.AbstractPage;
import vn.tiki.locators.ShoppingCartLocators;

public class ShoppingCartPage extends AbstractPage {

	WebDriver driver;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getNoOfProductsInShoppingCart() {
		waitToElementVisible(driver, ShoppingCartLocators.SELECTED_PRODUCTS_IN_SHOPPING_CART);
		return getElements(driver, ShoppingCartLocators.SELECTED_PRODUCTS_IN_SHOPPING_CART).size();
	}

	public String getProductNameAtRow(int row) {
		String rowIndex = String.valueOf(row);
		waitToElementVisible(driver, ShoppingCartLocators.NAME_BY_ROW, rowIndex);
		return getElementText(driver, ShoppingCartLocators.NAME_BY_ROW, rowIndex);
	}

	public String getRealPriceAtRow(int row) {
		String rowIndex = String.valueOf(row);
		waitToElementVisible(driver, ShoppingCartLocators.REAL_PRICE_BY_ROW, rowIndex);
		return getElementText(driver, ShoppingCartLocators.REAL_PRICE_BY_ROW, rowIndex);
	}

	public int getQuantityAtRow(int row) {
		String rowIndex = String.valueOf(row);
		waitToElementVisible(driver, ShoppingCartLocators.QUALITY_BY_ROW, rowIndex);
		String quantity = getElementAttribute(driver, ShoppingCartLocators.QUALITY_BY_ROW, "value", rowIndex);
		return Integer.parseInt(quantity);
	}

	public String getFinalPriceAtRow(int row) {
		String rowIndex = String.valueOf(row);
		waitToElementVisible(driver, ShoppingCartLocators.FINAL_PRICE_BY_ROW, rowIndex);
		return getElementText(driver, ShoppingCartLocators.FINAL_PRICE_BY_ROW, rowIndex);
	}

}
