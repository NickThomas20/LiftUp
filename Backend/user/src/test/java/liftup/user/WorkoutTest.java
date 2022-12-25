package liftup.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import liftup.user.model.Workout;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class WorkoutTest {
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
            .contentType(ContentType.JSON).body(new Workout(-1, "ned", "13-32-1000"))
            .when()
            .request("POST", "/addWorkout");
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "{\"valid\":true,\"message\":\"This workout has been added successfully!\"}");
    }
}

