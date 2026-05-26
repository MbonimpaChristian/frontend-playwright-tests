package tests;

import Base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import utils.ConfigReader;

public class CategoryNavigationTest extends BaseTest {

    @Test
    public void userShouldSeeAllMainCategoriesOnShopHomePage() {
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);

        homePage.openHomePage();
        homePage.clickStartShopping();

        PlaywrightAssertions.assertThat(page)
                .hasURL(ConfigReader.get("home.url"));

        PlaywrightAssertions.assertThat(productsPage.getWomenFashionCategory())
                .isVisible();

        PlaywrightAssertions.assertThat(productsPage.getMenFashionCategory())
                .isVisible();

        PlaywrightAssertions.assertThat(productsPage.getElectronicsCategory())
                .isVisible();

        PlaywrightAssertions.assertThat(productsPage.getHomeLivingCategory())
                .isVisible();

        PlaywrightAssertions.assertThat(productsPage.getFlashSalesCategory())
                .isVisible();
    }

    @Test
    public void userShouldNavigateToWomenFashionCategory() {
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);

        homePage.openHomePage();
        homePage.clickStartShopping();

        productsPage.clickWomenFashionCategory();

        System.out.println("URL AFTER CLICKING WOMEN'S FASHION: " + page.url());
        System.out.println("PAGE TEXT:");
        System.out.println(productsPage.getPageText());

        Assert.assertTrue(
                productsPage.getPageText().length() > 0,
                "Expected page to display content after clicking Women's Fashion category"
        );
    }

    @Test
    public void userShouldNavigateToMenFashionCategory() {
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);

        homePage.openHomePage();
        homePage.clickStartShopping();

        productsPage.clickMenFashionCategory();

        System.out.println("URL AFTER CLICKING MEN'S FASHION: " + page.url());
        System.out.println("PAGE TEXT:");
        System.out.println(productsPage.getPageText());

        Assert.assertTrue(
                productsPage.getPageText().length() > 0,
                "Expected page to display content after clicking Men's Fashion category"
        );
    }

    @Test
    public void userShouldNavigateToElectronicsCategory() {
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);

        homePage.openHomePage();
        homePage.clickStartShopping();

        productsPage.clickElectronicsCategory();

        System.out.println("URL AFTER CLICKING ELECTRONICS: " + page.url());
        System.out.println("PAGE TEXT:");
        System.out.println(productsPage.getPageText());

        Assert.assertTrue(
                productsPage.getPageText().length() > 0,
                "Expected page to display content after clicking Electronics category"
        );
    }

    @Test
    public void userShouldNavigateToHomeLivingCategory() {
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);

        homePage.openHomePage();
        homePage.clickStartShopping();

        productsPage.clickHomeLivingCategory();

        System.out.println("URL AFTER CLICKING HOME & LIVING: " + page.url());
        System.out.println("PAGE TEXT:");
        System.out.println(productsPage.getPageText());

        Assert.assertTrue(
                productsPage.getPageText().length() > 0,
                "Expected page to display content after clicking Home & Living category"
        );
    }

    @Test
    public void userShouldNavigateToFlashSalesCategory() {
        HomePage homePage = new HomePage(page);
        ProductsPage productsPage = new ProductsPage(page);

        homePage.openHomePage();
        homePage.clickStartShopping();

        productsPage.clickFlashSalesCategory();

        System.out.println("URL AFTER CLICKING FLASH SALES: " + page.url());
        System.out.println("PAGE TEXT:");
        System.out.println(productsPage.getPageText());

        Assert.assertTrue(
                productsPage.getPageText().length() > 0,
                "Expected page to display content after clicking Flash Sales category"
        );
    }
}