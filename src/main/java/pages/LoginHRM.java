package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginHRM {
    WebDriver driver;
    WebDriverWait wait;

    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//button[@type='submit']");
    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

    public LoginHRM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

  
    public void login(String user, String pass) {
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(user);
        driver.findElement(passwordField).sendKeys(pass);
        driver.findElement(loginButton).click();
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
    }
}