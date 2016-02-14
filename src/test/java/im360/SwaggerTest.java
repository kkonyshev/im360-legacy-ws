package im360;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;


public class SwaggerTest extends BaseTest {

    @Test
    public void getApiConf() {
        when()
                .get("/api-docs")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("apis", hasSize(2));
    }
}
