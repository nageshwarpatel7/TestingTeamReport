package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductPage {
	 WebDriver driver;

	    public ProductPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    By addToCartButton = By.cssSelector("button.add-to-cart-button");
	    By addToWishlistButton = By.xpath("//button[contains(@class,'add-to-wishlist-button')]");
	    By cartSuccessMessage = By.cssSelector(".bar-notification.success");

	    public void clickAddToCart() {
	        driver.findElement(addToCartButton).click();
	    }
	    
	    public void clickAddToWishlist() {
	        driver.findElement(addToWishlistButton).click();
	    }

	    public boolean isProductAddedToCart() {
	        return driver.findElement(cartSuccessMessage).isDisplayed();
	    }

}
