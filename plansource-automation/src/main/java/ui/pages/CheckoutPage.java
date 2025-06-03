package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {
        driver.findElement(By.xpath("//a[normalize-space()='Admin']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Proceed to Checkout')]")).click();
        driver.findElement(By.xpath("//button[contains(@class,'btn') and contains(@class,'btn-primary') and span[normalize-space()='Checkout']]")).click();

    }
}
