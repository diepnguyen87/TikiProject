package vn.tiki.locators;

public class ShoppingCartLocators {

	public static final String SELECTED_PRODUCTS_IN_SHOPPING_CART = "//div[@class='sellers']/ul/div/li[1]/div[@class='row']";
	public static final String NAME_BY_ROW = "//div[@class='sellers']/ul//div[@class='row'][%s]//a[contains(@class,'name')]";
	public static final String REAL_PRICE_BY_ROW = "//div[@class='sellers']/ul//div[@class='row'][%s]//span[contains(@class,'real-prices')]";
	public static final String QUALITY_BY_ROW = "//div[@class='sellers']/ul//div[@class='row'][1]//input[@class='qty-input']";
	public static final String FINAL_PRICE_BY_ROW = "//div[@class='sellers']/ul//div[@class='row'][1]//span[contains(@class, 'final-prices')]";
	
}
