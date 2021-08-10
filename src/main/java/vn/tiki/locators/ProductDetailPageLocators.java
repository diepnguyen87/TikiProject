package vn.tiki.locators;

public class ProductDetailPageLocators {

	public static final String SELECTED_PRODUCT_TITLE = "//div[@class='header']/h1[@class='title']";
	public static final String SELECTED_PRODUCT_PRICE = "//div[@class='body']//div[@class='product-price']/span[contains(@class, 'current-price')]";
	public static final String SELECTED_PRODUCT_FLASH_SALE_PRICE = "//div[@class='body']//div[contains(@class, 'price-and-icon')]//div[@class='flash-sale-price']/span";
	public static final String QUANTITY_INPUT = "//p[text()='Số Lượng']/parent::div//input";
	public static final String ADD_TO_CART_BUTTON = "//button[contains(@class, 'add-to-cart') and contains(@data-view-id, 'add_to_cart')]";
	
	//Login Popup
	public static final String LOGIN_POPUP = "//div[@class='heading']/p[text()='Đăng nhập hoặc Tạo tài khoản']";
	public static final String TELEPHONE_INPUT = "//p[text()='Đăng nhập hoặc Tạo tài khoản']/parent::div/following-sibling::form//input[@name='tel']";
	public static final String CONTINUE_BUTTON = "//p[text()='Đăng nhập hoặc Tạo tài khoản']/parent::div/following-sibling::form/button";
	public static final String PASSWORD_INPUT = "//input[@placeholder='Mật khẩu']";
	public static final String LOGIN_BUTTON = "//button[text()='Đăng Nhập']";
	
	//error message
	public static final String MAXIUM_QUANTITY_ALLOWED_ORDER_ERROR_MESSAGE = "//div[@type='error' and contains(text(), 'có số lượng tối đa được mua')]";
	public static final String OVER_LEFT_QUANTITY_INSTOCK_ERROR_MESSAGE = "//div[@type='error' and contains(text(), 'chỉ còn')]";
	
	//cart
	public static final String SHOPPING_CART = "//div[@class='cart-wrapper']";
	public static final String ADD_TO_CART_SUCCESS = "//p[@class='status']";
	
}
