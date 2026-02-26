package travelBookingTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTestTravel;
import pages.ReservationPage;
import org.openqa.selenium.io.FileHandler;

public class TicketBookingTest extends BaseTestTravel {

    @Test
    public void TC_05_EnterFlightDetails() {

        test = extent.createTest("TC_05 - Enter Flight Details With Alert");

        driver.get("https://demo.guru99.com/test/newtours/");

        ReservationPage reservation = new ReservationPage(driver);

        reservation.handleAlertIfPresent();
        test.info("Handled alert successfully");

     
        driver.findElement(By.linkText("Flights")).click();

       
        reservation.enterFlightDetails();
        test.info("Flight details entered successfully");

        test.pass("Flight Finder form submitted successfully");
    }

    }