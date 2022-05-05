package vn.tinypulse;

public class InvitePageLocators {

	public static final String FIRST_NAME_TXT = "//tbody/tr[%s]/td[2]/div[@data-cucumber='first-name']//input";
	public static final String LAST_NAME_TXT = "//tbody/tr[%s]/td[2]/div[@data-cucumber='last-name']//input";
	public static final String EMAIL_TXT = "//tbody/tr[%s]/td[3]//input[@field='email']";
	public static final String START_DATE_TXT = "//tbody/tr[%s]/td[4]//input[@field='startDate']";
	public static final String LOADING_ICON = "//div[contains(@class, loading)]//div[@class='tiny-spinner-item']";
	public static final String CURRENT_MONTH_CALENDAR = "//div[@data-value='%s']";
}
