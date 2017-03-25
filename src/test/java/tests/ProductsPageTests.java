package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.AdminLogInPage;
import pages.ProductsPage;
import utils.Browser;

public class ProductsPageTests {
	
	@Before
	public void init(){
		Browser.init("firefox");
		// Browser.init("chrome");
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
		ProductsPage.createProduct();
	}

}
