package tests.api.testdata;

public class ProductTestData {

    public static final String PRODUCT_DESCRIPTION =
            "A product created from REST Assured API automation test";

    public static final double PRODUCT_PRICE = 45.99;
    public static final double PRODUCT_COMPARE_PRICE = 69.99;
    public static final double PRODUCT_FLASH_SALE_PRICE = 39.99;

    public static final String UPDATED_PRODUCT_DESCRIPTION =
            "Updated product description from REST Assured API automation test";

    public static final double UPDATED_PRODUCT_PRICE = 55.99;
    public static final double UPDATED_PRODUCT_COMPARE_PRICE = 79.99;
    public static final boolean UPDATED_IS_FEATURED = false;
    public static final boolean UPDATED_IS_FLASH_SALE = false;
    public static final double UPDATED_FLASH_SALE_PRICE = 0;

    public static final String TAG_QA = "qa";
    public static final String TAG_AUTOMATION = "automation";
    public static final String TAG_API_TEST = "api-test";

    public static final String VARIANT_SIZE = "Large";
    public static final String VARIANT_COLOR = "Matte Black";
    public static final String VARIANT_COLOR_HEX = "#000000";
    public static final int VARIANT_STOCK = 120;

    private ProductTestData() {
    }
}