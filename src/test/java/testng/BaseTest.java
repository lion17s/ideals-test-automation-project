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
    @BeforeMethod
    public void setupEnv(@Optional(value = "desktop.chrome") String env) {
        Environment.setEnvironment(env);
    }

    @BeforeMethod(dependsOnMethods = "setupEnv")
    public void setupDriver() {
        String driver = Environment.get("driver");
        Map<String, Object> capabilities = Environment.getOrDefault(driver, new HashMap<>());
        DriverFactory.setDriver(driver, capabilities);
    }

    @AfterMethod
    public void quitDriver() {
        DriverFactory.quitDriver();
    }

}
