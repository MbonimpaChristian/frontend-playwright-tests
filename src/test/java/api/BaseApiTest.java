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

        System.out.println("REQUEST ENDPOINT: " + endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response postRequest(String endpoint, String requestBody) {
        validateEndpoint(endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response putRequest(String endpoint, String requestBody) {
        validateEndpoint(endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response deleteRequest(String endpoint) {
        validateEndpoint(endpoint);

        return RestAssured
                .given()
                .spec(requestSpec)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    private void validateEndpoint(String endpoint) {
        if (endpoint == null || endpoint.isBlank()) {
            throw new IllegalArgumentException("Endpoint must not be null or empty");
        }
    }
}