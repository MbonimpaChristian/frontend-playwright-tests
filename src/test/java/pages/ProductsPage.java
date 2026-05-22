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
