package api.payloads;

public class ProductPayloads {

    public static String createProductPayload(String uniqueName, String uniqueSku) {
        return """
                {
                  "name": "%s",
                  "description": "A product created from REST Assured API automation test",
                  "price": 45.99,
                  "comparePrice": 69.99,
                  "categoryId": "ddb77290-34ef-4e20-a19b-febca2c5c9d1",
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
                """.formatted(uniqueName, uniqueSku);
    }

    private ProductPayloads() {
    }
}