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
import java.time.Duration;

public class AddCandidateTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/TC_16_Report.html");
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
        login.login("admin", "admin123"); 
    }

    @Test
    public void TC_16_AddCandidate() throws Exception {
        test = extent.createTest("TC_16 – Add Candidate");
        
        try {
            RecruitmentPage recruitment = new RecruitmentPage(driver);
            
            recruitment.navigateToRecruitment();
            test.pass("Navigated to Recruitment");

            recruitment.clickAdd();
            recruitment.enterCandidateDetails("Alfiya", "Khan", "alfiyakhan@gmail.com");
            test.info("Entered candidate details for Alfiya Khan");

            recruitment.saveCandidate();
            
           
            Assert.assertTrue(recruitment.isCandidateCreated(), "Candidate was not saved successfully!");
            test.pass("Candidate profile created successfully");
            
        } catch (Exception e) {
            String screenPath = ScreenshotHRM.takeScreenshot(driver, "TC_16_Failure");
            test.fail("Test Failed: " + e.getMessage(), 
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