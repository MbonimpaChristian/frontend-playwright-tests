package tests.api;

import api.BaseApiTest;
import api.endpoints.AuthEndpoints;
import api.payloads.AuthPayloads;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class AuthLoginApiTest extends BaseApiTest {

    @Test
    public void loginWithCurrentCredentialsShouldReturnUnauthorized() {
        Response response =
                requestSpec
                        .body(AuthPayloads.loginPayload(
                                ConfigReader.get("admin.email"),
                                ConfigReader.get("admin.password")
                        ))
                        .when()
                        .post(AuthEndpoints.LOGIN)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.statusCode(), 401);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid email or password");
    }

    @Test
    public void loginWithEmptyCredentialsShouldReturnUnauthorized() {
        Response response =
                requestSpec
                        .body(AuthPayloads.emptyLoginPayload())
                        .when()
                        .post(AuthEndpoints.LOGIN)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.statusCode(), 401);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }

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
                "Current backend behavior: empty register body returns 500. This should ideally be 400 validation error."
        );

        Assert.assertFalse(response.jsonPath().getBoolean("success"));

        Assert.assertTrue(
                response.jsonPath().getString("message").contains("email"),
                "Expected error message to mention missing email"
        );
    }

    @Test
    public void loginApiShouldRespondWithinFiveSeconds() {
        Response response =
                requestSpec
                        .body(AuthPayloads.loginPayload(
                                ConfigReader.get("admin.email"),
                                ConfigReader.get("admin.password")
                        ))
                        .when()
                        .post(AuthEndpoints.LOGIN)
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