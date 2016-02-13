package im360;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;


public class ActuatorTest extends BaseTest {

    @Test
    public void getHealth() {
        when()
                .get("/health")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("status", Matchers.is("UP"));
    }
}
