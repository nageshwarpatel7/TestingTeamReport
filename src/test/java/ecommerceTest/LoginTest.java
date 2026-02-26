package ecommerceTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.LoginPage;
import report.ExtentManager;

public class LoginTest extends BaseTest {

    @Test
    public void TC_01_LoginWithValidCredentials() {

        test = ExtentManager.getReportInstance()
                .createTest("TC_01 – Login with Valid Credentials");

        LoginPage login = new LoginPage(driver);

        test.info("Clicking Login link");
        login.clickLoginLink();

        test.info("Entering valid email");
        login.enterEmail("kalfiya392@gmail.com");

        test.info("Entering valid password");
        login.enterPassword("alfiyakhan");

        test.info("Clicking Login button");
        login.clickLoginButton();

        test.info("Validating successful login");
        Assert.assertTrue(login.isLoginSuccessful(), 
                "User not redirected to dashboard");

        test.pass("User logged in successfully");
    }
    @Test
    public void TC_02_LoginWithInvalidCredentials() {

        test = ExtentManager.getReportInstance()
                .createTest("TC_02 – Login with Invalid Credentials");

        LoginPage login = new LoginPage(driver);

        test.info("Clicking Login link");
        login.clickLoginLink();

        test.info("Entering invalid email");
        login.enterEmail("abc@gmail.com");

        test.info("Entering invalid password");
        login.enterPassword("wrongpassword");

        test.info("Clicking Login button");
        login.clickLoginButton();

        test.info("Validating error message");

        String actualError = login.getErrorMessage();

        Assert.assertTrue(actualError.contains("Login was unsuccessful"),
                "Error message not displayed!");

        test.pass("Error message displayed successfully for invalid login");
    }
}