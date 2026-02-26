package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.id("small-searchterms");
    By searchButton = By.cssSelector("button.search-box-button");
    By productTitle = By.cssSelector(".product-title a");
    By noProductMessage = By.cssSelector(".no-result");
    By addToWishlistButton = By.cssSelector("button.add-to-wishlist-button");
    By wishlistSuccessMessage = By.cssSelector(".bar-notification.success");
    By firstProduct = By.xpath("(//h2[@class='product-title']/a)[1]");
    public void enterProductName(String productName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(productName);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public boolean isProductDisplayed() {
        return driver.findElements(productTitle).size() > 0;
    }

    public String getNoProductMessage() {
        return driver.findElement(noProductMessage).getText();
    }
    public void clickAddToWishlist() {
        driver.findElement(addToWishlistButton).click();
    }
    public void clickFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public boolean isWishlistSuccessMessageDisplayed() {
        return driver.findElement(wishlistSuccessMessage).isDisplayed();
    }
}