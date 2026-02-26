package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManagerTravel {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir")
                    + "/test-output/ExtentReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Travel Booking Automation Report");
            spark.config().setDocumentTitle("Guru99 Reservation Test");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}