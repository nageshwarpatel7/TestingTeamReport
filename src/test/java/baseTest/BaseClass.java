package baseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class BaseClass {
    public static WebDriver driver;

    public void initializeDriver() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public String captureScreenshot(String testName) throws IOException {
        String path = System.getProperty("user.dir") + "/reports/screenshots/" + testName + ".png";
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(path));
        return path;
    }
}