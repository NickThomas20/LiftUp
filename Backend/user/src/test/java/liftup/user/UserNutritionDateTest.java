package liftup.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import liftup.user.model.UserNutritionDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class UserNutritionDateTest {
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
                .contentType(ContentType.JSON).body(new UserNutritionDate("xrskgnbi", "13-32-1000", 0, 0, 0, 0))
                .when()
                .request("POST", "/user-nutrition-date");
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "{\"username\":\"xrskgnbi\",\"date\":\"13-32-1000\",\"calories\":0,\"protein\":0,\"fat\":0,\"carbs\":0}");
    }

    @Test
    public void getTest() {
        Response response = RestAssured.given()
                .with()
                .pathParam("username", "xrskgnbi")
                .when()
                .request("GET", "/user-nutrition-date/{username}");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "[{\"username\":\"xrskgnbi\",\"date\":\"13-32-1000\",\"calories\":0,\"protein\":0,\"fat\":0,\"carbs\":0}]");

        RestAssured.given()
                .with()
                .pathParam("username", "xrskgnbi")
                .pathParam("date", "13-32-1000")
                .when()
                .request("DELETE", "/user-nutrition-date/{username}/{date}");
    }
}

