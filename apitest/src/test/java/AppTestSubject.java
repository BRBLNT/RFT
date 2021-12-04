import org.junit.Test;

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

import java.io.FileReader;
import java.io.IOException;

public class AppTestSubject {

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

    public String getSubjectId(String subjectName){
        return String.valueOf(get(BASE_URL + "/subject/subjects").path("find {it.subjectName == '" + subjectName + "'}.id"));
    }

    public void deleteSubject(String subjectName) {
        String subjectId = getSubjectId(subjectName);
        Response response = request("delete", BASE_URL + "/subject/delete/" + subjectId);

        response.then()
                .statusCode(200);
    }

    @Test
    public void WhenSubjectsListIsRequestedExpectStatusOk() {
        Response response = request("get", BASE_URL + "/subject/subjects");

        response.then()
                .statusCode(200);
    }

    @Test
    public void WhenSubjectsListIsRequestedExpectItsNotEmpty() {
        Response response = request("get", BASE_URL + "/subject/subjects");

        response.then()
                .body("size()", is(not(empty())));
    }

    @Test
    public void WhenInvalidSubjectIsRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/subject/notValid");

        response.then()
                .statusCode(400);
    }

    @Test
    public void WhenNonExistingSubjectIsRequestedExpectErrorResponse() {
        Response response = request("get", BASE_URL + "/subject/500");

        response.then()
                .statusCode(404);
    }

    @Test
    public void WhenCreatingNewSubjectResponse() {
        RequestSpecification request = given().log().ifValidationFails();
        JSONObject testSubjectJson = readJSON("testSubject1.json");

        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(testSubjectJson));

        Response response = request.request("post", BASE_URL + "/subject/add");


    }

    @Test
    public void WhenUsersListIsRequestedExpectProperFields() {
        Response response = request("get", BASE_URL + "/subject/subjects");

        response.then()
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("teacherId"))
                .body("[0]", hasKey("date"));
    }

}
