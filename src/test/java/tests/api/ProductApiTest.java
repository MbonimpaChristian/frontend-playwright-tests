package tests.api;

import api.BaseApiTest;
import api.endpoints.AuthEndpoints;
import api.endpoints.CategoryEndpoints;
import api.endpoints.ProductEndpoints;
import api.payloads.AuthPayloads;
import api.payloads.ProductPayloads;
import api.status.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.List;

public class ProductApiTest extends BaseApiTest {

    private String getAdminToken() {
        Response response = postRequest(
                AuthEndpoints.LOGIN,
                AuthPayloads.loginPayload(
                        ConfigReader.get("admin.email"),
                        ConfigReader.get("admin.password")
                )
        );

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected admin login to return 200 OK"
        );

        String token = response.jsonPath().getString("data.accessToken");

        if (token == null) {
            token = response.jsonPath().getString("data.token");
        }

        if (token == null) {
            token = response.jsonPath().getString("accessToken");
        }

        if (token == null) {
            token = response.jsonPath().getString("token");
        }

        Assert.assertNotNull(
                token,
                "Expected admin login response to contain token"
        );

//        return token;
        return token;
    }

    private String getFirstCategoryId() {
        Response response = getRequest(CategoryEndpoints.CATEGORIES);

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /categories to return 200 OK"
        );

        List<String> categoryIds = response.jsonPath()
                .getList("data.findAll { it.id != null && it.name != '' }.id");

        Assert.assertNotNull(
                categoryIds,
                "Expected category ids list not to be null"
        );

        Assert.assertFalse(
                categoryIds.isEmpty(),
                "Expected at least one valid category id"
        );

        return categoryIds.get(0);
    }

    private String createProductAndReturnId(String token) {
        String categoryId = getFirstCategoryId();
        String uniqueValue = String.valueOf(System.currentTimeMillis());

        Response response = postRequestWithToken(
                ProductEndpoints.PRODUCTS,
                ProductPayloads.createProductPayload(
                        "QA Delete Product " + uniqueValue,
                        "QA-DELETE-" + uniqueValue,
                        categoryId
                ),
                token
        );

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.CREATED,
                "Expected product creation to return 201 Created"
        );

        String productId = response.jsonPath().getString("data.id");

        Assert.assertNotNull(
                productId,
                "Expected created product id not to be null"
        );

        Assert.assertFalse(
                productId.isBlank(),
                "Expected created product id not to be blank"
        );

        return productId;
    }

    @Test
    public void adminShouldDeleteCreatedProductSuccessfully() {
        String token = getAdminToken();
        String productId = createProductAndReturnId(token);

        Response response = deleteRequestWithToken(
                ProductEndpoints.productById(productId),
                token
        );

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected DELETE /products/{id} to return 200 OK"
        );

        Assert.assertTrue(
                response.jsonPath().getBoolean("success"),
                "Expected success to be true"
        );
    }
}