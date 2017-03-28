package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pages.AdminLogInPage;
import pages.InsertProductPage;
import pages.ProductsPage;
import utils.Browser;

public class ProductsPageTests {

	@Before
	public void init() {
		// Browser.init("firefox");
		Browser.init("chrome");
		AdminLogInPage.open();
		//mogat da se nabutat vsichki stypki do "Products"(do stranicata na koqto trqbva da bydem)
		ProductsPage.open();// predi beshe v testa addNewProduct / i po dobre da sa listnati otdelnite stypki /po izchusteni metodi
	}

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(1500);
		Browser.driver.quit();
	}

	@Test
	public void addNewProduct() {
		ProductsPage.isAt();
		if (ProductsPage.isProductExist(InsertProductPage.NAME, InsertProductPage.MODEL, InsertProductPage.PRICE)) {
			ProductsPage.deleteProduct(InsertProductPage.NAME, InsertProductPage.MODEL);
		}
		InsertProductPage.createProduct(InsertProductPage.NAME, InsertProductPage.MODEL, InsertProductPage.PRICE);
		
		Assert.assertTrue(ProductsPage.successMsg.isDisplayed());
		Assert.assertEquals("Wrong success message", ProductsPage.SUCCESS, ProductsPage.successMsg.getText());
		
		if (ProductsPage.isProductExist(InsertProductPage.NAME, InsertProductPage.MODEL, InsertProductPage.PRICE)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

	}
	
	@Test
	public void addNewProductSecond(){
		ProductsPage.isAt();
		ProductsPage.searchProduct("proba_method_2");
		if (ProductsPage.areThereMatchingResults("proba_method_2")) {
			ProductsPage.deleteProduct(InsertProductPage.NAME, InsertProductPage.MODEL);
		}
		
	}

}
