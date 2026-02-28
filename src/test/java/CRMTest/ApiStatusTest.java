package CRMTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import utilites.ScreenshotHRM;
import baseTest.BaseTestCRM;
public class ApiStatusTest extends BaseTestCRM{
@Test
public void validOrangeHRMStatus() throws Exception {
	test=extent.createTest("TC_20 - Native API Status Check");
	try {
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"))
                .GET()
                .build();
		test.info("Sendig Native Java GET request...");
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int code = response.statusCode();
        test.info("Response Code: " + code);

        Assert.assertEquals(code, 200, "API is down or unreachable!");
        test.pass("Native API check passed with status 200.");

    } catch (Exception e) {
        String path = ScreenshotHRM.takeScreenshot(driver, "NativeApiFail");
        test.fail("Request failed: " + e.getMessage()).addScreenCaptureFromPath(path);
        Assert.fail(e.getMessage());
    }
	}

}
