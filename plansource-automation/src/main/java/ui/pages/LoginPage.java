package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // ✅ Updated locators based on real HTML
    By usernameField = By.id("user_name");
    By passwordField = By.id("password");
    By loginButton = By.id("logon_submit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ✅ Check if login page is ready
    public boolean isLoginPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            return true;
        } catch (Exception e) {
            System.out.println("❌ Login page not loaded properly: " + e.getMessage());
            return false;
        }
    }

    // ✅ Perform login
    public void login(String username, String password) {
        if (!isLoginPageLoaded()) {
            throw new RuntimeException("Login page not loaded. Cannot proceed with login.");
        }

        WebElement userField = driver.findElement(usernameField);
        WebElement passField = driver.findElement(passwordField);
        WebElement loginBtn = driver.findElement(loginButton);

        userField.clear();
        userField.sendKeys(username);

        passField.clear();
        passField.sendKeys(password);

        loginBtn.click();
    }
}
