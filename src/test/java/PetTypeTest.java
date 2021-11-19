import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class PetTypeTest {
    @Test
    public void testPetEndpoint() {
        given()
                .when().get("/data/petsType")
                .then()
                .statusCode(200);

    }

    @Test
    public void testPetAddEndpoint(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n  \"type\":\"Fish\"\r\n}")
                .when().post("data/petsType/add")
                .then()
                .statusCode(200)
                .body("petTypeId",notNullValue())
                .body("petType",equalTo("Fish"));
    }
    @Test
    public void testPetDeleteEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petTypeId",1)
                .when().delete("data/petsType/delete/{petTypeId}")
                .then()
                .statusCode(200)
                .body("successful",equalTo(true));
    }

    @Test
    public void testPetUpdateEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petTypeId",2)
                .body("{\n \"type\":\"Tortise\"\n }")
                .when().put("data/petsType/edit/{petTypeId}")
                .then()
                .statusCode(200)
                .body("petTypeId",equalTo(2))
                .body("petType",equalTo("Tortise"));

    }


}
