import org.junit.Test;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasKey;

public class AppTestUser {

    private final String BASE_URL = "http://localhost:8080";

    public JSONObject readJSON(String fileName) {
        Object obj = new Object();
        try {
            obj = new JSONParser().parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/" + fileName));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return (JSONObject) obj;
    }

    public String getUserId(String userName){
        return String.valueOf(get(BASE_URL + "/user/users").path("find {it.userName == '" + userName + "'}.id"));
    }

    public void deleteUser(String userName) {
        String userId = getUserId(userName);
        Response response = request("delete", BASE_URL + "/user/delete/" + userId);

        response.then()
                .statusCode(200);
    }

    @Test
    public void WhenUsersListIsRequestedExpect_StatusOk() {
        Response response = request("get", BASE_URL + "/user/users");

        response.then()
                .statusCode(200);
    }

    @Test
    public void WhenUsersListIsRequestedExpectItsNotEmpty() {
        Response response = request("get", BASE_URL + "/user/users");

        response.then()
                .body("size()", is(not(empty())));
    }

    @Test
    public void WhenInvalidUserIsRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/user/notValid");

        response.then()
                .statusCode(400);
    }

    @Test
    public void WhenNonExistingUserIsRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/user/500");

        response.then()
                .statusCode(404);
    }


    @Test
    public void WhenCreatingNewUserWithAlreadyExistingEmailErrorResponse() {
        RequestSpecification request = given().log().ifValidationFails();
        JSONObject testUserJson = readJSON("testUser1.json");

        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(testUserJson));

        Response response = request.request("post", BASE_URL + "/user/add");

        response.then()
                .statusCode(400);
    }

    @Test
    public void WhenUsersListIsRequestedExpectProperFields() {
        Response response = request("get", BASE_URL + "/user/users");

        response.then()
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("userName"))
                .body("[0]", hasKey("password"));
    }

}
