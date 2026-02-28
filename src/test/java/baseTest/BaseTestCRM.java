package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestCRM {
    
    
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter spark;

    @BeforeSuite
    public void setupReport() {
       
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/report/ExtentReport.html");
        
        spark.config().setDocumentTitle("OrangeHRM Automation Report");
        spark.config().setReportName("Functional & API Testing");
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        
      
        extent.setSystemInfo("Environment", "Production/Demo");
        extent.setSystemInfo("Tester", "Alfiya Khan");
    }

    @BeforeMethod
    public void setupDriver() {
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
       
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {
       
        extent.flush();
    }
}