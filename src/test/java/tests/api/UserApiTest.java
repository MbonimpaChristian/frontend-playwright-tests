package tests.api;

import api.BaseApiTest;
import api.endpoints.UserEndpoints;
import api.payloads.UserPayloads;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseApiTest {

    private void assertUnauthorizedGetRequest(String endpoint) {
        Response response =
                requestSpec
                        .when()
                        .get(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        assertUnauthorizedResponse(response);
    }

    private void assertUnauthorizedPostRequest(String endpoint, String requestBody) {
        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        assertUnauthorizedResponse(response);
    }

    private void assertUnauthorizedPutRequest(String endpoint, String requestBody) {
        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .put(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();

        assertUnauthorizedResponse(response);
    }

    private void assertUnauthorizedResponse(Response response) {
        Assert.assertEquals(
                response.statusCode(),
                401,
                "Expected protected user endpoint without token to return 401"
        );

        Assert.assertFalse(
                response.jsonPath().getBoolean("success"),
                "Expected success to be false"
        );
    }

    @Test
    public void getUserAddressesWithoutTokenShouldReturnUnauthorized() {
        assertUnauthorizedGetRequest(UserEndpoints.ADDRESSES);
    }

    @Test
    public void updateProfileWithoutTokenShouldReturnUnauthorized() {
        String requestBody = UserPayloads.updateProfilePayload(
                "Chris",
                "Mbonimpa"
        );

        assertUnauthorizedPutRequest(UserEndpoints.PROFILE, requestBody);
    }

    @Test
    public void changePasswordWithoutTokenShouldReturnUnauthorized() {
        String requestBody = UserPayloads.changePasswordPayload(
                "OldPassword@123",
                "NewPassword@123"
        );

        assertUnauthorizedPutRequest(UserEndpoints.CHANGE_PASSWORD, requestBody);
    }

    @Test
    public void addAddressWithoutTokenShouldReturnUnauthorized() {
        String requestBody = UserPayloads.addressPayload(
                "KG 11 Ave",
                "Kigali",
                "Rwanda",
                "0780000000"
        );

        assertUnauthorizedPostRequest(UserEndpoints.ADDRESSES, requestBody);
    }

    @Test
    public void updateProfileWithEmptyBodyWithoutTokenShouldReturnUnauthorized() {
        assertUnauthorizedPutRequest(
                UserEndpoints.PROFILE,
                UserPayloads.emptyPayload()
        );
    }

    @Test
    public void changePasswordWithEmptyBodyWithoutTokenShouldReturnUnauthorized() {
        assertUnauthorizedPutRequest(
                UserEndpoints.CHANGE_PASSWORD,
                UserPayloads.emptyPayload()
        );
    }

    @Test
    public void addAddressWithEmptyBodyWithoutTokenShouldReturnUnauthorized() {
        assertUnauthorizedPostRequest(
                UserEndpoints.ADDRESSES,
                UserPayloads.emptyPayload()
        );
    }
}