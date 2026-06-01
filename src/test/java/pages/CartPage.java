package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    private final String cartPageText =
            "text=Cart, text=Shopping Cart, text=Your Cart";

    private final String cartItem =
            ".cart-item:visible, [data-testid='cart-item']:visible, div:has-text('Quantity'):visible, div:has-text('Remove'):visible";

    public CartPage(Page page) {
        this.page = page;
    }

    public Locator getCartPageText() {
        return page.locator(cartPageText).first();
    }

    public Locator getFirstCartItem() {
        return page.locator(cartItem).first();
    }

    public int getCartItemCount() {
        return page.locator(cartItem).count();
    }

    public String getPageText() {
        return page.locator("body").innerText();
    }
}