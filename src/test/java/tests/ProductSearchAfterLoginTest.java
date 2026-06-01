package tests;

import Base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class ProductSearchAfterLoginTest extends BaseTest {

    @Test
    public void adminShouldLoginThenOpenSearchBox() {
        LoginPage loginPage = new LoginPage(page);
        ProductsPage productsPage = new ProductsPage(page);

        loginPage.openLoginPage();

        System.out.println("URL BEFORE LOGIN: " + page.url());
        System.out.println("PAGE TEXT BEFORE LOGIN:");
        System.out.println(page.locator("body").innerText());

        loginPage.loginAsAdmin();

        page.waitForTimeout(5000);

        System.out.println("URL AFTER LOGIN: " + page.url());
        System.out.println("PAGE TEXT AFTER LOGIN:");
        System.out.println(page.locator("body").innerText());

        page.waitForURL(
                ConfigReader.get("home.url"),
                new Page.WaitForURLOptions().setTimeout(10000)
        );

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));

        productsPage.clickSearchIconButton();

        PlaywrightAssertions.assertThat(productsPage.getSearchInput())
                .isVisible();
    }

    @Test
    public void adminSearchInputShouldAcceptProductNameAfterLogin() {
        LoginPage loginPage = new LoginPage(page);
        ProductsPage productsPage = new ProductsPage(page);

        loginPage.openLoginPage();
        loginPage.loginAsAdmin();

        page.waitForURL(
                ConfigReader.get("home.url"),
                new Page.WaitForURLOptions().setTimeout(10000)
        );

        productsPage.clickSearchIconButton();

        PlaywrightAssertions.assertThat(productsPage.getSearchInput())
                .isVisible();

        productsPage.getSearchInput().fill("bag");

        Assert.assertEquals(
                productsPage.getSearchInputValue(),
                "bag",
                "Expected search input to contain typed product name"
        );
    }

    @Test
    public void adminShouldSearchForProductAfterLogin() {
        LoginPage loginPage = new LoginPage(page);
        ProductsPage productsPage = new ProductsPage(page);

        loginPage.openLoginPage();
        loginPage.loginAsAdmin();

        page.waitForURL(
                ConfigReader.get("home.url"),
                new Page.WaitForURLOptions().setTimeout(10000)
        );

        productsPage.searchProductWithoutEnter("bag");

        Assert.assertTrue(
                productsPage.getSearchInputValue().contains("bag"),
                "Expected search input to contain searched keyword"
        );
    }
}