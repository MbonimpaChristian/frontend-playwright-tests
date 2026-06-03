package api.endpoints;

public class ProductEndpoints {

    public static final String PRODUCTS = "/products";
    public static final String TRENDING_PRODUCTS = "/products/trending";
    public static final String FLASH_SALES_PRODUCTS = "/products/flash-sales";

    public static String productBySlug(String slug) {
        return "/products/" + slug;
    }

    public static String relatedProducts(String productId) {
        return "/products/" + productId + "/related";
    }

    public static String productById(String productId) {
        return "/products/" + productId;
    }

    public static String productImages(String productId) {
        return "/products/" + productId + "/images";
    }

    private ProductEndpoints() {
    }
}