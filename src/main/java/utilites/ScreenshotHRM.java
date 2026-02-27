package utilites;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotHRM {
public static String takeScreenshot(WebDriver driver,String screenshotName) throws Exception{
	String path=System.getProperty("user.dir")+ "/reports/screenshots/" + screenshotName + ".png";
	File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src,new File(path));
	return path;
}
}
