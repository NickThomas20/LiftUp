package liftup.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import liftup.user.model.UserWorkoutDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class UserWorkoutDateTest {
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
            .contentType(ContentType.JSON).body(new UserWorkoutDate("xrskgnbi", "13-32-1000", 1, false))
            .when()
            .request("POST", "/user-workout-date");
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "{\"username\":\"xrskgnbi\",\"date\":\"13-32-1000\",\"workoutId\":1,\"completed\":false}");
    }

    @Test
    public void getTest() {
        Response response = RestAssured.given()
            .with()
            .pathParam("username", "xrskgnbi")
            .when()
            .request("GET", "/user-workout-date/{username}");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "[{\"username\":\"xrskgnbi\",\"date\":\"13-32-1000\",\"workoutId\":1,\"completed\":false}]");

        RestAssured.given()
            .with()
            .pathParam("username", "xrskgnbi")
            .pathParam("date", "13-32-1000")
            .when()
            .request("DELETE", "/user-workout-date/{username}/{date}");
    }
}

