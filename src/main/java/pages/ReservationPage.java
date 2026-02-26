package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReservationPage {

    WebDriver driver;
    WebDriverWait wait;

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    
    By roundTrip = By.xpath("//input[@value='roundtrip']");
    By oneWay = By.xpath("//input[@value='oneway']");

    By passengers = By.name("passCount");

    By fromPort = By.name("fromPort");
    By fromMonth = By.name("fromMonth");
    By fromDay = By.name("fromDay");

    By toPort = By.name("toPort");
    By toMonth = By.name("toMonth");
    By toDay = By.name("toDay");

    By economyClass = By.xpath("//input[@value='Coach']");
    By businessClass = By.xpath("//input[@value='Business']");
    By firstClass = By.xpath("//input[@value='First']");

    By airline = By.name("airline");

    By continueBtn = By.name("findFlights");

    
    public void handleAlertIfPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept(); 
        } catch (Exception e) {
            System.out.println("No alert present");
        }
    }

    
    public void enterFlightDetails() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(roundTrip));

       
        driver.findElement(roundTrip).click();

        
        new Select(driver.findElement(passengers)).selectByVisibleText("1");

    
        new Select(driver.findElement(fromPort)).selectByVisibleText("Acapulco");

      
        new Select(driver.findElement(fromMonth)).selectByVisibleText("July");
        new Select(driver.findElement(fromDay)).selectByVisibleText("12");

        new Select(driver.findElement(toPort)).selectByVisibleText("Acapulco");

        new Select(driver.findElement(toMonth)).selectByVisibleText("July");
        new Select(driver.findElement(toDay)).selectByVisibleText("12");

      
        driver.findElement(economyClass).click();

        new Select(driver.findElement(airline)).selectByVisibleText("No Preference");

       
        driver.findElement(continueBtn).click();
    }
}