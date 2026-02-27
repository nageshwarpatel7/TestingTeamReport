package CRMTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pages.LoginHRM;
import pages.RecruitmentPage;
import utilites.ScreenshotHRM;
import java.time.Duration;

public class PerformanceTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/Performance_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        
        LoginHRM login = new LoginHRM(driver);
        login.login("Admin", "admin123");
    }

    @Test
    public void TC_18_PageLoadPerformance() throws Exception {
        test = extent.createTest("TC_18 – Page Load Performance");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            RecruitmentPage recruitment = new RecruitmentPage(driver);
            
           
            long start = System.currentTimeMillis();
            recruitment.navigateToRecruitment();
            
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Recruitment']")));
            long finish = System.currentTimeMillis();
            
            long totalTimeSeconds = (finish - start) / 1000;
            test.info("Recruitment Page load time: " + totalTimeSeconds + " seconds");

            
            if (totalTimeSeconds < 3) {
                test.pass("Performance OK: Load time was " + totalTimeSeconds + "s");
            } else {
                test.warning("Performance SLOW: Load time was " + totalTimeSeconds + "s (Exceeded 3s target)");
            }
            
            Assert.assertTrue(totalTimeSeconds <= 5, "Page load is critically slow!"); 

        } catch (Exception e) {
            String screenPath = ScreenshotHRM.takeScreenshot(driver, "TC_18_Perf_Failure");
            test.fail("Performance Test Failed: " + e.getMessage(), 
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