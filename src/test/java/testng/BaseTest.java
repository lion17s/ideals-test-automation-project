package testng;

import org.testng.annotations.*;
import ta.core.driver.DriverFactory;
import ta.core.env.Environment;
import ta.core.testng.listeners.TestListener;

import java.util.HashMap;
import java.util.Map;

@Listeners(TestListener.class)
public abstract class BaseTest {

    @Parameters("env")
    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment(@Optional(value = "desktop.chrome") String env) {
        Environment.setEnvironment(env);
    }

    @BeforeMethod
    public void setupDriver() {
        String driver = Environment.getCurrentEnvironment().get("driver");
        Map<String, Object> capabilities = Environment.getCurrentEnvironment().getOrDefault(driver, new HashMap<>());
        DriverFactory.setDriver(driver, capabilities);

    }

    @AfterMethod
    public void quitDriver() {
        DriverFactory.quitDriver();
    }

}
