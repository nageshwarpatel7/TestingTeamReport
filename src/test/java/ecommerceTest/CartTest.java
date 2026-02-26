package ecommerceTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import pages.ProductPage;
import pages.CartPage;

public class CartTest extends BaseTest {
	
	@Test
	
	public void TC_07_AddProductToCart() {

	    LoginPage login = new LoginPage(driver);
	    SearchPage search = new SearchPage(driver);
	    ProductPage product = new ProductPage(driver);

	    login.clickLoginLink();
	    login.enterEmail("kalfiya392@gmail.com");
	    login.enterPassword("alfiyakhan");
	    login.clickLoginButton();

	    search.enterProductName("Laptop");
	    search.clickSearch();
	    search.clickFirstProduct();
	    product.clickAddToCart();

	    Assert.assertTrue(product.isProductAddedToCart());
	}
	@Test
	public void TC_08_UpdateCartQuantity() {

	    LoginPage login = new LoginPage(driver);
	    SearchPage search = new SearchPage(driver);
	    CartPage cart = new CartPage(driver);
	    ProductPage product = new ProductPage(driver);

	    // Login
	    login.clickLoginLink();
	    login.enterEmail("kalfiya392@gmail.com");
	    login.enterPassword("alfiyakhan");
	    login.clickLoginButton();

	    // Add product first
	    search.enterProductName("Laptop");
	    search.clickSearch();
	    search.clickFirstProduct();
	    product.clickAddToCart();

	    // Now update cart
	    cart.openCart();
	    cart.updateQuantity("2");
	    cart.clickUpdate();

	    Assert.assertTrue(cart.isCartTotalUpdated());
	}
}
