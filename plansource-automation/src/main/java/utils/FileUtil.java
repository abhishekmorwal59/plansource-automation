package utils;

import java.io.File;

public class FileUtil {

    // ✅ Wait until a file containing a given name is downloaded
    public static boolean waitForFileDownload(String downloadDir, String filenameContains, int timeoutInSeconds) {
        File dir = new File(downloadDir);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("❌ Directory does not exist or is not a directory: " + downloadDir);
            return false;
        }

        System.out.println("📁 Watching download directory: " + downloadDir);

        int waited = 0;

        while (waited < timeoutInSeconds) {
            File[] files = dir.listFiles();

            if (files == null) {
                System.err.println("⚠️ listFiles() returned null. Ensure directory exists and is accessible.");
                return false; // hard fail instead of looping infinitely
            }

            for (File file : files) {
                if (file.getName().toLowerCase().contains(filenameContains.toLowerCase()) &&
                        file.getName().toLowerCase().endsWith(".pdf")) {
                    System.out.println("✅ File downloaded: " + file.getAbsolutePath());
                    return true;
                }
            }

            try {
                Thread.sleep(1000); // 1-second interval
                waited++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // best practice
                e.printStackTrace();
                return false;
            }
        }

        System.err.println("❌ Timed out waiting for file containing '" + filenameContains + "' in: " + downloadDir);
        return false;
    }

    // ✅ Clean any previously downloaded PDF files (recommended before download step)
    public static void cleanOldPDFs(String downloadDir) {
        File dir = new File(downloadDir);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("❌ Cannot clean PDFs: invalid directory path: " + downloadDir);
            return;
        }

        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".pdf"));
        if (files != null) {
            for (File file : files) {
                System.out.println("🧹 Deleting: " + file.getName());
                if (!file.delete()) {
                    System.err.println("⚠️ Failed to delete: " + file.getAbsolutePath());
                }
            }
        }
    }
}
