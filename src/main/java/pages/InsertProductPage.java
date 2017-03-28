package pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Browser;

public class InsertProductPage {

	public final static String URL = "http://shop.pragmatic.bg/admin/index.php?route=catalog/product/insert";
	public final static String NAME = "Telephone";
	public final static String MODEL = "M100";
	public final static String PRICE = "100.30";

	@FindBy(xpath = "//input[@name='product_description[1][name]']")
	public static WebElement productName;
	@FindBy(xpath = "//textarea[@name = 'product_description[1][meta_description]']")
	public static WebElement metaTagDesc;
	@FindBy(xpath = "//textarea[@name = 'product_description[1][meta_keyword]']")
	public static WebElement metaTagKey;
	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public static WebElement descrptionIframe;
	@FindBy(xpath = "//input[@name='product_description[1][tag]']")
	public static WebElement productsTag;
	@FindBy(linkText = "Data")
	public static WebElement dataTab;
	@FindBy(xpath = "//input[@name = 'model']")
	public static WebElement modelInput;
	@FindBy(xpath = "//input[@name = 'price']")
	public static WebElement priceInput;
	@FindBy(linkText = "Save")
	public static WebElement saveButton;

	public static void isAt() {
		assertTrue(Browser.driver.getCurrentUrl().contains(URL));
		PageFactory.initElements(Browser.driver, InsertProductPage.class);
	}

	public static void createProduct(String name, String model, String price) {
		ProductsPage.goToInsertProductPage();
		populateFieldsForCreateProduct(name, model, price);
		clickSaveButton();
	}


	public static void populateFieldsForCreateProduct(String name, String model, String price) {
		populateProductName(name);
		gotoDataTab();
		populateModel(model);
		populatePrice(price);
	}

	public static void populateProductName(String name) {
		productName.sendKeys(name);
	}

	public static void populateModel(String model) {
		modelInput.sendKeys(model);

	}

	public static void populatePrice(String price) {
		priceInput.sendKeys(price);
	}

	public static void clickSaveButton() {
		saveButton.click();
	}

	public static void gotoDataTab() {
		dataTab.click();
	}

}
