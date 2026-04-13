package org;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static WebDriver driver;
    private static String reportPath;

    public static void setupReport() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            reportPath = "test-output/ExtentReport_" + timestamp + ".html";
            
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Web Browser Automation Report");
            sparkReporter.config().setReportName("Selenium Test Execution Report");
            sparkReporter.config().setTheme(Theme.STANDARD);
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
    }

    public static void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
    }

    public static void logStep(String stepName, String status) {
        if (test != null) {
            switch (status.toLowerCase()) {
                case "pass":
                    test.pass(stepName);
                    break;
                case "fail":
                    test.fail(stepName);
                    break;
                case "info":
                default:
                    test.info(stepName);
                    break;
            }
        }
    }

    public static void addScreenshot(String screenshotName) {
        if (test != null && driver != null) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
                
                // Create screenshots directory if it doesn't exist
                String screenshotsDir = "test-output/screenshots";
                Path screenshotsPath = Paths.get(screenshotsDir);
                if (!Files.exists(screenshotsPath)) {
                    Files.createDirectories(screenshotsPath);
                }
                
                // Save screenshot with timestamp
                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String screenshotPath = screenshotsDir + "/" + screenshotName + "_" + timestamp + ".png";
                File destinationFile = new File(screenshotPath);
                
                // Copy file to destination
                Files.copy(screenshotFile.toPath(), destinationFile.toPath());
                
                // Add screenshot to report
                test.addScreenCaptureFromPath(screenshotPath, screenshotName);
                
            } catch (IOException e) {
                test.info("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("Extent Report generated at: " + reportPath);
        }
    }

    public static ExtentTest getTest() {
        return test;
    }
}
