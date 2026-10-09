
package base;

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
                    prefs.put("profile.managed_default_content_settings.images", 2);
                    prefs.put("profile.managed_default_content_settings.ads", 2);

                    options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);

                    // Preserve CI behavior and support terminal headless mode
                    boolean headless =
                            Boolean.parseBoolean(System.getenv("CI"))
                                    || Boolean.parseBoolean(System.getProperty("headless", "false"));

                    if (headless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(options);
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser);
            }

            // Preserve existing window behavior
            if (!"true".equalsIgnoreCase(System.getenv("CI"))) {
                if (!Boolean.parseBoolean(
                        System.getProperty("headless", "false"))) {
                    driver.manage().window().maximize();
                }
            }
        }

        return driver;
    }

    // Get the current WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException(
                    "Driver not initialized. Call initializeDriver() first.");
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
