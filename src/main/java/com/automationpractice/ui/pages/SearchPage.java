package com.automationpractice.ui.pages;

import com.automationpractice.ui.pages.elements.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ta.core.driver.DriverFactory;
import ta.core.ui.BaseUIElement;

import java.util.List;

public class SearchPage extends BaseUIElement {

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> productContainerList;

    @FindBy(xpath = "//a[@title='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@title='Close window']")
    private WebElement closeProductInfoButton;

    public SearchPage addProductToBasket(String productName) {
        productContainerList.forEach(productContainer -> {
            boolean containsProductName = productContainer
                    .findElement(By.xpath(".//a[@class='product_img_link']"))
                    .getAttribute("title").toLowerCase().contains(productName.toLowerCase());
            if (containsProductName) {
                Actions actions = new Actions(DriverFactory.getDriver());
                actions.moveToElement(productContainer).build().perform();
                addToCartButton.click();
                DriverFactory.getDriverWait(5).until(ExpectedConditions.elementToBeClickable(closeProductInfoButton)).click();
            }
        });
        return this;
    }

    @Step
    public ShoppingCartPage proceedToBasket() {
        return new Header()
                .clickOnShoppingCartLink();
    }


}
