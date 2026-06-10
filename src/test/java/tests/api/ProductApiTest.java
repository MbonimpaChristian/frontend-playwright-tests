package tests.api;

import api.BaseApiTest;
import api.endpoints.ProductEndpoints;
import api.status.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductApiTest extends BaseApiTest {

    private Response getProductsResponse() {
        return getRequest(ProductEndpoints.PRODUCTS);
    }

    private Response getFlashSalesProductsResponse() {
        return getRequest(ProductEndpoints.FLASH_SALES_PRODUCTS);
    }

    private Response getTrendingProductsResponse() {
        return getRequest(ProductEndpoints.TRENDING_PRODUCTS);
    }

    @Test
    public void getAllProductsShouldReturnSuccess() {
        Response response = getProductsResponse();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /products to return 200 OK"
        );
    }

    @Test
    public void getAllProductsShouldReturnResponseBody() {
        Response response = getProductsResponse();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected products response body not to be empty"
        );
    }

    @Test
    public void getAllProductsShouldRespondWithinFiveSeconds() {
        Response response = getProductsResponse();

        Assert.assertTrue(
                response.time() < 5000,
                "Expected GET /products response time to be below 5000 ms"
        );
    }

    @Test
    public void getTrendingProductsShouldReturnSuccess() {
        Response response = getTrendingProductsResponse();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /products/trending to return 200 OK"
        );
    }

    @Test
    public void getTrendingProductsShouldReturnResponseBody() {
        Response response = getTrendingProductsResponse();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected trending products response body not to be empty"
        );
    }

    @Test
    public void getTrendingProductsShouldRespondWithinFiveSeconds() {
        Response response = getTrendingProductsResponse();

        Assert.assertTrue(
                response.time() < 5000,
                "Expected GET /products/trending response time to be below 5000 ms"
        );
    }

    @Test
    public void getFlashSalesProductsShouldReturnSuccess() {
        Response response = getFlashSalesProductsResponse();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /products/flash-sales to return 200 OK"
        );
    }

    @Test
    public void getFlashSalesProductsShouldReturnResponseBody() {
        Response response = getFlashSalesProductsResponse();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected flash sales products response body not to be empty"
        );
    }

    @Test
    public void getFlashSalesProductsShouldRespondWithinFiveSeconds() {
        Response response = getFlashSalesProductsResponse();

        Assert.assertTrue(
                response.time() < 5000,
                "Expected GET /products/flash-sales response time to be below 5000 ms"
        );
    }
}