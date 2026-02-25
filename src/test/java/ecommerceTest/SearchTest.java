package ecommerceTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.LoginPage;
import pages.SearchPage;
import report.ExtentManager;

public class SearchTest extends BaseTest {

    @Test
    public void TC_03_SearchExistingProduct() {

        test = ExtentManager.getReportInstance()
                .createTest("TC_03 – Search Existing Product");

        LoginPage login = new LoginPage(driver);
        SearchPage search = new SearchPage(driver);

        // Step 1: Login
        test.info("Clicking Login link");
        login.clickLoginLink();

        test.info("Entering valid email");
        login.enterEmail("kalfiya392@gmail.com");

        test.info("Entering valid password");
        login.enterPassword("alfiyakhan");

        test.info("Clicking Login button");
        login.clickLoginButton();

        test.info("Login successful");

    
        test.info("Entering product name in search box");
        search.enterProductName("Laptop");

        test.info("Clicking search button");
        search.clickSearch();

        test.info("Validating product results");

        Assert.assertTrue(search.isProductDisplayed(),
                "Relevant products not displayed!");

        test.pass("Relevant products displayed successfully");
    }

    @Test
    public void TC_04_SearchNonExistingProduct() {

        test = ExtentManager.getReportInstance()
                .createTest("TC_04 – Search Non-Existing Product");

        SearchPage search = new SearchPage(driver);

        test.info("Entering invalid product name");
        search.enterProductName("InvalidProduct");

        test.info("Clicking search button");
        search.clickSearch();

     
        test.info("Validating 'No products found' message");

        String actualMessage = search.getNoProductMessage();

        Assert.assertTrue(
                actualMessage.contains("No products were found"),
                "No products found message not displayed!"
        );

        test.pass("'No products found' message displayed successfully");
    }
}