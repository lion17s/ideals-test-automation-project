package ta.core.ui;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import ta.core.driver.DriverFactory;
import ta.core.env.Environment;

public abstract class BaseUIElement {

    private static final int WAIT_TIME_SECONDS = Environment.getOrDefault("driverWait", 3);

    public BaseUIElement() {
        PageFactory.initElements(new AjaxElementLocatorFactory(
                DriverFactory.getDriver(), WAIT_TIME_SECONDS), this);
    }

}
