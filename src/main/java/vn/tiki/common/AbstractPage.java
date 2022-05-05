package vn.tiki.common;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import vn.tiki.config.Configuration;

public class AbstractPage {

	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private WebElement element;
	private Actions action;
	private Select select;
	public Configuration config;

	public AbstractPage() {
		config = ConfigFactory.create(Configuration.class);
	}

	public String getDynamicLocator(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getCurrentPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentpage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void getTextAlert(WebDriver driver) {
		driver.switchTo().alert().getText();
	}

	public void setTextAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getElement(WebDriver driver, String locator, String... values) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, values)));
	}

	public List<WebElement> getElements(WebDriver driver, String locator, String... values) {
		return driver.findElements(getByXpath(getDynamicLocator(locator, values)));
	}

	public void clickToElement(WebDriver driver, String locator) {
		if (driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(500);
		}
		element = getElement(driver, locator);
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		if (driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(500);
		}
		element = getElement(driver, getDynamicLocator(locator, values));
		element.click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value, String... dynamicLocatorValues) {

		if (driver.toString().toLowerCase().contains("chrome") || driver.toString().toLowerCase().contains("edge")) {
			sleepInSecond(2);
		}
		element = getElement(driver, getDynamicLocator(locator, dynamicLocatorValues));
		element.clear();
		element.sendKeys(value);
	} 

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		select = new Select(element);
		select.selectByVisibleText(itemValue);
	}

	public String getFirstSelectedTextInDropdown(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		select = new Select(element);
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {

		getElement(driver, parentLocator).click();

		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		List<WebElement> allItems = getElements(driver, childItemLocator);

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleepInMiliSecond(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... values) {
		return getElement(driver, getDynamicLocator(locator, values)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... values) {
		return getElement(driver, getDynamicLocator(locator, values)).getText();
	}

	public int countElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToCheckbox(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckbox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckbox(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
		return getElement(driver, getDynamicLocator(locator, values)).isDisplayed();
	}

	public void overrideImplicitWait(WebDriver driver) {

	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean areAllElementsSelected(WebDriver driver, String locator) {
		List<WebElement> allElements = getElements(driver, locator);
		for (WebElement element : allElements) {
			if (!element.isSelected()) {
				return false;
			}
		}
		return true;
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, getDynamicLocator(locator, values))).perform();
	}

	public void clickAndHoldToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.clickAndHold(getElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		WebElement sourceElement = getElement(driver, sourceLocator);
		WebElement targetElement = getElement(driver, targetLocator);
		action.dragAndDrop(sourceElement, targetElement).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator, values);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, getDynamicLocator(locator, values));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		sleepInSecond(1);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		return status;
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitToElementVisible(WebDriver driver, String locator, long timeout) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
	}

	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, config.getShortTimeout());

		overrideImplicitTimeout(driver, config.getShortTimeout());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
		overrideImplicitTimeout(driver, config.getLongTimeout());
	}

	public void waitToElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, config.getShortTimeout());

		overrideImplicitTimeout(driver, config.getShortTimeout());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
		// overrideImplicitTimeout(driver, config.getLongTimeout());
	}

	public void waitToAllElementsPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitToAllElementsPresence(WebDriver driver, String locator, long timeout) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
	}

	public void waitToElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, 180);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitToElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, config.getLongTimeout());
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, long timeout) {
		overrideImplicitTimeout(driver, timeout);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		overrideImplicitTimeout(driver, config.getLongTimeout());

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideImplicitTimeout(WebDriver driver, long seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

}
