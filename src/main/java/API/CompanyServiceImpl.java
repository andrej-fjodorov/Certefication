package API;

import Model.Company;
import io.restassured.common.mapper.TypeRef;

import java.util.List;
import com.github.javafaker.Faker;

import static io.restassured.RestAssured.given;


public class CompanyServiceImpl implements CompanyService{

    String uri = "https://x-clients-be.onrender.com";
    String path = "/company";
    String deletePath = "/company/delete";
    Faker faker = new Faker();
    @Override
    public Company getRandomCompany() {
        int companyId = faker.random().nextInt(1000, 10000);
        String companyName =  faker.twinPeaks().character();
        String companyDescription =  faker.twinPeaks().quote();

        return new Company();
    }

    @Override
    public List<Company> getAll() {
        return given()
                .baseUri(uri + path)
                .header("accept", "application/json")
                .when()
                .get()
                .then()
                .extract()
                .response()
                .then()
                .extract()
                .body().as(new TypeRef<List<Company>>() {
                });
    }

    @Override
    public List<Company> getAll(boolean isActive) {
        return given()
                .baseUri(uri + path)
                .header("accept", "application/json")
                .param("active", isActive)
                .when()
                .get()
                .then()
                .extract()
                .response()
                .then()
                .extract()
                .body().as(new TypeRef<List<Company>>() {
                });

    }

    @Override
    public Company getById(int id) {
        return null;
    }

    @Override
    public int create(String name, String description, String token) {
        return given()
                .baseUri(uri + path)
                .header("accept", "application/json")
                .header("x-client-token", token)
                .log().ifValidationFails()
                .contentType("application/json")
                .body("{\"name\": \"" + name + "\",\"description\": \"" + description + "\"}")
                .when()
                .post()
                .then()
                .log().ifValidationFails()
                .statusCode(201)
                .contentType("application/json; charset=utf-8")
                .extract().path("id");
    }

    @Override
    public void deleteById(int id, String token) {
        given()
                .log().ifValidationFails()
                .baseUri(uri + path + id)
                .header("accept", "application/json")
                .header("x-client-token", token)
                .log().ifValidationFails()
                .contentType("application/json")
                .when()
                .get()
                .then()
                .log().ifValidationFails()
                .statusCode(200);

    }
}
