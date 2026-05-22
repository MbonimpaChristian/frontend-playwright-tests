package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage {

    private final Page page;

    // Product / cart selectors
    private final String addToCartButton =
            "button:has-text('Add to Cart'), " +
                    "button:has-text('Add'), " +
                    "button:has-text('Cart')";

    private final String cartButton =
            "a:has-text('Cart'), " +
                    "button:has-text('Cart'), " +
                    "a[href*='cart'], " +
                    "[href*='cart']";

    private final String productContainer =
            "div:has(button:has-text('Add')), " +
                    "div:has(button:has-text('Add to Cart')), " +
                    "div:has-text('RWF'), " +
                    "div:has-text('Frw')";

    public ProductsPage(Page page) {
        this.page = page;
    }

    // Page/body debug methods
    public Locator getPageBody() {
        return page.locator("body");
    }

    public String getPageText() {
        return page.locator("body").innerText();
    }

    public int getImageCount() {
        return page.locator("img").count();
    }

    public int getButtonCount() {
        return page.locator("button").count();
    }

    public int getLinkCount() {
        return page.locator("a").count();
    }

    public void printDebugInfo() {
        System.out.println("CURRENT URL: " + page.url());
        System.out.println("PAGE TITLE: " + page.title());
        System.out.println("IMAGE COUNT: " + getImageCount());
        System.out.println("BUTTON COUNT: " + getButtonCount());
        System.out.println("LINK COUNT: " + getLinkCount());
        System.out.println("PAGE TEXT:");
        System.out.println(getPageText());
    }

    // Product methods
    public Locator getFirstProductContainer() {
        return page.locator(productContainer).first();
    }

    public int getProductContainerCount() {
        return page.locator(productContainer).count();
    }

    // Add to cart methods
    public Locator getFirstAddToCartButton() {
        return page.locator(addToCartButton).first();
    }

    public int getAddToCartButtonCount() {
        return page.locator(addToCartButton).count();
    }

    public void clickFirstAddToCartButton() {
        page.locator(addToCartButton).first().click();
    }

    // Cart methods
    public Locator getCartButton() {
        return page.locator(cartButton).first();
    }

    public void openCart() {
        page.locator(cartButton).first().click();
        page.waitForLoadState();
    }
}