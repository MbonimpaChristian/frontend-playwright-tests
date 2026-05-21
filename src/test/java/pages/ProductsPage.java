//package pages;
//
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.Page;
//
//public class ProductsPage {
//
//    private final Page page;
//    private final String productCards =
//            ".product-card:visible, [data-testid='product-card']:visible, article:visible, div:has(button:has-text('Add to Cart')):visible, div:has(button:has-text('View')):visible";
//
//    private final String productName =
//            ".product-card h2, .product-card h3, .card h2, .card h3, article h2, article h3";
//
//    private final String productPrice =
//            "text=$, text=RWF, text=Frw";
//
//    public ProductsPage(Page page) {
//        this.page = page;
//    }
//
//    public Locator getProductCards() {
//        return page.locator(productCards);
//    }
//
//    public Locator getFirstProductCard() {
//        return page.locator(productCards).first();
//    }
//
//    public Locator getProductName() {
//        return page.locator(productName).first();
//    }
//
//    public Locator getProductPrice() {
//        return page.locator(productPrice).first();
//    }
//
//    public int getProductCount() {
//        return page.locator(productCards).count();
//    }
//}
package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage {

    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

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
}
