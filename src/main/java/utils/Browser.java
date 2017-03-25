package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

	public final static String PATHFIREFOX = "D:/QA Pragmatic/QA Complete 7/Selenium/Selenium Drivers/geckodriver.exe";
	public final static String PATHCHROME = "D:/QA Pragmatic/QA Complete 7/Selenium/Selenium Drivers/chromedriver.exe";

	public static WebDriver driver;

	public static void init(String browser) {
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", PATHFIREFOX);
			driver = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", PATHCHROME);
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

}
