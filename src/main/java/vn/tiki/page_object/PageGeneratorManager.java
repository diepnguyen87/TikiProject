package vn.tiki.page_object;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	public static HomePage getHomePage(WebDriver driver) {
		return new HomePage(driver);
	}
	
	public static ProductDetailPage getProductDetailPage(WebDriver driver) {
		return new ProductDetailPage(driver);
	}
	
	public static ShoppingCartPage getShoppingCartPage(WebDriver driver) {
		return new ShoppingCartPage(driver);
	}
}
