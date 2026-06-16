package api.payloads;

public class ProductPayloads {

    public static String createProductPayload(String uniqueName, String uniqueSku, String categoryId) {
        return """
                {
                  "name": "%s",
                  "description": "A product created from REST Assured API automation test",
                  "price": 45.99,
                  "comparePrice": 69.99,
                  "categoryId": "%s",
                  "tags": [
                    "qa",
                    "automation",
                    "api-test"
                  ],
                  "isFeatured": true,
                  "isFlashSale": true,
                  "flashSalePrice": 39.99,
                  "variants": [
                    {
                      "size": "Large",
                      "color": "Matte Black",
                      "colorHex": "#000000",
                      "sku": "%s",
                      "stock": 120,
                      "price": 45.99
                    }
                  ]
                }
                """.formatted(uniqueName, categoryId, uniqueSku);
    }

    private ProductPayloads() {
    }
}