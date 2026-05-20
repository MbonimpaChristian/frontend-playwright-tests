//package pages;
//
//import com.microsoft.playwright.Page;
//import utils.ConfigReader;
//
//public class LoginPage {
//
//    private final Page page;
//
//    private final String signInButton = "text=Sign in";
//    private final String emailInput = "input[name='email'], input[placeholder*='Email'], input[type='email']";
//    private final String passwordInput = "input[name='password'], input[placeholder*='Password'], input[type='password']";
//    private final String loginButton = "button[type='submit'], button:has-text('Sign in'), button:has-text('Login')";
//
//    public LoginPage(Page page) {
//        this.page = page;
//    }
//
//    public void openHomePage() {
//        page.navigate(ConfigReader.get("frontend.url"));
//        page.waitForLoadState();
//    }
//
//    public void openLoginForm() {
//        page.locator(signInButton).click();
//        page.locator(emailInput).first().waitFor();
//    }
//
//    public void enterEmail(String email) {
//        page.locator(emailInput).first().fill(email);
//    }
//
//    public void enterPassword(String password) {
//        page.locator(passwordInput).first().fill(password);
//    }
//
//    public void clickLoginButton() {
//        page.locator(loginButton).first().click();
//    }
//
//    public void loginAsAdmin() {
//        openLoginForm();
//        enterEmail(ConfigReader.get("admin.email"));
//        enterPassword(ConfigReader.get("admin.password"));
//        clickLoginButton();
//    }
//}

package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ConfigReader;

public class LoginPage {

    private final Page page;

    private final String signInButton = "text=Sign in";

    private final String emailInput =
            "input[name='email'], input[placeholder*='Email'], input[type='email']";

    private final String passwordInput =
            "input[name='password'], input[placeholder*='Password'], input[type='password']";

    private final String loginButton =
            "button[type='submit'], button:has-text('Sign in'), button:has-text('Login')";


    private final String errorMessage =
            "text=Invalid, text=incorrect, text=required, text=Wrong, text=failed";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void openHomePage() {
        page.navigate(ConfigReader.get("frontend.url"));
        page.waitForLoadState();
    }

    public void openLoginForm() {
        page.locator(signInButton).click();
        page.locator(emailInput).first().waitFor();
    }

    public void enterEmail(String email) {
        page.locator(emailInput).first().fill(email);
    }

    public void enterPassword(String password) {
        page.locator(passwordInput).first().fill(password);
    }

    public void clickLoginButton() {
        page.locator(loginButton).first().click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void loginAsAdmin() {
        login(
                ConfigReader.get("admin.email"),
                ConfigReader.get("admin.password")
        );
    }

    public Locator getErrorMessage() {
        return page.locator(errorMessage).first();
    }

    public Locator getEmailInput() {
        return page.locator(emailInput).first();
    }

    public Locator getPasswordInput() {
        return page.locator(passwordInput).first();
    }
}