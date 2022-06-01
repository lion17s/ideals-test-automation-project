package ta.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ta.core.testng.listeners.DriverEventListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

@Log4j2
@SuppressWarnings("unused")
public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER_INSTANCE = new ThreadLocal<>();

    private static String getPlatformNameFromCapabilities(Map<String, Object> capabilities) {
        return capabilities.getOrDefault("platformName", "").toString();
    }

    @SneakyThrows
    private static URL getURLFromCapabilities(Map<String, Object> capabilities) {
        return new URL(capabilities.getOrDefault("hub", "").toString());
    }

    private static EventFiringWebDriver registerEventFiringDriver(WebDriver driver) {
        log.debug("registering firing event driver");
        var eventFiringWebDriver = new EventFiringWebDriver(driver);
        var driverEventListener = new DriverEventListener();
        eventFiringWebDriver.register(driverEventListener);
        log.debug("event firing driver registered");
        return eventFiringWebDriver;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static WebDriver initWebDriver(String driverName, Map<String, Object> capabilities) {
        WebDriver driver;
        if (driverName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            var chromeOptions = new ChromeOptions();
            capabilities.forEach(chromeOptions::setCapability);
            chromeOptions.addArguments((ArrayList) capabilities.getOrDefault("arguments", new ArrayList<>()));
            driver = new ChromeDriver(chromeOptions);
            log.debug("chrome driver initialized with options:\n{}", chromeOptions);
            return registerEventFiringDriver(driver);
        } else if (driverName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            var firefoxOptions = new FirefoxOptions();
            capabilities.forEach(firefoxOptions::setCapability);
            firefoxOptions.addArguments((ArrayList) capabilities.getOrDefault("arguments", new ArrayList<>()));
            driver = new FirefoxDriver(firefoxOptions);
            log.debug("firefox driver initialized with options:\n{}", firefoxOptions);
            return registerEventFiringDriver(driver);
        } else if (driverName.equalsIgnoreCase("safari")) {
            var safariOptions = new SafariOptions();
            capabilities.forEach(safariOptions::setCapability);
            driver = new SafariDriver(safariOptions);
            log.debug("safari driver initialized with options:\n{}", safariOptions);
            return registerEventFiringDriver(driver);
        } else if (driverName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            var edgeOptions = new EdgeOptions();
            capabilities.forEach(edgeOptions::setCapability);
            driver = new EdgeDriver(edgeOptions);
            log.debug("edge driver initialized with options:\n{}", edgeOptions);
            return registerEventFiringDriver(driver);
        } else {
            throw new ExceptionInInitializerError("missing <driver> capability");
        }
    }

    public static void setDriver(String driverName, Map<String, Object> capabilities) {
        WebDriver driver = null;
        switch (driverName.toLowerCase()) {
            case "chrome":
            case "firefox":
            case "safari":
            case "edge": {
                driver = initWebDriver(driverName, capabilities);
                break;
            }
        }
        DRIVER_INSTANCE.set(driver);
    }

    public static WebDriver getDriver() {
        return DRIVER_INSTANCE.get();
    }

    public static WebDriverWait getDriverWait(int timeOutInSeconds) {
        return new WebDriverWait(DRIVER_INSTANCE.get(), timeOutInSeconds);
    }

    public static void quitDriver() {
        if (DRIVER_INSTANCE.get() != null) {
            DRIVER_INSTANCE.get().quit();
            DRIVER_INSTANCE.remove();
            log.debug("driver quit");
        }
    }

}
