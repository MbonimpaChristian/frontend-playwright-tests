package api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseApiTest {

    protected RequestSpecification requestSpec;

    @BeforeMethod
    public void setupApi() {
        RestAssured.baseURI = ConfigReader.get("api.base.uri");
        RestAssured.basePath = ConfigReader.get("api.base.path");

        requestSpec = RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .log().all();
    }
}