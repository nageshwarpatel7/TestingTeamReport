package travelBookingTest;

import baseTest.BasePaymentTest;
import pages.BookingPage;
import report.ExtentManagerTravel;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;

public class EndToEndBookingTest extends BasePaymentTest {
    ExtentTest test;

    @BeforeMethod 
    public void setup() {
        initializeDriver();
        driver.get("https://blazedemo.com/purchase.php");
    }

    @Test(priority = 1)
    public void TC_06_TC_07_Combined_Booking_Flow() {
        test = ExtentManagerTravel.getInstance().createTest("TC_06 & TC_07: Booking & Payment Success");
        BookingPage booking = new BookingPage(driver);

        try {
            test.log(Status.INFO, "Starting Passenger Details entry...");
            booking.enterPassengerDetails("Alfiya Khan", "1600 Amphitheatre Pkwy", "Mountain View");
            test.log(Status.PASS, "Step 1: Passenger details entered.");

            test.log(Status.INFO, "Entering Payment Details...");
            booking.enterPaymentDetails("Visa", "4242424242424242");
            test.log(Status.PASS, "Step 2: Payment details entered.");

            booking.clickPurchase();
            
            String confirmationId = booking.getConfirmationId();
            Assert.assertNotNull(confirmationId, "Booking ID was not generated!");
            
            test.log(Status.PASS, "Step 3: Booking Successful! ID: " + confirmationId);
            test.addScreenCaptureFromPath(captureScreenshot("TC07_BookingSuccess"));

        } catch (Exception e) {
            test.log(Status.FAIL, "Test Failed due to: " + e.getMessage());
            test.addScreenCaptureFromPath(captureScreenshot("TC07_Error"));
            Assert.fail(e.getMessage());
        }
    }

   

    @AfterMethod
    public void tearDown() {
        // Ensuring report is updated after every single test
        ExtentManagerTravel.getInstance().flush();
        if (driver != null) {
            driver.quit();
        }
    }
}