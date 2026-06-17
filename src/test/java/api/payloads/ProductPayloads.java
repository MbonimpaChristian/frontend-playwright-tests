package api.payloads;

import api.testdata.ProductTestData;

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
              "description": "Updated product description from REST Assured API automation test",
              "price": 55.99,
              "comparePrice": 79.99,
              "isFeatured": false,
              "isFlashSale": false,
              "flashSalePrice": 0
            }
            """.formatted(updatedName);
    }

    private ProductPayloads() {
    }
}