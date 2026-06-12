package tests.api;

import api.BaseApiTest;
import api.endpoints.AuthEndpoints;
import api.endpoints.ProductEndpoints;
import api.payloads.AuthPayloads;
import api.payloads.ProductPayloads;
import api.status.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

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

        return token;
    }

    @Test
    public void createProductWithoutToken() {
        String uniqueValue = String.valueOf(System.currentTimeMillis());

        Response response = postRequest(
                ProductEndpoints.PRODUCTS,
                ProductPayloads.createProductPayload(
                        "QA Automation Backpack " + uniqueValue,
                        "QA-BACKPACK-" + uniqueValue
                )
        );

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.UNAUTHORIZED,
                "Expected POST /products without token to return 401 Unauthorized"
        );

        Assert.assertFalse(
                response.jsonPath().getBoolean("success"),
                "Expected success to be false"
        );
    }

    @Test
    public void adminShouldCreateProductSuccessfully() {
        String token = getAdminToken();
        String uniqueValue = String.valueOf(System.currentTimeMillis());

        Response response = postRequestWithToken(
                ProductEndpoints.PRODUCTS,
                ProductPayloads.createProductPayload(
                        "QA Automation Backpack " + uniqueValue,
                        "QA-BACKPACK-" + uniqueValue
                ),
                token
        );

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.CREATED,
                "Expected admin to create product successfully"
        );

        Assert.assertTrue(
                response.jsonPath().getBoolean("success"),
                "Expected success to be true"
        );
    }
}