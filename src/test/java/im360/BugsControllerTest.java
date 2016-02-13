package im360;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;

public class BugsControllerTest extends BaseTest {

    @Test
    public void getStatusLAB() {
        when()
                .get("/bags/{restaurantID}/{status}/{ATTR1}", 1, 0, "LAB")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("resultCode", Matchers.is("success"));
    }

    @Test
    public void getStatusPHOTO() {
        when()
                .get("/bags/{restaurantID}/{status}/{ATTR1}", 1, 0, "PHOTO")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("resultCode", Matchers.is("success"));
    }

    @Test
    public void getStatusFail() {
        when()
                .get("/bags/{restaurantID}/{status}/{ATTR1}", 1, 0, "X")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("resultCode", Matchers.is("fail"));
    }
}
