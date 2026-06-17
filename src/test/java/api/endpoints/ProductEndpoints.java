package api.endpoints;

public class ProductEndpoints {

    public static final String PRODUCT_BASE = "/products";

    public static final String PRODUCTS = PRODUCT_BASE;
    public static final String TRENDING_PRODUCTS = PRODUCT_BASE + "/trending";
    public static final String FLASH_SALES_PRODUCTS = PRODUCT_BASE + "/flash-sales";

    public static String productBySlug(String slug) {
        return PRODUCT_BASE + "/" + slug;
    }

    public static String productById(String productId) {
        return PRODUCT_BASE + "/" + productId;
    }

    public static String relatedProducts(String productId) {
        return PRODUCT_BASE + "/" + productId + "/related";
    }

    public static String productImages(String productId) {
        return PRODUCT_BASE + "/" + productId + "/images";
    }

    private ProductEndpoints() {
    }
}