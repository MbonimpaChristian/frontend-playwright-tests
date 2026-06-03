package tests.api;

import api.BaseApiTest;
import api.endpoints.AuthEndpoints;
import api.payloads.AuthPayloads;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthPasswordApiTest extends BaseApiTest {

    @Test
    public void forgotPasswordWithInvalidEmailShouldReturnGenericSuccessMessage() {
        Response response =
                requestSpec
                        .body(AuthPayloads.forgotPasswordPayload("wrong-email-format"))
                        .when()
                        .post(AuthEndpoints.FORGOT_PASSWORD)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));

        Assert.assertEquals(
                response.jsonPath().getString("message"),
                "If an account with that email exists, a reset link has been sent."
        );
    }

    @Test
    public void resetPasswordWithInvalidTokenShouldReturnClientError() {
        Response response =
                requestSpec
                        .body(AuthPayloads.resetPasswordPayload("NewPassword@123"))
                        .when()
                        .post(AuthEndpoints.RESET_PASSWORD + "invalid-token")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() >= 400 && response.statusCode() < 500,
                "Expected reset password with invalid token to return client error"
        );
    }
}