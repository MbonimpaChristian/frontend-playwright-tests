package api.payloads;

public class ProductPayloads {

    public static String createProductPayload() {
        return """
                {
                  "name": "Test Product",
                  "description": "This is a test product",
                  "price": 10000,
                  "stock": 10,
                  "categoryId": "test-category-id"
                }
                """;
    }

    public static String updateProductPayload() {
        return """
                {
                  "name": "Updated Test Product",
                  "description": "Updated product description",
                  "price": 15000,
                  "stock": 5
                }
                """;
    }

    private ProductPayloads() {
    }
}