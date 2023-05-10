package io.quarkiverse.generate.picocli.commands.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GeneratePicocliCommandsResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/generate-picocli-commands")
                .then()
                .statusCode(200)
                .body(is("Hello generate-picocli-commands"));
    }
}
