package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PassengerDetailPage {
    WebDriver driver;

    public PassengerDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputName") WebElement nameField;
    @FindBy(id = "address") WebElement ageField; 
    @FindBy(id = "city") WebElement genderField; 
    @FindBy(id = "zipCode") WebElement contactField; 
    @FindBy(xpath = "//input[@value='Purchase Flight']") WebElement continueBtn;

    public void enterPassengerName(String name) {
        nameField.sendKeys(name);
    }

    public void enterAgeAndGender(String age, String gender) {
        ageField.sendKeys(age);
        genderField.sendKeys(gender);
    }

    public void enterContactDetails(String contact) {
        contactField.sendKeys(contact);
    }

    public void clickContinue() {
        continueBtn.click();
    }
}