package reporting;

import org.testng.annotations.AfterSuite;
import java.io.IOException;

public class AllureReportGenerator {

    @AfterSuite(alwaysRun = true)
    public void generateAllureReport() {
        try {
            // Generate Allure report to target/allure-report
            ProcessBuilder builder = new ProcessBuilder("allure", "generate", "target/allure-results", "-o", "target/allure-report", "--clean");
            builder.inheritIO(); // Show output in terminal
            Process process = builder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("❌ Could not generate Allure report. Make sure Allure CLI is installed and in PATH.");
        }
    }
}
