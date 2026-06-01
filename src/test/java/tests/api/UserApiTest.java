package tests.api;

import api.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseApiTest {

    @Test
    public void getUserAddressesWithoutTokenShouldReturnUnauthorized() {
        Response response =
                requestSpec
                        .when()
                        .get("/users/addresses")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                401,
                "Expected /users/addresses without token to return 401"
        );
    }

    @Test
    public void updateProfileWithoutTokenShouldReturnUnauthorized() {
        String requestBody = """
                {
                  "firstName": "Chris",
                  "lastName": "Mbonimpa"
                }
                """;

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .put("/users/profile")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                401,
                "Expected /users/profile without token to return 401"
        );
    }

    @Test
    public void changePasswordWithoutTokenShouldReturnUnauthorized() {
        String requestBody = """
                {
                  "currentPassword": "OldPassword@123",
                  "newPassword": "NewPassword@123"
                }
                """;

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .put("/users/change-password")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                401,
                "Expected /users/change-password without token to return 401"
        );
    }

    @Test
    public void addAddressWithoutTokenShouldReturnUnauthorized() {
        String requestBody = """
                {
                  "street": "KG 11 Ave",
                  "city": "Kigali",
                  "country": "Rwanda",
                  "phone": "0780000000"
                }
                """;

        Response response =
                requestSpec
                        .body(requestBody)
                        .when()
                        .post("/users/addresses")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                401,
                "Expected /users/addresses without token to return 401"
        );
    }
}