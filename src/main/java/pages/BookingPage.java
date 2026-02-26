package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookingPage {
    WebDriver driver;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(id = "inputName") WebElement nameField;
    @FindBy(id = "address") WebElement addressField;
    @FindBy(id = "city") WebElement cityField;

  
    @FindBy(id = "cardType") WebElement cardTypeDropdown;
    @FindBy(id = "creditCardNumber") WebElement creditCardField;
    @FindBy(id = "creditCardMonth") WebElement monthField;
    @FindBy(id = "creditCardYear") WebElement yearField;
    @FindBy(xpath = "//input[@value='Purchase Flight']") WebElement purchaseBtn;
    @FindBy(id = "error-message") WebElement errorMsg;
    
    @FindBy(xpath = "//table//tr[1]/td[2]") WebElement bookingId;

    public void enterPassengerDetails(String name, String address, String city) {
        nameField.sendKeys(name);
        addressField.sendKeys(address);
        cityField.sendKeys(city);
    }

    public void enterPaymentDetails(String cardType, String cardNumber) {
        new Select(cardTypeDropdown).selectByVisibleText(cardType);
        creditCardField.sendKeys(cardNumber);
        monthField.clear();
        monthField.sendKeys("11");
        yearField.clear();
        yearField.sendKeys("2027");
    }

    public void clickPurchase() {
        purchaseBtn.click();
    }

    public String getConfirmationId() {
        return bookingId.getText();
    }
    public void enterInvalidCardDetails(String invalidNumber) {
        creditCardField.clear();
        creditCardField.sendKeys(invalidNumber);
    }

    public void clickPayNow() {
        purchaseBtn.click();
    }

    public String getErrorMessage() {
        return errorMsg.getText();
    }
}