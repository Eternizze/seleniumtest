package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Action {

	public static void clickNotVisible(WebElement element) {
		Actions build = new Actions(Browser.driver);
		build.click(element).perform();
	}

	public static void hoverElement(WebElement element) {
		Actions build = new Actions(Browser.driver);
		build.moveToElement(element).perform();
	}
}
