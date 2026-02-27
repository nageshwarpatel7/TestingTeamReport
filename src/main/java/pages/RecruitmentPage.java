package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RecruitmentPage {
    WebDriver driver;
    WebDriverWait wait;


    By recruitmentMenu = By.xpath("//span[text()='Recruitment']");
    By addButton = By.xpath("//button[text()=' Add ']");
    By firstNameField = By.name("firstName");
    By lastNameField = By.name("lastName"); 
    By emailField = By.xpath("(//input[@placeholder='Type here'])[1]");
    By saveButton = By.xpath("//button[@type='submit']");
    By successToast = By.xpath("//p[text()='Successfully Saved']");
    By fileInput = By.xpath("//input[@type='file']"); 
    By errorMessage = By.xpath("//span[contains(@class, 'oxd-input-field-error-message')]");
   
    By browseButton = By.xpath("//div[@class='oxd-file-button']");
    // Flexible locator for the error message
    By fileError = By.xpath("//span[contains(@class, 'oxd-input-field-error-message')]");
    By pimMenu = By.xpath("//span[text()='PIM']");
    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToRecruitment() {
        wait.until(ExpectedConditions.elementToBeClickable(recruitmentMenu)).click();
    }

    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void enterCandidateDetails(String fname, String lname, String email) {
        driver.findElement(firstNameField).sendKeys(fname);
        driver.findElement(lastNameField).sendKeys(lname);
        driver.findElement(emailField).sendKeys(email);
    }

    public void saveCandidate() {
        driver.findElement(saveButton).click();
    }

    public boolean isCandidateCreated() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successToast)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
   
    public void uploadFileWithRobot(String path) throws Exception {
      
        wait.until(ExpectedConditions.elementToBeClickable(browseButton)).click();
        
       
        StringSelection selection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

       
        Robot robot = new Robot();
        robot.delay(2000); 

       
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

      
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        Thread.sleep(3000); 
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fileError)).getText();
    }
    public void navigateToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public boolean isAddButtonVisible() {
        try {
            WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(addButton));
      
            return btn.isDisplayed() && btn.getSize().getWidth() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}