package ta.core.testng.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.*;

import ta.core.reporting.allure.ReportingHelper;
import ta.core.driver.DriverFactory;
import ta.core.env.Environment;

import java.util.UUID;

@Log4j2
public class TestListener implements ITestListener, ISuiteListener {

    private boolean shouldAlwaysAttachScreenshot;
    private boolean shouldAttachScreenshot;

    @Override
    public void onStart(ISuite suite) {
        log.info("{} execution started", suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("{} STARTED", result.getMethod().getMethodName());
        if (DriverFactory.getDriver() != null) {
            shouldAlwaysAttachScreenshot = Environment
                    .getOrDefault("alwaysAttachScreenshot", false);
            shouldAttachScreenshot = Environment
                    .getOrDefault("attachScreenshot", shouldAlwaysAttachScreenshot);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("{} PASSED", result.getMethod().getMethodName());
        ReportingHelper
                .attachScreenshot(DriverFactory.getDriver(),
                        "screenshot-" + UUID.randomUUID(), shouldAlwaysAttachScreenshot);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("{} FAILED", result.getMethod().getMethodName());
        ReportingHelper
                .attachScreenshot(DriverFactory.getDriver(),
                        "screenshot-" + UUID.randomUUID(), shouldAttachScreenshot);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("{} SKIPPED", result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("{} execution finished", suite.getName());
    }

}
