package tests;

import base.DriverManager;
import io.qameta.allure.*;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.pages.*;
import utils.*;
import pojo.EmployeeData;
import api.APIClient;
import pojo.BenefitRequest;

import java.util.*;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("PlanSource Benefits Enrollment")
@Feature("End-to-End Enrollment Workflow")
public class PlanSourceTest {

    WebDriver driver;
    Logger logger = LogManager.getLogger(PlanSourceTest.class);

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
        logger.info("Launching browser and navigating to: " + ConfigReader.get("url"));
        driver.get(ConfigReader.get("url"));
    }

    @Test(description = "Verify complete enrollment workflow and PDF confirmation")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Enroll a new employee and download confirmation PDF")

    @Step("Login with username: {0}")
    public void testFullEnrollmentFlow() {
        Allure.step("Login to application");
        LoginPage login = new LoginPage(driver);
        login.login(ConfigReader.get("username"), ConfigReader.get("password"));
        logger.info("Logged in successfully");

        Allure.step("Create new employee");
        EmployeeData emp = TestDataFactory.getDefaultEmployee();
        EmployeePage empPage = new EmployeePage(driver);
        empPage.createEmployee(emp);
        logger.info("Employee created successfully");

        AlertHelper.pressEnterToCloseChromeModal();
        AlertHelper.dismissAddressSavePrompt();

        Allure.step("Navigate to Enrollment page");
        PlanSelectionPage planPage = new PlanSelectionPage(driver);
        planPage.startNewHireEnrollment();
        Assert.assertTrue(planPage.isEnrollmentPageLoaded(), "❌ Enrollment page did not load!");
        logger.info("✅ Navigated to Enrollment page");

        Allure.step("Add two dependents: spouse and child");
        DependentPage depPage = new DependentPage(driver);
        depPage.addTwoDependents();
        logger.info("✅ Added spouse and child dependents");

        Allure.step("Select Medical and Voluntary Plans");
        PlanSelectionPage planSelection = new PlanSelectionPage(driver);
        planSelection.selectMedicalPlans();
        logger.info("✅ Selected Medical and Voluntary plans successfully");

        Allure.step("Call Dental API to enroll employee");
        String sessionId = driver.manage().getCookieNamed("_session_id").getValue();
        Map<String, String> cookieMap = new HashMap<>();
        cookieMap.put("_session_id", sessionId);

        BenefitRequest request = new BenefitRequest();
        BenefitRequest.Election election = new BenefitRequest.Election();
        election.setCoverage_level_id(137L);
        election.setDependent_ids(new ArrayList<>());
        election.setOrg_plan_id(319901188L);
        request.setElection(election);
        request.setOrg_benefit_id(121137668L);
        request.setEnrollment_context_type("initial");
        request.setInclude_benefits_in_response(true);
        request.setInclude_related_coverage_changes(true);
        request.setLife_event_completed(false);

        String refererUrl = "https://partner-dev-benefits.plansource.com/subscriber/benefit/121137668";
        Response response = APIClient.enrollDentalBenefit(cookieMap, request, refererUrl);

        logger.info("API response: " + response.getStatusCode());
        logger.info("API response body:\n" + response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200, "❌ API call failed");
        logger.info("✅ Dental enrollment successful");

        Allure.step("Click Admin and Proceed to Checkout");
        driver.findElement(By.id("toggleNavMenu")).click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.proceedToCheckout();
        logger.info("✅ Proceeded to Checkout");

        Allure.step("Download and validate Confirmation PDF");
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String downloadDir = System.getProperty("user.home") + "/Downloads";
        FileUtil.cleanOldPDFs(downloadDir); // clean before download

        confirmationPage.downloadConfirmationPDF();

        boolean isDownloaded = FileUtil.waitForFileDownload(downloadDir, "confirmation", 30);
        Assert.assertTrue(isDownloaded, "❌ Confirmation PDF was not downloaded.");
        logger.info("✅ Confirmation PDF downloaded successfully");
    }

    @AfterMethod
    public void teardown() {
        logger.info("Closing browser.");
        DriverManager.quitDriver();
    }
    @AfterSuite
    public void openAllureReportInBrowser() {
        AllureReportOpener.openLocalhostReport();
    }
}
