package tests.api;

import api.BaseApiTest;
import api.endpoints.AuthEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTokenApiTest extends BaseApiTest {

    @Test
    public void verifyEmailWithInvalidTokenShouldReturnClientError() {
        Response response =
                requestSpec
                        .when()
                        .get(AuthEndpoints.VERIFY_EMAIL + "invalid-token")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() >= 400 && response.statusCode() < 500,
                "Expected invalid email verification token to return client error"
        );
    }

    @Test
    public void getCurrentUserWithoutTokenShouldReturnUnauthorized() {
        Response response =
                requestSpec
                        .when()
                        .get(AuthEndpoints.CURRENT_USER)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                401,
                "Expected /auth/me without token to return 401"
        );
    }

    @Test
    public void refreshTokenWithoutTokenShouldReturnUnauthorizedOrClientError() {
        Response response =
                requestSpec
                        .when()
                        .post(AuthEndpoints.REFRESH_TOKEN)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() == 401 || response.statusCode() == 400,
                "Expected refresh token without token to return 400 or 401"
        );
    }
}