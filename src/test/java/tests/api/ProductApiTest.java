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

    private String getFirstProductSlug() {
        Response response = getProductsResponse();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /products to return 200 OK before extracting slug"
        );

        String slug = response.jsonPath().getString("data[0].slug");

        Assert.assertNotNull(
                slug,
                "Expected first product slug not to be null"
        );

        Assert.assertFalse(
                slug.isBlank(),
                "Expected first product slug not to be blank"
        );

        return slug;
    }

    @Test
    public void getSingleProductBySlugSuccess() {
        String slug = getFirstProductSlug();

        Response response = getRequest(ProductEndpoints.productBySlug(slug));

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /products/{slug} to return 200 OK"
        );

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected single product response body not to be empty"
        );
    }

    @Test
    public void getSingleProductBySlugCorrectProduct() {
        String slug = getFirstProductSlug();

        Response response = getRequest(ProductEndpoints.productBySlug(slug));

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /products/{slug} to return 200 OK"
        );

        String actualSlug = response.jsonPath().getString("data.slug");

        Assert.assertEquals(
                actualSlug,
                slug,
                "Expected returned product slug to match requested slug"
        );
    }
}