package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartLink = By.xpath("//a[contains(@class,'ico-cart')]");
    By quantityField = By.xpath("//input[contains(@class,'qty-input')]");
    By updateCartButton = By.name("updatecart");
    By removeCheckbox = By.xpath("//input[@name='removefromcart']");
    By addToCartButton = By.xpath("//button[contains(@class,'add-to-cart-button')]");

    By totalPrice = By.className("product-subtotal");

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public void updateQuantity(String qty) {
        WebElement qtyField = driver.findElement(quantityField);
        qtyField.clear();
        qtyField.sendKeys(qty);
    }

    public void clickUpdate() {
        driver.findElement(updateCartButton).click();
    }

    public boolean isCartTotalUpdated() {
        return driver.findElement(totalPrice).isDisplayed();
    }
}


