package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import utils.ElementUtil;


public class DependentPage {

    private WebDriver driver;

    public DependentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By getStartedBtn = By.xpath("//a[contains(text(),'Get Started')]");
    private By nextReviewFamilyBtn = By.id("submit_form");
    private By addFamilyMemberBtn = By.xpath("//a[contains(text(),'Add Family Member')]");
    private By dependentHeading = By.xpath("//span[contains(text(),'Please enter')]");
    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By gender = By.id("gender");
    private By birthdate = By.id("birthdate");
    private By relationshipDropdown = By.id("relationship");
    private By saveBtn = By.id("submit_form"); // Already defined correctly

    // 📌 Navigate to dependent section
    public void openDependentSection() {
        ElementUtil.waitForElementClickable(driver, getStartedBtn, 10).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        ElementUtil.waitForElementClickable(driver, nextReviewFamilyBtn, 10).click();
    }

    // 📌 Add single dependent
    public void addDependent(String fname, String lname, String genderVal, String birthdateVal, String relation) {
        ElementUtil.waitForElementVisible(driver, addFamilyMemberBtn, 10).click();
        ElementUtil.waitForElementVisible(driver, dependentHeading, 10);

        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(gender).sendKeys(genderVal);
        driver.findElement(birthdate).sendKeys(birthdateVal);
        ElementUtil.selectByVisibleText(driver.findElement(relationshipDropdown), relation);

        ElementUtil.waitForElementClickable(driver, saveBtn, 10).click();
    }

    // 📌 Add two dependents (spouse & child)
    public void addTwoDependents() {
        try {
            Thread.sleep(1000); // small wait after modal closes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openDependentSection();

        // Add Spouse
        addDependent("Jane", "Doe", "Female", "02/10/1991", "Spouse");

        // ✅ Wait for modal to close and button to be clickable again
        ElementUtil.waitForElementClickable(driver, addFamilyMemberBtn, 10);

        try {
            Thread.sleep(1000); // small wait after modal closes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Add Child
        addDependent("Tommy", "Doe", "Male", "03/15/2015", "Child");
    }


}
