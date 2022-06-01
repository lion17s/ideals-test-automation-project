package testng.com.automationpractice;

import com.automationpractice.ui.pages.HomePage;
import org.testng.annotations.Test;
import testng.BaseTest;

public class AuthTests extends BaseTest {

    @Test
    public void verifyUserCanLogIn() {
        new HomePage()
                .openHomePage()
                .signIn("john.deer@gmail.com", "qwerty")
                .verifyUserAccountName("John Deer");
    }

}
