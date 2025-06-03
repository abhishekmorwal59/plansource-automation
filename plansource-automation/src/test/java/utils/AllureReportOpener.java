package utils;

import java.awt.Desktop;
import java.net.URI;

public class AllureReportOpener {
    public static void openLocalhostReport() {
        try {
            String url = "http://localhost:63342/plansource-automation/target/allure-report/index.html";
            Desktop.getDesktop().browse(new URI(url));
            System.out.println("✅ Opened Allure Report at: " + url);
        } catch (Exception e) {
            System.err.println("❌ Failed to open Allure Report");
            e.printStackTrace();
        }
    }
}
