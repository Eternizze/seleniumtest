package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tests.ProductsPageTests;
import utils.Action;
import utils.Browser;

public class ProductsPage {
	
	public final static String HEADING = "Products";
	
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
	
	
	public static void open(){
		DashboardPage.open();
		Action.hoverElement(DashboardPage.catalogNavLink);
		Action.clickNotVisible(DashboardPage.productsNavLink);
		PageFactory.initElements(Browser.driver, ProductsPage.class);	
	}
	
	public static void isAt(){
		assertEquals(HEADING, heading.getText());
	}

	public static void createProduct(String name, String model, String price) {
		InsertProductPage.goTo();
		InsertProductPage.isAt();
		InsertProductPage.populateGeneralTabFields(name,model,price);
	}
	
	public static void clickInsert(){
		insertButton.click();
	}
	
	public static boolean isProductExist(String name, String model, String price){
		productNameFilter.clear();
		productNameFilter.sendKeys(name);
		modelFilter.clear();
		modelFilter.sendKeys(model);
		priceFilter.clear();
		priceFilter.sendKeys(price);
		filterButton.click();
		try{
			assertTrue(Browser.driver.findElement(By.xpath("//tbody//td[text() = '"+ProductsPageTests.NAME+"']")).isDisplayed());
			assertTrue(Browser.driver.findElement(By.xpath("//tbody//td[text() = '"+ProductsPageTests.MODEL+"']")).isDisplayed());
//			assertTrue(Browser.driver.findElement(By.xpath("//tbody//td[text() = '"+ProductsPageTests.PRICE+"']")).isDisplayed());
		} catch(Throwable e){
			return false;
		}
		return true;
		
	}

	public static void deleteProduct(String name, String model) {
		WebElement checkbox = Browser.driver
				.findElement(By.xpath("//td[.='" + name + "']//preceding-sibling::td/input[@name='selected[]']"));
		if (Browser.driver.findElement(By.xpath("//td[.='" + name + "']//following-sibling::td[1]")).getText().equals(model)){
		checkbox.click();
		deleteButton.click();	
		}
	        Alert alert = Browser.driver.switchTo().alert();
	        try{
	        assertEquals("Wrong alert message","Delete/Uninstall cannot be undone! Are you sure you want to do this?",alert.getText());
	        } catch (Throwable e){
	        	System.out.println("Wrong alert text");
	        }
	        alert.accept();
	    } 

}