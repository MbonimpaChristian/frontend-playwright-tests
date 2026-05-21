package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ConfigReader;

public class HomePage {

    private final Page page;

    private final String startShoppingButton = "text=Start Shopping";
    private final String signInButton = "text=Sign in";

    public HomePage(Page page) {
        this.page = page;
    }

    public void openHomePage() {
        page.navigate(ConfigReader.get("frontend.url"));
        page.waitForLoadState();
    }

    public void clickStartShopping() {
        page.locator(startShoppingButton).click();
        page.waitForLoadState();
    }

    public Locator getStartShoppingButton() {
        return page.locator(startShoppingButton);
    }

    public void clickSignIn() {
        page.locator(signInButton).click();
        page.waitForLoadState();
    }

    public Locator getSignInButton() {
        return page.locator(signInButton);
    }
}