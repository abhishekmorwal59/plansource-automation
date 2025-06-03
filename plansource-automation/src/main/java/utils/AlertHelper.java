package utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class AlertHelper {

    // Handles Chrome native modals like "Change your password"
    public static void pressEnterToCloseChromeModal() {
        try {
            Thread.sleep(1000); // wait a bit for modal to appear
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);   // Press Enter key
            robot.keyRelease(KeyEvent.VK_ENTER); // Release Enter key
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Optional: Handles 'Save address?' popups by pressing Tab + Enter
    public static void dismissAddressSavePrompt() {
        try {
            Thread.sleep(500); // slight delay before action
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
