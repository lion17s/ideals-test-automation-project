package ta.core.reporting.allure;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

@Log4j2
public class ReportingHelper {

    public static void attachScreenshot(WebDriver driver, String name, boolean shouldAttach) {
        if (driver != null && shouldAttach) {
            var screenshotByteArray = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            log.debug("attaching screenshot");
            Allure.addAttachment(name, new ByteArrayInputStream(screenshotByteArray));
            log.debug("screenshot attached");
        }
    }

}
