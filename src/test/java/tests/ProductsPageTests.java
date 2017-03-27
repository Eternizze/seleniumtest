package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pages.AdminLogInPage;
import pages.ProductsPage;
import utils.Browser;

public class ProductsPageTests {
	
	public final static String NAME = "Telephone";
	public final static String MODEL = "M100";
	public final static String PRICE = "100.30";
	public final static String SUCCESS = "Success: You have modified products!";
	@Before
	public void init(){
//		Browser.init("firefox");
		 Browser.init("chrome");
		AdminLogInPage.open();
	}
	
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(1500);
		Browser.driver.quit();
	}
	
	@Test
	public void addNewProduct(){
		ProductsPage.open();
		ProductsPage.isAt();
		if(ProductsPage.isProductExist(NAME, MODEL, PRICE)){
			ProductsPage.deleteProduct(NAME, MODEL);
		}
		ProductsPage.createProduct(NAME,MODEL,PRICE);
		Assert.assertTrue(ProductsPage.successMsg.isDisplayed());
		Assert.assertEquals("Wrong success message",SUCCESS, ProductsPage.successMsg.getText());
		if(ProductsPage.isProductExist(NAME, MODEL, PRICE)){
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}
		
	}

}
