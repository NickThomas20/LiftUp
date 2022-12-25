package liftup.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import liftup.user.model.Nutrition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class NutritionTest {
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void addTest() {
        Response response = RestAssured.given()
            .with()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON).body(new Nutrition("xrskgnbi", 15, 15, 15, 15))
            .when()
            .request("POST", "/addnutrition");
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "{\"username\":\"xrskgnbi\",\"caloriesConsumed\":15,\"proteinConsumed\":15,\"fatConsumed\":15,\"carbsConsumed\":15}");
    }

    @Test
    public void getTest() {
        Response response = RestAssured.given()
            .with()
            .pathParam("username", "xrskgnbi")
            .when()
            .request("GET", "/{username}/nutrition");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "{\"username\":\"xrskgnbi\",\"caloriesConsumed\":15,\"proteinConsumed\":15,\"fatConsumed\":15,\"carbsConsumed\":15}");

        RestAssured.given()
            .with()
            .pathParam("username", "xrskgnbi")
            .when()
            .request("DELETE", "/{username}/deletenutrition");
    }
}

