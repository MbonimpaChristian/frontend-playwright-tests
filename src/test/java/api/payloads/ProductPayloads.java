package api.payloads;

import tests.api.testdata.ProductTestData;

public class ProductPayloads {

    public static String createProductPayload(String uniqueName, String uniqueSku, String categoryId) {
        return """
                {
                  "name": "%s",
                  "description": "%s",
                  "price": %.2f,
                  "comparePrice": %.2f,
                  "categoryId": "%s",
                  "tags": [
                    "%s",
                    "%s",
                    "%s"
                  ],
                  "isFeatured": true,
                  "isFlashSale": true,
                  "flashSalePrice": %.2f,
                  "variants": [
                    {
                      "size": "%s",
                      "color": "%s",
                      "colorHex": "%s",
                      "sku": "%s",
                      "stock": %d,
                      "price": %.2f
                    }
                  ]
                }
                """.formatted(
                uniqueName,
                ProductTestData.PRODUCT_DESCRIPTION,
                ProductTestData.PRODUCT_PRICE,
                ProductTestData.PRODUCT_COMPARE_PRICE,
                categoryId,
                ProductTestData.TAG_QA,
                ProductTestData.TAG_AUTOMATION,
                ProductTestData.TAG_API_TEST,
                ProductTestData.PRODUCT_FLASH_SALE_PRICE,
                ProductTestData.VARIANT_SIZE,
                ProductTestData.VARIANT_COLOR,
                ProductTestData.VARIANT_COLOR_HEX,
                uniqueSku,
                ProductTestData.VARIANT_STOCK,
                ProductTestData.PRODUCT_PRICE
        );
    }

    public static String updateProductPayload(String updatedName) {
        return """
                {
                  "name": "%s",
                  "description": "%s",
                  "price": %.2f,
                  "comparePrice": %.2f,
                  "isFeatured": %b,
                  "isFlashSale": %b,
                  "flashSalePrice": %.2f
                }
                """.formatted(
                updatedName,
                ProductTestData.UPDATED_PRODUCT_DESCRIPTION,
                ProductTestData.UPDATED_PRODUCT_PRICE,
                ProductTestData.UPDATED_PRODUCT_COMPARE_PRICE,
                ProductTestData.UPDATED_IS_FEATURED,
                ProductTestData.UPDATED_IS_FLASH_SALE,
                ProductTestData.UPDATED_FLASH_SALE_PRICE
        );
    }

    private ProductPayloads() {
    }
}