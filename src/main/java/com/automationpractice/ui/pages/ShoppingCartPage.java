package com.automationpractice.ui.pages;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ta.core.ui.BaseUIElement;

public class ShoppingCartPage extends BaseUIElement {

    @FindAll
    ({@FindBy(xpath = "//p//button[@type='submit']"), @FindBy(xpath = "//p//a[@title='Proceed to checkout']")})
    private WebElement proceedToCheckoutLink;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement termsCheckBox;

    @FindBy(xpath = "//a[@title='Pay by bank wire']")
    private WebElement bankWireLink;

    @FindBy(xpath = "//p[@class='cheque-indent']/strong")
    private WebElement indentHolder;

    @Step
    public ShoppingCartPage clickOnProceedToCheckoutButton() {
        proceedToCheckoutLink.click();
        return this;
    }

    @Step
    public ShoppingCartPage acceptServiceTerms() {
        termsCheckBox.click();
        return this;
    }

    @Step
    public ShoppingCartPage clickOnPayByBankWireLink() {
        bankWireLink.click();
        return this;
    }

    @Step
    public ShoppingCartPage confirmOrder() {
        proceedToCheckoutLink.click();
        return this;
    }

    @Step
    public ShoppingCartPage buyProduct() {
        clickOnProceedToCheckoutButton();
        clickOnProceedToCheckoutButton();
        acceptServiceTerms();
        clickOnProceedToCheckoutButton();
        clickOnPayByBankWireLink();
        confirmOrder();
        return this;
    }

    @Step
    public void verifyOrderComplete(String expectedMessage) {
        String actualMessage = indentHolder.getText().trim();
        Assertions.assertThat(actualMessage).containsIgnoringCase(expectedMessage);
    }

}
