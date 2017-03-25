package pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Browser;

public class AdminLogInPage {

	public final static String URL_ADMIN = "http://shop.pragmatic.bg/admin";
	public final static String USERNAME = "admin";
	public final static String PASSWORD = "parola";

	@FindBy(name = "username")
	public static WebElement username;
	@FindBy(name = "password")
	public static WebElement password;
	@FindBy(className = "button")
	public static WebElement buttonLogin;
	@FindBy(className = "warning")
	public static WebElement warnning;
	@FindBy(className = "heading")
	public static WebElement loginFormHeading;

	public static void open() {
		Browser.driver.get(URL_ADMIN);
		PageFactory.initElements(Browser.driver, AdminLogInPage.class);
	}

	public static void isAt() {
		PageFactory.initElements(Browser.driver, AdminLogInPage.class);
		assertEquals("Please enter your login details.", loginFormHeading.getText());
	}

	public static void fillUsername(String name) {
		username.sendKeys(name);
	}

	public static void fillPassword(String pass) {
		password.sendKeys(pass);
	}

	public static void clickLoginButton() {
		buttonLogin.click();
	}

	public static void logIn(String name, String pass) {
		fillUsername(name);
		fillPassword(pass);
		clickLoginButton();
	}

}
