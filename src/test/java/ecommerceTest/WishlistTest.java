package ecommerceTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.LoginPage;
import pages.SearchPage;
import pages.WishlistPage;
import report.ExtentManager;
import pages.ProductPage;

public class WishlistTest extends BaseTest {

    @Test
    public void TC_05_AddProductToWishlist() {

        test = ExtentManager.getReportInstance()
                .createTest("TC_05 - Add product to wishlist");

        LoginPage login = new LoginPage(driver);
        SearchPage search = new SearchPage(driver);

        test.info("Logging in");
        login.clickLoginLink();
        login.enterEmail("kalfiya392@gmail.com");
        login.enterPassword("alfiyakhan");
        login.clickLoginButton();

        test.info("Searching product");
        search.enterProductName("Laptop");
        search.clickSearch();

        test.info("Adding product to wishlist");
        search.clickAddToWishlist();

        Assert.assertTrue(search.isWishlistSuccessMessageDisplayed(),
                "Product not added to wishlist");

        test.pass("Product added to wishlist successfully");
    }
    
    @Test
    public void TC_06_RemoveProductFromWishlist() throws InterruptedException {

        LoginPage login = new LoginPage(driver);
        SearchPage search = new SearchPage(driver);
        WishlistPage wishlist = new WishlistPage(driver);

        // Login
        login.clickLoginLink();
        login.enterEmail("kalfiya392@gmail.com");
        login.enterPassword("alfiyakhan");
        login.clickLoginButton();

        // Add product first
        search.enterProductName("Laptop");
        search.clickSearch();
        search.clickFirstProduct();
        
        ProductPage product = new ProductPage(driver);
        product.clickAddToWishlist();
        
        Thread.sleep(2000);
     

        // Now remove
        wishlist.openWishlist();
        wishlist.selectRemoveCheckbox();
        
        wishlist.updateWishlist();

        Assert.assertTrue(wishlist.isWishlistEmpty());
    }
}