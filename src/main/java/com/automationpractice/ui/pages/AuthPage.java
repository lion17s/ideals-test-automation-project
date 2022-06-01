package com.automationpractice.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ta.core.ui.BaseUIElement;

public class AuthPage extends BaseUIElement {

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    @Step
    public AuthPage setEmail(final String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    @Step
    public AuthPage setPassword(final String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step
    public AuthPage clickOnSignInButton() {
        signInButton.click();
        return this;
    }

}
