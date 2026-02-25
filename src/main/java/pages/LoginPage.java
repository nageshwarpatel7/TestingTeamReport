package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver) {
	this.driver=driver;
}
By loginLink=By.className("ico-login");
By emailField=By.id("Email");
By passwordField=By.id("Password");
By loginButton=By.xpath("//button[text()='Log in']");
By myAccount=By.className("ico-account");
By errorMessage = By.cssSelector(".message-error.validation-summary-errors");
public void clickLoginLink() {
    driver.findElement(loginLink).click();
}

public void enterEmail(String email) {
    driver.findElement(emailField).sendKeys(email);
}

public void enterPassword(String password) {
    driver.findElement(passwordField).sendKeys(password);
}

public void clickLoginButton() {
    driver.findElement(loginButton).click();
}

public boolean isLoginSuccessful() {
    return driver.findElement(myAccount).isDisplayed();
}
public String getErrorMessage() {
    return driver.findElement(errorMessage).getText();
}
}
