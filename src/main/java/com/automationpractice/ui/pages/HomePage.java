package com.automationpractice.ui.pages;

import com.automationpractice.ui.pages.elements.Header;
import io.qameta.allure.Step;
import ta.core.driver.DriverFactory;
import ta.core.env.Environment;
import ta.core.ui.BaseUIElement;

public class HomePage extends BaseUIElement {

    @Step
    public HomePage openHomePage() {
        DriverFactory.getDriver().get(Environment.get("home.page.url"));
        return new HomePage();
    }

    @Step
    public AccPagePage signIn(final String email, final String password) {
        new Header()
                .clickOnSignInLink()
                .setEmail(email)
                .setPassword(password)
                .clickOnSignInButton();
        return new AccPagePage();
    }

}
