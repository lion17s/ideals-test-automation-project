package testng.com.automationpractice;

import com.automationpractice.ui.pages.HomePage;
import org.testng.annotations.Test;
import testng.BaseTest;

public class BasketTests extends BaseTest {

    @Test
    public void verifyUserCanBuyProductUsingRandomBasket() {
        new HomePage()
                .openHomePage()
                .signIn("john.deer@gmail.com", "qwerty")
                .searchForProduct("blouse")
                .addProductToBasket("blouse")
                .proceedToBasket()
                .buyProduct()
                .verifyOrderComplete("Your order on My Store is complete.");
    }

    @Test
    public void verifyUserCanBuyProductUsingBasketPath() {
        new HomePage()
                .openHomePage()
                .signIn("john.deer@gmail.com", "qwerty")
                .hoverProductCategory("Women")
                .hoverProductCategory("T-shirts")
                .choose()
                .addProductToBasket("Short Sleeve")
                .proceedToBasket()
                .buyProduct()
                .verifyOrderComplete("Your order on My Store is complete.");
    }

}
