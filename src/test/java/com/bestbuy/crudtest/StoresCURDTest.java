package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest {
    static ValidatableResponse response;

    static int id;
    static String storeName = TestUtils.getRandomValue() + "store";
    static String storeType = "Big";
    static String address1 = "Sydney";
    static String address2 = "";
    static String city = "Australia";
    static String state = "MN";
    static String zip = "55305";
    static double lat = 44.969658;
    static double lng = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }

    @Test(priority = 0)
    public void createStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(storeName);
        storePojo.setType(storeType);
        storePojo.setAddress(address1);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.then().log().ifValidationFails().statusCode(201);
        response.prettyPrint();
        id = response.jsonPath().get("id");
    }

    @Test(priority = 1)
    public void viewStore() {
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test(priority = 2)
    public void updateStore() {
        storeName = StoresCURDTest.storeName + "Updated";
        storeType = StoresCURDTest.storeType + "Updated";
        address1 = StoresCURDTest.address1 + "Updated";
        address2 = StoresCURDTest.address2;
        city = StoresCURDTest.city + "Updated";
        state = StoresCURDTest.state + "Updated";
        zip = TestUtils.getRandomValue();
        lat = 44.898855;
        lng = -36.556651;
        hours = "Mon: 9-10; Tue: 9-10; Wed: 9-10; Thurs: 9-10; Fri: 9-10; Sat: 11-6; Sun: 12-5";

        StorePojo storePojo = new StorePojo();
        storePojo.setName(storeName);
        storePojo.setType(storeType);
        storePojo.setAddress(address1);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(storePojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);

    }

    @Test(priority = 3)
    public void deleteStore() {
        given().log().ifValidationFails()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then().log().ifValidationFails().statusCode(200);

        given()
                .pathParam("id", id)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }
}
