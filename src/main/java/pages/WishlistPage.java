package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class WishlistPage {
	
	WebDriver driver;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }
    By wishlistLink = By.xpath("//a[contains(@class,'ico-wishlist')]");
    By removeCheckbox = By.xpath("//input[@name='removefromcart']");
    By updateWishlistButton = By.xpath("//input[@name='updatecart']");
    By emptyWishlistMessage = By.xpath("//div[contains(@class,'no-data')]");
    

    public void openWishlist() {
        driver.findElement(wishlistLink).click();
    }
    
    

    public void selectRemoveCheckbox() {
        driver.findElement(removeCheckbox).click();
    }
    public void updateWishlist() {
        driver.findElement(updateWishlistButton).click();
    }

    public boolean isWishlistEmpty() {
        return driver.getPageSource().contains("The wishlist is empty");
    }



	
		
	}



