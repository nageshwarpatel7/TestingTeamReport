package CRMTest;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pages.LoginHRM;
import pages.RecruitmentPage;
import utilites.ScreenshotHRM;
import java.time.Duration;

public class ResponsiveUITest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/Responsive_UI_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");
        
        LoginHRM login = new LoginHRM(driver);
        login.login("Admin", "admin123");
    }

    @Test
    public void TC_19_ResponsiveUIValidation() throws Exception {
        test = extent.createTest("TC_19 – Responsive UI Validation");
        RecruitmentPage recruitment = new RecruitmentPage(driver);
        recruitment.navigateToRecruitment();

       
        Dimension desktop = new Dimension(1920, 1080);
        Dimension tablet = new Dimension(768, 1024);
        Dimension mobile = new Dimension(375, 667);

        try {
            
            driver.manage().window().setSize(tablet);
            test.info("Resized browser to Tablet (768x1024)");
            Thread.sleep(1000); 
            Assert.assertTrue(recruitment.isAddButtonVisible(), "Add button hidden in Tablet view!");
            test.pass("UI elements aligned correctly in Tablet view");

            
            driver.manage().window().setSize(mobile);
            test.info("Resized browser to Mobile (375x667)");
            Thread.sleep(1000);
            
            
            Assert.assertTrue(recruitment.isAddButtonVisible(), "Add button hidden or overlapped in Mobile view!");
            test.pass("UI elements aligned correctly in Mobile view");

            String screenPath = ScreenshotHRM.takeScreenshot(driver, "TC_19_Mobile_Layout");
            test.pass("Mobile UI screenshot captured", 
                MediaEntityBuilder.createScreenCaptureFromPath(screenPath).build());

        } catch (Exception e) {
            String screenPath = ScreenshotHRM.takeScreenshot(driver, "TC_19_UI_Error");
            test.fail("UI Validation Failed: " + e.getMessage(), 
                MediaEntityBuilder.createScreenCaptureFromPath(screenPath).build());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}