package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pojo.EmployeeData;
import utils.ElementUtil;
import utils.TestDataFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class EmployeePage {

    private WebDriver driver;

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    private By addEmployeeButton = By.xpath("//a[contains(text(),'Add a New Employee')]");
    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By ssn = By.id("ssn_text");
    private By address1 = By.id("address_1");
    private By address2 = By.id("address_2");
    private By city = By.id("city");
    private By stateTypeahead = By.id("stateTypeahead");
    private By zip = By.id("zip_code");
    private By country = By.id("countryTypeahead");
    private By birthdate = By.id("birthdate");
    private By gender = By.id("gender");
    private By maritalStatus = By.id("marital_status");
    private By hireDate = By.id("hire_date");
    private By eligibilityStartDate = By.id("benefits_start_date");
    private By employmentLevel = By.id("employment_level");
    private By location = By.id("location");
    private By currentSalary = By.id("current_salary");
    private By benefitSalary = By.id("benefit_salary");
    private By password = By.id("password");
    private By saveBtn = By.id("btn_save");

    private static final Map<String, String> stateMap = new HashMap<>();
    static {
        stateMap.put("AL", "Alabama");
        stateMap.put("CA", "California");
        stateMap.put("NY", "New York");
        stateMap.put("TX", "Texas");
    }

    public void createEmployee(EmployeeData data) {
        if (ElementUtil.waitForElementClickable(driver, addEmployeeButton, 10) != null) {
            driver.findElement(addEmployeeButton).click();
        } else {
            throw new RuntimeException("❌ 'Add a New Employee' button not visible or clickable");
        }
        driver.findElement(firstName).sendKeys(data.getFirstName());
        driver.findElement(lastName).sendKeys(data.getLastName());




    String randomSSN = TestDataFactory.generateRandomSSN();
        driver.findElement(ssn).sendKeys(randomSSN);

        driver.findElement(address1).sendKeys(data.getAddress1());
        driver.findElement(address2).sendKeys(data.getAddress2());
        driver.findElement(city).sendKeys(data.getCity());

        String fullState = stateMap.getOrDefault(data.getStateAbbr(), data.getStateAbbr());
        driver.findElement(stateTypeahead).clear();
        driver.findElement(stateTypeahead).sendKeys(fullState.substring(0, 3));

        driver.findElement(zip).sendKeys(data.getZip());
        driver.findElement(country).sendKeys(data.getCountry());
        driver.findElement(birthdate).sendKeys(data.getBirthDate());
        driver.findElement(gender).sendKeys(data.getGender());
        driver.findElement(maritalStatus).sendKeys(data.getMaritalStatus());

        int daysAgo = 2;
        String hire = TestDataFactory.getFormattedPastDate(daysAgo);

        WebElement hireDateElement = driver.findElement(hireDate);
        hireDateElement.clear();
        hireDateElement.sendKeys(hire);

        WebElement eligibilityElement = driver.findElement(eligibilityStartDate);
        eligibilityElement.clear();
        eligibilityElement.sendKeys(hire);


        ElementUtil.selectByVisibleText(driver.findElement(employmentLevel), data.getEmploymentLevel());
        ElementUtil.selectByVisibleText(driver.findElement(location), data.getLocation());

        driver.findElement(currentSalary).sendKeys(data.getCurrentSalary());
        driver.findElement(benefitSalary).sendKeys(data.getBenefitSalary());
        driver.findElement(password).sendKeys(data.getPassword());

        driver.findElement(saveBtn).click();
    }
}
