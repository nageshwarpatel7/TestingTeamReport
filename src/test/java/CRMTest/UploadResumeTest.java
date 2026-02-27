package CRMTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pages.LoginHRM;
import pages.RecruitmentPage;
import utilites.ScreenshotHRM;
import java.io.File;
import java.time.Duration;

public class UploadResumeTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/TC_17_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");
        
        LoginHRM login = new LoginHRM(driver);
        login.login("Admin", "admin123");
    }

    @Test
    public void TC_17_UploadResumeValidation() throws Exception {
        test = extent.createTest("TC_17 – Upload Resume Validation");
        
        try {
            RecruitmentPage recruitment = new RecruitmentPage(driver);
            recruitment.navigateToRecruitment();
            recruitment.clickAdd();
            
            // Fill mandatory fields to satisfy form requirements
            recruitment.enterCandidateDetails("Alfiya", "Khan", "alfiya@test.com");
            test.info("Mandatory details filled");

            // Define the manual path to your .exe file
            String manualPath = "C:\\Users\\hp\\OneDrive\\Desktop\\Resume.jpeg"; 
            
            test.info("Triggering Robot upload for: " + manualPath);
            recruitment.uploadFileWithRobot(manualPath);

            // Capture the error message
            String actualError = recruitment.getErrorMessage();
            test.info("Detected UI Error: " + actualError);

            Assert.assertEquals(actualError, "File type not allowed");
            test.pass("System successfully restricted the .exe file upload.");

        } catch (Exception e) {
            String screenPath = ScreenshotHRM.takeScreenshot(driver, "TC_17_Robot_Fail");
            test.fail("Robot execution or validation failed: " + e.getMessage(), 
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