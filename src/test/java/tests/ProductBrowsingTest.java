package tests;

import Base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import utils.ConfigReader;

public class ProductBrowsingTest extends BaseTest {

    @Test
    public void adminShouldAccessHomePageAfterLogin() {
        loginAsAdminFromHomePage();

        System.out.println("Current URL after login: " + page.url());

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));
    }

    @Test
    public void userShouldNavigateToHomePageFromHomePage() {
        HomePage homePage = new HomePage(page);

        homePage.openHomePage();

        PlaywrightAssertions.assertThat(homePage.getStartShoppingButton())
                .isVisible();

        homePage.clickStartShopping();

        System.out.println("Current URL after Start Shopping: " + page.url());

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));
    }

    @Test
    public void homePageShouldDisplayContentAfterStartShopping() {
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);

        homePage.openHomePage();
        homePage.clickStartShopping();

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));

        productsPage.printDebugInfo();

        PlaywrightAssertions.assertThat(productsPage.getPageBody())
                .isVisible();
    }

    @Test
    public void homePageShouldDisplayContentAfterAdminLogin() {
        loginAsAdminFromHomePage();

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));

        ProductsPage productsPage = new ProductsPage(page);

        productsPage.printDebugInfo();

        PlaywrightAssertions.assertThat(productsPage.getPageBody())
                .isVisible();
    }
}
