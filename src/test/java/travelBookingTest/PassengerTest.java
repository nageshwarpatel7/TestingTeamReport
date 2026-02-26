package travelBookingTest;

import baseTest.BasePaymentTest;
import pages.PassengerDetailPage;
import report.ExtentManagerTravel;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.IOException;

import org.testng.annotations.Test;

public class PassengerTest extends BasePaymentTest {
    ExtentTest test;

    @Test
    public void TC_06_EnterPassengerDetails() throws IOException {
 
        test = ExtentManagerTravel.getInstance().createTest("TC_06 – Enter Passenger Details");
        
        PassengerDetailPage passengerPage = new PassengerDetailPage(driver);

        try {
          
            passengerPage.enterPassengerName("Alfiya Khan");
            test.log(Status.PASS, "Step 1: Passenger name entered successfully");

            passengerPage.enterAgeAndGender("21", "Feamle");
            test.log(Status.PASS, "Step 2: Age and Gender entered successfully");

           
            passengerPage.enterContactDetails("9876543210");
            test.log(Status.PASS, "Step 3: Contact details entered successfully");

            passengerPage.clickContinue();
            test.log(Status.PASS, "Step 4: Clicked Continue button");

          
            String screenshotPath = captureScreenshot("TC_06_Success");
            test.addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            test.log(Status.FAIL, "Test Case Failed: " + e.getMessage());
            String errorPath = captureScreenshot("TC_06_Failure");
            test.addScreenCaptureFromPath(errorPath);
        } finally {
            
            ExtentManagerTravel.getInstance().flush();
        }
    }
}