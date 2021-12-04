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

public class AppTestSubjectUser {

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

    public String getSubjectUserId(String subjectUserName){
        return String.valueOf(get(BASE_URL + "/subjectuser/subjectusers").path("find {it.subjectUserName == '" + subjectUserName + "'}.id"));
    }

    public void deleteSubjectUser(String subjectUserName) {
        String subjectUserId = getSubjectUserId(subjectUserName);
        Response response = request("delete", BASE_URL + "/subjectuser/delete/" + subjectUserId);

        response.then()
                .statusCode(200);
    }


    @Test
    public void WhenSubjectUsersListIsRequestedExpectItsNotEmpty() {
        Response response = request("get", BASE_URL + "/subjectuser/subjectusers");

        response.then()
                .body("size()", is(not(empty())));
    }

    @Test
    public void WhenInvalidSubjectUserIsRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/subjectuser/notValid");

        response.then()
                .statusCode(400);
    }

    @Test
    public void WhenNonExistingSubjectUserIsRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/subjectuser/500");

        response.then()
                .statusCode(404);
    }

    @Test
    public void WhenCreatingNewSubjectResponse() {
        RequestSpecification request = given().log().ifValidationFails();
        JSONObject testSubjectUserJson = readJSON("testSubjectUser1.json");

        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(testSubjectUserJson));

        Response response = request.request("post", BASE_URL + "/subjectuser/add");


    }

}
