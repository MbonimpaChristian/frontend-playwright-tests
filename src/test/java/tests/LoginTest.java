package tests;

import Base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void adminShouldLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.openHomePage();
        loginPage.openLoginForm();
        loginPage.loginAsAdmin();

        PlaywrightAssertions.assertThat(page).not()
                .hasURL(ConfigReader.get("home.url"));
    }
@Test
public void adminShouldNotLoginWithWrongEmail() {
    LoginPage loginPage = new LoginPage(page);

    loginPage.openHomePage();
    loginPage.openLoginForm();

    loginPage.login("wrongemail@example.com", ConfigReader.get("admin.password"));

    PlaywrightAssertions.assertThat(page)
            .hasURL(ConfigReader.get("login.url"));
}
@Test
public void adminShouldNotLoginWithWrongPassword() {
    LoginPage loginPage = new LoginPage(page);

    loginPage.openHomePage();
    loginPage.openLoginForm();

    loginPage.login(ConfigReader.get("admin.email"), "WrongPassword@123");

    PlaywrightAssertions.assertThat(page)
            .hasURL(ConfigReader.get("login.url"));
}
    @Test
    public void adminShouldNotLoginWithEmptyEmail() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.openHomePage();
        loginPage.openLoginForm();

        loginPage.login("", ConfigReader.get("admin.password"));

        PlaywrightAssertions.assertThat(loginPage.getEmailInput()).isVisible();
    }

    @Test
    public void adminShouldNotLoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.openHomePage();
        loginPage.openLoginForm();

        loginPage.login(ConfigReader.get("admin.email"), "");

        PlaywrightAssertions.assertThat(loginPage.getPasswordInput()).isVisible();
    }

    @Test
    public void adminShouldNotLoginWithEmptyEmailAndEmptyPassword() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.openHomePage();
        loginPage.openLoginForm();

        loginPage.login("", "");

        PlaywrightAssertions.assertThat(loginPage.getEmailInput()).isVisible();
        PlaywrightAssertions.assertThat(loginPage.getPasswordInput()).isVisible();
    }
}