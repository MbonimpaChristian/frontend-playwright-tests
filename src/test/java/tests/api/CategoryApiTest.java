package tests.api;

import api.BaseApiTest;
import api.endpoints.CategoryEndpoints;
import api.status.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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

        List<String> slugs = response.jsonPath().getList("data.slug");

        Assert.assertNotNull(
                slugs,
                "Expected category slugs list not to be null"
        );

        return slugs.stream()
                .filter(slug -> slug != null && !slug.isBlank())
                .findFirst()
                .orElseThrow(() -> new AssertionError("Expected at least one valid category slug"));
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

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected category by slug response body not to be empty"
        );
    }
}