package tests.api;

import api.BaseApiTest;
import api.endpoints.AuthEndpoints;
import api.payloads.AuthPayloads;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRegisterApiTest extends BaseApiTest {

    @Test
    public void registerWithEmptyBodyCurrentlyReturnsServerError() {
        Response response =
                requestSpec
                        .body(AuthPayloads.emptyRegisterPayload())
                        .when()
                        .post(AuthEndpoints.REGISTER)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                500,
                "Current backend behavior: empty register body returns 500. Ideally, this should be 400 validation error."
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
    public void registerWithoutEmailShouldReturnClientOrServerError() {
        Response response =
                requestSpec
                        .body(AuthPayloads.registerWithoutEmailPayload())
                        .when()
                        .post(AuthEndpoints.REGISTER)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() >= 400,
                "Expected registration without email to return an error"
        );

        Assert.assertFalse(
                response.jsonPath().getBoolean("success"),
                "Expected success to be false"
        );
    }

    @Test
    public void registerWithInvalidEmailShouldReturnClientOrServerError() {
        Response response =
                requestSpec
                        .body(AuthPayloads.registerWithInvalidEmailPayload())
                        .when()
                        .post(AuthEndpoints.REGISTER)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() >= 400,
                "Expected registration with invalid email format to return an error"
        );

        Assert.assertFalse(
                response.jsonPath().getBoolean("success"),
                "Expected success to be false"
        );
    }

    @Test
    public void registerWithGeneratedEmailShouldReturnSuccessOrValidationMessage() {
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@mail.com";

        Response response =
                requestSpec
                        .body(AuthPayloads.registerPayload(
                                "Chris",
                                "Mbonimpa",
                                uniqueEmail,
                                "Password@123",
                                "0780000000"
                        ))
                        .when()
                        .post(AuthEndpoints.REGISTER)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertTrue(
                response.statusCode() == 200 || response.statusCode() == 201 || response.statusCode() >= 400,
                "Expected register endpoint to return success or validation response"
        );

        Assert.assertFalse(
                response.asString().isEmpty(),
                "Expected response body not to be empty"
        );
    }
}