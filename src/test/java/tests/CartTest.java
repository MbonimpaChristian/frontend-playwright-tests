package tests;

import Base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utils.ConfigReader;

public class CartTest extends BaseTest {

    @Test
    public void adminShouldSeeAddToCartButtonAfterLogin() {
        loginAsAdminFromHomePage();

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));

        ProductsPage productsPage = new ProductsPage(page);

        productsPage.printDebugInfo();

        PlaywrightAssertions.assertThat(productsPage.getFirstAddToCartButton())
                .isVisible();

        Assert.assertTrue(
                productsPage.getAddToCartButtonCount() > 0,
                "Expected at least one Add to Cart button to be visible"
        );
    }

    @Test
    public void adminShouldAddFirstProductToCart() {
        loginAsAdminFromHomePage();

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));

        ProductsPage productsPage = new ProductsPage(page);

        PlaywrightAssertions.assertThat(productsPage.getFirstAddToCartButton())
                .isVisible();

        productsPage.clickFirstAddToCartButton();

        page.waitForTimeout(2000);

        System.out.println("PAGE TEXT AFTER ADDING TO CART:");
        System.out.println(productsPage.getPageText());

        PlaywrightAssertions.assertThat(productsPage.getCartButton())
                .isVisible();
        page.waitForTimeout(15000);
    }
}