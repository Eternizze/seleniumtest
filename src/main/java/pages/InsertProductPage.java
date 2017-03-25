package pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Browser;

public class InsertProductPage {
	
	public final static String URL = "http://shop.pragmatic.bg/admin/index.php?route=catalog/product/insert";
	
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
	
	public static void goTo(){
		ProductsPage.clickInsert();
		PageFactory.initElements(Browser.driver, ProductsPage.class);
	}
	
	public static void isAt(){
		assertTrue(Browser.driver.getCurrentUrl().contains(URL));
	}
	
	public static void populateGeneralTabFields(){
		
	}

}
