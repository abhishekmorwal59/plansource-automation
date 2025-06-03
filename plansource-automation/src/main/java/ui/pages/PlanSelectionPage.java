package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtil;

import java.time.Duration;
import java.util.List;

public class PlanSelectionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PlanSelectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By newHireEnrollmentLink = By.xpath("//a[contains(text(),'New Hire Enrollment')]");
    private By enrollmentHeader = By.xpath("//h2[contains(text(),'Shop and Enroll in Benefits')]");
    private By nextShopBenefitsBtn = By.id("submit_form");
    private By shopPlansBtn = By.xpath("//a[contains(text(),'Shop Plans')]");
    private By selectMedicalPlanHeader = By.xpath("//span[contains(text(),'Select your Medical Plan')]"); // Add this line
    private By availablePlans = By.cssSelector("div[class*='product-card']"); // adjust if needed
    private By updateCartBtn = By.id("updateCartBtn");
    private By hsaSurveyHeading = By.xpath("//span[contains(text(),'HSA Survey Question')]");
    By yesRadioText = By.xpath("//label[@class='radio'][.//span[normalize-space()='Yes']]");
    private By nextBtn = By.id("next");
    private By saveBtn = By.id("submit_form");



    public void startNewHireEnrollment() {
        ElementUtil.waitForElementClickable(driver, newHireEnrollmentLink, 10).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(enrollmentHeader));
    }

    public boolean isEnrollmentPageLoaded() {
        return driver.findElement(enrollmentHeader).isDisplayed();
    }

    public void selectMedicalPlans() {
        // Step 1: Click "Next: Shop Benefits"
        ElementUtil.waitForElementClickable(driver, nextShopBenefitsBtn, 10).click();

        // Step 2: Click "Shop Plans"
        ElementUtil.waitForElementClickable(driver, shopPlansBtn, 10).click();


        // Step 3: Wait for Medical Plan heading to ensure page loaded
        ElementUtil.waitForElementVisible(driver, selectMedicalPlanHeader, 10);

        // Step 4: Select first plan (or two if needed)
        List<WebElement> plans = driver.findElements(availablePlans);
        if (!plans.isEmpty()) {
            plans.get(0).click(); // Select first available plan
        }

        // Step 5: Click "Update Cart"
        ElementUtil.waitForElementClickable(driver, updateCartBtn, 10).click();

    }

    public void clickYesOnHsaQuestion() {
        WebElement yesLabel = ElementUtil.waitForElementClickable(driver, yesRadioText, 10);

        // Scroll to element and click via JS to avoid overlay/intercept issues
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yesLabel);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesLabel);
    }



    public boolean isHSASurveyHeadingDisplayed() {
        return ElementUtil.waitForElementVisible(driver, hsaSurveyHeading, 10).isDisplayed();
    }
    public void clickNextSave() {

        ElementUtil.waitForElementClickable(driver, nextBtn, 10).click();

        ElementUtil.waitForElementClickable(driver, saveBtn, 10).click();

    }

}
