import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest

public class PetdataTest {

    @Test
    public void testPetEndpoint() {
        given()
                .when().get("/data/pets")
                .then()
                .statusCode(200);

    }

    @Test
    public void testPetAddEndpoint(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n    \"name\":\"browny\",\r\n  \"age\":5,\r\n   \"type\":\"Dog\"\r\n}")
                .when().post("data/pets/add")
                .then()
                .statusCode(200)
                .body("petId",notNullValue())
                .body("petAge",equalTo(5))
                .body("petName",equalTo("browny"))
                .body("petType",equalTo("Dog"));
    }
    @Test
    public void testPetDeleteEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petId",1)
                .when().delete("data/pets/delete/{petId}")
                .then()
                .statusCode(200)
                .body("successful",equalTo(true));
    }

    @Test
    public void testPetUpdateEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petId",1)
                .body("{\r\n    \"name\":\"Jasfer\",\r\n  \"age\":4,\r\n }")
                .when().put("data/pets/edit/{petId}")
                .then()
                .statusCode(200)
                .body("petId",equalTo(1))
                .body("petAge",equalTo(4))
                .body("petName",equalTo("Jasfer"))
                .body("petType",notNullValue());
    }


}
