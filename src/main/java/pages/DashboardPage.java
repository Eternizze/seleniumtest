package pages;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.Browser;

public class DashboardPage {

	@FindBy(className = "div3")
	public static WebElement loginAsText;
	@FindBy(linkText = "Logout")
	public static WebElement logoutLink;
	@FindBy(id = "catalog")
	public static WebElement catalogNavLink;
	@FindBy(linkText = "Products")
	public static WebElement productsNavLink;

	public static void open() {
		AdminLogInPage.open();
		AdminLogInPage.logIn(AdminLogInPage.USERNAME, AdminLogInPage.PASSWORD);
		Browser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(Browser.driver, DashboardPage.class);
	}

	public static void isAt() {
		PageFactory.initElements(Browser.driver, DashboardPage.class);
		assertEquals(" You are logged in as admin", loginAsText.getText());
	}

	public static void logout() {

		Action.clickNotVisible(logoutLink);
		// Actions build = new Actions(Browser.driver);
		// build.click(logoutLink).perform();

	}

}
