package com.automationpractice.ui.pages.elements;

import com.automationpractice.ui.pages.AuthPage;
import com.automationpractice.ui.pages.HomePage;
import com.automationpractice.ui.pages.SearchPage;
import com.automationpractice.ui.pages.ShoppingCartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ta.core.driver.DriverFactory;
import ta.core.ui.BaseUIElement;

public class Header extends BaseUIElement {

    private Actions actions;

    public Header() {
        actions = new Actions(DriverFactory.getDriver());
    }

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInLink;

    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logOutLink;

    @FindBy(xpath = "//a[@title='My Store']")
    private WebElement logoLink;

    @FindBy(id = "search_query_top")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@name='submit_search']")
    private WebElement submitSearchButton;

    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    private WebElement shoppingCartLink;

    @FindBy(id = "block_top_menu")
    private WebElement topMenuContainer;

    @Step
    public AuthPage clickOnSignInLink() {
        signInLink.click();
        return new AuthPage();
    }

    @Step
    public Header clickOnStoreLogoLink() {
        logoLink.click();
        return this;
    }

    @Step
    public HomePage clickOnLogOutLink() {
        logOutLink.click();
        return new HomePage();
    }

    @Step
    public Header setSearchProduct(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
        return this;
    }

    @Step
    public SearchPage clickOnSubmitSearchButton() {
        submitSearchButton.click();
        return new SearchPage();
    }

    @Step
    public ShoppingCartPage clickOnShoppingCartLink() {
        shoppingCartLink.click();
        return new ShoppingCartPage();
    }

    @Step
    public Header hoverProductCategory(final String categoryName) {
        actions.moveToElement(topMenuContainer.findElement(By.xpath("//a[@title='" + categoryName + "']")))
                .build().perform();
        return this;
    }

    @Step
    public SearchPage choose() {
        actions.click().build().perform();
        return new SearchPage();
    }

}
