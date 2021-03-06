package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.Browser;

public class ProductsPage {

	public final static String HEADING = "Products";
	public final static String SUCCESS = "Success: You have modified products!";

	@FindBy(xpath = "//div[@class='heading']//h1")
	public static WebElement heading;
	@FindBy(linkText = "Insert")
	public static WebElement insertButton;
	@FindBy(className = "success")
	public static WebElement successMsg;
	@FindBy(xpath = "//input[@name = 'filter_name']")
	public static WebElement productNameFilter;
	@FindBy(xpath = "//input[@name = 'filter_model']")
	public static WebElement modelFilter;
	@FindBy(xpath = "//input[@name = 'filter_price']")
	public static WebElement priceFilter;
	@FindBy(linkText = "Filter")
	public static WebElement filterButton;
	@FindBy(linkText = "Delete")
	public static WebElement deleteButton;
//	@FindBy(xpath = "//tbody/tr[@class = not('filter')]")
//	public static WebElement searchRows;

	public static void open() {
		DashboardPage.open();// izlishno da se pravi v tozi metod
		Action.hoverElement(DashboardPage.catalogNavLink);
		Action.clickNotVisible(DashboardPage.productsNavLink);
		PageFactory.initElements(Browser.driver, ProductsPage.class);
	}

	public static void isAt() {
		assertEquals(HEADING, heading.getText());
	}

	public static void clickInsert() {
		insertButton.click();
	}

	public static void goToInsertProductPage() {
		clickInsert();
		InsertProductPage.isAt();
	}

	public static void populateNameFilter(String name) {
		productNameFilter.clear();
		productNameFilter.sendKeys(name);
	}

	public static void populateModelFilter(String model) {
		modelFilter.clear();
		modelFilter.sendKeys(model);
	}

	public static void populatePriceFilter(String price) {
		priceFilter.clear();
		priceFilter.sendKeys(price);
	}

	public static void clickFilterButton() {
		filterButton.click();
	}

	public static void searchProduct(String name, String model, String price) {
		populateNameFilter(name);
		populateModelFilter(model);
		populatePriceFilter(price);
		clickFilterButton();
	}
	public static void searchProduct(String name) {
		populateNameFilter(name);
		clickFilterButton();
	}
	
	

	public static boolean isProductExist(String name, String model, String price) {
		searchProduct(name, model, price);
		try {
			assertTrue(Browser.driver.findElement(By.xpath("//tbody//td[text() = '" + InsertProductPage.NAME + "']"))
					.isDisplayed());
			assertTrue(Browser.driver.findElement(By.xpath("//tbody//td[text() = '" + InsertProductPage.MODEL + "']"))
					.isDisplayed());
			// assertTrue(Browser.driver.findElement(By.xpath("//tbody//td[text()
			// = '"+InsertProductPage.PRICE+"']")).isDisplayed());
		} catch (Throwable e) {
			return false;
		}
		return true;
	}

	public static void deleteProduct(String name, String model) {
// trqbva da se opravi :D
		List<WebElement> models = Browser.driver
				.findElements(By.xpath("//td[.='" + name + "']//following-sibling::td[1]"));
		for (WebElement m : models) {
			if (m.getText().equals(model)) {
				WebElement checkbox = Browser.driver.findElement(
						By.xpath("//td[.='" + name + "']//preceding-sibling::td/input[@name='selected[]']"));
				checkbox.click();
				deleteButton.click();
				break;
			}
		}

		Alert alert = Browser.driver.switchTo().alert();
		try {
			assertEquals("Wrong alert message", "Delete/Uninstall cannot be undone! Are you sure you want to do this?",
					alert.getText());
		} catch (Throwable e) {
			System.out.println("Wrong alert text");
		}
		alert.accept();
	}
/**
 * Check if product with given name exists in the search results list
 * @param name the product that we are looking for
 * @return true if product is in the list, false otherwise
 */
	public static boolean areThereMatchingResults(String name) {
		
		List<WebElement> searchResults = Browser.driver.findElements(By.xpath("//tbody/tr[@class = not('filter')]"));
		
		for(WebElement result : searchResults){
			try{
			result.findElement(By.xpath(".//td[.='No results!']"));
			searchResults.remove(result);
			break;
			} catch (Throwable e){
				System.out.println("do nothing remove");
			}
		}
		
		if(searchResults.size() == 0){
			return false;
		}
		
		for(WebElement result : searchResults){
			try{
			result.findElement(By.xpath(".//td[.='"+ name +"']"));
			return true;
			} catch (Throwable e){
				System.out.println("do nothing");
			}
		}
		return false;
	}

}