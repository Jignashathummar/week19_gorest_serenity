package com.gorest.crudtest;

import com.gorest.gorestinfo.UserSteps;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static com.gorest.utilis.TestUtils.getRandomValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UsersCrud extends TestBase {
    static String name = "ABC";
    static String email = "abc" + getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userID;

    @Steps
    UserSteps steps;

    @Title("This will create a new User")
    @Test
    public void test001() {
        steps.createUser(name,email,gender,status).statusCode(201);
    }

    @Title("Verify if the User was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> users = steps.getUser(name);
        Assert.assertThat(users, hasValue(name));
        userID = (int) users.get("id");
    }

    @Title("Update the product and verify updated product information")
    @Test
    public void test003() {
        ValidatableResponse response = steps.updateUser(name+ "_update",email,gender,status,userID);
        Assert.assertThat(response.extract().path("name"), equalTo(name + "_update"));
    }

    @Title("Delete the User and verify if the User is deleted")
    @Test
    public void test004() {
        steps.deleteUser(userID).statusCode(204);

        steps.getUserById(userID).statusCode(404);
    }
}
