package base;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriver {

    private static WebDriver driver;
    private static ChromeOptions options;
    

    // Initialize WebDriver
    public static WebDriver initializeDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                   
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.managed_default_content_settings.images", 2); // block images
                prefs.put("profile.managed_default_content_settings.ads", 2);    // block ads

                options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    // Get the current WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initializeDriver() first.");
        }
        return driver;
    }

    // Quit the driver and clean up
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
