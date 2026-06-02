//package tests.api;
//
//import api.BaseApiTest;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import utils.ConfigReader;
//
//public class AuthApiTest extends BaseApiTest {
//
//    @Test
//    public void loginWithCurrentCredentialsShouldReturnUnauthorized() {
//        String requestBody = """
//                {
//                  "email": "%s",
//                  "password": "%s"
//                }
//                """.formatted(
//                ConfigReader.get("admin.email"),
//                ConfigReader.get("admin.password")
//        );
//
//        Response response =
//                requestSpec
//                        .body(requestBody)
//                        .when()
//                        .post("/auth/login")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertEquals(response.statusCode(), 401);
//        Assert.assertFalse(response.jsonPath().getBoolean("success"));
//        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid email or password");
//    }
//
//    @Test
//    public void loginWithEmptyCredentialsShouldReturnUnauthorized() {
//        String requestBody = """
//                {
//                  "email": "",
//                  "password": ""
//                }
//                """;
//
//        Response response =
//                requestSpec
//                        .body(requestBody)
//                        .when()
//                        .post("/auth/login")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertEquals(response.statusCode(), 401);
//        Assert.assertFalse(response.jsonPath().getBoolean("success"));
//    }
//
//    @Test
//    public void registerWithEmptyBodyCurrentlyReturnsServerError() {
//        String requestBody = """
//            {}
//            """;
//
//        Response response =
//                requestSpec
//                        .body(requestBody)
//                        .when()
//                        .post("/auth/register")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertEquals(
//                response.statusCode(),
//                500,
//                "Current backend behavior: empty register body returns 500. This should ideally be 400 validation error."
//        );
//
//        Assert.assertFalse(
//                response.jsonPath().getBoolean("success"),
//                "Expected success to be false"
//        );
//
//        Assert.assertTrue(
//                response.jsonPath().getString("message").contains("email"),
//                "Expected error message to mention missing email"
//        );
//    }
//
//    @Test
//    public void verifyEmailWithInvalidTokenShouldReturnClientError() {
//        Response response =
//                requestSpec
//                        .when()
//                        .get("/auth/verify-email/invalid-token")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertTrue(
//                response.statusCode() >= 400 && response.statusCode() < 500,
//                "Expected invalid email verification token to return client error"
//        );
//    }
//
//    @Test
//    public void forgotPasswordWithInvalidEmailShouldReturnGenericSuccessMessage() {
//        String requestBody = """
//            {
//              "email": "wrong-email-format"
//            }
//            """;
//
//        Response response =
//                requestSpec
//                        .body(requestBody)
//                        .when()
//                        .post("/auth/forgot-password")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertEquals(
//                response.statusCode(),
//                200,
//                "Expected forgot password API to return 200 with generic message"
//        );
//
//        Assert.assertTrue(
//                response.jsonPath().getBoolean("success"),
//                "Expected success to be true for generic forgot password response"
//        );
//
//        Assert.assertEquals(
//                response.jsonPath().getString("message"),
//                "If an account with that email exists, a reset link has been sent."
//        );
//    }
//    @Test
//    public void resetPasswordWithInvalidTokenShouldReturnClientError() {
//        String requestBody = """
//                {
//                  "password": "NewPassword@123"
//                }
//                """;
//
//        Response response =
//                requestSpec
//                        .body(requestBody)
//                        .when()
//                        .post("/auth/reset-password/invalid-token")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertTrue(
//                response.statusCode() >= 400 && response.statusCode() < 500,
//                "Expected reset password with invalid token to return client error"
//        );
//    }
//
//    @Test
//    public void getCurrentUserWithoutTokenShouldReturnUnauthorized() {
//        Response response =
//                requestSpec
//                        .when()
//                        .get("/auth/me")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertEquals(
//                response.statusCode(),
//                401,
//                "Expected /auth/me without token to return 401"
//        );
//    }
//
//    @Test
//    public void refreshTokenWithoutTokenShouldReturnUnauthorizedOrClientError() {
//        Response response =
//                requestSpec
//                        .when()
//                        .post("/auth/refresh")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertTrue(
//                response.statusCode() == 401 || response.statusCode() == 400,
//                "Expected refresh token without token to return 400 or 401"
//        );
//    }
//
//    @Test
//    public void loginApiShouldRespondWithinFiveSeconds() {
//        String requestBody = """
//                {
//                  "email": "%s",
//                  "password": "%s"
//                }
//                """.formatted(
//                ConfigReader.get("admin.email"),
//                ConfigReader.get("admin.password")
//        );
//
//        Response response =
//                requestSpec
//                        .body(requestBody)
//                        .when()
//                        .post("/auth/login")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .response();
//
//        Assert.assertTrue(
//                response.time() < 5000,
//                "Expected login API response time to be below 5000 ms"
//        );
//    }
//}