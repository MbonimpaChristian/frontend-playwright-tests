package tests.api;

import api.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.status.StatusCode;

public class CategoryApiTest extends BaseApiTest {

    @Test
    public void getAllCategoriesShouldReturnSuccess() {
        Response response =
                requestSpec
                        .when()
                        .get("/categories")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /categories to return 200 OK"
        );
    }

    @Test
    public void getAllCategoriesShouldReturnData() {
        Response response =
                requestSpec
                        .when()
                        .get("/categories")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected categories response body not to be empty"
        );
    }

    @Test
    public void getAllCategoriesShouldRespondWithinFiveSeconds() {
        Response response =
                requestSpec
                        .when()
                        .get("/categories")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.time() < 5000,
                "Expected categories API response time to be below 5000 ms"
        );
    }

    @Test
    public void getSingleCategoryWithInvalidSlugShouldReturnClientOrNotFoundError() {
        Response response =
                requestSpec
                        .when()
                        .get("/categories/invalid-category-slug")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() == StatusCode.BAD_REQUEST
                        || response.statusCode() == StatusCode.NOT_FOUND,
                "Expected invalid category slug to return 400 Bad Request or 404 Not Found"
        );
    }
}