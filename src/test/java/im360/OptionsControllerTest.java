package im360;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;

public class OptionsControllerTest extends BaseTest {

    @Test
    public void getStatus0() {
        when()
                .get("/options/{restaurantID}/{active}", 0, 0)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("resultCode", Matchers.is("success"));
    }

    @Test
    public void getStatusNegative() {
        when()
                .get("/options/{restaurantID}/{active}", 0, -1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("resultCode", Matchers.is("fail"));
    }
}
