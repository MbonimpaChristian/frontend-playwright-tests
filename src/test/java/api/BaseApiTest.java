package api;

import api.specs.SpecBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseApiTest {

    protected RequestSpecification requestSpec;

    @BeforeMethod
    public void setupApi() {
        requestSpec = SpecBuilder.getRequestSpec();
    }

    protected Response getRequest(String endpoint) {
        validateEndpoint(endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response postRequest(String endpoint, String requestBody) {
        validateEndpoint(endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response putRequest(String endpoint, String requestBody) {
        validateEndpoint(endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response deleteRequest(String endpoint) {
        validateEndpoint(endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    private void validateEndpoint(String endpoint) {
        if (endpoint == null || endpoint.isBlank()) {
            throw new IllegalArgumentException("Endpoint must not be null or empty");
        }
    }

    protected Response postRequestWithToken(String endpoint, String requestBody, String token) {
        validateEndpoint(endpoint);

        return io.restassured.RestAssured
                .given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}