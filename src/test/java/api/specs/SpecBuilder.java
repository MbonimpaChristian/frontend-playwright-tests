package api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        String baseUri = ConfigReader.get("api.base.uri");
        String basePath = ConfigReader.get("api.base.path");

        if (baseUri == null || baseUri.isBlank()) {
            throw new IllegalArgumentException("api.base.uri is missing in config.properties");
        }

        if (basePath == null || basePath.isBlank()) {
            throw new IllegalArgumentException("api.base.path is missing in config.properties");
        }

        System.out.println("API BASE URI: " + baseUri);
        System.out.println("API BASE PATH: " + basePath);

        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    private SpecBuilder() {
    }
}