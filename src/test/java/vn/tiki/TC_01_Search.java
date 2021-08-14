package vn.tiki;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import vn.tiki.common.AbstractTest;
import vn.tiki.page_object.HomePage;
import vn.tiki.page_object.PageGeneratorManager;
import vn.tiki.page_object.ProductDetailPage;
import vn.tiki.page_object.ShoppingCartPage;
//test commit git
public class TC_01_Search extends AbstractTest {

	private WebDriver driver;
	private HomePage homePage;
	private ProductDetailPage productPage;
	private ShoppingCartPage shoppingCartPage;

	private String searchData = "samsung";
	private int productIndex = 4;
	private String selectedProductName, selectedProductPrice;
	private int selectedQuantity = 2;
	private long selectedRealPrice, selectedFinalPrice;

	@Parameters({ "browserName", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		startEnvironment(environmentName);
		log.info("Open webpage: " + conf.getURL());
		driver.get(conf.getURL());
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void TC_01_search_tikinow_products_by_name() throws IOException, ParseException {
		log.info("input '" + searchData + "' into search field");
		homePage.inputSearchField(searchData);

		log.info("click search button");
		homePage.clickToSearchButton();

		log.info("click to TIKI NOW checkbox");
		homePage.clickToTikiNowChkbox();

		selectedProductName = homePage.getProductName(String.valueOf(productIndex));
		log.info("The " + (productIndex + 1) + "nth product name is: " + selectedProductName);

		selectedProductPrice = homePage.getProductPrice(String.valueOf(productIndex));
		selectedRealPrice = Long.parseLong(selectedProductPrice.split("\s")[0].replace(".", ""));
		log.info("The " + (productIndex + 1) + "nth product price is: " + selectedProductPrice);

		log.info("click to " + (productIndex + 1) + "nth product");
		homePage.clickToSelectedProduct(String.valueOf(productIndex));
		productPage = PageGeneratorManager.getProductDetailPage(driver);

		String productPageTitle = productPage.getTitle();
		String productPagePrice = productPage.getPrice();

		if (productPageTitle.equals(selectedProductName) && productPagePrice.equals(selectedProductPrice)) {

			log.info("add 2 items into card");
			productPage.addToCart(selectedQuantity);

			log.info("click to button 'Chon Mua'");
			productPage.clickToAddToCartBtn();

			// login Tiki with autoIT
			// Runtime.getRuntime().exec("C:\\Users\\Admin\\Downloads\\authen.exe admin admin");

			// login Tiki with selenium
			log.info("input phone number");
			productPage.inputTelephomeNumber("input phone number");

			log.info("click button 'Tiep tuc'");
			productPage.clickToContinueBtn();

			log.info("input papssword");
			productPage.inputPassword("input password");

			log.info("click button 'Dang nhap'");
			productPage.clickToLoginBtn();

			boolean maxiumQuantityAllowedOrderErrorMsgUndisplayed = productPage.isMaxiumQuantityAllowedOrderErrorMsgUndisplayed();
			log.info("Is the error message 'maxium quantity allowed order' undisplayed: " + maxiumQuantityAllowedOrderErrorMsgUndisplayed);

			if (maxiumQuantityAllowedOrderErrorMsgUndisplayed == false) {

				int maximumQuantityAllowedOrdered = productPage.getMaxiumQuantityAllowedOrdered();
				log.info("Num of products user can order: " + maximumQuantityAllowedOrdered);

				log.info("add " + maximumQuantityAllowedOrdered + " into card");
				selectedQuantity = maximumQuantityAllowedOrdered;
				productPage.addToCart(selectedQuantity);

				log.info("click to button 'Chon Mua'");
				productPage.clickToAddToCartBtn();
			} else {
				boolean overLeftQuantityInstockErrorMsgUndisplayed = productPage.isOverleftQuantityInstockErrorMsgUndisplayed();
				log.info("Is over left quantity instock error message undisplayed: " + overLeftQuantityInstockErrorMsgUndisplayed);

				if (overLeftQuantityInstockErrorMsgUndisplayed == false) {

					int leftQuantityInstock = productPage.getLeftQuantityInstockAllowedOrdered();
					log.info("Num of products user can order: " + leftQuantityInstock);

					if (leftQuantityInstock != 0) {
						log.info("add " + leftQuantityInstock + " into card");
						selectedQuantity = leftQuantityInstock;
						productPage.addToCart(selectedQuantity);

						log.info("click to button 'Chon Mua'");
						productPage.clickToAddToCartBtn();
					}
				}
			}

			log.info("click to shopping cart");
			productPage.clickToShoppingCart();
			shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

			log.info("get no of products in shopping cart");
			int noOfProductsInShoppingCart = shoppingCartPage.getNoOfProductsInShoppingCart();

			if (noOfProductsInShoppingCart == 1) {
				log.info("get product name at row 1");
				String name = shoppingCartPage.getProductNameAtRow(1);
				Assert.assertTrue(name.contains(selectedProductName));

				log.info("get real price at row 1");
				String realPrice = shoppingCartPage.getRealPriceAtRow(1).replace("đ", "");
				Assert.assertEquals(realPrice, selectedProductPrice.replace(" ₫", ""));

				log.info("get quanity at row 1");
				int quantity = shoppingCartPage.getQuantityAtRow(1);
				Assert.assertEquals(quantity, selectedQuantity);

				log.info("get final price at row 1");
				String finalPrice = shoppingCartPage.getFinalPriceAtRow(1);
				selectedFinalPrice = selectedRealPrice * selectedQuantity;

				System.out.println("selected real price: " + selectedRealPrice);
				System.out.println("selected quantity: " + selectedQuantity);

				Assert.assertEquals(finalPrice, NumberFormat.getNumberInstance(Locale.ITALY).format(selectedFinalPrice) + "đ");
			} else {
				System.out.println("Tong so luong vuot qua 1");
			}
		} else {
			System.out.println("Ko bang nhau");
			System.out.println("Product page title " + productPageTitle + " compare with " + selectedProductName);
			System.out.println("Product page price " + productPagePrice + " compare with " + selectedProductPrice);
		}
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
