package tests.api;

import api.BaseApiTest;
import api.endpoints.ProductEndpoints;
import api.status.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductApiTest extends BaseApiTest {

    @Test
    public void getAllProductsShouldReturnSuccess() {
        Response response =
                requestSpec
                        .when()
                        .get(ProductEndpoints.PRODUCTS)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /products to return 200 OK"
        );
    }

    @Test
    public void getAllProductsShouldReturnResponseBody() {
        Response response =
                requestSpec
                        .when()
                        .get(ProductEndpoints.PRODUCTS)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected products response body not to be empty"
        );
    }

    @Test
    public void getAllProductsShouldRespondWithinFiveSeconds() {
        Response response =
                requestSpec
                        .when()
                        .get(ProductEndpoints.PRODUCTS)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.time() < 5000,
                "Expected GET /products response time to be below 5000 ms"
        );
    }
}