package liftup.user;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import liftup.user.model.QuoteOfTheDay;
import liftup.user.model.TrainerApp;
import liftup.user.model.Validation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TrainerAppTest {

        @LocalServerPort
        int port;

        @Before
        public void setUp() {
            RestAssured.port = port;
            RestAssured.baseURI = "http://localhost";
        }

        //Nicks Test
        @Test
        public void addTest(){
            Validation isValid;
            Response response = RestAssured.given()
                    .with()
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON).body(new TrainerApp("Nick123", 3, "Yoga" ))
                    .when()
                    .request("POST", "addTrainerApp");

            int statusCode = response.getStatusCode();
            assertEquals(200, statusCode);

            String returnString = response.getBody().asString();
            assertEquals(returnString, "{\"valid\":true,\"message\":\"Your application has been sucessfully sent \"}");
        }
    }

