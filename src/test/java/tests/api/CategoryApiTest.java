package tests.api;

import api.BaseApiTest;
import api.endpoints.CategoryEndpoints;
import api.status.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryApiTest extends BaseApiTest {

    private Response getCategoriesResponse() {
        return getRequest(CategoryEndpoints.CATEGORIES);
    }

    private String getFirstCategorySlug() {
        Response response = getCategoriesResponse();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /categories to return 200 OK before extracting slug"
        );

        String slug = response.jsonPath().getString("data[0].slug");

        Assert.assertNotNull(
                slug,
                "Expected first category slug not to be null"
        );

        Assert.assertFalse(
                slug.isBlank(),
                "Expected first category slug not to be blank"
        );

        return slug;
    }

    @Test
    public void getAllCategoriesShouldReturnSuccess() {
        Response response = getCategoriesResponse();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /categories to return 200 OK"
        );
    }

    @Test
    public void getAllCategoriesShouldReturnResponseBody() {
        Response response = getCategoriesResponse();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected categories response body not to be empty"
        );
    }

    @Test
    public void getCategoryBySlugShouldReturnSuccess() {
        String slug = getFirstCategorySlug();

        Response response = getRequest(CategoryEndpoints.categoryBySlug(slug));

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /categories/{slug} to return 200 OK"
        );
    }
}