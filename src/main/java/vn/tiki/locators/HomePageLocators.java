package vn.tiki.locators;

public class HomePageLocators {

	public static final String SEARCH_INPUT = "//input[@data-view-id='main_search_form_input']";
	public static final String SEARCH_BUTTON = "//button[@data-view-id='main_search_form_button']";
	public static final String TIKI_NOW_CHKBOX = "//span[text()='Giao siêu tốc 2H']";
	public static final String SEARCH_RESULT_TITLE = "//div[@class='search-summary']/div[@class='title']/h1[contains(text(), 'Kết quả tìm kiếm cho')]";
	public static final String SElECTED_PRODUCT = "//div[@data-view-id='product_list_container']//a[@data-view-index=%s]";
	public static final String SELECTED_ITEM_NAME = "//a[@class='product-item' and @data-view-index=%s]//div[@class='info']/div[@class='name']/span";
	public static final String SELECTED_ITEM_PRICE = "//a[@class='product-item' and @data-view-index=%s]//div[@class='info']/div[@class='price-discount']/div[@class='price-discount__price']";
	

}
