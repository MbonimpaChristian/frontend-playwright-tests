package tests.api;

import api.BaseApiTest;
import api.status.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class AuthApiTest extends BaseApiTest {

    @Test
    public void loginWithCurrentCredentialsShouldReturnUnauthorized() {
        String requestBody = """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(
                ConfigReader.get("admin.email"),
                ConfigReader.get("admin.password")
        );

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post("/auth/login")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.UNAUTHORIZED,
                "Expected protected endpoint without token to return 401 Unauthorized"
        );
    }

    @Test
    public void loginWithEmptyCredentialsShouldReturnUnauthorized() {
        String requestBody = """
                {
                  "email": "",
                  "password": ""
                }
                """;

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post("/auth/login")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.UNAUTHORIZED,
                "Expected protected endpoint without token to return 401 Unauthorized"
        );
    }

    @Test
    public void registerWithEmptyBodyCurrentlyReturnsServerError() {
        String requestBody = """
            {}
            """;

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post("/auth/register")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                500,
                "Current backend behavior: empty register body returns 500. This should ideally be 400 validation error."
        );

        Assert.assertFalse(
                response.jsonPath().getBoolean("success"),
                "Expected success to be false"
        );

        Assert.assertTrue(
                response.jsonPath().getString("message").contains("email"),
                "Expected error message to mention missing email"
        );
    }

    @Test
    public void verifyEmailWithInvalidTokenShouldReturnClientError() {
        Response response =
                requestSpec
                        .when()
                        .get("/auth/verify-email/invalid-token")
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

    @Test
    public void forgotPasswordWithInvalidEmailShouldReturnGenericSuccessMessage() {
        String requestBody = """
            {
              "email": "wrong-email-format"
            }
            """;

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post("/auth/forgot-password")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.OK,
                "Expected GET /categories to return 200 OK"
        );

        Assert.assertTrue(
                response.jsonPath().getBoolean("success"),
                "Expected success to be true for generic forgot password response"
        );

        Assert.assertEquals(
                response.jsonPath().getString("message"),
                "If an account with that email exists, a reset link has been sent."
        );
    }
    @Test
    public void resetPasswordWithInvalidTokenShouldReturnClientError() {
        String requestBody = """
                {
                  "password": "NewPassword@123"
                }
                """;

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post("/auth/reset-password/invalid-token")
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

    @Test
    public void getCurrentUserWithoutTokenShouldReturnUnauthorized() {
        Response response =
                requestSpec
                        .when()
                        .get("/auth/me")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                StatusCode.UNAUTHORIZED,
                "Expected protected endpoint without token to return 401 Unauthorized"
        );
    }

    @Test
    public void refreshTokenWithoutTokenShouldReturnUnauthorizedOrClientError() {
        Response response =
                requestSpec
                        .when()
                        .post("/auth/refresh")
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

    @Test
    public void loginApiShouldRespondWithinFiveSeconds() {
        String requestBody = """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(
                ConfigReader.get("admin.email"),
                ConfigReader.get("admin.password")
        );

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post("/auth/login")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.time() < 5000,
                "Expected login API response time to be below 5000 ms"
        );
    }
}