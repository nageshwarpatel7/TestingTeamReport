package CRMTest;

import org.testng.annotations.Test;
import org.testng.Assert;
import baseTest.BaseTestCRM;
import pages.LoginHRM;
import utilites.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DataPersistenceTest extends BaseTestCRM {

    @Test(description = "TC_21 – Data Persistence Check")
    public void testDataPersistence() {
        test = extent.createTest("TC_21 – Data Persistence Check");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        LoginHRM loginPage = new LoginHRM(driver);
        
        String fName = "Auto";
        String lName = "User" + System.currentTimeMillis();

        try {
           
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            loginPage.login("Admin", "admin123");
            
           
            wait.until(ExpectedConditions.urlContains("dashboard"));
            test.pass("Successfully logged in and reached Dashboard URL.");

          
            test.info("Adding employee: " + fName + " " + lName);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Add ']"))).click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(fName);
            driver.findElement(By.name("lastName")).sendKeys(lName);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            
            
            wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
            test.pass("Employee record created.");

         
            test.info("Logging out...");
            wait.until(ExpectedConditions.elementToBeClickable(By.className("oxd-userdropdown-name"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout"))).click();
            wait.until(ExpectedConditions.urlContains("login"));

           
            test.info("Re-logging in...");
            loginPage.login("Admin", "admin123");
            wait.until(ExpectedConditions.urlContains("dashboard"));

           
            test.info("Searching for employee: " + lName);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
            
           
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Type for hints...'])[1]")))
                .sendKeys(lName);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

           
            Thread.sleep(3000); 
            
           
            boolean isPresent = driver.getPageSource().contains(lName);

            Assert.assertTrue(isPresent, "Data Persistence Failed: Record not found in search results.");
            test.pass("Data Persistence Verified: Record is still present.");

        } catch (Exception e) {
            String path = ScreenshotUtil.captureScreenshot(driver, "TC_21_Final_Retry_Fail");
            test.fail("Execution Error: " + e.getMessage()).addScreenCaptureFromPath(path);
            Assert.fail("TC_21 Failed: " + e.getMessage());
        }
    }
}