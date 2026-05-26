//package pages;
//
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.Page;
//import utils.ConfigReader;
//
//public class LoginPage {
//
//    private final Page page;
//
//    private final String signInButton = "text=Sign in";
//
//    private final String emailInput =
//            "input[name='email'], input[placeholder*='Email'], input[type='email']";
//
//    private final String passwordInput =
//            "input[name='password'], input[placeholder*='Password'], input[type='password']";
//
//    private final String loginButton =
//            "button[type='submit'], button:has-text('Sign in'), button:has-text('Login')";
//
//
//    private final String errorMessage =
//            "text=Invalid, text=incorrect, text=required, text=Wrong, text=failed";
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
//    public void login(String email, String password) {
//        enterEmail(email);
//        enterPassword(password);
//        clickLoginButton();
//    }
//
//    public void loginAsAdmin() {
//        login(
//                ConfigReader.get("admin.email"),
//                ConfigReader.get("admin.password")
//        );
//    }
//
//    public Locator getErrorMessage() {
//        return page.locator(errorMessage).first();
//    }
//
//    public Locator getEmailInput() {
//        return page.locator(emailInput).first();
//    }
//
//    public Locator getPasswordInput() {
//        return page.locator(passwordInput).first();
//    }
//}

//package pages;
//
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.Page;
//import utils.ConfigReader;
//
//public class LoginPage {
//
//    private final Page page;
//
//    private final String emailInput =
//            "input[type='email'], input[placeholder='you@example.com']";
//
//    private final String passwordInput =
//            "input[type='password']";
//
//    private final String loginButton =
//            "button[type='submit']";
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
//    public void openLoginPage() {
//        page.navigate(ConfigReader.get("login.url"));
//        page.waitForLoadState();
//    }
//
//    public void openLoginForm() {
//        page.getByText("Sign in").first().click();
//        page.waitForLoadState();
//    }
//
//    public Locator getEmailInput() {
//        return page.locator(emailInput).first();
//    }
//
//    public Locator getPasswordInput() {
//        return page.locator(passwordInput).first();
//    }
//
//    public Locator getLoginButton() {
//        return page.locator(loginButton).first();
//    }
//
//    public void enterEmail(String email) {
//        getEmailInput().fill(email);
//    }
//
//    public void enterPassword(String password) {
//        getPasswordInput().fill(password);
//    }
//
//    public void clickLoginButton() {
//        getLoginButton().click();
//    }
//
//    public void login(String email, String password) {
//        enterEmail(email);
//        enterPassword(password);
//
//        System.out.println("EMAIL VALUE BEFORE SUBMIT: " + getEmailInput().inputValue());
//        System.out.println("PASSWORD VALUE BEFORE SUBMIT: " + getPasswordInput().inputValue());
//        System.out.println("LOGIN BUTTON DISABLED: " + getLoginButton().isDisabled());
//
//        getLoginButton().click();
//    }
//
//    public void loginAsAdmin() {
//        login(
//                ConfigReader.get("admin.email"),
//                ConfigReader.get("admin.password")
//        );
//    }
//}

package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ConfigReader;

public class LoginPage {

    private final Page page;

    private final String signInLink = "text=Sign in";

    private final String emailInput =
            "input[type='email'], " +
                    "input[name='email'], " +
                    "input[placeholder='you@example.com']";

    private final String passwordInput =
            "input[type='password'], " +
                    "input[name='password']";

    private final String loginButton =
            "button[type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void openHomePage() {
        page.navigate(ConfigReader.get("frontend.url"));
        page.waitForLoadState();
    }

    public void openLoginPage() {
        page.navigate(ConfigReader.get("login.url"));
        page.waitForLoadState();
    }

    public void openLoginForm() {
        page.locator(signInLink).first().click();
        page.waitForLoadState();
    }

    public Locator getEmailInput() {
        return page.locator(emailInput).first();
    }

    public Locator getPasswordInput() {
        return page.locator(passwordInput).first();
    }

    public Locator getLoginButton() {
        return page.locator(loginButton).first();
    }

    public void enterEmail(String email) {
        getEmailInput().waitFor();
        getEmailInput().clear();
        getEmailInput().fill(email);
    }

    public void enterPassword(String password) {
        getPasswordInput().waitFor();
        getPasswordInput().clear();
        getPasswordInput().fill(password);
    }

    public void clickLoginButton() {
        getLoginButton().waitFor();

        System.out.println("LOGIN BUTTON ENABLED: " + getLoginButton().isEnabled());

        getLoginButton().click();
        page.waitForTimeout(3000);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);

        System.out.println("EMAIL VALUE: " + getEmailInput().inputValue());
        System.out.println("PASSWORD VALUE LENGTH: " + getPasswordInput().inputValue().length());

        clickLoginButton();
    }

    public void loginAsAdmin() {
        login(
                ConfigReader.get("admin.email"),
                ConfigReader.get("admin.password")
        );
    }
}