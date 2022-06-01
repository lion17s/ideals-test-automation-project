package com.automationpractice.ui.pages;

import com.automationpractice.ui.pages.elements.Header;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ta.core.ui.BaseUIElement;

public class AccPagePage extends BaseUIElement {

    private Header header;

    public AccPagePage() {
        header = new Header();
    }

    @FindBy(xpath = "//a[@title='View my customer account']/span")
    private WebElement userAccountLink;

    @Step
    public SearchPage searchForProduct(String productName) {
        header
                .setSearchProduct(productName)
                .clickOnSubmitSearchButton();
        return new SearchPage();
    }

    public Header hoverProductCategory(String categoryName) {
        return header
                .hoverProductCategory(categoryName);
    }

    @Step
    public void verifyUserAccountName(final String expectedName) {
        String actualName = userAccountLink.getText();
        Assertions.assertThat(actualName.trim()).isEqualToIgnoringCase(expectedName);
    }

}
