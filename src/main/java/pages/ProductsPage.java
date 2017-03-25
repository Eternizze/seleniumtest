package pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Action;
import utils.Browser;

public class ProductsPage {
	
	public final static String HEADING = "Products";
	
	@FindBy(xpath = "//div[@class='heading']//h1")
	public static WebElement heading;
	@FindBy(linkText = "Insert")
	public static WebElement insertButton;
	
	public static void open(){
		DashboardPage.open();
		Action.hoverElement(DashboardPage.catalogNavLink);
		Action.clickNotVisible(DashboardPage.productsNavLink);
		PageFactory.initElements(Browser.driver, ProductsPage.class);	
	}
	
	public static void isAt(){
		assertEquals(HEADING, heading.getText());
	}

	public static void createProduct() {
		InsertProductPage.goTo();
		InsertProductPage.isAt();
	}
	
	public static void clickInsert(){
		insertButton.click();
		
	}
	
	

}
