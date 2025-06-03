package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtil;

public class ConfirmationPage {

    private WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By downloadBtn = By.xpath("//button[contains(@class, 'btn-link') and contains(., 'Download')]");

    public void downloadConfirmationPDF() {
        try {
            Thread.sleep(3000); // small wait after modal closes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ElementUtil.waitForElementClickable(driver, downloadBtn, 10).click();
    }
}
