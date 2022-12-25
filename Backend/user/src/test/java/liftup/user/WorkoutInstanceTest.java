package liftup.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import liftup.user.model.WorkoutInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class WorkoutInstanceTest {
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
            .contentType(ContentType.JSON).body(new WorkoutInstance(-1, -1, 3, 8, "bench"))
            .when()
            .request("POST", "/workout-instance");
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "{\"workoutId\":-1,\"liftId\":-1,\"sets\":3,\"reps\":8}");
    }

    @Test
    public void getTest() {
        Response response = RestAssured.given()
            .with()
            .pathParam("workoutId", -1)
            .when()
            .request("GET", "/workout-instance/{workoutId}");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String returnString = response.getBody().asString();
        assertEquals(returnString, "[{\"workoutId\":-1,\"liftId\":-1,\"sets\":3,\"reps\":8}]");

        RestAssured.given()
            .with()
            .pathParam("workoutId", -1)
            .pathParam("liftId", -1)
            .when()
            .request("DELETE", "/workout-instance/{workoutId}/{liftId}");
    }
}

