package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import report.ExtentManagerTravel;
import utilites.ScreenshotUtil;

public class BaseTestTravel {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManagerTravel.getInstance();
    }

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demo.guru99.com/test/newtours/reservation.php");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.fail("Test Failed");
            test.addScreenCaptureFromPath(path);

        } else if (result.getStatus() == ITestResult.SUCCESS) {

            String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.pass("Test Passed");
            test.addScreenCaptureFromPath(path);
        }

        driver.quit();
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }
}