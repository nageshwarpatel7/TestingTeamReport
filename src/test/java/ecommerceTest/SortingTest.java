package ecommerceTest;

import baseTest.BaseEcommerce;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingTest extends BaseEcommerce {

    @Test
    public void validateSortingLogic() throws InterruptedException {
      
        test = extent.createTest("Scenario 3: Search Result Sorting Logic Validation");

        driver.get("https://www.flipkart.com/");
        test.info("Navigated to Flipkart");
      
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("Mobile\n");
        test.info("Searched for 'Mobile'");

      
        driver.findElement(By.xpath("//div[text()='Price -- Low to High']")).click();
        Thread.sleep(3000); 
        test.info("Applied Sorting: Low to High");

      
        List<WebElement> priceElements = driver.findElements(By.className("Nx9b0j"));
        List<Integer> actualPrices = new ArrayList<>();

        for (int i = 0; i < 10 && i < priceElements.size(); i++) {
            String rawPrice = priceElements.get(i).getText().replaceAll("[^0-9]", "");
            actualPrices.add(Integer.parseInt(rawPrice));
        }
        test.info("Captured Prices: " + actualPrices);

        
        List<Integer> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not in ascending order!");
    }
}