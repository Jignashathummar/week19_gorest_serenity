package com.gorest.gorestinfo;

import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class UserSteps {
    @Step("Create the Users information with name : {0} , email : {1}, gender : {2} and status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 7434571fef3646c24f2aecbad16768b670b629132ca445a9f1be5ca3ac9c41f2")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post()
                .then().log().all();
    }

    @Step("Getting the Users information with name : {0}")
    public HashMap<String, Object> getUser(String name) {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";
        HashMap<String, Object> usersMap = SerenityRest.given().log().all()
                .header("Authorization","Bearer 7434571fef3646c24f2aecbad16768b670b629132ca445a9f1be5ca3ac9c41f2")
                .when()
                .get()
                .then().log().all().statusCode(200)
                .extract()
                .path(s1 + name + s2);
        return usersMap;
    }

    @Step("Update the Users information with name : {0} , email : {1}, gender : {2} and status : {3}")
    public ValidatableResponse updateUser(String name, String email, String gender, String status, int userID) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 7434571fef3646c24f2aecbad16768b670b629132ca445a9f1be5ca3ac9c41f2")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .put("/"+userID)
                .then().log().all();
    }

    @Step("Delete the User information with id : {0}")
    public ValidatableResponse deleteUser(int userId) {
        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 7434571fef3646c24f2aecbad16768b670b629132ca445a9f1be5ca3ac9c41f2")
                .when()
                .delete("/"+userId)
                .then().log().all();
    }

    @Step("Getting User information with userId: {0}")
    public ValidatableResponse getUserById(int userId) {
        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 7434571fef3646c24f2aecbad16768b670b629132ca445a9f1be5ca3ac9c41f2")
                .when()
                .get("/"+userId)
                .then().log().all();
    }
}
