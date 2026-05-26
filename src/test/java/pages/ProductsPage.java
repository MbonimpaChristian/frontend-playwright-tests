package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage {

    private final Page page;

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
private final String searchIconButton =
        "button:has(svg.lucide-search), button:has(.lucide-search)";

    private final String searchInput =
            "input[placeholder*='Search products'], " +
                    "input[placeholder*='Search'], " +
                    "input[placeholder*='search'], " +
                    "input[type='search'], " +
                    "input[type='text']";

    private final String womenFashionCategory =
            "text=Women's Fashion";

    private final String menFashionCategory =
            "text=Men's Fashion";

    private final String electronicsCategory =
            "text=Electronics";

    private final String homeLivingCategory =
            "text=Home & Living";

    private final String flashSalesCategory =
            "text=Flash Sales";

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

    public int getInputCount() {
        return page.locator("input").count();
    }

    public void printDebugInfo() {
        System.out.println("CURRENT URL: " + page.url());
        System.out.println("PAGE TITLE: " + page.title());
        System.out.println("IMAGE COUNT: " + getImageCount());
        System.out.println("BUTTON COUNT: " + getButtonCount());
        System.out.println("LINK COUNT: " + getLinkCount());
        System.out.println("INPUT COUNT: " + getInputCount());
        System.out.println("PAGE TEXT:");
        System.out.println(getPageText());
    }

    public void printAllButtonsText() {
        int count = page.locator("button").count();

        System.out.println("TOTAL BUTTONS: " + count);

        for (int i = 0; i < count; i++) {
            String text = page.locator("button").nth(i).innerText();
            System.out.println("BUTTON " + i + " TEXT: [" + text + "]");
        }
    }

    public void printAllButtonsHtml() {
        int count = page.locator("button").count();

        System.out.println("TOTAL BUTTONS HTML: " + count);

        for (int i = 0; i < count; i++) {
            String html = page.locator("button").nth(i)
                    .evaluate("element => element.outerHTML")
                    .toString();

            System.out.println("BUTTON " + i + " HTML: " + html);
        }
    }

    public void printAllInputsHtml() {
        int count = page.locator("input").count();

        System.out.println("TOTAL INPUTS: " + count);

        for (int i = 0; i < count; i++) {
            String html = page.locator("input").nth(i)
                    .evaluate("element => element.outerHTML")
                    .toString();

            System.out.println("INPUT " + i + " HTML: " + html);
        }
    }

    public void printSearchDebugInfo() {
        System.out.println("========== SEARCH DEBUG INFO ==========");
        printDebugInfo();
        printAllButtonsText();
        printAllButtonsHtml();
        printAllInputsHtml();
        System.out.println("=======================================");
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
        page.waitForTimeout(1000);
    }


    // Cart methods


    public Locator getCartButton() {
        return page.locator(cartButton).first();
    }

    public void openCart() {
        page.locator(cartButton).first().click();
        page.waitForLoadState();
    }
    public Locator getSearchIconButton() {
        return page.locator(
                "button:has(svg), " +
                        "button[aria-label*='Search'], " +
                        "button[aria-label*='search'], " +
                        "svg.lucide-search, " +
                        ".lucide-search"
        ).first();
    }

    public void clickSearchIconButton() {
        page.locator(
                "button:has(svg), " +
                        "button[aria-label*='Search'], " +
                        "button[aria-label*='search'], " +
                        "svg.lucide-search, " +
                        ".lucide-search"
        ).first().click();

        page.waitForTimeout(1000);
    }
    public Locator getSearchInput() {
        return page.locator(searchInput).first();
    }

    public int getSearchInputCount() {
        return page.locator(searchInput).count();
    }

    public void searchProductWithoutEnter(String productName) {
        clickSearchIconButton();
        page.locator(searchInput).first().fill(productName);
        page.waitForTimeout(1000);
    }

    public void searchProduct(String productName) {
        clickSearchIconButton();
        page.locator(searchInput).first().fill(productName);
        page.keyboard().press("Enter");
        page.waitForTimeout(2000);
    }

    public String getSearchInputValue() {
        return page.locator(searchInput).first().inputValue();
    }

    public void clearSearchInput() {
        page.locator(searchInput).first().clear();
        page.waitForTimeout(1000);
    }


// Category navigation methods


    public Locator getWomenFashionCategory() {
        return page.locator(womenFashionCategory).first();
    }

    public Locator getMenFashionCategory() {
        return page.locator(menFashionCategory).first();
    }

    public Locator getElectronicsCategory() {
        return page.locator(electronicsCategory).first();
    }

    public Locator getHomeLivingCategory() {
        return page.locator(homeLivingCategory).first();
    }

    public Locator getFlashSalesCategory() {
        return page.locator(flashSalesCategory).first();
    }

    public void clickWomenFashionCategory() {
        page.locator(womenFashionCategory).first().click();
        page.waitForLoadState();
        page.waitForTimeout(10000);
    }

    public void clickMenFashionCategory() {
        page.locator(menFashionCategory).first().click();
        page.waitForLoadState();
        page.waitForTimeout(10000);
    }

    public void clickElectronicsCategory() {
        page.locator(electronicsCategory).first().click();
        page.waitForLoadState();
        page.waitForTimeout(10000);
    }

    public void clickHomeLivingCategory() {
        page.locator(homeLivingCategory).first().click();
        page.waitForLoadState();
        page.waitForTimeout(10000);
    }

    public void clickFlashSalesCategory() {
        page.locator(flashSalesCategory).first().click();
        page.waitForLoadState();
        page.waitForTimeout(10000);
    }
}